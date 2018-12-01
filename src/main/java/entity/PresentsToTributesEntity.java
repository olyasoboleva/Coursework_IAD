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
    private ShopEntity product;
    private TributesEntity tribute;
    private UsersEntity sender;

    public PresentsToTributesEntity() {}

    public PresentsToTributesEntity(ShopEntity product, TributesEntity tribute, UsersEntity sender, int quantity) {
        this.product = product;
        this.tribute = tribute;
        this.sender = sender;
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
                Objects.equals(product, that.product) &&
                Objects.equals(tribute, that.tribute) &&
                Objects.equals(sender, that.sender);
    }

    @Override
    public int hashCode() {

        return Objects.hash(sendingId, quantity, product, tribute, sender);
    }

    @ManyToOne
    @JoinColumn(name = "productid", referencedColumnName = "productid", nullable = false)
    public ShopEntity getProduct() {
        return product;
    }

    public void setProduct(ShopEntity shopByProductid) {
        this.product = shopByProductid;
    }

    @ManyToOne
    @JoinColumn(name = "tributeid", referencedColumnName = "tributeid", nullable = false)
    public TributesEntity getTribute() {
        return tribute;
    }

    public void setTribute(TributesEntity tributesByTributeid) {
        this.tribute = tributesByTributeid;
    }

    @ManyToOne
    @JoinColumn(name = "senderid", referencedColumnName = "userid", nullable = false)
    public UsersEntity getSender() {
        return sender;
    }

    public void setSender(UsersEntity usersBySenderid) {
        this.sender = usersBySenderid;
    }
}
