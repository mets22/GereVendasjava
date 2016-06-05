package Produto;


import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeSet;

public class CatProdutos implements Serializable{

    private Map<Character,Ppletra> catprod;

    public CatProdutos(){
        this.catprod = new HashMap<Character, Ppletra>();
    }

    public CatProdutos(CatProdutos c){
        this.catprod = c.getCatprod();
    }

    public Map<Character, Ppletra> getCatprod() {
        return catprod;
    }

    public void setCatprod(HashMap<Character, Ppletra> catprod) {
        this.catprod = catprod;
    }

    public void insereprod(Produto p){
        Character pos = getHash(p);

        Ppletra aux;
        if((aux = this.catprod.get(pos))==null) aux = new Ppletra();
        aux.insereProduto(p);

        this.catprod.put(pos,aux);
    }

    public boolean existeprod(Produto p){
        Character pos = getHash(p);

        Ppletra aux = this.catprod.get(pos);

        return aux.existeProduto(p);
    }

    private Character getHash(Produto p){
        String cod = p.getCodigo();
        Character pos = cod.charAt(0);
        pos = Character.toUpperCase(pos);
        return pos;
    }
    
}
