package com.leeheejin.myproject.handler;

import java.util.List;
import com.leeheejin.myproject.domain.Cat;
import com.leeheejin.util.Prompt;

public class CatAddHandler extends AbstractCatHandler {

  public CatAddHandler(List<Cat> catList) {
    super(catList);
  }

  @Override
  public void service() {
    System.out.println("[ 홈 > 관리자 메뉴 > 구조동물목록 > 신규등록 > 신규고양이등록* ]");

    try {
      Cat c = new Cat();

      c.setIds(catList.size() + 1);
      System.out.printf("[%d]\n",c.getIds());
      c.setPhotos(Prompt.inputString("사진? "));
      c.setBreeds(Prompt.inputString("품종? "));
      c.setGenders(Prompt.inputString("성별? "));
      c.setAges(Prompt.inputInt("나이? "));
      c.setDates(Prompt.inputDate("구조일? "));
      c.setPlaces(Prompt.inputString("구조장소? "));
      c.setStatus("신규");
      System.out.println();

      catList.add(c);

      System.out.println("- 등록이 완료되었습니다. ");
      System.out.println();
    } catch (Exception e) {
      System.out.println("---------------------");
      System.out.println(" 잘못된 입력입니다. ");
      System.out.println("---------------------");
    }
    System.out.println();
  }
}
