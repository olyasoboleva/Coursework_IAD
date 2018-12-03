package entity;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Data
@NoArgsConstructor
@Table(name = "status", schema = "public", catalog = "postgres")
public class Status {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "statusId")
    private Integer statusId;

    @Column(name = "name", length = 40)
    private String name;

    @OneToOne
    @JoinColumn(name = "priceId", referencedColumnName = "priceId", insertable = false, updatable = false)
    private Price price;

    @OneToMany(mappedBy = "status", fetch = FetchType.LAZY)
    private Collection<User> users;

    public Status(String name, Price price) {
        this.name = name;
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Status that = (Status) o;

        if (!Objects.equals(statusId, that.statusId)) return false;
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
