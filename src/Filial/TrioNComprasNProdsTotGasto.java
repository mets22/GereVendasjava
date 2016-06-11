package Filial;


public class TrioNComprasNProdsTotGasto {
    private Integer nCompras;
    private Integer nProds;
    private double totgasto;


    public TrioNComprasNProdsTotGasto(Integer nCompras, Integer nProds, double totgasto) {
        this.nCompras = nCompras;
        this.nProds = nProds;
        this.totgasto = totgasto;
    }

    public Integer getnCompras() {
        return this.nCompras;
    }

    public Integer getnProds() {
        return this.nProds;
    }

    public double getTotgasto() {
        return this.totgasto;
    }
}
