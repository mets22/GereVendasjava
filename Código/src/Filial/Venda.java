package Filial;

import Produto.Produto;
import Cliente.Cliente;

import java.util.Objects;

public class Venda {

    private Integer filial;
    private Produto produto;
    private Cliente cliente;
    private Double preco;
    private Integer nuni; //nº unidades
    private Boolean promo;
    private Integer mes;


    public Venda(Integer filial,Produto produto, Cliente cliente, Double preco, Integer nuni, Boolean promo,Integer mes)
    {
        this.filial = filial;
        this.produto = produto.clone();
        this.cliente = cliente.clone();
        this.preco = preco;
        this.nuni = nuni;
        this.promo = promo;
        this.mes = mes;
    }

    public Venda(Venda v)
    {
        this.filial = v.getFilial();
        this.cliente = v.getCliente().clone();
        this.produto = v.getProduto().clone();
        this.preco = v.getPreco();
        this.nuni = v.getNuni();
        this.promo = v.getPromo();
        this.mes = v.getMes();
    }

    public Integer getFilial() {
        return filial;
    }

    public Produto getProduto() {return this.produto;}

    public Double getPreco() {
        return this.preco;
    }

    public Cliente getCliente() {return this.cliente;}

    public Integer getNuni() {
        return this.nuni;
    }

    public Boolean getPromo() {
        return this.promo;
    }

    public Integer getMes() {
        return this.mes;
    }

    @Override
    public Venda clone()
    {
        return new Venda(this);
    }

    @Override
    public String toString() {
        return "Venda{" +
                "filial=" + this.filial.toString() +
                ", codProd='" + this.produto.toString() + '\'' +
                ", codCli='" + this.cliente.toString() + '\'' +
                ", preco=" + this.preco.toString() +
                ", nuni=" + this.nuni.toString() +
                ", promo=" + this.promo.toString() +
                ", mes=" + this.mes.toString() +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Venda)) return false;
        Venda venda = (Venda) o;
        return ((this.getFilial()==venda.getFilial()) &&
                (this.getProduto().equals(venda.getProduto())) &&
                (this.getCliente().equals(venda.getCliente())) &&
                (this.getPreco() == venda.getPreco()) &&
                (this.getNuni() == venda.getNuni()) &&
                (this.getMes() == venda.getMes()));
    }

    @Override
    public int hashCode() {
        return Objects.hash(filial, produto, cliente, preco, nuni, promo, mes);
    }
}
