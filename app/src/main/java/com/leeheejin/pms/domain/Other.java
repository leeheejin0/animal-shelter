package com.leeheejin.pms.domain;

import java.sql.Date;
import com.leeheejin.util.CsvObject;

public class Other implements CsvObject {
  private int ids;
  private String species;
  private String photos;
  private String breeds;
  private String genders;
  private int ages;
  private Date dates;
  private String places;
  private String status;

  @Override
  public String toCsvString() {
    return String.format("%d,%s,%s,%s,%s,%d,%s,%s,%s",
        this.getIds(),
        this.getSpecies(),
        this.getPhotos(),
        this.getBreeds(),
        this.getGenders(),
        this.getAges(),
        this.getDates().toString(),
        this.getPlaces(),
        this.getStatus());
  }

  public static Other valueOfCsv(String csv) {
    String[] fields = csv.split(",");
    Other o = new Other();
    o.setIds(Integer.parseInt(fields[0]));
    o.setSpecies(fields[1]);
    o.setPhotos(fields[2]);
    o.setBreeds(fields[3]);
    o.setGenders(fields[4]);
    o.setAges(Integer.parseInt(fields[5]));
    o.setDates(Date.valueOf(fields[6]));
    o.setPlaces(fields[7]);
    o.setStatus(fields[8]);
    return o;
  }

  public int getIds() {
    return ids;
  }
  public void setIds(int ids) {
    this.ids = ids;
  }
  public String getSpecies() {
    return species;
  }
  public void setSpecies(String species) {
    this.species = species;
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
