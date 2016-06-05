package Produto;


import java.io.Serializable;
import java.util.Objects;

public class Produto implements Serializable{

    private String codigo;

    public Produto(String codigo){
        this.codigo = codigo;
    }

    public Produto(Produto produto){
        this.codigo = produto.getCodigo();
    }


    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    @Override
    public String toString(){
        StringBuilder sp = new StringBuilder();
        sp.append("Produto:" + this.getCodigo());
        return sp.toString();
    }

    @Override
    public Produto clone(){
        return new Produto(this);
    }

    @Override
    public boolean equals(Object p){
        if(this == p) return true;
        if((p==null) || (this.getClass()!= p.getClass())) return false;
        else{
            Produto newp = (Produto) p;
            return this.getCodigo().equals(newp.getCodigo());
        }
    }

}
