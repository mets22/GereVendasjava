import java.util.Comparator;

/**
 * Created by mets on 23-05-2016.
 */

//comparador por mês da compra
public class VendaComparator implements Comparator<Venda> {
    public int compare(Venda v1, Venda v2)
    {
        if(v1.getMes() < v2.getMes()) return -1;
        else if(v1.getMes() > v2.getMes()) return 1;
        else return 0;
    }
}
