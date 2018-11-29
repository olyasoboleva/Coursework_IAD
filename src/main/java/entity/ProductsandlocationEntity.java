package entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "productsandlocation", schema = "public", catalog = "postgres")
public class ProductsandlocationEntity {
    private int applyingid;
    private int locationid;
    private int productid;
    private LocationsEntity location;
    private ShopEntity shopByProductid;

    public ProductsandlocationEntity() {}

    public ProductsandlocationEntity(ShopEntity product, LocationsEntity location) {
        setLocation(location);
        setShopByProductid(product);
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "applyingid")
    public int getApplyingid() {
        return applyingid;
    }

    public void setApplyingid(int applyingid) {
        this.applyingid = applyingid;
    }

    @Basic
    @NotNull
    @Column(name = "locationid")
    public int getLocationid() {
        return locationid;
    }

    public void setLocationid(int locationid) {
        this.locationid = locationid;
    }

    @Basic
    @NotNull
    @Column(name = "productid")
    public int getProductid() {
        return productid;
    }

    public void setProductid(int productid) {
        this.productid = productid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProductsandlocationEntity that = (ProductsandlocationEntity) o;

        if (applyingid != that.applyingid) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = applyingid;
        result = 31 * result + (location != null ? location.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "productid", referencedColumnName = "productid", nullable = false, insertable = false, updatable = false)
    public ShopEntity getShopByProductid() {
        return shopByProductid;
    }

    public void setShopByProductid(ShopEntity shopByProductid) {
        this.shopByProductid = shopByProductid;
        setProductid(shopByProductid.getProductid());
    }


    @ManyToOne
    @JoinColumn(name = "locationid", referencedColumnName = "locationid", nullable = false, insertable = false, updatable = false)
    public LocationsEntity getLocation() {
        return location;
    }

    public void setLocation(LocationsEntity location) {
        this.location = location;
        setLocationid(location.getLocationid());
    }
}
