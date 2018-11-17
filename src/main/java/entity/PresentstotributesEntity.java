package entity;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "presentstotributes", schema = "public", catalog = "postgres")
public class PresentstotributesEntity {
    private long sendingid;
    private int productid;
    private long tributeid;
    private int senderid;
    private Short quantity;
    private ShopEntity shopByProductid;
    private TributesEntity tributesByTributeid;
    private UsersEntity usersBySenderid;

    public PresentstotributesEntity() {}

    public PresentstotributesEntity(int productid, long tributeid, int senderid, Short quantity) {
        this.productid = productid;
        this.tributeid = tributeid;
        this.senderid = senderid;
        this.quantity = quantity;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sendingid")
    public long getSendingid() {
        return sendingid;
    }

    public void setSendingid(long sendingid) {
        this.sendingid = sendingid;
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

    @Basic
    @NotNull
    @Column(name = "tributeid")
    public long getTributeid() {
        return tributeid;
    }

    public void setTributeid(long tributeid) {
        this.tributeid = tributeid;
    }

    @Basic
    @NotNull
    @Column(name = "senderid")
    public int getSenderid() {
        return senderid;
    }

    public void setSenderid(int senderid) {
        this.senderid = senderid;
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
        if (productid != that.productid) return false;
        if (tributeid != that.tributeid) return false;
        if (senderid != that.senderid) return false;
        if (quantity != null ? !quantity.equals(that.quantity) : that.quantity != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (sendingid ^ (sendingid >>> 32));
        result = 31 * result + productid;
        result = 31 * result + (int) (tributeid ^ (tributeid >>> 32));
        result = 31 * result + senderid;
        result = 31 * result + (quantity != null ? quantity.hashCode() : 0);
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

    @ManyToOne
    @JoinColumn(name = "tributeid", referencedColumnName = "tributeid", nullable = false)
    public TributesEntity getTributesByTributeid() {
        return tributesByTributeid;
    }

    public void setTributesByTributeid(TributesEntity tributesByTributeid) {
        this.tributesByTributeid = tributesByTributeid;
    }

    @ManyToOne
    @JoinColumn(name = "senderid", referencedColumnName = "userid", nullable = false)
    public UsersEntity getUsersBySenderid() {
        return usersBySenderid;
    }

    public void setUsersBySenderid(UsersEntity usersBySenderid) {
        this.usersBySenderid = usersBySenderid;
    }
}
