package Filial;

/**
 * Created by mets on 11-06-2016.
 */

import Cliente.Cliente;

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
}
