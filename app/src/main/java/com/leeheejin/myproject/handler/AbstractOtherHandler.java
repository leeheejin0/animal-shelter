package com.leeheejin.myproject.handler;

import java.util.List;
import com.leeheejin.myproject.domain.Other;

public abstract class AbstractOtherHandler {

  protected List<Other> otherList;

  public AbstractOtherHandler(List<Other> otherList) {
    this.otherList = otherList;
  }

  protected void print() {
    Object[] list = otherList.toArray();

    for (Object obj : list) {
      Other o = (Other) obj;
      System.out.printf("  [%d] %s / %s   ", o.getIds(), o.getSpecies(), o.getPhotos());
      System.out.printf("%s/%s/%d살   ", o.getBreeds(), o.getGenders(), o.getAges());
      System.out.printf("%s, %s, %s\n", o.getDates(), o.getPlaces(), o.getStatus());
    }
  }

  protected void print(int printNo) {
    Object obj = otherList.get(printNo);
    Other o = (Other) obj;
    System.out.printf("  [%d] %s / %s   ", o.getIds(), o.getSpecies(), o.getPhotos());
    System.out.printf("%s/%s/%d살   ", o.getBreeds(), o.getGenders(), o.getAges());
    System.out.printf("%s, %s, %s\n", o.getDates(), o.getPlaces(), o.getStatus());
  }

  protected Other findByNo(int otherNo) {
    Other[] list = otherList.toArray(new Other[0]);
    for (Other o : list) {
      if (o.getIds() == otherNo) {
        return o;
      }
    }
    return null;
  }
}
