package Filial;

import Cliente.Cliente;
import Produto.Produto;

import java.util.*;

import static Filial.FilialCli.getVendascli;


public class Filial {
    private TreeMap<Produto,ArrayList<FilialCli>> vendas;
    private TreeMap<Produto,Integer> quantidadevendas;

    public Filial(){
        vendas = new TreeMap<>();quantidadevendas = new TreeMap<>();
    }

    public void insere(Venda v){
        Integer mes = v.getMes();
        Produto prod = v.getProduto();

        if(vendas.containsKey(prod)) {
            ArrayList<FilialCli> aux = vendas.get(prod);
            FilialCli auxfilial = aux.get(mes-1);
            Integer auxalint = quantidadevendas.get(prod) + v.getNuni();

            auxfilial.insereFilialCli(v);
            aux.remove(mes-1);
            aux.add(mes-1,auxfilial);
            vendas.put(prod,aux);
            quantidadevendas.put(prod,auxalint);

        }
        else{
            ArrayList<FilialCli> aux = new ArrayList<>();
            FilialCli auxfilial = new FilialCli();
            Integer quanti = v.getNuni();

            aux.add(mes-1,auxfilial);
            auxfilial.insereFilialCli(v);
            vendas.put(prod,aux);
            quantidadevendas.put(prod,quanti);
        }
    }

    public ParTotVendasTotClientesMes getTotVendasTotCli(Integer mes){ //query 2
        ParTotVendasTotClientesMes res;
        Integer nVendas=0, nClientes=0;
        TreeSet<Produto> auxprod = (TreeSet<Produto>) vendas.keySet();
        TreeSet<Cliente> auxtreecli = new TreeSet<>();
        Iterator<Produto> auxalcli = auxprod.iterator();
        Iterator<FilialCli> auxitfilcli;
        FilialCli auxfilcli;

        while(auxalcli.hasNext()){
            auxfilcli = vendas.get(auxalcli.next()).get(mes-1);
            auxtreecli.addAll(auxfilcli.getClientes());
            nVendas += auxfilcli.getnVendas();
        }
        nClientes = auxtreecli.size();

        res = new ParTotVendasTotClientesMes(nVendas,nClientes);
        return res;
    }

    public ArrayList<TrioNComprasNProdsTotGasto> getComprasMensais(Cliente c){//resposta a query 3
        ArrayList<TrioNComprasNProdsTotGasto> res = new ArrayList<>(12);
        Integer nCompras = 0, nProds = 0, i;
        double totgasto = 0.0;
        TreeSet<Produto> auxprod = (TreeSet<Produto>) vendas.keySet();
        Iterator<Produto> auxitprod = auxprod.iterator();
        ArrayList<FilialCli> auxalcli;
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
        Iterator<FilialCli> aux1 = vendas.get(p).iterator();
        FilialCli filcli;
        Integer nVendas = 0, nClientes = 0;
        double totfacturado;
        TreeSet<Cliente> auxcli;

        while(aux1.hasNext()){
            filcli = aux1.next();
            auxcli = filcli.getClientes();
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

    /* Devolve os X clientes com maior variedade de produtos comprados  */

    public ArrayList<String> topvariedade(int X){
        ArrayList<Clienteqt> res = new ArrayList<>();
        ArrayList<String> cods = new ArrayList<>();

        Set keys = getVendascli().keySet();

        for (Iterator i = keys.iterator(); i.hasNext();) {
            Cliente cliente = (Cliente) i.next();
            TreeSet compras = (TreeSet) getVendascli().get(cliente);
            Integer qt = compras.size();

            Clienteqt ins = new Clienteqt(cliente,qt);

            for (int j = 0; j < res.size() ; j++) {
                Clienteqt aux = res.get(j);

                if (qt > aux.getQt()){res.add(j,ins);}
            }
        }

        res.subList(1,X);
        for (int i = 0; i < X ; i++) {
            cods.add(res.get(i).getC().getCodigo());
        }

        return cods;
    }


}
