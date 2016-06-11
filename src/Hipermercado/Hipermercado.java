package Hipermercado;

import Cliente.CatClientes;
import Cliente.Cliente;
import Cliente.ClienteComparator;
import Faturacao.Faturacao;
import Filial.Venda;
import Leitor.Input;
import Leitor.LeitorFicheiros;
import Produto.CatProdutos;
import Produto.Produto;
import Filial.ParClienteTotGasto;
import Filial.Filial;
import Filial.ParStringDouble;
import Filial.TrioNComprasNProdsTotGasto;
import Filial.TrioNVendasNClientesTotFact;
import Filial.ParTotVendasTotClientesMes;
import Filial.ParProdQuantidade;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

import static Leitor.LeitorFicheiros.parseFicheiroClientes;
import static Leitor.LeitorFicheiros.parseFicheiroProdutos;
import static Leitor.LeitorFicheiros.parseFicheiroVendas;

public class Hipermercado implements Serializable{

    private static CatClientes catalogoClientes;
    private static CatProdutos catalogoProdutos;
    private static Map<Integer, Filial> vendas;
    private static Faturacao faturacao;
    private static Estatistica estatistica;

    public Hipermercado(){
        catalogoClientes = new CatClientes();
        catalogoProdutos = new CatProdutos();
        vendas = new HashMap<>();
        estatistica = new Estatistica();
        faturacao = new Faturacao();
    }

    public Hipermercado(CatClientes cclientes,CatProdutos cprodutos, Map<Integer,Filial> vvendas,Faturacao faturacao1){
        catalogoClientes = cclientes;
        catalogoProdutos = cprodutos;
        vendas = new HashMap<Integer, Filial>();
        vvendas.forEach((k,v) -> vendas.put(k,v));
        estatistica = new Estatistica();
        faturacao = faturacao1.clone();
    }


    /** Métodos para guardar e restaurar estado do Hipermercado **/

    public void gravaEstado(String nome) throws IOException{
        /*ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(nome));
        oos.writeObject(this);
        oos.flush();
        oos.close();*/
        FileOutputStream fileOutputStream = new FileOutputStream(nome);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(catalogoClientes);
        objectOutputStream.writeObject(catalogoProdutos);
        objectOutputStream.close();
    }

