package entity;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "shop", schema = "public", catalog = "postgres")
public class ShopEntity {
    private int productId;
    private String name;
    private int cost;
    private String typeOfPresent;
    private String description;
    private int healthRecovery;
    private Collection<TributesEntity> productOwners;
    private Collection<LocationsEntity> locations;
    private String picturePath;

    public ShopEntity() { }

    public ShopEntity(String name, int cost, String typeOfPresent, String description, int healthRecovery, String picture) {
        this.name = name;
        this.cost = cost;
        this.typeOfPresent = typeOfPresent;
        this.description = description;
        this.healthRecovery = healthRecovery;
        this.picturePath = picture;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "productId")
    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    @Basic
    @NotNull
    @Column(name = "name", length = 64)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @NotNull
    @Min(0)
    @Column(name = "cost")
    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    @Basic
    @NotNull
    @Column(name = "typeOfPresent", length = 40)
    public String getTypeOfPresent() {
        return typeOfPresent;
    }

    public void setTypeOfPresent(String typeOfPresent) {
        this.typeOfPresent = typeOfPresent;
    }

    @Basic
    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "picturePath")
    public String getPicturePath() {
        return picturePath;
    }

    public void setPicturePath(String picturePath) {
        this.picturePath = picturePath;
    }


    @Basic
    @Min(0)
    @Max(100)
    @Column(name = "healthRecovery")
    public int getHealthRecovery() {
        return healthRecovery;
    }

    public void setHealthRecovery(int healthRecovery) {
        this.healthRecovery = healthRecovery;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ShopEntity that = (ShopEntity) o;
        return productId == that.productId &&
                cost == that.cost &&
                healthRecovery == that.healthRecovery &&
                Objects.equals(name, that.name) &&
                Objects.equals(typeOfPresent, that.typeOfPresent) &&
                Objects.equals(description, that.description) &&
                Objects.equals(productOwners, that.productOwners) &&
                Objects.equals(locations, that.locations) &&
                Objects.equals(picturePath, that.picturePath);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId, name, cost, typeOfPresent, description, healthRecovery, productOwners, locations, picturePath);
    }

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "productsAndLocation",
            joinColumns = {@JoinColumn(name = "productId")},
            inverseJoinColumns = {@JoinColumn(name = "locationId")}
    )
    public Collection<LocationsEntity> getLocations() {
        return locations;
    }
    public void setLocations(Collection<LocationsEntity> locations) {
        this.locations = locations;
    }

    @ManyToMany(mappedBy = "productsOfTribute", fetch = FetchType.LAZY)
    public Collection<TributesEntity> getProductOwners() {
        return productOwners;
    }
    public void setProductOwners(Collection<TributesEntity> productOwners) {
        this.productOwners = productOwners;
    }
}
