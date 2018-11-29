package entity;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.HashSet;

@Entity
@Table(name = "shop", schema = "public", catalog = "postgres")
public class ShopEntity {
    private int productid;
    private String name;
    private short cost;
    private String typeofpresent;
    private String description;
    private Short healthrecovery;
    private Collection<PresentstotributesEntity> presentstotributesByProductid;
    private Collection<LocationsEntity> locations;
    private String picturePath;

    public ShopEntity() { }

    public ShopEntity(String name, short cost, String typeofpresent, String description, Short healthrecovery, String picture) {
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
    public short getCost() {
        return cost;
    }

    public void setCost(short cost) {
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
    public Short getHealthrecovery() {
        return healthrecovery;
    }

    public void setHealthrecovery(Short healthrecovery) {
        this.healthrecovery = healthrecovery;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ShopEntity that = (ShopEntity) o;

        if (productid != that.productid) return false;
        if (cost != that.cost) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (typeofpresent != null ? !typeofpresent.equals(that.typeofpresent) : that.typeofpresent != null)
            return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        if (healthrecovery != null ? !healthrecovery.equals(that.healthrecovery) : that.healthrecovery != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = productid;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (int) cost;
        result = 31 * result + (typeofpresent != null ? typeofpresent.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (healthrecovery != null ? healthrecovery.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "shopByProductid")
    public Collection<PresentstotributesEntity> getPresentstotributesByProductid() {
        return presentstotributesByProductid;
    }

    public void setPresentstotributesByProductid(Collection<PresentstotributesEntity> presentstotributesByProductid) {
        this.presentstotributesByProductid = presentstotributesByProductid;
    }

    @ManyToMany
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
}
