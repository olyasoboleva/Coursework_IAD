package entity;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.HashSet;

@Entity
@Table(name = "prices", schema = "public", catalog = "postgres")
public class PricesEntity {
    private int priceid;
    private String name;
    private Integer cost;
    private Collection<StatusesEntity> statusesByPriceid;

    public PricesEntity() {
        statusesByPriceid = new HashSet<StatusesEntity>();
    }

    public PricesEntity(String name, Integer cost) {
        this.name = name;
        this.cost = cost;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "priceid")
    public int getPriceid() {
        return priceid;
    }

    public void setPriceid(int priceid) {
        this.priceid = priceid;
    }

    @Basic
    @NotNull
    @Column(name = "name", length = 64)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Min(0)
    @Column(name = "cost")
    public Integer getCost() {
        return cost;
    }

    public void setCost(Integer cost) {
        this.cost = cost;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PricesEntity that = (PricesEntity) o;

        if (priceid != that.priceid) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (cost != null ? !cost.equals(that.cost) : that.cost != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = priceid;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (cost != null ? cost.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "pricesByPriceid")
    public Collection<StatusesEntity> getStatusesByPriceid() {
        return statusesByPriceid;
    }

    public void setStatusesByPriceid(Collection<StatusesEntity> statusesByPriceid) {
        this.statusesByPriceid = statusesByPriceid;
    }
}
