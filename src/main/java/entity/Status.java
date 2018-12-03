package entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "statuses", schema = "public", catalog = "postgres")
public class Status {
    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "statusId")
    private int statusId;

    @Getter
    @Setter
    @Basic
    @Column(name = "name", length = 40)
    private String name;

    @Getter
    @Setter
    @OneToOne
    @JoinColumn(name = "priceId", referencedColumnName = "priceId", insertable = false, updatable = false)
    private Price price;

    @Getter
    @Setter
    @OneToMany(mappedBy = "status", fetch = FetchType.LAZY)
    private Collection<User> users;

    public Status() {}

    public Status(String name, Price price) {
        this.name = name;
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Status that = (Status) o;

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

}
