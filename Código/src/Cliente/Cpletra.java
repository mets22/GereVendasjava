package Cliente;


import java.io.Serializable;
import java.util.Set;
import java.util.TreeSet;

public class Cpletra implements Serializable {

    private Set<Cliente> letras;

    public Cpletra(){
        this.letras=new TreeSet<Cliente>(new ClienteComparator());
    }

    public Cpletra(Cpletra c){
        this.letras = c.getLetras();
    }


    public Set<Cliente> getLetras() {
        return this.letras;
    }

    public void setLetras(TreeSet<Cliente> letras) {
        this.letras = letras;
    }

    public void insereCliente(Cliente c){
        if(!this.existeCliente(c)) this.letras.add(c);
    }

    public boolean existeCliente(Cliente c){
        return this.letras.contains(c);
    }

    public int getSize(){
        return this.letras.size();
    }
}
