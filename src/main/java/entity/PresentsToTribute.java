package entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.util.Objects;

@Entity
@Table(name = "presentsToTributes", schema = "public", catalog = "postgres")
public class PresentsToTribute {
    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sendingId")
    private int sendingId;

    @Getter
    @Setter
    @Basic
    @Min(0)
    @Column(name = "quantity")
    private int quantity;

    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name = "productId", referencedColumnName = "productId", nullable = false)
    private Shop product;

    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name = "tributeId", referencedColumnName = "tributeId", nullable = false)
    private Tribute tribute;

    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name = "senderId", referencedColumnName = "userId", nullable = false)
    private User sender;

    public PresentsToTribute() {}

    public PresentsToTribute(Shop product, Tribute tribute, User sender, int quantity) {
        this.product = product;
        this.tribute = tribute;
        this.sender = sender;
        this.quantity = quantity;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PresentsToTribute that = (PresentsToTribute) o;
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
}
