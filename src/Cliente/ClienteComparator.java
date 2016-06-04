package Cliente;


import java.util.Comparator;

public class ClienteComparator implements Comparator<Cliente>{

    public int compare(Cliente c1,Cliente c2){
        if(c1.equals(c2)) return 0;
        return c1.getCodigo().compareTo(c2.getCodigo());
    }

}
