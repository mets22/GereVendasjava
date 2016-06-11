package Filial;

import Cliente.Cliente;
import Produto.Produto;
import Produto.ProdutoComparator;
import Cliente.ClienteComparator;

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

    public Set<Cliente> totalClientesDistintosPorMes(Integer mes){
        Set<Cliente> resultado = new TreeSet<Cliente>(new ClienteComparator());
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
        Integer nCompras = 0, nProds = 0, i;
        double totgasto = 0.0;
        TreeSet<Produto> auxprod = (TreeSet<Produto>) vendas.keySet();
        Iterator<Produto> auxitprod = auxprod.iterator();
        Map<Integer,FilialCli> auxalcli;
        boolean flag = false;

        while(auxitprod.hasNext()){
            Produto p = auxitprod.next();
            auxalcli = vendas.get(p);
            for(i=0;i<12;i++){
                FilialCli auxfilcli = auxalcli.get(i);
                if(auxfilcli.existeCli(c)){
                    if(flag == false) {nProds++;flag = true;}
                    nCompras += auxfilcli.getnVendasCli(c);
                    totgasto += auxfilcli.getTotFacturadoCli(c);
                }
            }
            flag = false;
            res.add(new TrioNComprasNProdsTotGasto(nCompras,nProds,totgasto));
        }
        return res;
    }

    public ArrayList<TrioNVendasNClientesTotFact> getVendasMensais(Produto p){// resposta a query 4
        ArrayList<TrioNVendasNClientesTotFact> res = new ArrayList<>(12);
        Iterator<FilialCli> aux1 = vendas.get(p).values().iterator();
        FilialCli filcli;
        Integer nVendas = 0, nClientes = 0;
        double totfacturado;
        TreeSet<Cliente> auxcli;

        while(aux1.hasNext()){
            filcli = aux1.next();
            auxcli = (TreeSet<Cliente>)filcli.getClientes();
            nClientes = auxcli.size();
            totfacturado = filcli.getTotFacturado();
            nVendas = filcli.getnVendas();
            res.add(new TrioNVendasNClientesTotFact(nVendas,nClientes,totfacturado));
        }
        return res;
    }

    public TreeMap<Produto,Integer> getProdsMaisComprados(Cliente c){ // query 5
        TreeMap<Produto,Integer> res = new TreeMap<>();
        FilialCli auxfilcli;
        Iterator<Produto> auxit = vendas.keySet().iterator();
        Integer i, nCompras;

        while(auxit.hasNext()){
            nCompras = 0;
            Produto p = auxit.next();
            for(i=0;i<12;i++){
                auxfilcli = vendas.get(p).get(i);
                nCompras += auxfilcli.getnVendasCli(c);
            }
            res.put(p,nCompras);
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
            for(i=0;i<12;i++){
                auxfilcli = vendas.get(p).get(i);
                auxtreecli.addAll(auxfilcli.getClientes());
            }
            res.put(p,new ParTotVendasTotClientesMes(auxres.get(p),auxtreecli.size()));
        }
        return res;
    }

    public TreeMap<Cliente,Double> getTop3Cli(){// query 7
        TreeMap<Cliente,Double> auxres = new TreeMap<>();
        TreeMap<Cliente,Double> res = new TreeMap<>();
        Map.Entry<Cliente,Double> maxentry = null;
        FilialCli auxfilcli;
        Iterator<Produto> auxit = vendas.keySet().iterator();
        Integer i;
        Double totgasto;

        while(auxit.hasNext()){
            Produto p = auxit.next();
            for(i=0;i<12;i++){
                auxfilcli = vendas.get(p).get(i);
                Iterator<Cliente> auxitcli = auxfilcli.getClientes().iterator();
                while(auxitcli.hasNext()){
                    totgasto = 0.0;
                    Cliente c = auxitcli.next();
                    totgasto = auxfilcli.getTotFacturadoCli(c) + auxres.get(c);
                    auxres.put(c,totgasto);
                }
            }

        }
        for(i=0;i<3;i++) {
            for (Map.Entry<Cliente, Double> entry : auxres.entrySet()) {
                if (maxentry == null || entry.getValue().compareTo(maxentry.getValue()) > 0) {
                    maxentry = entry;
                    res.put(maxentry.getKey(), maxentry.getValue());
                }
            }
        }
        res = auxres;
        return res;
    }

    /*Dado um produto p da-nos os X clientes que mais compraram*/

    public ArrayList<Clienteqt> clientesquemaiscompraram(Produto p,int X){
        HashMap<Integer,FilialCli> aux = (HashMap<Integer,FilialCli>) vendas.get(p);
        ArrayList<Clienteqt> res = new ArrayList<>();

        for (int i = 0; i <12 ; i++) {
            FilialCli f = aux.get(i);
            Set keys = f.getVendascli().keySet();

            for (Iterator it = keys.iterator(); it.hasNext();) {
                Cliente cliente = (Cliente) it.next();
                TreeSet compras = (TreeSet) f.getVendascli().get(cliente);
                Integer qt = compras.size();
                Double tot = f.getTotFacturadoCli(cliente);

                Clienteqt ins = new Clienteqt(cliente,qt,tot);

                for (int j = 0; j < res.size() ; j++) {
                    Clienteqt clista = res.get(j);

                    if (qt > clista.getQt()){res.add(j,ins);}
                }
            }
        }
        res = (ArrayList<Clienteqt>) res.subList(0,X);
        return res;
    }
}
