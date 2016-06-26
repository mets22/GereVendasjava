package Hipermercado;


import javafx.beans.property.SimpleStringProperty;

public class EstatisticaFilial {
    private int mes;
    private double faturadoMes;


    public EstatisticaFilial(){
        this.mes =0;
        this.faturadoMes = 0;
    }

    public EstatisticaFilial(int mes, double faturadoMes){
        this.mes = mes;
        this.faturadoMes = faturadoMes;
    }

    public EstatisticaFilial(EstatisticaFilial estatisticaFilial){
        this.mes = estatisticaFilial.getMes();
        this.faturadoMes = estatisticaFilial.getFaturadoMes();
    }


    public double getFaturadoMes() {
        return faturadoMes;
    }

    public void setFaturadoMes(double faturadoMes) {
        this.faturadoMes = faturadoMes;
    }

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public SimpleStringProperty getMesProperty(){
        return new SimpleStringProperty(String.valueOf(this.mes));
    }

    public SimpleStringProperty getFaturadoProperty(){
        return new SimpleStringProperty(String.valueOf(this.faturadoMes));
    }

    @Override
    public boolean equals(Object o){
        if(this==o) return true;
        if((o==null) || (this.getClass()!=o.getClass())) return false;
        else{
            EstatisticaFilial estatisticaFilial = (EstatisticaFilial) o;
            return ((this.getMes()==estatisticaFilial.getMes())&&(this.getFaturadoMes()==estatisticaFilial.getFaturadoMes()));
        }
    }

    @Override
    public EstatisticaFilial clone(){
        return new EstatisticaFilial(this);
    }

    @Override
    public String toString(){
        StringBuilder sp = new StringBuilder();
        sp.append("Mes:" + this.getMes() +"\n");
        sp.append("Faturado:" +this.getFaturadoMes());
        return sp.toString();
    }
}
