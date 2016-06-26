package Filial;

import Produto.Produto;

import java.util.*;

/**
 * Created by mets on 10-06-2016.
 */
public class ProdutoQuantidadeComparator implements Comparator<Map.Entry<Produto,Integer>> {
    public int compare(Map.Entry<Produto,Integer> m1, Map.Entry<Produto,Integer> m2){
        if(m1.getValue() < m2.getValue()) return -1;
        else if(m1.getValue() > m2.getValue()) return 1;
        else if(m1.getKey().toString().compareTo(m2.getKey().toString())<0) return -1;
        else if(m1.getKey().toString().compareTo(m2.getKey().toString())>0) return 1;
        else return 0;
    }
}
