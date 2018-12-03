package entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "productsAndLocation", schema = "public", catalog = "postgres")
public class ProductsAndLocation {
    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "applyingId")
    private int applyingId;

    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name = "locationId", referencedColumnName = "locationId", nullable = false)
    private Location location;

    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name = "productId", referencedColumnName = "productId", nullable = false)
    private Shop product;

    public ProductsAndLocation() {}

    public ProductsAndLocation(Shop product, Location location) {
        setLocation(location);
        setProduct(product);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProductsAndLocation that = (ProductsAndLocation) o;

        if (applyingId != that.applyingId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = applyingId;
        result = 31 * result + (location != null ? location.hashCode() : 0);
        return result;
    }

}
