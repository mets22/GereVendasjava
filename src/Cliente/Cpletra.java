package Cliente;


import java.util.TreeSet;

public class Cpletra {

    private TreeSet<Cliente> letras;

    public Cpletra(){
        this.letras=new TreeSet<Cliente>(new ClienteComparator());
    }

    public Cpletra(Cpletra c){
        this.letras = c.getLetras();
    }


    public TreeSet<Cliente> getLetras() {
        return letras;
    }

    public void setLetras(TreeSet<Cliente> letras) {
        this.letras = letras;
    }
}
