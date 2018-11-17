package entity;

import javax.persistence.*;

@Entity
@Table(name = "productsandlocation", schema = "public", catalog = "s243887")
public class ProductsandlocationEntity {
    private int applyingid;
    private int productid;
    private String typeoflocation;
    private ShopEntity shopByProductid;

    @Id
    @Column(name = "applyingid")
    public int getApplyingid() {
        return applyingid;
    }

    public void setApplyingid(int applyingid) {
        this.applyingid = applyingid;
    }

    @Basic
    @Column(name = "productid")
    public int getProductid() {
        return productid;
    }

    public void setProductid(int productid) {
        this.productid = productid;
    }

    @Basic
    @Column(name = "typeoflocation")
    public String getTypeoflocation() {
        return typeoflocation;
    }

    public void setTypeoflocation(String typeoflocation) {
        this.typeoflocation = typeoflocation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProductsandlocationEntity that = (ProductsandlocationEntity) o;

        if (applyingid != that.applyingid) return false;
        if (productid != that.productid) return false;
        if (typeoflocation != null ? !typeoflocation.equals(that.typeoflocation) : that.typeoflocation != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = applyingid;
        result = 31 * result + productid;
        result = 31 * result + (typeoflocation != null ? typeoflocation.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "productid", referencedColumnName = "productid", nullable = false)
    public ShopEntity getShopByProductid() {
        return shopByProductid;
    }

    public void setShopByProductid(ShopEntity shopByProductid) {
        this.shopByProductid = shopByProductid;
    }
}
