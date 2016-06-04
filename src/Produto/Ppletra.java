package Produto;


import Cliente.Cliente;

import java.util.TreeSet;

public class Ppletra {

    private TreeSet<Produto> letras;

    private Ppletra(){
        this.letras = new TreeSet<Produto>(new ProdutoComparator());
    }

    private Ppletra(Ppletra p){
        this.letras = p.getLetras();
    }

    public TreeSet<Produto> getLetras() {
        return letras;
    }

    public void setLetras(TreeSet<Produto> letras) {
        this.letras = letras;
    }
}
