package com.leeheejin.pms.handler;

import java.util.List;
import com.leeheejin.pms.domain.Dog;
import com.leeheejin.util.Prompt;

public class DogAddHandler extends AbstractDogHandler {

  public DogAddHandler(List<Dog> dogList) {
    super(dogList);
  }

  @Override
  public void service() {
    System.out.println("[ 홈 > 관리자 메뉴 > 구조동물목록 > 신규등록 > 신규개등록* ]");
    try {
      Dog d = new Dog();
      d.setIds(dogList.size() + 1);
      System.out.printf("[%d]\n",d.getIds());
      d.setPhotos(Prompt.inputString("사진? "));
      d.setBreeds(Prompt.inputString("품종? "));
      d.setGenders(Prompt.inputString("성별? "));
      d.setAges(Prompt.inputInt("나이? "));
      d.setDates(Prompt.inputDate("구조일? "));
      d.setPlaces(Prompt.inputString("구조장소? "));
      d.setStatus("신규");

      dogList.add(d);

      System.out.println("- 등록이 완료되었습니다. ");
      System.out.println();

    } catch (Exception e) {
      System.out.println("---------------------");
      System.out.println(" 잘못된 입력입니다. ");
      System.out.println("---------------------");
    }
  }
}
