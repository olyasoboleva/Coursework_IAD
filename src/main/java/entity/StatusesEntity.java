package entity;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "statuses", schema = "public", catalog = "postgres")
public class StatusesEntity {
    private int statusId;
    private String name;
    private PricesEntity price;
    private Collection<UsersEntity> users;

    public StatusesEntity() {}

    public StatusesEntity(String name, PricesEntity price) {
        this.name = name;
        this.price = price;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "statusId")
    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
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

        if (statusId != that.statusId) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (statusId ^ (statusId >>> 32));
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
    @JoinColumn(name = "priceId", referencedColumnName = "priceId", insertable = false, updatable = false)
    public PricesEntity getPrice() {
        return price;
    }
    public void setPrice(PricesEntity price) {
        this.price = price;
    }
}