    public static Hipermercado carregaEstado(String nome) throws IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(nome));
        Hipermercado hipermercado = (Hipermercado)ois.readObject();
        ois.close();
        return hipermercado;
        /*Hipermercado res;
        CatClientes clientes = new CatClientes();
        CatProdutos produtos = new CatProdutos();
        FileInputStream fileInputStream = new FileInputStream(nome);
        ObjectInputStream in = new ObjectInputStream(fileInputStream);
        Object obj;
        while((obj = in.readObject())!=null){
            if(obj instanceof CatClientes){
                clientes = (CatClientes) obj;
            }
            if(obj instanceof CatProdutos){
                produtos = (CatProdutos) obj;
            }
        }
        res = new Hipermercado(clientes,produtos);
        return res;*/
    }

    public static void lerFicheiros(String ficheiroClientes, String ficheiroProdutos,String ficheiroVendas){
        catalogoClientes = parseFicheiroClientes(ficheiroClientes);
        catalogoProdutos = parseFicheiroProdutos(ficheiroProdutos,faturacao);
        vendas = parseFicheiroVendas(ficheiroVendas,catalogoClientes,catalogoProdutos, estatistica,faturacao);
    }

    public static int tamanho(){
        return catalogoClientes.getSize();
    }



    public static int vendasAZero(){
        return estatistica.getVendasAzero();
    }

    public static double faturacaoTotal(){
        return faturacao.getFaturacaoTotal();
    }

    public static int vendasErradas(){
        return estatistica.getVendasErradas();
    }

    public static String ficheiroLido(){
        return estatistica.getFicheiro();
    }

    public static int clientesCompraram(){
        return estatistica.getNrClientesCompraram();
    }

    public static int produtosComprados(){
        Set<Produto> resultado = faturacao.produtosComprados();
        return resultado.size();
    }

    public static int vendasLidasTotal(){
        return estatistica.getVendasTotais();
    }

    public static int ClientesNCompraram(){
        return nrClientesTotal() - clientesCompraram();
    }

    public static int ProdutosNComprados(){
        return nrProdutosTotal() - produtosComprados();
    }

    public static String ultimoFicheiroLido(){
        return estatistica.getFicheiro();
    }

    public static int nrClientesTotal(){
        return catalogoClientes.getSize();
    }

    public static int nrProdutosTotal(){
        return catalogoProdutos.getSize();
    }

    public static boolean existeProduto(String p) {
        return catalogoProdutos.existeprod(new Produto(p));
    }

    public ObservableList<Produto> listaProdutosNaoComprados(){
        ObservableList<Produto> res = FXCollections.observableArrayList();
        res.addAll(faturacao.produtosNaoComprados().stream().collect(Collectors.toList()));
        return res;
    }

    public  ObservableList<OutrasEstatisticas> outrasEstatisticasPedidas(){
        ObservableList<OutrasEstatisticas> res = FXCollections.observableArrayList();
        int nrComprasMes;
        double faturado;
        int nrClientes;

        for(int i=1;i<=12;i++){
            nrComprasMes = faturacao.getTotalVendasMes(i);
            faturado = faturacao.getFaturacaoTotalMes(i);
            nrClientes = nrClientesDistintosMes(i);
            res.add(new OutrasEstatisticas(i,nrComprasMes,faturado,nrClientes));
        }
        return res;
    }

    public  ObservableList<EstatisticaFilial> estatisticaFilialObservableList(int filial){
        ObservableList<EstatisticaFilial> res = FXCollections.observableArrayList();

        double faturado;

        for(int i=1;i<=12;i++){
            faturado = faturacao.faturadoPorFilialMes(filial,i);
            res.add(new EstatisticaFilial(i,faturado));
        }
        return res;
    }


    public  int nrClientesDistintosMes(Integer mes){
        Iterator<Map.Entry<Integer,Filial>> it = vendas.entrySet().iterator();
        Set<Cliente> clientesCompraram = new TreeSet<Cliente>(new ClienteComparator());

        while (it.hasNext()){
            Map.Entry<Integer,Filial> par = it.next();
            Filial aux = par.getValue();
            clientesCompraram.addAll(aux.totalClientesDistintosPorMes(mes));
        }
        return clientesCompraram.size();
    }

    public ObservableList<TrioNComprasNProdsTotGasto> getComprasMensais(Cliente c){
        ObservableList<TrioNComprasNProdsTotGasto> lista = FXCollections.observableArrayList();

        ArrayList<TrioNComprasNProdsTotGasto> response;
        Map<Integer, TrioNComprasNProdsTotGasto> resultado = new HashMap<>();

        Iterator<Map.Entry<Integer,Filial>> it = vendas.entrySet().iterator();


        while (it.hasNext()){
            Map.Entry<Integer,Filial> par = it.next();
            Filial aux = par.getValue();
            response = aux.getComprasMensais(c);
            for(int i=0; i<response.size();i++){
                TrioNComprasNProdsTotGasto trio = response.get(i);
                if(trio!=null){
                    TrioNComprasNProdsTotGasto trioResultante = resultado.get(i+1);
                    if(trioResultante==null) {
                        trioResultante = new TrioNComprasNProdsTotGasto();
                        trioResultante.setMes(trio.getMes());
                    }
                    trioResultante.adicionaNrCompras(trio.getnCompras());
                    trioResultante.adicionaProdutos(trio.getProdutos());
                    trioResultante.adicionaTotalGasto(trio.getTotgasto());
                    resultado.put(i+1,trioResultante);
                }
            }
        }

        resultado.forEach((k,v) -> lista.add(v));
        return lista;
    }

    public ObservableList<TrioNVendasNClientesTotFact> getVendasMensais(Produto p){
        ObservableList<TrioNVendasNClientesTotFact> lista = FXCollections.observableArrayList();
        Map<Integer, TrioNVendasNClientesTotFact> resultado = new HashMap<>();
        ArrayList<TrioNVendasNClientesTotFact> response;

        for(Integer key :vendas.keySet()){
            Filial aux = vendas.get(key);
            response = aux.getVendasMensais(p);
            for(int i=0;i<response.size();i++){
                TrioNVendasNClientesTotFact trio = response.get(i);
                if(trio!=null){
                    TrioNVendasNClientesTotFact trioResultante = resultado.get(i+1);
                    if(trioResultante==null){
                        trioResultante = new TrioNVendasNClientesTotFact();
                        trioResultante.setMes(trio.getMes());
                    }
                    trioResultante.adicionaFaturacao(trio.getTotfacturado());
                    trioResultante.adicionaClientes(trio.getClientes());
                    trioResultante.adicionaVendas(trio.getnVendas());
                }
            }

        }
        resultado.forEach((k,v) -> lista.add(v));
        return lista;
    }



    public ParTotVendasTotClientesMes getTotVendasTotCli(Integer mes){
        Iterator<Map.Entry<Integer,Filial>> it = vendas.entrySet().iterator();
        int nrVendas = 0;
        int nrClientes = 0;
        ParTotVendasTotClientesMes resultado;
        Set<Cliente> clientesCompraram = new TreeSet<Cliente>(new ClienteComparator());

        while(it.hasNext()){
            Map.Entry<Integer,Filial> par = it.next();
            Filial aux = par.getValue();
            clientesCompraram.addAll(aux.totalClientesDistintosPorMes(mes));
        }
        nrClientes = clientesCompraram.size();
        nrVendas+= faturacao.getTotalVendasMes(mes);
        resultado = new ParTotVendasTotClientesMes(nrVendas,nrClientes);
        return resultado;

    }

    public ObservableList<ParProdQuantidade> getProdsMaisComprados(Cliente c){
        ObservableList<ParProdQuantidade> lista = FXCollections.observableArrayList();
        Set<ParProdQuantidade> resultado = new TreeSet<ParProdQuantidade>();

        for(Integer key:vendas.keySet()){
            Filial aux = vendas.get(key);
            Set<ParProdQuantidade> temp = aux.getProdsMaisComprados(c);

            for(ParProdQuantidade par :temp){
               resultado.add(par);
            }
        }

        resultado.forEach(parProdQuantidade -> lista.add(parProdQuantidade));
        System.out.println(resultado.size());
        return lista;
    }

    public  ObservableList<ParClienteTotGasto> getTop3Cli(int filial){
        ObservableList<ParClienteTotGasto> lista = FXCollections.observableArrayList();
        Filial fil = vendas.get(filial);
        Set<ParClienteTotGasto> response = fil.getTop3Cli();
        response.forEach(parClienteTotGasto -> lista.add(parClienteTotGasto));
        return lista;
    }

    public ObservableList<ParStringDouble> clientesquemaiscompraram(int filial, Produto p, int x){
        ObservableList<ParStringDouble> lista = FXCollections.observableArrayList();
        Filial fil = vendas.get(filial);
        ArrayList<ParStringDouble> response = fil.clientesquemaiscompraram(p,x);
        response.forEach(parStringDouble -> lista.add(parStringDouble));
        return lista;
    }

}
