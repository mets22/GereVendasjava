package Filial;

import Cliente.Cliente;
import Produto.Produto;

import java.util.*;



public class Filial {
    private TreeMap<Produto,ArrayList<FilialCli>> vendas;
    private TreeMap<Produto,ArrayList<Integer>> quantidadevendas;

    public Filial(){
        vendas = new TreeMap<>();quantidadevendas = new TreeMap<>();
    }

    public void insere(Venda v){
        Integer mes = v.getMes();
        Produto prod = v.getProduto();

        if(vendas.containsKey(prod)) {
            ArrayList<FilialCli> aux = vendas.get(prod);
            FilialCli auxfilial = aux.get(mes-1);
            ArrayList<Integer> auxalint = quantidadevendas.get(prod);
            Integer quanti = auxalint.get(mes-1) + v.getNuni();

            auxalint.remove(mes-1);
            auxalint.add(mes-1,quanti);
            auxfilial.insereFilialCli(v);
            aux.remove(mes-1);
            aux.add(mes-1,auxfilial);
            vendas.put(prod,aux);
            quantidadevendas.put(prod,auxalint);

        }
        else{
            ArrayList<FilialCli> aux = new ArrayList<>();
            FilialCli auxfilial = new FilialCli();
            ArrayList<Integer> auxalint = new ArrayList<>();
            Integer quanti = v.getNuni();

            aux.add(mes-1,auxfilial);
            auxfilial.insereFilialCli(v);
            vendas.put(prod,aux);
            auxalint.add(mes-1,quanti);
            quantidadevendas.put(prod,auxalint);
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
    }


}
