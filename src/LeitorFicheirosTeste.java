import java.util.ArrayList;

/**
 * Created by mets on 25-05-2016.
 */
public class LeitorFicheirosTeste {
    private static ArrayList<Venda> aux = new ArrayList<>(LeitorFicheiros.readVendasWithBuff("Vendas_3M.txt"));

    public static void main(String args[]){
        for(Venda v: aux){
            v.toString();
        }
    }
}
