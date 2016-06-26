package Filial;

import Cliente.Cliente;
import Produto.Produto;
import Produto.ProdutoComparator;
import Cliente.ClienteComparator;
import Filial.ParStringDoubleComparator;

import java.util.*;


public class Filial {
    private Map<Produto,Map<Integer,FilialCli>> vendas;
    private Map<Produto,Integer> quantidadevendas;

    public Filial(){
        vendas = new TreeMap<>(new ProdutoComparator());quantidadevendas = new TreeMap<>(new ProdutoComparator());
    }

    public void insere(Venda v){
        Integer mes = v.getMes();
        Produto prod = v.getProduto();
        Integer quantidade = quantidadevendas.get(prod);
        Map<Integer,FilialCli> aux = vendas.get(prod);
        if(aux == null) {aux = new TreeMap<Integer, FilialCli>();}
        FilialCli auxfilial = aux.get(mes);
        if(auxfilial==null) auxfilial= new FilialCli();
        if(quantidade==null) quantidade = 0;

        auxfilial.insereFilialCli(v);

        aux.put(mes,auxfilial);
        vendas.put(prod,aux);
        quantidadevendas.put(prod,quantidade+v.getNuni());
    }

    /*public ParTotVendasTotClientesMes getTotVendasTotCli(Integer mes){ //query 2
        ParTotVendasTotClientesMes res;
        Integer nVendas=0, nClientes=0;
        TreeSet<Produto> auxprod = new TreeSet<Produto>(new ProdutoComparator());
        vendas.forEach((k,v) -> auxprod.add(k));
        TreeSet<Cliente> auxtreecli = new TreeSet<>(new ClienteComparator());
        Iterator<Produto> auxalcli = auxprod.iterator();
        Iterator<FilialCli> auxitfilcli;
        FilialCli auxfilcli;

        while(auxalcli.hasNext()){
            auxfilcli = vendas.get(auxalcli.next()).get(mes);
            if(auxfilcli!=null) {
                auxtreecli.addAll(auxfilcli.getClientes());
                nVendas += auxfilcli.getnVendas();
            }
        }
        nClientes = auxtreecli.size();

        res = new ParTotVendasTotClientesMes(nVendas,nClientes);
        return res;

        ParTotVendasTotClientesMes resultado;
        Integer nVendas=0, nClientes =0;

    }*/

    public Set<Cliente> totalClientesDistintosPorMes(Integer mes){ // query 2
        Set<Cliente> resultado = new TreeSet<>(new ClienteComparator());
        Iterator<Map.Entry<Produto,Map<Integer,FilialCli>>> it = this.vendas.entrySet().iterator();

        while(it.hasNext()){
            Map.Entry<Produto,Map<Integer,FilialCli>> par = it.next();
            Map<Integer,FilialCli> aux = par.getValue();
            FilialCli filialCli = aux.get(mes);

            if(filialCli!=null) {
                resultado.addAll(filialCli.getClientes());
            }

        }

        return resultado;
    }

    public ArrayList<TrioNComprasNProdsTotGasto> getComprasMensais(Cliente c){//resposta a query 3
        ArrayList<TrioNComprasNProdsTotGasto> res = new ArrayList<>(12);
        Map<Integer,Integer> nCompras = new TreeMap<>();

        Map<Integer,Double> totgasto = new TreeMap<>();

        for(int i= 1; i<= 12;i++){
            nCompras.put(i,0);
            totgasto.put(i,0.0);
        }

        TreeSet<Produto> produtosQueComprou = new TreeSet<Produto>(new ProdutoComparator());

        Iterator<Produto> auxitprod = vendas.keySet().iterator();
        Map<Integer,FilialCli> auxalcli;


        while(auxitprod.hasNext()){
            Produto p = auxitprod.next();
            auxalcli = vendas.get(p);
            for (int i = 1; i <= 12; i++) {
                FilialCli auxfilcli = auxalcli.get(i);
                if(auxfilcli!=null){
                    if (auxfilcli.existeCli(c)) {
                        produtosQueComprou.add(p);
                        int nrComprasTemp = nCompras.get(i) + auxfilcli.getnVendasCli(c);
                        nCompras.put(i,nrComprasTemp);
                        double toGastoTemp = totgasto.get(i) + auxfilcli.getTotFacturadoCli(c);
                        totgasto.put(i,toGastoTemp);
                    }
                }
            }
        }
        for(int i=1;i<=12;i++){
            res.add(new TrioNComprasNProdsTotGasto(i,nCompras.get(i),produtosQueComprou,totgasto.get(i)));
        }
        return res;
    }

