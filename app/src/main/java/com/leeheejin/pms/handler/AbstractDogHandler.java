package com.leeheejin.pms.handler;

import java.util.List;
import com.leeheejin.pms.domain.Dog;

public abstract class AbstractDogHandler implements Command {

  protected List<Dog> dogList;

  public AbstractDogHandler(List<Dog> dogList) {
    this.dogList = dogList;
  }

  @Override
  public void service() {}

  @Override
  public void service(int no) {}

  @Override
  public void service(String str1, String str2) {}

  protected void print() {
    Object[] list = dogList.toArray();

    for (Object obj : list) {
      Dog d = (Dog) obj;
      System.out.printf("  [%d] %s   %s/%s/%d살   ", 
          d.getIds(), d.getPhotos(), d.getBreeds(), d.getGenders(), d.getAges());
      System.out.printf("%s, %s, %s\n", d.getDates(), d.getPlaces(), d.getStatus());
    }
  }

  protected void print(int printNo) {
    Object obj = dogList.get(printNo);
    Dog d = (Dog) obj;
    System.out.printf("  [%d] %s   %s/%s/%d살   ", 
        d.getIds(), d.getPhotos(), d.getBreeds(), d.getGenders(), d.getAges());
    System.out.printf("%s, %s, %s\n", d.getDates(), d.getPlaces(), d.getStatus());
  }

  protected Dog findByNo(int dogNo) {
    Dog [] list = dogList.toArray(new Dog[0]);
    for (Dog d : list) {
      if (d.getIds() == dogNo) {
        return d;
      }
    }
    return null;
  }

}
