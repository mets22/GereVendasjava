package Cliente;

import java.util.TreeSet;

public class CatClientes {

    private TreeSet<Cliente> catalogo;

    public CatClientes(){
        this.catalogo = new TreeSet<Cliente>(new ClienteComparator());
    }

    public CatClientes(CatClientes c){

    }
}
