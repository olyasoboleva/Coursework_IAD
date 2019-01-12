package entity;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
@Data
@NoArgsConstructor
@Table(name = "products_and_location")
public class ProductsAndLocation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "applying_id")
    private Integer applyingId;

    @ManyToOne
    @JoinColumn(name = "location_id", referencedColumnName = "location_id", nullable = false)
    private Location location;

    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "product_id", nullable = false)
    private Shop product;

    public ProductsAndLocation(Shop product, Location location) {
        setLocation(location);
        setProduct(product);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProductsAndLocation that = (ProductsAndLocation) o;

        if (!Objects.equals(applyingId, that.applyingId)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = applyingId;
        result = 31 * result + (location != null ? location.hashCode() : 0);
        return result;
    }

}
