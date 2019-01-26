package entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name = "presents_to_tribute")
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

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "sender_id", referencedColumnName = "user_id", nullable = false)
    private User sender;

    public PresentsToTribute(Shop product, Tribute tribute, User sender, int quantity) {
        this.product = product;
        this.tribute = tribute;
        this.sender = sender;
        this.quantity = quantity;
    }

}
