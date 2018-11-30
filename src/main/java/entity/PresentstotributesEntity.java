package entity;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
@Table(name = "presentstotributes", schema = "public", catalog = "postgres")
public class PresentstotributesEntity {
    private int sendingid;
    private int quantity;
    private ShopEntity shopByProductid;
    private TributesEntity tributesByTributeid;
    private UsersEntity usersBySenderid;

    public PresentstotributesEntity() {}

    public PresentstotributesEntity(ShopEntity product, TributesEntity tribute, UsersEntity sender, int quantity) {
        this.shopByProductid = product;
        this.tributesByTributeid = tribute;
        this.usersBySenderid = sender;
        this.quantity = quantity;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sendingid")
    public int getSendingid() {
        return sendingid;
    }

    public void setSendingid(int sendingid) {
        this.sendingid = sendingid;
    }

    @Basic
    @Min(0)
    @Column(name = "quantity")
    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PresentstotributesEntity that = (PresentstotributesEntity) o;
        return sendingid == that.sendingid &&
                quantity == that.quantity &&
                Objects.equals(shopByProductid, that.shopByProductid) &&
                Objects.equals(tributesByTributeid, that.tributesByTributeid) &&
                Objects.equals(usersBySenderid, that.usersBySenderid);
    }

    @Override
    public int hashCode() {

        return Objects.hash(sendingid, quantity, shopByProductid, tributesByTributeid, usersBySenderid);
    }

    @ManyToOne
    @JoinColumn(name = "productid", referencedColumnName = "productid", nullable = false, insertable = false, updatable = false)
    public ShopEntity getShopByProductid() {
        return shopByProductid;
    }

    public void setShopByProductid(ShopEntity shopByProductid) {
        this.shopByProductid = shopByProductid;
    }

    @ManyToOne
    @JoinColumn(name = "tributeid", referencedColumnName = "tributeid", nullable = false, insertable = false, updatable = false)
    public TributesEntity getTributesByTributeid() {
        return tributesByTributeid;
    }

    public void setTributesByTributeid(TributesEntity tributesByTributeid) {
        this.tributesByTributeid = tributesByTributeid;
    }

    @ManyToOne
    @JoinColumn(name = "senderid", referencedColumnName = "userid", nullable = false, insertable = false, updatable = false)
    public UsersEntity getUsersBySenderid() {
        return usersBySenderid;
    }

    public void setUsersBySenderid(UsersEntity usersBySenderid) {
        this.usersBySenderid = usersBySenderid;
    }
}
