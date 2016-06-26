package Hipermercado;


import javafx.beans.property.SimpleStringProperty;

public class OutrasEstatisticas {
    private int mes;
    private int nrcompras;
    private double faturacaomes;
    private int nrclientesdistintos;

    public OutrasEstatisticas(){
        this.mes =0;
        this.nrcompras = 0;
        this.faturacaomes = 0.0;
        this.nrclientesdistintos=0;
    }

    public OutrasEstatisticas(int mes, int nrcompras, double faturacaomes, int nrclientesdistintos){
        this.mes = mes;
        this.nrcompras = nrcompras;
        this.faturacaomes = faturacaomes;
        this.nrclientesdistintos = nrclientesdistintos;
    }

    public OutrasEstatisticas(OutrasEstatisticas outrasEstatisticas){
        this.mes = outrasEstatisticas.getMes();
        this.nrcompras = outrasEstatisticas.getNrcompras();
        this.faturacaomes = outrasEstatisticas.getFaturacaomes();
        this.nrclientesdistintos = outrasEstatisticas.getNrclientesdistintos();
    }

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public int getNrcompras() {
        return nrcompras;
    }

    public void setNrcompras(int nrcompras) {
        this.nrcompras = nrcompras;
    }

    public double getFaturacaomes() {
        return faturacaomes;
    }

    public void setFaturacaomes(double faturacaomes) {
        this.faturacaomes = faturacaomes;
    }

    public int getNrclientesdistintos() {
        return nrclientesdistintos;
    }

    public void setNrclientesdistintos(int nrclientesdistintos) {
        this.nrclientesdistintos = nrclientesdistintos;
    }

    public SimpleStringProperty getMesProperty(){
        return new SimpleStringProperty(String.valueOf(this.mes));
    }

    public SimpleStringProperty getNrComprasProperty(){
        return new SimpleStringProperty(String.valueOf(this.nrcompras));
    }

    public SimpleStringProperty getFaturacaoMesProperty(){
        return new SimpleStringProperty(String.valueOf(this.faturacaomes));
    }

    public SimpleStringProperty getNrClientesDistintosProperty(){
        return new SimpleStringProperty(String.valueOf(this.nrclientesdistintos));
    }


    @Override
    public boolean equals(Object o){
        if(this == o) return true;
        if((o==null) || (this.getClass()!= o.getClass())) return false;
        else{
            OutrasEstatisticas outras = (OutrasEstatisticas) o;
            return ((this.getMes()==outras.getMes()) && (this.getNrcompras()==outras.getNrcompras()) && (this.getFaturacaomes()==outras.getFaturacaomes())&&(this.getNrclientesdistintos()==outras.getNrclientesdistintos()));
        }
    }

    @Override
    public String toString(){
        StringBuilder sp = new StringBuilder();
        sp.append("Mes:" + this.getMes() + "\n");
        sp.append("Número de compras:" + this.getNrcompras() +"\n");
        sp.append("Faturação:" + this.getFaturacaomes() + "\n");
        sp.append("Número de clientes distintos:" + this.getNrclientesdistintos());
        return sp.toString();
    }

    @Override
    public OutrasEstatisticas clone(){
        return new OutrasEstatisticas(this);
    }
}
