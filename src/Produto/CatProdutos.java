package Produto;


import java.util.HashMap;
import java.util.TreeSet;

public class CatProdutos {

    private HashMap<String,Ppletra> catprod;

    private CatProdutos(){
        this.catprod = new HashMap<String,Ppletra>(26,1);
    }

    private CatProdutos(CatProdutos c){
        this.catprod = c.getCatprod();
    }

    public HashMap<String, Ppletra> getCatprod() {
        return catprod;
    }

    public void setCatprod(HashMap<String, Ppletra> catprod) {
        this.catprod = catprod;
    }

    public void insereprod(Produto p){
        String cod = p.getCodigo();
        String primeira = String.valueOf(cod.charAt(0));

        Ppletra paux = catprod.get(primeira);

        TreeSet aux = paux.getLetras();
        aux.add(p);
    }

    public boolean existeprod(Produto p){
        String cod = p.getCodigo();
        String primeira = String.valueOf(cod.charAt(0));

        Ppletra paux = catprod.get(primeira);

        TreeSet aux = paux.getLetras();
        return aux.contains(p);
    }
}
