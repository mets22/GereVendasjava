package Cliente;


import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeSet;

public class CatClientes implements Serializable{


    private Map<Character,Cpletra> catcli;

    public CatClientes(){
        this.catcli = new HashMap<Character, Cpletra>();
    }

    public CatClientes(CatClientes c){
        this.catcli = c.catcli;
    }

    public void inserecli(Cliente c){
        Character pos = getHash(c);
        Cpletra aux;
        if((aux = this.catcli.get(pos)) == null) aux = new Cpletra();
        aux.insereCliente(c);
        catcli.put(pos,aux);
    }

    public boolean existecli(Cliente c){
        Character pos = getHash(c);

        Cpletra aux = catcli.get(pos);
        return aux.existeCliente(c);
    }

    private Character getHash(Cliente c){
        String cod = c.getCodigo();
        Character pos = cod.charAt(0);
        pos = Character.toUpperCase(pos);
        return pos;
    }

    public int getSize(){
        int sizess = 0;
        Iterator it = this.catcli.entrySet().iterator();
        while(it.hasNext()){
            Map.Entry thisentry = (Map.Entry) it.next();
            Cpletra a = (Cpletra)thisentry.getValue();
            sizess+=a.getSize();
        }
        return sizess;
    }
}
