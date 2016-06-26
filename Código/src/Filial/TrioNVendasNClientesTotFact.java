package Filial;

import Cliente.Cliente;
import Cliente.ClienteComparator;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.Set;
import java.util.TreeSet;


public class TrioNVendasNClientesTotFact {
    private Integer mes;
    private Integer nVendas;
    private Set<Cliente> Clientes;
    private double totfacturado;

    public TrioNVendasNClientesTotFact(){
        this.mes = 0;
        this.nVendas = 0;
        this.Clientes = new TreeSet<Cliente>(new ClienteComparator());
        this.totfacturado = 0.0;
    }

    public TrioNVendasNClientesTotFact(Integer mes, Integer nVendas, Set<Cliente> clientes, double totfacturado) {
        this.mes = mes;
        this.nVendas = nVendas;
        this.Clientes = new TreeSet<Cliente>(new ClienteComparator());
        clientes.forEach(cliente -> this.Clientes.add(cliente));
        this.totfacturado = totfacturado;
    }

    public TrioNVendasNClientesTotFact(TrioNVendasNClientesTotFact trio){
        this.mes = trio.getMes();
        this.nVendas = trio.getnVendas();
        this.Clientes = trio.getClientes();
        this.totfacturado = trio.getTotfacturado();
    }


    public Integer getnVendas() {
        return this.nVendas;
    }

    public Set<Cliente> getClientes(){
        Set<Cliente> resultado = new TreeSet<Cliente>(new ClienteComparator());
        this.Clientes.forEach(cliente -> resultado.add(cliente));
        return resultado;
    }

    public void adicionaClientes(Set<Cliente> clientes){
        clientes.forEach(cliente -> this.Clientes.add(cliente));
    }

    public void adicionaVendas(int vendas){
        this.nVendas+=vendas;
    }

    public void adicionaFaturacao(double faturacao){
        this.totfacturado+=faturacao;
    }


    public Integer getnClientes() {return this.Clientes.size();}

    public double getTotfacturado() {
        return this.totfacturado;
    }

    public Integer getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public SimpleStringProperty getMesProperty(){
        return new SimpleStringProperty(String.valueOf(this.mes));
    }

    public SimpleStringProperty getTotalFaturadoProperty(){
        return new SimpleStringProperty(String.valueOf(this.totfacturado));
    }

    public SimpleStringProperty getNrClientesProperty(){
        return new SimpleStringProperty(String.valueOf(this.Clientes.size()));
    }

    public SimpleStringProperty getNrVendasProperty(){
        return new SimpleStringProperty(String.valueOf(this.nVendas));
    }
}
