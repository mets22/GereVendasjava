import java.io.*;
import java.util.*;

/**
 * Created by mets on 24-05-2016.
 */
public class LeitorFicheiros {

    public LeitorFicheiros(){
    }

    public static ArrayList<String> readLinesWithBuff(String fich) {
    ArrayList<String> linhas = new ArrayList<>();
    BufferedReader inStream = null;
    String linha = null;
    try {
        inStream = new BufferedReader(new FileReader(fich));
        while( (linha = inStream.readLine()) !=  null )
            linhas.add(linha);
    }
    catch(IOException e)
    {
        System.out.println(e.getMessage());
        return null;
    }
    return linhas;
    }

    public static Venda parseLinhaVenda(String linha) {
        Venda v;
        String[] aux = linha.split(" ");
        Integer filial;
        String codProd;
        String codCli;
        Double preco;
        Integer nuni; //nº unidades
        Boolean promo;
        Integer mes;
        Integer i = 0;

        codProd = aux[i++];
        preco = Double.parseDouble(aux[i++]);
        nuni = Integer.parseInt(aux[i++]);
        if (aux[i++].compareTo("S") == 0) promo = Boolean.TRUE;
        else promo = Boolean.FALSE;
        codCli = aux[i++];
        mes = Integer.parseInt(aux[i++]);
        filial = Integer.parseInt(aux[i++]);

        v = new Venda(filial, codProd, codCli, preco, nuni, promo, mes);
        return v;
    }

    public static ArrayList<Venda> parseAllLinhas(ArrayList<String> linhas){
        List<Venda> aux = new ArrayList<>();

        for (String s: linhas) {
            aux.add(parseLinhaVenda(s));
        }

        return (ArrayList<Venda>) aux;
    }

    public static HashSet<Venda> parseAllLinhasToSet(ArrayList<String> linhas){
        Set<Venda> aux = new HashSet<>();
        Venda v;

        for (String s: linhas) {
            v = parseLinhaVenda(s);
            if(!aux.contains(v)) aux.add(parseLinhaVenda(s));
        }

        return (HashSet<Venda>) aux;
    }

    public static ArrayList<Venda> readVendasWithBuff(String fich){
        return LeitorFicheiros.parseAllLinhas(LeitorFicheiros.readLinesWithBuff(fich));
    }
}
