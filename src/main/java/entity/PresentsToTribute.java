package entity;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.util.Objects;

@Entity
@Data
@NoArgsConstructor
@Table(name = "presents_to_tribute", schema = "public", catalog = "postgres")
public class PresentsToTribute {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sending_id")
    private Integer sendingId;

    @Min(0)
    @Column(name = "quantity")
    private int quantity;

    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "product_id", nullable = false)
    private Shop product;

    @ManyToOne
    @JoinColumn(name = "tribute_id", referencedColumnName = "tribute_id", nullable = false)
    private Tribute tribute;

    @ManyToOne
    @JoinColumn(name = "sender_id", referencedColumnName = "user_id", nullable = false)
    private User sender;

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
        return Objects.equals(sendingId, that.sendingId) &&
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
