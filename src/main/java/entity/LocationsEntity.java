package entity;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;

@Entity
@Table(name = "locations", schema = "public", catalog = "postgres")
public class LocationsEntity {
    private int locationid;
    private String name;
    private String picturepath;

    private Collection<ShopEntity> products;
    private Collection<ArenasEntity> arenas;

    public LocationsEntity(){ }

    public LocationsEntity(String name, String picturepath){
        this.name = name;
        this.picturepath = picturepath;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "locationid")
    public int getLocationid() {
        return locationid;
    }

    public void setLocationid(int locationid) {
        this.locationid = locationid;
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
    @Column(name = "picturepath")
    public String getPicturepath() {
        return picturepath;
    }

    public void setPicturepath(String picturepath) {
        this.picturepath = picturepath;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LocationsEntity that = (LocationsEntity) o;
        return locationid == that.locationid &&
                Objects.equals(name, that.name) &&
                Objects.equals(picturepath, that.picturepath);
    }

    @Override
    public int hashCode() {
        return Objects.hash(locationid, name, picturepath);
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
}
