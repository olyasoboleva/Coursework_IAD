package entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "prices", schema = "public", catalog = "postgres")
public class Price {
    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "priceId")
    private int priceId;

    @Getter
    @Setter
    @Basic
    @NotNull
    @Column(name = "name", length = 64)
    private String name;

    @Getter
    @Setter
    @Basic
    @Min(0)
    @Column(name = "cost")
    private Integer cost;

    @Getter
    @Setter
    @OneToOne(mappedBy = "price")
    private Status status;

    public Price() { }

    public Price(String name, Integer cost) {
        this.name = name;
        this.cost = cost;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Price that = (Price) o;

        if (priceId != that.priceId) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (cost != null ? !cost.equals(that.cost) : that.cost != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = priceId;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (cost != null ? cost.hashCode() : 0);
        return result;
    }
}
