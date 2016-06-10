package Filial;

import Cliente.Cliente;

/* Utilizada para a query8*/

public class Clienteqt {
    private Cliente c;
    private Integer qt;

    public Clienteqt(Cliente cli, Integer qtd){
        setC(cli);
        setQt(qtd);
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
}
