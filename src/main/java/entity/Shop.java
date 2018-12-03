package entity;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.Objects;

@Entity
@Data
@NoArgsConstructor
@Table(name = "shop", schema = "public", catalog = "postgres")
public class Shop {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "productId")
    private Integer productId;

    @NotNull
    @Column(name = "name", length = 64)
    private String name;

    @NotNull
    @Min(0)
    @Column(name = "cost")
    private int cost;

    @NotNull
    @Column(name = "typeOfPresent", length = 40)
    private String typeOfPresent;

    @Column(name = "description")
    private String description;

    @Min(0)
    @Max(100)
    @Column(name = "healthRecovery")
    private int healthRecovery;

    @Column(name = "picture")
    private byte[] picture;

    @ManyToMany(mappedBy = "productsOfTribute", fetch = FetchType.LAZY)
    private Collection<Tribute> productOwners;

    @ManyToMany(mappedBy = "sendings", fetch = FetchType.LAZY)
    private Collection<User> senders;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "productsAndLocation",
            joinColumns = {@JoinColumn(name = "productId")},
            inverseJoinColumns = {@JoinColumn(name = "locationId")}
    )
    private Collection<Location> locations;

    public Shop(String name, int cost, String typeOfPresent, String description, int healthRecovery, byte[] picture) {
        this.name = name;
        this.cost = cost;
        this.typeOfPresent = typeOfPresent;
        this.description = description;
        this.healthRecovery = healthRecovery;
        this.picture = picture;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Shop that = (Shop) o;
        return Objects.equals(productId, that.productId) &&
                cost == that.cost &&
                healthRecovery == that.healthRecovery &&
                Objects.equals(name, that.name) &&
                Objects.equals(typeOfPresent, that.typeOfPresent) &&
                Objects.equals(description, that.description) &&
                Objects.equals(productOwners, that.productOwners) &&
                Objects.equals(locations, that.locations) &&
                Objects.equals(picture, that.picture);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId, name, cost, typeOfPresent, description, healthRecovery, productOwners, locations, picture);
    }

}
