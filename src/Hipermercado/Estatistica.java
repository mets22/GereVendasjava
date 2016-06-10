package Hipermercado;

import Cliente.Cliente;
import Cliente.ClienteComparator;
import Produto.ProdutoComparator;
import Produto.Produto;

import java.util.Set;
import java.util.TreeSet;

public class Estatistica {
    private static String ficheiro;
    private static int VendasErradas;
    private static Set<Produto> ProdutosComprados;
    private static Set<Cliente> ClientesCompraram;
    private static int vendasAzero;
    private static double faturacaototal;
    private static int VendasCorretas;

    public Estatistica(){
        ficheiro = "NA";
        VendasErradas = 0;
        ProdutosComprados = new TreeSet<>(new ProdutoComparator());
        ClientesCompraram = new TreeSet<>(new ClienteComparator());
        vendasAzero = 0;
        faturacaototal = 0.0;
        VendasCorretas = 0;
    }

    public Estatistica(String ficheirov){
        ficheiro = ficheirov;
        VendasErradas = 0;
        ProdutosComprados = new TreeSet<>(new ProdutoComparator());
        ClientesCompraram = new TreeSet<>(new ClienteComparator());
        vendasAzero = 0;
        faturacaototal = 0.0;
        VendasCorretas = 0;
    }

    public Estatistica(String ficheirov, int vendasErradas, Set<Produto> produtosComprados, Set<Cliente> clientesCompraram, int vendaszero, double faturacao,int vendasCorretas){
        ficheiro = ficheirov;
        VendasErradas = vendasErradas;
        vendasAzero = vendaszero;
        faturacaototal = faturacao;
        VendasCorretas = vendasCorretas;
    }

    public Estatistica(Estatistica e){
        ficheiro = e.getFicheiro();
        VendasErradas = e.getVendasErradas();
        vendasAzero = e.getVendasAzero();
        faturacaototal = e.getFaturacaototal();
        VendasCorretas = e.getVendasCorretas();
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

    public static void setFaturacaototal(double faturacao){
        faturacaototal = faturacao;
    }

    public static void adicionaFaturacaoTotal(double faturacao){
        faturacaototal+=faturacao;
    }

    public static double getFaturacaototal() {
        return faturacaototal;
    }

    public static Set<Produto> getProdutosComprados() {
        return ProdutosComprados;
    }

    public static void setProdutosComprados(Set<Produto> produtosComprados) {
        ProdutosComprados = produtosComprados;
    }

    public static void adicionaProdutoComprado(Produto produto){
        ProdutosComprados.add(produto);
    }

    public static int getNrProdutosComprados(){
        return ProdutosComprados.size();
    }

    public static Set<Cliente> getClientesCompraram() {
        return ClientesCompraram;
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
}
