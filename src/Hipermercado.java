import Cliente.CatClientes;
import Filial.Venda;
import Leitor.Input;
import Leitor.LeitorFicheiros;
import Produto.CatProdutos;
import Produto.Produto;

import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import static Leitor.LeitorFicheiros.parseFicheiroClientes;
import static Leitor.LeitorFicheiros.parseFicheiroProdutos;
import static Leitor.LeitorFicheiros.parseFicheiroVendas;

public class Hipermercado {

    private static CatClientes catalogoClientes;
    private static CatProdutos catalogoProdutos;
    private static Set<Venda> catVendas;

    public Hipermercado(){
        catalogoClientes = new CatClientes();
        catalogoProdutos = new CatProdutos();
        catVendas = new HashSet<Venda>();
    }

    public Hipermercado(CatClientes cclientes,CatProdutos cprodutos, Set<Venda> vendas){
        catalogoClientes = cclientes;
        catalogoProdutos = cprodutos;
        catVendas = vendas;
    }




    /** Métodos para guardar e restaurar estado do Hipermercado **/

    public void gravaEstado(String nome) throws IOException{
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(nome));
        oos.writeObject(this);
        oos.flush();
        oos.close();
    }

    public static Hipermercado carregaEstado(String nome) throws IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(nome));
        Hipermercado hipermercado = (Hipermercado)ois.readObject();
        ois.close();
        return hipermercado;
    }

    public static void lerFicheiros(String ficheiroClientes, String ficheiroProdutos, String ficheiroVendas){
        catalogoClientes = parseFicheiroClientes(ficheiroClientes);
        catalogoProdutos = parseFicheiroProdutos(ficheiroProdutos);
        catVendas = parseFicheiroVendas(ficheiroVendas);
    }



    public static int nrTotalComprasPorFilial(ArrayList<Venda> vendas, int filial){
        int res = 0;
        for(Venda v: vendas){
            if(v.getFilial()==filial) res++;
        }
        return res;
    }

    public static int comprasAZero(ArrayList<Venda> vendas){
        int res = 0;
        for(Venda v: vendas){
            if(v.getPreco()==0) res++;
        }
        return res;
    }


    public static boolean existeProduto(String p) {
        return catalogoProdutos.existeprod(new Produto(p));
    }

}
