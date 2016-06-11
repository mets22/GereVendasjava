package Filial;

import Cliente.Cliente;
import Cliente.ClienteComparator;

import java.util.Iterator;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;


public class FilialCli {
    private TreeMap<Cliente,TreeSet<Venda>> vendascli;

    public TreeMap<Cliente, TreeSet<Venda>> getVendascli() {
        return vendascli;
    }

    public FilialCli(){
        this.vendascli = new TreeMap<>(new ClienteComparator());
    }

    public TreeSet<Venda> getVendasCli(Cliente cli){
        TreeSet<Venda> aux = new TreeSet<>(vendascli.get(cli));

        return aux;
    }

    public boolean existeCli(Cliente c){
        return vendascli.containsKey(c);
    }

    public Integer getnVendasCli(Cliente cli){
        return vendascli.get(cli).size();
    }

    public Integer getnVendas(){
        Integer res = 0;
        TreeSet<Cliente> auxcli = (TreeSet<Cliente>) vendascli.keySet();
        Iterator<Cliente> auxit = auxcli.iterator();

        while(auxit.hasNext()){
            res += vendascli.get(auxit.next()).size();
        }
        return res;
    }

    public Set<Cliente> getClientes(){
        Set<Cliente> resultado = new TreeSet<Cliente>(new ClienteComparator());
        this.vendascli.forEach((k,v) -> resultado.add(k));
        return resultado;
    }

    public Integer getnClientes(){
        return vendascli.size();
    }

    public double getTotFacturado(){
        double res = 0.0;
        Iterator<TreeSet<Venda>> aux = vendascli.values().iterator();

        while(aux.hasNext()){
            TreeSet<Venda> auxtree = aux.next();
            Iterator<Venda> auxit = auxtree.iterator();
            while(auxit.hasNext()){
                Venda v = auxit.next();
                res += (v.getPreco() * v.getNuni());
            }
        }
        return res;
    }

    public double getTotFacturadoCli(Cliente c){
        double res = 0.0;
        Iterator<Venda> auxit = vendascli.get(c).iterator();


        while(auxit.hasNext()){
            Venda v = auxit.next();
            res += (v.getPreco() * v.getNuni());
        }
        return res;
    }

    public void insereFilialCli(Venda v){
        Cliente cli = v.getCliente();

        TreeSet<Venda> aux = vendascli.get(cli);
        if(aux==null) aux = new TreeSet<Venda>(new VendaComparator());
        aux.add(v);
        vendascli.put(cli,aux);
    }


 }

