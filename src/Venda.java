import java.util.Objects;

/**
 * Created by mets on 23-05-2016.
 */
public class Venda {
    String codProd;
    String codCli;
    Float preco;
    Integer nuni; //nº unidades
    Boolean promo;
    Integer mes;

    public Venda(String codProd, String codCli, Float preco, Integer nuni, Boolean promo,Integer mes)
    {
        this.codProd = codProd;
        this.codCli = codCli;
        this.preco = preco;
        this.nuni = nuni;
        this.promo = promo;
        this.mes = mes;
    }

    public Venda(Venda v)
    {
        this.codCli = v.getCodCli();
        this.codProd = v.getCodProd();
        this.preco = v.getPreco();
        this.nuni = v.getNuni();
        this.promo = v.getPromo();
        this.mes = v.getMes();
    }

    public String getCodProd() {
        return this.codProd;
    }

    public Float getPreco() {
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Venda venda = (Venda) o;
        return Objects.equals(codProd, venda.codProd) &&
                Objects.equals(codCli, venda.codCli) &&
                Objects.equals(preco, venda.preco) &&
                Objects.equals(nuni, venda.nuni) &&
                Objects.equals(promo, venda.promo) &&
                Objects.equals(mes, venda.mes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codProd, codCli, preco, nuni, promo, mes);
    }

    @Override
    public String toString() {
        return "Venda{" +
                "codProd='" + codProd + '\'' +
                ", codCli='" + codCli + '\'' +
                ", preco=" + preco +
                ", nuni=" + nuni +
                ", promo=" + promo +
                ", mes=" + mes +
                '}';
    }
}
