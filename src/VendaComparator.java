import java.util.Comparator;

/**
 * Created by mets on 23-05-2016.
 */

//comparador por mês da compra
public class VendaComparator implements Comparator<Venda> {
    public int compare(Venda v1, Venda v2)
    {
        if(v1.getFilial() < v2.getFilial()) return -1;
        else if(v1.getFilial() > v2.getFilial()) return 1;
        else if(v1.getCodCli().compareTo(v2.getCodCli()) == -1) return -1;
        else if(v1.getCodCli().compareTo(v2.getCodCli()) == 1) return 1;
        else if(v1.getCodProd().compareTo(v2.getCodProd()) == -1) return -1;
        else if(v1.getCodProd().compareTo(v2.getCodProd()) == 1) return 1;
        else if(v1.getMes() < v2.getMes()) return -1;
        else if(v1.getMes() > v2.getMes()) return 1;
        else if((v1.getPromo() == Boolean.TRUE) != v2.getPromo()) return -1;
        else if((v1.getPromo() == Boolean.FALSE) != v2.getPromo()) return 1;
        else if(v1.getNuni() < v2.getNuni()) return -1;
        else if(v1.getNuni() > v2.getNuni()) return 1;
        else if(v1.getPreco() < v2.getPreco()) return -1;
        else if(v1.getPreco() > v2.getPreco()) return 1;
        else return 0;
    }
}
