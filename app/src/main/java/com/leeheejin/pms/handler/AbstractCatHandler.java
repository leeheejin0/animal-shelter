package com.leeheejin.pms.handler;

import java.util.List;
import com.leeheejin.pms.domain.Cat;

public abstract class AbstractCatHandler implements Command {

  protected List<Cat> catList;

  public AbstractCatHandler(List<Cat> catList) {
    this.catList = catList;
  }

  @Override
  public void service() {}

  @Override
  public void service(int no) {}

  @Override
  public void service(String str) {}

  @Override
  public void service(String str1, String str2) {}

  protected void print() {
    Object[] list = catList.toArray();

    for (Object obj : list) {
      Cat c = (Cat) obj;

      System.out.printf("  [%d] %s   %s/%s/%d살   ", 
          c.getIds(), c.getPhotos(), c.getBreeds(), c.getGenders(), c.getAges());
      System.out.printf("%s, %s, %s\n", c.getDates(), c.getPlaces(), c.getStatus());
    }
  }

  protected void print(int printNo) {
    Object obj = catList.get(printNo);
    Cat c = (Cat) obj;
    System.out.printf("  [%d] %s   %s/%s/%d살   ", 
        c.getIds(), c.getPhotos(), c.getBreeds(), c.getGenders(), c.getAges());
    System.out.printf("%s, %s, %s\n", c.getDates(), c.getPlaces(), c.getStatus());
  }

  protected Cat findByNo(int catNo) {
    Cat [] list = catList.toArray(new Cat[0]);
    for (Cat c : list) {
      if (c.getIds() == catNo) {
        return c;
      }
    }
    return null;
  }
}
