package Cliente;


public class Cliente {
    private String codigo;

    public Cliente(String codigo){
        this.codigo = codigo;
    }

    public Cliente(Cliente c){
        this.codigo = c.getCodigo();
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String toString(){
        StringBuilder sp = new StringBuilder();
        sp.append("Cliente:"+this.getCodigo());
        return sp.toString();
    }

    public Cliente clone(){
        return new Cliente(this);
    }

    @Override
    public boolean equals(Object o){
        if(this==o) return true;
        if((o==null) || (this.getClass()!= o.getClass())) return false;
        else {
            Cliente newc = (Cliente) o;
            return this.getCodigo().equals(newc.getCodigo());
        }
    }
}
