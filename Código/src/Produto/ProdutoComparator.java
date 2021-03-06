package Produto;

import java.io.Serializable;
import java.util.Comparator;

public class ProdutoComparator implements Comparator<Produto>,Serializable {

    public int compare(Produto p1,Produto p2){
        if (p1.equals(p2)) return 0;
        return p1.getCodigo().compareTo(p2.getCodigo());
    }

}
