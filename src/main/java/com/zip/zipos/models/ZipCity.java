package com.zip.zipos.models;

import javax.persistence.*;

@Entity
@Table(name = "zipcity")
public class ZipCity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;

    @Column(name = "zipcode")
    private long zipcode;

    @Column(name = "city")
    private String city;

    public ZipCity() {
    }

    public ZipCity(long zipcode, String city) {
        this.zipcode = zipcode;
        this.city = city;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getZipcode() {
        return zipcode;
    }

    public void setZipcode(long zipcode) {
        this.zipcode = zipcode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return "ZipCity{" +
                "zipcode=" + zipcode +
                ", city='" + city + '\'' +
                '}';
    }
}
