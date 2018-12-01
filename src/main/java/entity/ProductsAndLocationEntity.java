package entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "productsAndLocation", schema = "public", catalog = "postgres")
public class ProductsAndLocationEntity {
    private int applyingId;
    private int locationId;
    private int productId;
    private LocationsEntity location;
    private ShopEntity product;

    public ProductsAndLocationEntity() {}

    public ProductsAndLocationEntity(ShopEntity product, LocationsEntity location) {
        setLocation(location);
        setProduct(product);
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "applyingId")
    public int getApplyingId() {
        return applyingId;
    }

    public void setApplyingId(int applyingId) {
        this.applyingId = applyingId;
    }

    @Basic
    @NotNull
    @Column(name = "locationId")
    public int getLocationId() {
        return locationId;
    }

    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }

    @Basic
    @NotNull
    @Column(name = "productId")
    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProductsAndLocationEntity that = (ProductsAndLocationEntity) o;

        if (applyingId != that.applyingId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = applyingId;
        result = 31 * result + (location != null ? location.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "productId", referencedColumnName = "productId", nullable = false, insertable = false, updatable = false)
    public ShopEntity getProduct() {
        return product;
    }

    public void setProduct(ShopEntity shopByProductid) {
        this.product = shopByProductid;
        setProductId(shopByProductid.getProductId());
    }


    @ManyToOne
    @JoinColumn(name = "locationId", referencedColumnName = "locationId", nullable = false, insertable = false, updatable = false)
    public LocationsEntity getLocation() {
        return location;
    }

    public void setLocation(LocationsEntity location) {
        this.location = location;
        setLocationId(location.getLocationId());
    }
}
