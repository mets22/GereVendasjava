package Cliente;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeSet;

public class CatClientes {


    private HashMap<String,Cpletra> catcli = new HashMap<>();

    public CatClientes(){
        this.catcli = new HashMap<String,Cpletra>(26,1);
    }

    public CatClientes(CatClientes c){
        this.catcli = c.catcli;
    }

    public void inserecli(Cliente c){
        String cod = c.getCodigo();
        String primeira = String.valueOf(cod.charAt(0));

        Cpletra caux = catcli.get(primeira);

        TreeSet aux = caux.getLetras();
        aux.add(c);
    }

    public boolean existecli(Cliente c){
        String cod = c.getCodigo();
        String primeira = String.valueOf(cod.charAt(0));

        Cpletra caux = catcli.get(primeira);

        TreeSet aux = caux.getLetras();
        return aux.contains(c);
    }

}
