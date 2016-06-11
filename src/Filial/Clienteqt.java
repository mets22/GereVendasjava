package Filial;

import Cliente.Cliente;

/* Utilizada para a query9*/

public class Clienteqt {
    private Cliente c;
    private Integer qt;
    private Double total;

    public Clienteqt(Cliente cli, Integer qtd, Double tot){
        setC(cli);
        setQt(qtd);
        setTotal(tot);
    }

    public Integer getQt() {
        return qt;
    }

    public void setQt(Integer qt) {
        this.qt = qt;
    }

    public Cliente getC() {
        return c;
    }

    public void setC(Cliente c) {
        this.c = c;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }
}
