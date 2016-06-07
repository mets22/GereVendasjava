package Filial;

/**
 * Created by mets on 06-06-2016.
 */
public class TrioNVendasNClientesTotFact {
    private Integer nVendas;
    private Integer nClientes;
    private double totfacturado;

    public TrioNVendasNClientesTotFact(Integer nVendas, Integer nClientes, double totfacturado) {
        this.nVendas = nVendas;
        this.nClientes = nClientes;
        this.totfacturado = totfacturado;
    }

    public Integer getnVendas() {
        return this.nVendas;
    }

    public Integer getnClientes() {
        return this.nClientes;
    }

    public double getTotfacturado() {
        return this.totfacturado;
    }
}
