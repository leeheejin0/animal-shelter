package com.leeheejin.myproject.handler;

import java.util.List;
import com.leeheejin.myproject.domain.Other;
import com.leeheejin.util.Prompt;

public class OtherAddHandler extends AbstractOtherHandler {

  public OtherAddHandler(List<Other> otherList) {
    super(otherList);
  }

  @Override
  public void service() {
    System.out.println("[ 홈 > 관리자 메뉴 > 구조동물목록 > 신규등록 > 신규기타동물등록* ]");
    try {
      Other o = new Other();

      o.setIds(otherList.size() + 1);
      System.out.printf("[%d]\n",o.getIds());
      o.setSpecies(Prompt.inputString("종류? "));
      o.setPhotos(Prompt.inputString("사진? "));
      o.setBreeds(Prompt.inputString("품종? "));
      o.setGenders(Prompt.inputString("성별? "));
      o.setAges(Prompt.inputInt("나이? "));
      o.setDates(Prompt.inputDate("구조일? "));
      o.setPlaces(Prompt.inputString("구조장소? "));
      o.setStatus("신규");
      System.out.println();

      otherList.add(o);

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
