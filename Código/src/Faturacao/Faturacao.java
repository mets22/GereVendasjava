package Faturacao;

import Filial.Venda;
import Produto.Produto;
import Produto.ProdutoComparator;

import java.util.*;


public class Faturacao {
    private static Map<String,FaturacaoFilial> faturacaoPorProduto;

    public Faturacao(){
        faturacaoPorProduto = new HashMap<>();
    }

    public Faturacao(Map<String,FaturacaoFilial> novaFaturacao){
        faturacaoPorProduto = new HashMap<>();
        novaFaturacao.forEach((k,v) -> faturacaoPorProduto.put(k,v.clone()));
    }

    public Faturacao(Faturacao faturacao){
        faturacaoPorProduto = (HashMap<String,FaturacaoFilial>) getFaturacaoPorProduto();
    }


    public Map<String,FaturacaoFilial> getFaturacaoPorProduto(){
        Map<String,FaturacaoFilial> resultado = new HashMap<String, FaturacaoFilial>(faturacaoPorProduto.size(),1);
        faturacaoPorProduto.forEach((k,v) -> resultado.put(k,v.clone()));
        return resultado;
    }

    public void adicionaProduto(Produto produto){
        String chave = produto.getCodigo();
        FaturacaoFilial faturacaoFilial = faturacaoPorProduto.get(chave);
        if(faturacaoFilial==null) {
            faturacaoFilial = new FaturacaoFilial();
            faturacaoPorProduto.put(chave, faturacaoFilial);
        }
    }

    public void adicionaVenda(Venda venda){
        String chave = venda.getProduto().getCodigo();
        FaturacaoFilial faturacaoFilial = faturacaoPorProduto.get(chave);
        try{
            faturacaoFilial.adicionarVendaAFaturacao(venda);
            faturacaoPorProduto.put(chave,faturacaoFilial);
        }catch (NullPointerException e){
            System.out.println("Erro ao adicionar produto que não existe!");
        }
    }


    public Set<Produto> produtosNaoComprados(){
        Set<Produto> resultado = new TreeSet<Produto>(new ProdutoComparator());
        Iterator<Map.Entry<String,FaturacaoFilial>> it = faturacaoPorProduto.entrySet().iterator();
        while(it.hasNext()){
            Map.Entry<String,FaturacaoFilial> par = it.next();
            FaturacaoFilial aux = par.getValue();
            if(aux.semVendas()) resultado.add(new Produto(par.getKey()));
        }

        return resultado;
    }

    public Set<Produto> produtosComprados(){
        Set<Produto> resultado = new TreeSet<Produto>(new ProdutoComparator());
        Iterator<Map.Entry<String,FaturacaoFilial>> it = faturacaoPorProduto.entrySet().iterator();
        while(it.hasNext()){
            Map.Entry<String,FaturacaoFilial> par = it.next();
            FaturacaoFilial aux = par.getValue();
            if(!aux.semVendas()) resultado.add(new Produto(par.getKey()));
        }
        return resultado;
    }


    public double getFaturacaoTotal(){
        double faturado = faturacaoPorProduto.values().stream().mapToDouble(FaturacaoFilial::faturadoTotal).sum();
        return faturado;
    }

    public double getFaturacaoTotalMes(int mes){
        double faturado= 0;
        Iterator<Map.Entry<String,FaturacaoFilial>> it = faturacaoPorProduto.entrySet().iterator();
        while (it.hasNext()){
            Map.Entry<String,FaturacaoFilial> par = it.next();
            FaturacaoFilial aux = par.getValue();
            faturado+= aux.faturadoTotalMes(mes);
        }
        return faturado;
    }

    public int getTotalVendasMes(int mes){
        int vendas = 0;
        Iterator<Map.Entry<String,FaturacaoFilial>> it = faturacaoPorProduto.entrySet().iterator();
        while(it.hasNext()){
            Map.Entry<String,FaturacaoFilial> par = it.next();
            FaturacaoFilial aux = par.getValue();
            vendas+= aux.nrVendasNormaisTotalMes(mes);
            vendas+= aux.nrVendasPromocaoTotalMes(mes);
        }
        return vendas;
    }

    public double faturadoPorFilial(int filial){
        double faturado = 0;
        Iterator<Map.Entry<String,FaturacaoFilial>> it = faturacaoPorProduto.entrySet().iterator();

        while (it.hasNext()){
            Map.Entry<String,FaturacaoFilial> par = it.next();
            FaturacaoFilial aux = par.getValue();
            faturado += aux.faturadoTotalFilial(filial);
        }
        return faturado;
    }

    public double faturadoPorFilialMes(int filial,int mes){
        double faturado = 0;
        Iterator<Map.Entry<String,FaturacaoFilial>> it = faturacaoPorProduto.entrySet().iterator();

        while (it.hasNext()){
            Map.Entry<String,FaturacaoFilial> par = it.next();
            FaturacaoFilial aux = par.getValue();
            faturado += aux.faturadoTotalFilialMes(filial,mes);
        }
        return faturado;
    }


    @Override
    public Faturacao clone(){return new Faturacao(this);}

}
