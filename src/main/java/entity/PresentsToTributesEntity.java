package entity;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.util.Objects;

@Entity
@Table(name = "presentstotributes", schema = "public", catalog = "postgres")
public class PresentsToTributesEntity {
    private int sendingId;
    private int quantity;
    //TODO: names
    private ShopEntity shopByProductid;
    private TributesEntity tributesByTributeid;
    private UsersEntity usersBySenderid;

    public PresentsToTributesEntity() {}

    public PresentsToTributesEntity(ShopEntity product, TributesEntity tribute, UsersEntity sender, int quantity) {
        this.shopByProductid = product;
        this.tributesByTributeid = tribute;
        this.usersBySenderid = sender;
        this.quantity = quantity;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sendingId")
    public int getSendingId() {
        return sendingId;
    }

    public void setSendingId(int sendingId) {
        this.sendingId = sendingId;
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
        PresentsToTributesEntity that = (PresentsToTributesEntity) o;
        return sendingId == that.sendingId &&
                quantity == that.quantity &&
                Objects.equals(shopByProductid, that.shopByProductid) &&
                Objects.equals(tributesByTributeid, that.tributesByTributeid) &&
                Objects.equals(usersBySenderid, that.usersBySenderid);
    }

    @Override
    public int hashCode() {

        return Objects.hash(sendingId, quantity, shopByProductid, tributesByTributeid, usersBySenderid);
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
