package Faturacao;


import Filial.Venda;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FaturacaoProduto {
    private Map<Integer,FaturacaoProdutoDetalhes> detalhesPorMes;

    public FaturacaoProduto(){
        this.detalhesPorMes = new HashMap<Integer,FaturacaoProdutoDetalhes>();
    }

    public FaturacaoProduto(Map<Integer,FaturacaoProdutoDetalhes> novaFaturacaoProduto){
        this.detalhesPorMes = new HashMap<Integer,FaturacaoProdutoDetalhes>(novaFaturacaoProduto.size(),1);

        novaFaturacaoProduto.forEach((k,v) -> this.detalhesPorMes.put(k,v.clone()));
    }

    public FaturacaoProduto(FaturacaoProduto novaFaturacaoProduto){
        this.detalhesPorMes = (HashMap<Integer,FaturacaoProdutoDetalhes>)getDetalhesPorMes();
    }


    public Map<Integer,FaturacaoProdutoDetalhes> getDetalhesPorMes(){
        Map<Integer,FaturacaoProdutoDetalhes> resultado = new HashMap<Integer, FaturacaoProdutoDetalhes>(this.detalhesPorMes.size(),1);
        this.detalhesPorMes.forEach((k,v) -> resultado.put(k,v.clone()));
        return resultado;
    }


    public void adicionarVendaAFaturacao(Venda venda){
        FaturacaoProdutoDetalhes aux = this.detalhesPorMes.get(venda.getMes());
        if(aux == null) aux = new FaturacaoProdutoDetalhes();
        if(venda.getPromo()== true){
            aux.adicionaFaturadoEmPromocao(venda.getPreco());
            aux.adicionaQuantidadePromocao(venda.getNuni());
            aux.adicionaVendasPromocao(1);
        }else {
            aux.adicionaFaturadoEmNormal(venda.getPreco());
            aux.adicionaQuantidadeNormal(venda.getNuni());
            aux.adicionaVendasNormais(1);
        }
        this.detalhesPorMes.put(venda.getMes(),aux);
    }


    public int quantidadeVendidaNormalMes(int mes){
        FaturacaoProdutoDetalhes fat = this.detalhesPorMes.get(mes);
        if(fat == null) return 0;
        return fat.getVendasNormais();
    }

    public int quantidadeVendidaNormalTotal(){
        int qtdVendidaTotal = this.detalhesPorMes.values().stream().mapToInt(FaturacaoProdutoDetalhes::getQuantNormal).sum();
        return qtdVendidaTotal;
    }

    public int quantidadeVendidaPromocaoMes(int mes){
        FaturacaoProdutoDetalhes fat = this.detalhesPorMes.get(mes);
        if(fat==null) return 0;
        return fat.getQuantPromocao();
    }

    public int quantidadeVendidaPromocaoTotal(){
        int qtdVendidaTotal = this.detalhesPorMes.values().stream().mapToInt(FaturacaoProdutoDetalhes::getQuantPromocao).sum();
        return qtdVendidaTotal;
    }

    public int nrVendasNormalMes(int mes){
        FaturacaoProdutoDetalhes fat = this.detalhesPorMes.get(mes);
        if(fat==null) return 0;
        return fat.getVendasNormais();
    }

    public int nrVendasNormalTotal(){
        int qtdVendasTotal = this.detalhesPorMes.values().stream().mapToInt(FaturacaoProdutoDetalhes::getVendasNormais).sum();
        return qtdVendasTotal;
    }

    public int nrVendasPromocaoMes(int mes){
        FaturacaoProdutoDetalhes fat = this.detalhesPorMes.get(mes);
        if(fat == null) return 0;
        return fat.getVendasPromocao();
    }

    public int nrVendasPromocaoTotal(){
        int qtdVendasTotal = this.detalhesPorMes.values().stream().mapToInt(FaturacaoProdutoDetalhes::getVendasPromocao).sum();
        return qtdVendasTotal;
    }

    public double faturadoNormalMes(int mes){
        FaturacaoProdutoDetalhes fat = this.detalhesPorMes.get(mes);
        if(fat == null) return 0;
        return fat.getFaturadoEmNormal();
    }

    public double faturadoNormalTotal(){
        double faturado = this.detalhesPorMes.values().stream().mapToDouble(FaturacaoProdutoDetalhes::getFaturadoEmNormal).sum();
        return faturado;
    }

    public double faturadoPromocaoMes(int mes){
        FaturacaoProdutoDetalhes fat = this.detalhesPorMes.get(mes);
        if(fat == null) return 0;
        return fat.getFaturadoEmPromocao();
    }

    public double faturadoPromocaoTotal(){
        double faturado = this.detalhesPorMes.values().stream().mapToDouble(FaturacaoProdutoDetalhes::getFaturadoEmPromocao).sum();
        return faturado;
    }


    @Override
    public FaturacaoProduto clone(){
        return new FaturacaoProduto(this);
    }

    @Override
    public String toString(){
        StringBuilder sp = new StringBuilder();
        this.detalhesPorMes.forEach((k,v) -> sp.append("Mes:" + k + "\n" + v.toString()));
        return sp.toString();
    }

}
