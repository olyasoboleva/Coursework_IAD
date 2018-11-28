package entity;

import javax.persistence.*;

@Entity
@Table(name = "statuses", schema = "public", catalog = "postgres")
public class StatusesEntity {
    private long statusid;
    private String name;
    private PricesEntity pricesByPriceid;

    public StatusesEntity() {}

    public StatusesEntity(String name, Integer priceid) {
        this.name = name;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "statusid")
    public long getStatusid() {
        return statusid;
    }

    public void setStatusid(long statusid) {
        this.statusid = statusid;
    }

    @Basic
    @Column(name = "name", length = 40)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        StatusesEntity that = (StatusesEntity) o;

        if (statusid != that.statusid) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (statusid ^ (statusid >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "priceid", referencedColumnName = "priceid", insertable = false, updatable = false)
    public PricesEntity getPricesByPriceid() {
        return pricesByPriceid;
    }

    public void setPricesByPriceid(PricesEntity pricesByPriceid) {
        this.pricesByPriceid = pricesByPriceid;
    }
}
