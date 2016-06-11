package Filial;


public class ParTotVendasTotClientesMes {
    Integer nVendas;
    Integer nClientes;

    public ParTotVendasTotClientesMes(Integer nVendas, Integer nClientes) {
        this.nVendas = nVendas;
        this.nClientes = nClientes;
    }


    public Integer getnVendas() {
        return this.nVendas;
    }

    public Integer getnClientes() {
        return this.nClientes;
    }
}
