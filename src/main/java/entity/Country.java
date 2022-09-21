package entity;

import javax.persistence.*;

@Entity
@Table(name = "countries")
public class Country {
    @Id
    @Column(name = "country_id", nullable = false, length = 2)
    private String id;

    @Column(name = "country_name", length = 40)
    private String countryName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "region_id")
    private Region region;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

}