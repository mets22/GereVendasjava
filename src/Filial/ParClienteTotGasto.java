package Filial;


import Cliente.Cliente;
import javafx.beans.property.SimpleStringProperty;

public class ParClienteTotGasto implements Comparable<ParClienteTotGasto>{
    private Cliente c;
    private double totgasto;

    public ParClienteTotGasto(double totgasto, Cliente c) {
        this.totgasto = totgasto;
        this.c = c;
    }

    public Cliente getC() {
        return this.c;
    }

    public double getTotgasto() {
        return this.totgasto;
    }

    @Override
    public int compareTo(ParClienteTotGasto pc) {
        if(this.totgasto>pc.getTotgasto()) return -1;
        else return 1;
    }


    public SimpleStringProperty getCodigoClienteProperty(){
        return new SimpleStringProperty(this.c.getCodigo());
    }

    public SimpleStringProperty getTotalGastoProperty(){
        return new SimpleStringProperty(String.valueOf(this.totgasto));
    }
}
