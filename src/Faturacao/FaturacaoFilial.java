package Faturacao;


import Filial.Venda;

import java.text.CollationElementIterator;
import java.util.*;

public class FaturacaoFilial {
    private Map<Integer,FaturacaoProduto> faturacaoPorFilial;

    public FaturacaoFilial(){
        this.faturacaoPorFilial = new HashMap<>();
    }

    public FaturacaoFilial(Map<Integer,FaturacaoProduto> faturacaoPorFilial){
        this.faturacaoPorFilial = new HashMap<>();
        faturacaoPorFilial.forEach((k,v) -> faturacaoPorFilial.put(k,v.clone()));
    }

    public FaturacaoFilial(FaturacaoFilial faturacaoFilial){
        this.faturacaoPorFilial = (HashMap<Integer,FaturacaoProduto>)getFaturacaoPorFilial();
    }

    public Map<Integer,FaturacaoProduto> getFaturacaoPorFilial(){
        Map<Integer,FaturacaoProduto> resultado = new HashMap<>();
        this.faturacaoPorFilial.forEach((k,v)-> resultado.put(k,v.clone()));
        return resultado;
    }


    public void adicionarVendaAFaturacao(Venda venda){
        Integer chave = venda.getFilial();
        FaturacaoProduto aux = this.faturacaoPorFilial.get(chave);
        if(aux == null) aux = new FaturacaoProduto();
        aux.adicionarVendaAFaturacao(venda);
        faturacaoPorFilial.put(chave,aux);
    }


    public int quantidadeVendidaNormalFilialTotal(int filial){
        FaturacaoProduto fat = this.faturacaoPorFilial.get(filial);
        return fat.quantidadeVendidaNormalTotal();
    }

    public int quantidadeVendidaNormalFilialMes(int filial,int mes){
        FaturacaoProduto fat = this.faturacaoPorFilial.get(filial);
        return fat.quantidadeVendidaNormalMes(mes);
    }

    public int quantidadeVendidaNormalTotal(){
        int quantidade = this.faturacaoPorFilial.values().stream().mapToInt(FaturacaoProduto::quantidadeVendidaNormalTotal).sum();
        return quantidade;
    }

    public int quantidadeVendidaNormalTotalMes(int mes){
        int quantidade = 0;
        Collection<FaturacaoProduto> filiais = this.faturacaoPorFilial.values();
        for(FaturacaoProduto f:filiais){
            quantidade+=f.quantidadeVendidaNormalMes(mes);
        }
        return quantidade;
    }

    public int quantidadeVendidaPromocaoFilialTotal(int filial){
        FaturacaoProduto fat = this.faturacaoPorFilial.get(filial);
        return fat.quantidadeVendidaPromocaoTotal();
    }

    public int quantidadeVendidaPromocaoFilialMes(int filial,int mes){
        FaturacaoProduto fat = this.faturacaoPorFilial.get(filial);
        return fat.quantidadeVendidaPromocaoMes(mes);
    }

    public int quantidadeVendidaPromocaoTotal(){
        int quantidade = this.faturacaoPorFilial.values().stream().mapToInt(FaturacaoProduto::quantidadeVendidaPromocaoTotal).sum();
        return quantidade;
    }

    public int quantidadeVendidaPromocaoTotalMes(int mes){
        int quantidade = 0;
        Collection<FaturacaoProduto> filiais = this.faturacaoPorFilial.values();
        for(FaturacaoProduto f:filiais){
            quantidade+=f.quantidadeVendidaPromocaoMes(mes);
        }
        return quantidade;
    }

    public double faturadoNormalFilialTotal(int filial){
        FaturacaoProduto fat = this.faturacaoPorFilial.get(filial);
        return fat.faturadoNormalTotal();
    }

    public double faturadoNormalFilialMes(int filial, int mes){
        FaturacaoProduto fat = this.faturacaoPorFilial.get(filial);
        return fat.faturadoNormalMes(mes);
    }

    public double faturadoNormalTotal(){
        double faturado = this.faturacaoPorFilial.values().stream().mapToDouble(FaturacaoProduto::faturadoNormalTotal).sum();
        return faturado;
    }

    public double faturadoNormalTotalMes(int mes){
        double faturado = 0;
        Collection<FaturacaoProduto> filiais = this.faturacaoPorFilial.values();
        for(FaturacaoProduto f:filiais){
            faturado+=f.faturadoNormalMes(mes);
        }
        return faturado;
    }

    public double faturadoPromocaoFilialTotal(int filial){
        FaturacaoProduto fat = this.faturacaoPorFilial.get(filial);
        return fat.faturadoPromocaoTotal();
    }

    public double faturadoPromocaoFilialMes(int filial, int mes){
        FaturacaoProduto fat = this.faturacaoPorFilial.get(filial);
        return fat.faturadoPromocaoMes(mes);
    }

    public double faturadoPromocaoTotal(){
        double faturado = this.faturacaoPorFilial.values().stream().mapToDouble(FaturacaoProduto::faturadoPromocaoTotal).sum();
        return faturado;
    }

    public double faturadoPromocaoTotalMes(int mes){
        double faturado = 0;
        Collection<FaturacaoProduto> filiais = this.faturacaoPorFilial.values();
        for(FaturacaoProduto f: filiais){
            faturado+=f.faturadoPromocaoMes(mes);
        }
        return faturado;
    }

    public int nrVendasNormaisFilialTotal(int filial){
        FaturacaoProduto fat = this.faturacaoPorFilial.get(filial);
        return fat.nrVendasNormalTotal();
    }

    public int nrVendasNormaisFilialMes(int filial, int mes){
        FaturacaoProduto fat = this.faturacaoPorFilial.get(filial);
        return fat.nrVendasNormalMes(mes);
    }

    public int nrVendasNormaisTotal(){
        int nr = this.faturacaoPorFilial.values().stream().mapToInt(FaturacaoProduto::nrVendasNormalTotal).sum();
        return nr;
    }

    public int nrVendasNormaisTotalMes(int mes){
        int nr = 0;
        Collection<FaturacaoProduto> filiais = this.faturacaoPorFilial.values();
        for(FaturacaoProduto f:filiais){
            nr += f.nrVendasNormalMes(mes);
        }
        return nr;
    }

    public int nrVendasPromocaoFilialTotal(int filial){
        FaturacaoProduto fat =this.faturacaoPorFilial.get(filial);
        return fat.nrVendasPromocaoTotal();
    }

    public int nrVendasPromocaoFilialMes(int filial,int mes){
        FaturacaoProduto fat = this.faturacaoPorFilial.get(filial);
        return fat.nrVendasPromocaoMes(mes);
    }

    public int nrVendasPromocaoTotal(){
        int nr = this.faturacaoPorFilial.values().stream().mapToInt(FaturacaoProduto::nrVendasPromocaoTotal).sum();
        return nr;
    }

    public int nrVendasPromocaoTotalMes(int mes){
        int nr = 0;
        Collection<FaturacaoProduto> filiais = this.faturacaoPorFilial.values();
        for(FaturacaoProduto f:filiais){
            nr += f.nrVendasPromocaoMes(mes);
        }
        return nr;
    }

    public boolean semVendas(){
        return ((nrVendasPromocaoTotal() == 0) && (nrVendasNormaisTotal() == 0));
    }


    @Override
    public FaturacaoFilial clone(){return new FaturacaoFilial(this);}
}
