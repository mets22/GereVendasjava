package Filial;

import Produto.Produto;

/**
 * Created by mets on 11-06-2016.
 */
public class ParProdQuantidade implements Comparable<ParProdQuantidade>{
    private int quantidade;
    private Produto p;

    public ParProdQuantidade(int quantidade, Produto p) {
        this.quantidade = quantidade;
        this.p = p;
    }

    public int getQuantidade() {
        return this.quantidade;
    }

    public Produto getP() {
        return this.p;
    }

    @Override
    public int compareTo(ParProdQuantidade ppq) {
        if(this.quantidade>ppq.quantidade) return -1;
        else if(this.quantidade<ppq.quantidade) return 1;
        else if(this.p.getCodigo().compareTo(ppq.getP().getCodigo())<0) return -1;
        else if(this.p.getCodigo().compareTo(ppq.getP().getCodigo())<0) return 1;
        else return 0;
    }
}
