package Filial;

import Cliente.Cliente;
import sun.reflect.generics.tree.Tree;

import java.util.Iterator;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 * Created by mets on 06-06-2016.
 */
public class FilialCli {
    private static TreeMap<Cliente,TreeSet<Venda>> vendascli;

    public FilialCli(){
        this.vendascli = new TreeMap<>();
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

    public TreeSet<Cliente> getClientes(){
        return (TreeSet<Cliente>) vendascli.navigableKeySet();
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

        if(vendascli.containsKey(cli)) vendascli.get(cli).add(v);
        else {
            TreeSet<Venda> aux = new TreeSet<>();
            aux.add(v);
            vendascli.put(cli,aux);
        }
    }
}
