package Filial;


import Produto.Produto;
import Produto.ProdutoComparator;
import javafx.beans.property.SimpleStringProperty;

import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class TrioNComprasNProdsTotGasto {
    private Integer mes;
    private Integer nCompras;
    private Set<Produto> produtosComprados;
    private double totgasto;


    public TrioNComprasNProdsTotGasto(){
        this.mes = 0;
        this.nCompras = 0;
        this.produtosComprados = new TreeSet<Produto>(new ProdutoComparator());
        this.totgasto = 0;
    }

    public TrioNComprasNProdsTotGasto(Integer mes,Integer nCompras, Set<Produto> produtosComprados, double totgasto) {
        this.mes = mes;
        this.nCompras = nCompras;
        this.produtosComprados= produtosComprados.stream().collect(Collectors.toSet());
        this.totgasto = totgasto;
    }

    public Integer getMes(){return this.mes;}

    public Integer getnCompras() {
        return this.nCompras;
    }

    public Integer getnProds() {
        return this.produtosComprados.size();
    }

    public double getTotgasto() {
        return this.totgasto;
    }

    public void setMes(int mes){this.mes = mes;}

    public void adicionaNrCompras(int nrcompras){this.nCompras+=nrcompras;}

    public void adicionaProduto(Produto produto){
        this.produtosComprados.add(produto);
    }

    public void adicionaProdutos(Set<Produto> produtos){
        produtos.stream().forEach(produto -> this.produtosComprados.add(produto));
    }

    public Set<Produto> getProdutos(){
        Set<Produto> resultado = new TreeSet<Produto>(new ProdutoComparator());
        resultado = this.produtosComprados.stream().collect(Collectors.toSet());
        return resultado;
    }

    public void adicionaTotalGasto(double totalgasto){
        this.totgasto+=totalgasto;
    }

    public SimpleStringProperty getNComprasProperty(){
        return new SimpleStringProperty(String.valueOf(this.nCompras));
    }

    public SimpleStringProperty getNProds(){
        return new SimpleStringProperty(String.valueOf(this.produtosComprados.size()));
    }

    public SimpleStringProperty getMesProperty(){
        return new SimpleStringProperty(String.valueOf(this.mes));
    }

    public SimpleStringProperty getTotalGastoProperty(){
        return new SimpleStringProperty(String.valueOf(this.totgasto));
    }
}
