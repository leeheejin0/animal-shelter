package com.leeheejin.pms.domain;

import java.sql.Date;
import com.leeheejin.util.CsvObject;

public class Dog implements CsvObject {
  private int ids;
  private String photos;
  private String breeds;
  private String genders;
  private int ages;
  private Date dates;
  private String places;
  private String status;

  @Override
  public String toCsvString() {
    return String.format("%d,%s,%s,%s,%d,%s,%s,%s",
        this.getIds(),
        this.getPhotos(),
        this.getBreeds(),
        this.getGenders(),
        this.getAges(),
        this.getDates().toString(),
        this.getPlaces(),
        this.getStatus());
  }

  public static Dog valueOfCsv(String csv) {
    String[] fields = csv.split(",");
    Dog d = new Dog();
    d.setIds(Integer.parseInt(fields[0]));
    d.setPhotos(fields[1]);
    d.setBreeds(fields[2]);
    d.setGenders(fields[3]);
    d.setAges(Integer.parseInt(fields[4]));
    d.setDates(Date.valueOf(fields[5]));
    d.setPlaces(fields[6]);
    d.setStatus(fields[7]);
    return d;
  }

  public int getIds() {
    return ids;
  }
  public void setIds(int ids) {
    this.ids = ids;
  }
  public String getPhotos() {
    return photos;
  }
  public void setPhotos(String photos) {
    this.photos = photos;
  }
  public String getBreeds() {
    return breeds;
  }
  public void setBreeds(String breeds) {
    this.breeds = breeds;
  }
  public String getGenders() {
    return genders;
  }
  public void setGenders(String genders) {
    this.genders = genders;
  }
  public int getAges() {
    return ages;
  }
  public void setAges(int ages) {
    this.ages = ages;
  }
  public Date getDates() {
    return dates;
  }
  public void setDates(Date dates) {
    this.dates = dates;
  }
  public String getPlaces() {
    return places;
  }
  public void setPlaces(String places) {
    this.places = places;
  }
  public String getStatus() {
    return status;
  }
  public void setStatus(String status) {
    this.status = status;
  }
}
