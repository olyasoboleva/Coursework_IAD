package entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "productsandlocation", schema = "public", catalog = "postgres")
public class ProductsandlocationEntity {
    private int applyingid;
    //TODO: добавить сущность с локациями и их отображениями на карте
    private String typeoflocation;
    private ShopEntity shopByProductid;

    public ProductsandlocationEntity() {}

    public ProductsandlocationEntity(int productid, String typeoflocation) {
        this.typeoflocation = typeoflocation;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "applyingid")
    public int getApplyingid() {
        return applyingid;
    }

    public void setApplyingid(int applyingid) {
        this.applyingid = applyingid;
    }

    @Basic
    @NotNull
    @Column(name = "typeoflocation", length = 40)
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
        if (typeoflocation != null ? !typeoflocation.equals(that.typeoflocation) : that.typeoflocation != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = applyingid;
        result = 31 * result + (typeoflocation != null ? typeoflocation.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "productid", referencedColumnName = "productid", nullable = false, insertable = false, updatable = false)
    public ShopEntity getShopByProductid() {
        return shopByProductid;
    }

    public void setShopByProductid(ShopEntity shopByProductid) {
        this.shopByProductid = shopByProductid;
    }
}