    public ArrayList<TrioNVendasNClientesTotFact> getVendasMensais(Produto p){// resposta a query 4
        ArrayList<TrioNVendasNClientesTotFact> res = new ArrayList<>(12);
        Map<Integer,FilialCli> aux1;
        try{
            aux1 =vendas.get(p);
        } catch (NullPointerException e){
            throw new NullPointerException();
        }

        FilialCli filcli;
        Integer nVendas = 0;
        double totfacturado;
        TreeSet<Cliente> auxcli;

        for(Map.Entry<Integer,FilialCli> par :aux1.entrySet()){
            filcli = par.getValue();
            auxcli =(TreeSet<Cliente>) filcli.getClientes();
            if(auxcli!=null) {
                totfacturado = filcli.getTotFacturado();
                nVendas = filcli.getnVendas();
                res.add(new TrioNVendasNClientesTotFact(par.getKey(), nVendas, auxcli, totfacturado));
            }
        }

        return res;
    }

    public TreeSet<ParProdQuantidade> getProdsMaisComprados(Cliente c){ // query 5
        TreeSet<ParProdQuantidade> res = new TreeSet<>();
        FilialCli auxfilcli;
        Iterator<Produto> auxit = vendas.keySet().iterator();
        Integer i, nCompras;

        while(auxit.hasNext()){
            nCompras = 0;
            Produto p = auxit.next();
            for(i=1;i<=12;i++){
                auxfilcli = vendas.get(p).get(i);
                if(auxfilcli!=null) nCompras += auxfilcli.getnVendasCli(c);
            }
            res.add(new ParProdQuantidade(nCompras,p));
        }
        return res;
    }

    public TreeMap<Produto,ParTotVendasTotClientesMes> getProdsMaisVendidosUnid(Integer x){//query 6
        TreeMap<Produto,ParTotVendasTotClientesMes> res = new TreeMap<>();
        TreeMap<Produto,Integer> auxres = new TreeMap<>();
        Map.Entry<Produto,Integer> maxentry = null;
        Iterator<Produto> auxitprod;
        Integer i;
        FilialCli auxfilcli;
        TreeSet<Cliente> auxtreecli;

        for(i=0;i<x;i++) {
            for (Map.Entry<Produto, Integer> entry : quantidadevendas.entrySet()) {
                if (maxentry == null || entry.getValue().compareTo(maxentry.getValue()) > 0) {
                    maxentry = entry;
                    auxres.put(maxentry.getKey(), maxentry.getValue());
                    quantidadevendas.remove(maxentry.getKey());
                }
            }
        }

        quantidadevendas.putAll(auxres);

        auxitprod = auxres.keySet().iterator();

        while (auxitprod.hasNext()){
            auxtreecli = new TreeSet<>();
            Produto p = auxitprod.next();
            for(i=1;i<=12;i++){
                auxfilcli = vendas.get(p).get(i);
                if(auxfilcli != null){
                    auxtreecli.addAll(auxfilcli.getClientes());
                }

            }
            res.put(p,new ParTotVendasTotClientesMes(auxres.get(p),auxtreecli.size()));
        }
        return res;
    }

    /*public Set<ParClienteTotGasto> getTop3Cli(){// query 7
        TreeMap<Cliente,Double> auxres = new TreeMap<>();
        TreeSet<ParClienteTotGasto> res = new TreeSet<>();
        Map.Entry<Cliente,Double> maxentry = null;
        FilialCli auxfilcli;
        Iterator<Produto> auxit = vendas.keySet().iterator();
        Integer i;
        Double totgasto;

        while(auxit.hasNext()){
            Produto p = auxit.next();
            for(i=1;i<=12;i++){
                auxfilcli = vendas.get(p).get(i);
                if(auxfilcli!=null){
                    Set<Cliente> auxitcli = auxfilcli.getClientes();
                    for(Cliente c : auxitcli ){
                        totgasto = auxfilcli.getTotFacturadoCli(c) + auxres.get(c);
                        auxres.put(c,totgasto);
                    }
                }
            }

        }
        for(i=0;i<3;i++) {
            for (Map.Entry<Cliente, Double> entry : auxres.entrySet()) {
                if (maxentry == null || entry.getValue().compareTo(maxentry.getValue()) > 0) {
                    maxentry = entry;
                    res.add(new ParClienteTotGasto(maxentry.getValue(),maxentry.getKey()));
                }
            }
        }
        return res;
    }*/

