package Filial;


import java.io.Serializable;
import java.util.Comparator;

public class ParStringDoubleComparator implements Comparator<ParStringDouble>,Serializable {

    public int compare(ParStringDouble p1, ParStringDouble p2){
        if(p1.equals(p2)) return 0;
        else if(p1.getQt() < p2.getQt()) return -1;
        else if(p1.getQt() > p2.getQt()) return 1;
        else return 0;
    }
}
