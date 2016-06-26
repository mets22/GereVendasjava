package Produto;


import Cliente.Cliente;

import java.io.Serializable;
import java.util.Set;
import java.util.TreeSet;

public class Ppletra implements Serializable{

    private Set<Produto> letras;

    public Ppletra(){
        this.letras = new TreeSet<Produto>(new ProdutoComparator());
    }

    public Ppletra(Ppletra p){
        this.letras = p.getLetras();
    }

    public Set<Produto> getLetras() {
        return letras;
    }

    public void setLetras(TreeSet<Produto> letras) {
        this.letras = letras;
    }

    public void insereProduto(Produto p){
        if(!existeProduto(p)) this.letras.add(p);
    }

    public boolean existeProduto(Produto p){
        return this.letras.contains(p);
    }

    public int getSize(){return this.letras.size();}
}
