package Filial;

import java.util.Comparator;


//comparador por mês da compra
public class VendaComparator implements Comparator<Venda> {
    public int compare(Venda v1, Venda v2)
    {
        if(v1.getFilial() < v2.getFilial()) return -1;
        else if(v1.getFilial() > v2.getFilial()) return 1;
        else if(v1.getCliente().getCodigo().compareTo(v2.getCliente().getCodigo()) == -1) return -1;
        else if(v1.getCliente().getCodigo().compareTo(v2.getCliente().getCodigo()) == 1) return 1;
        else if(v1.getProduto().getCodigo().compareTo(v2.getProduto().getCodigo()) == -1) return -1;
        else if(v1.getProduto().getCodigo().compareTo(v2.getProduto().getCodigo()) == 1) return 1;
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
