package entity;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "locations", schema = "public", catalog = "postgres")
public class LocationsEntity {
    private int locationId;
    private String name;
    private byte[] picture;

    private Collection<ShopEntity> products;
    private Collection<ArenasEntity> arenas;
    private Collection<HooksEntity> hooks;

    public LocationsEntity(){ }

    public LocationsEntity(String name, byte[] picture){
        this.name = name;
        this.picture = picture;
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
    @Column(name = "picture")
    public byte[] getPicture() {
        return picture;
    }

    public void setPicture(byte[] picture) {
        this.picture = picture;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LocationsEntity that = (LocationsEntity) o;
        return locationId == that.locationId &&
                Objects.equals(name, that.name) &&
                Objects.equals(picture, that.picture);
    }

    @Override
    public int hashCode() {
        return Objects.hash(locationId, name, picture);
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
