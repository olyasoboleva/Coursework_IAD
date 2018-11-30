package entity;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "statuses", schema = "public", catalog = "postgres")
public class StatusesEntity {
    private int statusid;
    private String name;
    private PricesEntity pricesByPriceid;
    private Collection<UsersEntity> users;

    public StatusesEntity() {}

    public StatusesEntity(String name, PricesEntity price) {
        this.name = name;
        this.pricesByPriceid = price;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "statusid")
    public int getStatusid() {
        return statusid;
    }

    public void setStatusid(int statusid) {
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

    @OneToMany(mappedBy = "status", fetch = FetchType.LAZY)
    public Collection<UsersEntity> getUsers() {
        return users;
    }
    public void setUsers(Collection<UsersEntity> users) {
        this.users = users;
    }

    @OneToOne
    @JoinColumn(name = "priceid", referencedColumnName = "priceid", insertable = false, updatable = false)
    public PricesEntity getPricesByPriceid() {
        return pricesByPriceid;
    }

    public void setPricesByPriceid(PricesEntity pricesByPriceid) {
        this.pricesByPriceid = pricesByPriceid;
    }
}
