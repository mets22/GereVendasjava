import java.util.*;

/**
 * Created by mets on 23-05-2016.
 */
public class Filial {
    private static ArrayList<Venda> vendas;

    public Filial()
    {
        this.vendas = new ArrayList<>();
    }

    public Filial(ArrayList<Venda> vendas)
    {
        this.vendas = new ArrayList<>(vendas);
    }

    public ArrayList<Venda> getVendas() {
        return new ArrayList<>(this.vendas);
    }

    public static Integer nTotalComprasFilial(Integer filial){
        Integer i,ntot=0;
        Venda v;

        for (i=0; i<vendas.size();i++) {
            v = vendas.get(i);
            if(v.getFilial() == filial) ntot++;
        }

        return ntot;
    }

    public static Integer nTotalBorlas(){
        Integer i,ntot=0;
        Venda v;

        for (i=0; i<vendas.size();i++) {
            v = vendas.get(i);
            if(v.getPreco() == 0.0) ntot++;
        }

        return ntot;
    }

    public static Integer nTotalDup(String c){
        Integer i,ntot=0;
        Venda v;

        for (i=0; i<vendas.size();i++) {
            v = vendas.get(i);
            if(v.getPreco() == 0.0) ntot++;
        }

        return ntot;
    }

    @Override
    public String toString() {
        return "Filial{" +
                "vendas=" + vendas.toString() +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Filial filial = (Filial) o;
        return Objects.equals(vendas, filial.vendas);
    }

    @Override
    public int hashCode() {
        return Objects.hash(vendas);
    }
}
