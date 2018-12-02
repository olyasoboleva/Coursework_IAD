package entity;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "locations", schema = "public", catalog = "postgres")
public class LocationsEntity {
    private int locationId;
    private String name;
    private String picturePath;

    private Collection<ShopEntity> products;
    private Collection<ArenasEntity> arenas;
    private Collection<HooksEntity> hooks;

    public LocationsEntity(){ }

    public LocationsEntity(String name, String picturepath){
        this.name = name;
        this.picturePath = picturepath;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "locationId")
    public int getLocationId() {
        return locationId;
    }

    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "picturePath")
    public String getPicturepath() {
        return picturePath;
    }

    public void setPicturepath(String picturepath) {
        this.picturePath = picturepath;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LocationsEntity that = (LocationsEntity) o;
        return locationId == that.locationId &&
                Objects.equals(name, that.name) &&
                Objects.equals(picturePath, that.picturePath);
    }

    @Override
    public int hashCode() {
        return Objects.hash(locationId, name, picturePath);
    }


    @ManyToMany(mappedBy = "locations", fetch = FetchType.LAZY)
    public Collection<ShopEntity> getProducts() {
        return products;
    }

    public void setProducts(Collection<ShopEntity> products) {
        this.products = products;
    }

    @OneToMany(mappedBy = "mainLocation", fetch = FetchType.LAZY)
    public Collection<ArenasEntity> getArenas() {
        return arenas;
    }

    public void setArenas(Collection<ArenasEntity> arenas) {
        this.arenas = arenas;
    }

    @OneToMany(mappedBy = "location", fetch = FetchType.LAZY)
    public Collection<HooksEntity> getHooks() {
        return hooks;
    }

    public void setHooks(Collection<HooksEntity> hooks) {
        this.hooks = hooks;
    }
}