    public Set<ParClienteTotGasto> getClientesOrdenadosPorQuantidadeComprada(){
        Set<ParClienteTotGasto> resultado = new TreeSet<>();
        Map<Cliente,Double> temp = new HashMap<>();

        for(Produto p: vendas.keySet()){
            for(int i = 1; i<=12;i++){
                FilialCli filialCli = vendas.get(p).get(i);
                if(filialCli!=null){
                    Set<Cliente> clientesProduto = filialCli.getClientes();
                    for (Cliente c: clientesProduto){
                        double temp1 = filialCli.getTotFacturadoCli(c);
                        double temp2;
                        if(temp.containsKey(c)) temp2 = temp.get(c);
                        else temp2 =0.0;
                        temp.put(c,temp1+temp2);
                    }
                }
            }
        }
        temp.forEach((k,v) -> resultado.add(new ParClienteTotGasto(v,k.clone())));
        return resultado;
    }

    public Set<ParClienteTotGasto> getTop3Cli(){
        NavigableSet<ParClienteTotGasto> temp = (NavigableSet<ParClienteTotGasto>)getClientesOrdenadosPorQuantidadeComprada();
        Set<ParClienteTotGasto> resultado = new TreeSet<>();
        int i= 0;

        Iterator<ParClienteTotGasto> it = temp.iterator();
        while (it.hasNext() && i<3){
            resultado.add(it.next());
            i++;
        }
        return resultado;
    }

    public ArrayList<String> topvariedade(int X){
        ArrayList<ParStringDouble> aux = new ArrayList<ParStringDouble>();
        ArrayList<String> res = new ArrayList<String>();
        int j =0;

        Iterator it = vendas.entrySet().iterator();
        while (it.hasNext()){
            Map<Integer,FilialCli> aux1 = (Map<Integer,FilialCli>)it.next();

            for (int i = 0; i <12 ; i++) {
                FilialCli f = aux1.get(i);
                Set keys = f.getVendascli().keySet();

                for (Iterator ite = keys.iterator(); ite.hasNext();) {
                    Cliente cliente = (Cliente) ite.next();
                    TreeSet compras = (TreeSet) f.getVendascli().get(cliente);
                    Integer qt = compras.size();
                    Double tot = f.getTotFacturadoCli(cliente);

                    ParStringDouble ins = new ParStringDouble(cliente,qt,tot);

                    if(aux.contains(ins)){
                        Integer qtactual =aux.get(aux.indexOf(ins)).getQt();
                        qtactual+=qt;
                        aux.get(aux.indexOf(ins)).setQt(qtactual);
                        Double totactual = aux.get(aux.indexOf(ins)).getTotal();
                        totactual+=tot;
                        aux.get(aux.indexOf(ins)).setTotal(totactual);
                    }else{
                        aux.add(j,ins);
                        j++;

                    }

            }
        }
    }
        Collections.sort(aux, new ParStringDoubleComparator());
        for (int x = 0; x < X; x++) {
                res.add(x,aux.get(x).getC().getCodigo());
        }
        return res;
    }

    /*Dado um produto p da-nos os X clientes que mais compraram*/

    public ArrayList<ParStringDouble> clientesquemaiscompraram(Produto p,int X){
        HashMap<Integer,FilialCli> aux = (HashMap<Integer,FilialCli>) vendas.get(p);
        ArrayList<ParStringDouble> res = new ArrayList<ParStringDouble>();

        for (int i = 0; i <12 ; i++) {
            FilialCli f = aux.get(i);
            Set keys = f.getVendascli().keySet();

            for (Iterator it = keys.iterator(); it.hasNext();) {
                Cliente cliente = (Cliente) it.next();
                TreeSet compras = (TreeSet) f.getVendascli().get(cliente);
                Integer qt = compras.size();
                Double tot = f.getTotFacturadoCli(cliente);

                ParStringDouble ins = new ParStringDouble(cliente,qt,tot);

                for (int j = 0; j < res.size() ; j++) {
                    ParStringDouble clista = res.get(j);

                    if (qt > clista.getQt()){res.add(j,ins);}
                }
            }
        }
        res = (ArrayList<ParStringDouble>) res.subList(0,X);
        return res;
    }
}

