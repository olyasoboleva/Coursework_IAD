package entity;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "presentstotributes", schema = "public", catalog = "postgres")
public class PresentstotributesEntity {
    private long sendingid;
    private Short quantity;
    private ShopEntity shopByProductid;
    private TributesEntity tributesByTributeid;
    private UsersEntity usersBySenderid;

    public PresentstotributesEntity() {}

    public PresentstotributesEntity(int productid, long tributeid, int senderid, Short quantity) {
        this.quantity = quantity;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "sendingid")
    public long getSendingid() {
        return sendingid;
    }

    public void setSendingid(long sendingid) {
        this.sendingid = sendingid;
    }

    @Basic
    @Min(0)
    @Column(name = "quantity")
    public Short getQuantity() {
        return quantity;
    }

    public void setQuantity(Short quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PresentstotributesEntity that = (PresentstotributesEntity) o;

        if (sendingid != that.sendingid) return false;
        if (quantity != null ? !quantity.equals(that.quantity) : that.quantity != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (sendingid ^ (sendingid >>> 32));
        result = 31 * result + (quantity != null ? quantity.hashCode() : 0);
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
