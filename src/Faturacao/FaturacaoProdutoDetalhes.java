package Faturacao;


public class FaturacaoProdutoDetalhes {

    private int quantPromocao;
    private int quantNormal;
    private double faturadoEmPromocao;
    private double faturadoEmNormal;
    private int vendasPromocao;
    private int vendasNormais;


    public FaturacaoProdutoDetalhes(){
        this.quantPromocao = 0;
        this.quantNormal = 0;
        this.faturadoEmPromocao = 0;
        this.faturadoEmNormal = 0;
        this.vendasPromocao = 0;
        this.vendasNormais = 0;
    }

    public FaturacaoProdutoDetalhes(int qtdPromocao, int quantNormal, double faturadoEmPromocao, double faturadoEmNormal, int vendasPromocao, int vendasNormais){
        this.quantPromocao=qtdPromocao;
        this.quantNormal = quantNormal;
        this.faturadoEmPromocao = faturadoEmPromocao;
        this.faturadoEmNormal = faturadoEmNormal;
        this.vendasPromocao = vendasPromocao;
        this.vendasNormais = vendasNormais;
    }

    public FaturacaoProdutoDetalhes(FaturacaoProdutoDetalhes faturacao){
        this.quantPromocao = faturacao.getQuantPromocao();
        this.quantNormal = faturacao.getQuantNormal();
        this.faturadoEmPromocao = faturacao.getFaturadoEmPromocao();
        this.faturadoEmNormal = faturacao.getFaturadoEmNormal();
        this.vendasPromocao = faturacao.getVendasPromocao();
        this.vendasNormais = faturacao.getVendasNormais();
    }

    public int getQuantPromocao(){return this.quantPromocao;}
    public int getQuantNormal(){return this.quantNormal;}
    public double getFaturadoEmPromocao(){return this.faturadoEmPromocao;}
    public double getFaturadoEmNormal(){return this.faturadoEmNormal;}
    public int getVendasPromocao() {return vendasPromocao;}
    public int getVendasNormais() {return vendasNormais;}


    public void setQuantPromocao(int quantPromocao){this.quantPromocao=quantPromocao;}
    public void setQuantNormal(int quantNormal){this.quantNormal = quantNormal;}
    public void setFaturadoEmPromocao(double faturadoEmPromocao){this.faturadoEmPromocao=faturadoEmPromocao;}
    public void setFaturadoEmNormal(double faturadoEmNormal){this.faturadoEmNormal = faturadoEmNormal;}
    public void setVendasNormais(int vendasNormais) {this.vendasNormais = vendasNormais;}
    public void setVendasPromocao(int vendasPromocao) {this.vendasPromocao = vendasPromocao;}


    public void adicionaQuantidadePromocao(int quantidade){this.quantPromocao+=quantidade;}
    public void adicionaQuantidadeNormal(int quantidade){this.quantNormal+=quantidade;}
    public void adicionaFaturadoEmPromocao(double faturado){this.faturadoEmPromocao+=faturado;}
    public void adicionaFaturadoEmNormal(double faturado){this.faturadoEmNormal+= faturado;}
    public void adicionaVendasNormais(int vendas){this.vendasNormais+=vendas;}
    public void adicionaVendasPromocao(int vendas){this.vendasPromocao+=vendas;}

    @Override
    public String toString(){
        StringBuilder sp = new StringBuilder();
        sp.append("Quantidade de unidades em vendas normais:" + this.getQuantNormal() + "\n");
        sp.append("Faturado em vendas normais:" +this.getFaturadoEmNormal() + "\n");
        sp.append("Quantidade de unidades em vendas em promoção:" + this.getQuantPromocao() + "\n");
        sp.append("Faturado em vendas em promoção" + this.getFaturadoEmPromocao() + "\n");
        sp.append("Número de vendas normais:"+ this.getVendasNormais() + "\n");
        sp.append("Número de vendas em promoção:" + this.getVendasPromocao());
        return sp.toString();
    }

    @Override
    public boolean equals(Object o){
        if(this==o) return true;
        if((o==null) || (this.getClass()!=o.getClass())) return false;
        else{
            FaturacaoProdutoDetalhes fat = (FaturacaoProdutoDetalhes) o;
            return ((this.getQuantNormal()==fat.getQuantNormal())&&
                    (this.getFaturadoEmNormal()==fat.getFaturadoEmNormal()) &&
                    (this.getQuantPromocao()==fat.getQuantPromocao())&&
                    (this.getFaturadoEmPromocao()==fat.getFaturadoEmPromocao()) &&
                    (this.getVendasNormais() == fat.getVendasNormais()) &&
                    (this.getVendasPromocao() == fat.getVendasPromocao())
            );
        }

    }

    @Override
    public FaturacaoProdutoDetalhes clone(){return new FaturacaoProdutoDetalhes(this);}






}
