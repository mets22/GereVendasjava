package Hipermercado;

import Cliente.Cliente;
import Cliente.ClienteComparator;
import Produto.ProdutoComparator;
import Produto.Produto;

import java.io.Serializable;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class Estatistica implements Serializable{
    private static String ficheiro;
    private static int VendasErradas;
    private static Set<Cliente> ClientesCompraram;
    private static int vendasAzero;
    private static int VendasCorretas;
    private static int vendasTotais;

    public Estatistica(){
        ficheiro = "NA";
        VendasErradas = 0;
        ClientesCompraram = new TreeSet<>(new ClienteComparator());
        vendasAzero = 0;
        VendasCorretas = 0;
        vendasTotais = 0;
    }

    public Estatistica(String ficheirov){
        ficheiro = ficheirov;
        VendasErradas = 0;
        ClientesCompraram = new TreeSet<>(new ClienteComparator());
        vendasAzero = 0;
        VendasCorretas = 0;
        vendasTotais = 0;
    }

    public Estatistica(String ficheirov, int vendasErradas, Set<Cliente> clientesCompraram, int vendaszero,int vendasCorretas,int vendasTotaisArg){
        ficheiro = ficheirov;
        VendasErradas = vendasErradas;
        ClientesCompraram = new TreeSet<Cliente>(new ClienteComparator());
        clientesCompraram.forEach(cliente -> ClientesCompraram.add(cliente.clone()));
        vendasAzero = vendaszero;
        VendasCorretas = vendasCorretas;
        vendasTotais = vendasTotaisArg;
    }

    public Estatistica(Estatistica e){
        ficheiro = e.getFicheiro();
        VendasErradas = e.getVendasErradas();
        ClientesCompraram = e.getClientesCompraram();
        vendasAzero = e.getVendasAzero();
        VendasCorretas = e.getVendasCorretas();
        vendasTotais = e.getVendasTotais();

    }

    public static String getFicheiro() {
        return ficheiro;
    }

    public static void setFicheiro(String ficheiro) {
        Estatistica.ficheiro = ficheiro;
    }

    public static int getVendasErradas() {
        return VendasErradas;
    }

    public static void adicionaVendasAZero(int vendasazero){
        vendasAzero+=vendasazero;
    }

    public static void setVendasErradas(int vendasErradas) {
        VendasErradas = vendasErradas;
    }
    public static void adicionaVendaErrada(int vendaserradas){
        VendasErradas+=vendaserradas;
    }

    public static int getVendasAzero() {
        return vendasAzero;
    }


    public static Set<Cliente> getClientesCompraram() {
        Set<Cliente> resultado = new TreeSet<Cliente>(new ClienteComparator());
        ClientesCompraram.forEach(cliente -> resultado.add(cliente.clone()));
        return resultado;
    }

    public static int getNrClientesCompraram(){
        return ClientesCompraram.size();
    }

    public static void setClientesCompraram(Set<Cliente> clientesCompraram) {
        ClientesCompraram = clientesCompraram;
    }

    public static void adicionaClienteComprou(Cliente cliente){
        ClientesCompraram.add(cliente);
    }

    public static int getVendasCorretas() {
        return VendasCorretas;
    }

    public static void setVendasCorretas(int vendasCorretas) {
        VendasCorretas = vendasCorretas;
    }

    public static void adicionaVendaCorreta(int vendas){
        VendasCorretas+=vendas;
    }

    public static int getVendasTotais() {
        return vendasTotais;
    }

    public static void setVendasTotais(int vendasTotais) {
        Estatistica.vendasTotais = vendasTotais;
    }

    public static void adicionaVenda(int  venda){vendasTotais+=venda;}
}
