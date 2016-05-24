import java.util.Objects;

/**
 * Created by mets on 23-05-2016.
 */
public class Venda {

    private Integer filial;
    private String codProd;
    private String codCli;
    private Double preco;
    private Integer nuni; //nº unidades
    private Boolean promo;
    private Integer mes;

    public Venda(Integer filial,String codProd, String codCli, Double preco, Integer nuni, Boolean promo,Integer mes)
    {
        this.filial = filial;
        this.codProd = codProd;
        this.codCli = codCli;
        this.preco = preco;
        this.nuni = nuni;
        this.promo = promo;
        this.mes = mes;
    }

    public Venda(Venda v)
    {
        this.filial = v.getFilial();
        this.codCli = v.getCodCli();
        this.codProd = v.getCodProd();
        this.preco = v.getPreco();
        this.nuni = v.getNuni();
        this.promo = v.getPromo();
        this.mes = v.getMes();
    }

    public Integer getFilial() {
        return filial;
    }

    public String getCodProd() {
        return this.codProd;
    }

    public Double getPreco() {
        return this.preco;
    }

    public String getCodCli() {
        return this.codCli;
    }

    public Integer getNuni() {
        return this.nuni;
    }

    public Boolean getPromo() {
        return this.promo;
    }

    public Integer getMes() {
        return this.mes;
    }

    public Venda Clone(Venda v)
    {
        return new Venda(v);
    }

    @Override
    public String toString() {
        return "Venda{" +
                "filial=" + filial +
                ", codProd='" + codProd + '\'' +
                ", codCli='" + codCli + '\'' +
                ", preco=" + preco +
                ", nuni=" + nuni +
                ", promo=" + promo +
                ", mes=" + mes +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Venda)) return false;
        Venda venda = (Venda) o;
        return Objects.equals(filial, venda.filial) &&
                Objects.equals(codProd, venda.codProd) &&
                Objects.equals(codCli, venda.codCli) &&
                Objects.equals(preco, venda.preco) &&
                Objects.equals(nuni, venda.nuni) &&
                Objects.equals(promo, venda.promo) &&
                Objects.equals(mes, venda.mes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(filial, codProd, codCli, preco, nuni, promo, mes);
    }
}
