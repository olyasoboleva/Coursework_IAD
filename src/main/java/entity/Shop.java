package entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Arrays;
import java.util.Collection;
import java.util.Objects;

@Entity
@Data
@NoArgsConstructor
@Table(name = "shop")
public class Shop {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Integer productId;

    @NotNull
    @Column(name = "name", length = 64)
    private String name;

    @NotNull
    @Min(0)
    @Column(name = "cost")
    private int cost;

    @NotNull
    @Column(name = "type_of_present", length = 40)
    private String typeOfPresent;

    @NotNull
    @Column(name = "type_of_recovery", length = 40)
    private String typeOfRecovery;

    @Column(name = "description")
    private String description;

    @Min(0)
    @Max(100)
    @Column(name = "health_recovery")
    private int healthRecovery;

    @Column(name = "picture")
    private byte[] picture;

    @JsonIgnore
    @ManyToMany(mappedBy = "productsOfTribute", fetch = FetchType.LAZY)
    private Collection<Tribute> productOwners;


    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "products_and_location",
            joinColumns = {@JoinColumn(name = "product_id")},
            inverseJoinColumns = {@JoinColumn(name = "location_id")}
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

}
