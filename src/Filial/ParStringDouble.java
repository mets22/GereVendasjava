package Filial;

import Cliente.Cliente;
import javafx.beans.property.SimpleStringProperty;

/* Utilizada para a query9*/

public class ParStringDouble {
    private Cliente c;
    private Integer qt;
    private Double total;

    public ParStringDouble(Cliente cli, Integer qtd, Double tot){
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

    public SimpleStringProperty getCodigoClienteProperty(){
        return new SimpleStringProperty(this.c.getCodigo());
    }

    public SimpleStringProperty getQuantidadeProperty(){
        return new SimpleStringProperty(String.valueOf(this.qt));
    }

    public SimpleStringProperty getTotalFaturadoPropert(){
        return new SimpleStringProperty(String.valueOf(this.total));
    }

}
