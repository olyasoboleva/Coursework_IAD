package entity;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;

@Entity
@Table(name = "shop", schema = "public", catalog = "postgres")
public class ShopEntity {
    private int productid;
    private String name;
    private int cost;
    private String typeofpresent;
    private String description;
    private int healthrecovery;
    private Collection<TributesEntity> productOwners;
    private Collection<LocationsEntity> locations;
    private String picturePath;

    public ShopEntity() { }

    public ShopEntity(String name, int cost, String typeofpresent, String description, int healthrecovery, String picture) {
        this.name = name;
        this.cost = cost;
        this.typeofpresent = typeofpresent;
        this.description = description;
        this.healthrecovery = healthrecovery;
        this.picturePath = picture;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "productid")
    public int getProductid() {
        return productid;
    }

    public void setProductid(int productid) {
        this.productid = productid;
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
    @Column(name = "typeofpresent", length = 40)
    public String getTypeofpresent() {
        return typeofpresent;
    }

    public void setTypeofpresent(String typeofpresent) {
        this.typeofpresent = typeofpresent;
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
    @Column(name = "picturepath")
    public String getPicturePath() {
        return picturePath;
    }

    public void setPicturePath(String picturePath) {
        this.picturePath = picturePath;
    }


    @Basic
    @Min(0)
    @Max(100)
    @Column(name = "healthrecovery")
    public int getHealthrecovery() {
        return healthrecovery;
    }

    public void setHealthrecovery(int healthrecovery) {
        this.healthrecovery = healthrecovery;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ShopEntity that = (ShopEntity) o;
        return productid == that.productid &&
                cost == that.cost &&
                healthrecovery == that.healthrecovery &&
                Objects.equals(name, that.name) &&
                Objects.equals(typeofpresent, that.typeofpresent) &&
                Objects.equals(description, that.description) &&
                Objects.equals(productOwners, that.productOwners) &&
                Objects.equals(locations, that.locations) &&
                Objects.equals(picturePath, that.picturePath);
    }

    @Override
    public int hashCode() {

        return Objects.hash(productid, name, cost, typeofpresent, description, healthrecovery, productOwners, locations, picturePath);
    }

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "productsandlocation",
            joinColumns = {@JoinColumn(name = "productid")},
            inverseJoinColumns = {@JoinColumn(name = "locationid")}
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
