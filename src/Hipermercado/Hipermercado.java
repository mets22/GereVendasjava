package Hipermercado;

import Cliente.CatClientes;
import Faturacao.Faturacao;
import Filial.Venda;
import Leitor.Input;
import Leitor.LeitorFicheiros;
import Produto.CatProdutos;
import Produto.Produto;
import Filial.Filial;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.*;
import java.lang.reflect.Array;
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
        return estatistica.getFaturacaototal();
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
        return estatistica.getNrProdutosComprados();
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

}
