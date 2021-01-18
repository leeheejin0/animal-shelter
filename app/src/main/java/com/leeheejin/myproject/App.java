package com.leeheejin.myproject;

import java.sql.Date;
import java.util.Scanner;

public class App {
  public static void main(String[] args) {
    Scanner keyScan = new Scanner(System.in);

    // 고양이
    final int CAT_LENGTH = 100;
    int[] catIds = new int[CAT_LENGTH];
    String[] catPhotos = new String[CAT_LENGTH];
    String[] catBreeds = new String[CAT_LENGTH];
    String[] catGenders = new String[CAT_LENGTH];
    int[] catAges = new int[CAT_LENGTH];
    Date[] catDates = new Date[CAT_LENGTH];
    String[] catPlaces = new String[CAT_LENGTH];
    int[] catStatus = new int[CAT_LENGTH];
    int catSize = 0;

    // 개
    final int DOG_LENGTH = 100;
    int[] dogIds = new int[DOG_LENGTH];
    String[] dogPhotos = new String[DOG_LENGTH];
    String[] dogBreeds = new String[DOG_LENGTH];
    String[] dogGenders = new String[DOG_LENGTH];
    int[] dogAges = new int[DOG_LENGTH];
    Date[] dogDates = new Date[DOG_LENGTH];
    String[] dogPlaces = new String[DOG_LENGTH];
    int[] dogStatus = new int[DOG_LENGTH];
    int dogSize = 0;

    // 기타
    final int OTHER_LENGTH = 100;
    int[] otherIds = new int[OTHER_LENGTH];
    String[] otherSpecies = new String[OTHER_LENGTH];
    String[] otherPhotos = new String[OTHER_LENGTH];
    String[] otherBreeds = new String[OTHER_LENGTH];
    String[] otherGenders = new String[OTHER_LENGTH];
    int[] otherAges = new int[OTHER_LENGTH];
    Date[] otherDates = new Date[OTHER_LENGTH];
    String[] otherPlaces = new String[OTHER_LENGTH];
    int[] otherStatus = new int[OTHER_LENGTH];
    int otherSize = 0;

    while (true) {
      System.out.print("명령> ");
      String response = keyScan.nextLine();

      if (response.equalsIgnoreCase("exit") || response.equalsIgnoreCase("quit")) {
        System.out.println("종료합니다. ");
        break;
      } else if (response.equalsIgnoreCase("/cat/add")) {
        System.out.println("[고양이 구조]");
        System.out.print("번호? ");
        catIds[catSize] = Integer.parseInt(keyScan.nextLine());

        System.out.print("사진? ");
        catPhotos[catSize] = keyScan.nextLine();

        System.out.print("품종? ");
        catBreeds[catSize] = keyScan.nextLine();

        System.out.print("성별? ");
        catGenders[catSize] = keyScan.nextLine();

        System.out.print("나이? ");
        catAges[catSize] = Integer.parseInt(keyScan.nextLine());

        System.out.print("구조일? ");
        catDates[catSize] = Date.valueOf(keyScan.nextLine());

        System.out.print("구조장소? ");
        catPlaces[catSize] = keyScan.nextLine();

        System.out.print("상태?(0:신규, 1:공고, 2:입양완료) ");
        catStatus[catSize] = Integer.parseInt(keyScan.nextLine());

        System.out.println();
        catSize++;

      } else if (response.equalsIgnoreCase("/cat/list")) {
        System.out.println("[고양이 구조 목록]");

        for (int i = 0; i < catSize; i++) {
          String stateLabel = null;
          switch (catStatus[i]) {
            case 1:
              stateLabel = "공고중";
              break;
            case 2:
              stateLabel = "입양완료";
              break;
            default:
              stateLabel = "신규";
              break;
          }
          System.out.printf("%d : %s   %s/%s/%d살   ", 
              catIds[i], catPhotos[i], catBreeds[i], catGenders[i], catAges[i]);
          System.out.printf("%s, %s, %s\n", catDates[i], catPlaces[i], stateLabel);
        }
        System.out.println();
      } else if (response.equalsIgnoreCase("/dog/add")) {
        System.out.println("[개 구조]");

        System.out.print("번호? ");
        dogIds[dogSize] = Integer.parseInt(keyScan.nextLine());

        System.out.print("사진? ");
        dogPhotos[dogSize] = keyScan.nextLine();

        System.out.print("품종? ");
        dogBreeds[dogSize] = keyScan.nextLine();

        System.out.print("성별? ");
        dogGenders[dogSize] = keyScan.nextLine();

        System.out.print("나이? ");
        dogAges[dogSize] = Integer.parseInt(keyScan.nextLine());

        System.out.print("구조일? ");
        dogDates[dogSize] = Date.valueOf(keyScan.nextLine());

        System.out.print("구조장소? ");
        dogPlaces[dogSize] = keyScan.nextLine();

        System.out.print("상태?(0:신규, 1:공고, 2:입양완료) ");
        dogStatus[dogSize] = Integer.parseInt(keyScan.nextLine());

        System.out.println();
        dogSize++;

      } else if (response.equalsIgnoreCase("/dog/list")) {
        System.out.println("[개 구조 목록]");

        for (int i = 0; i < dogSize; i++) {
          String stateLabel = null;
          switch (dogStatus[i]) {
            case 1:
              stateLabel = "공고중";
              break;
            case 2:
              stateLabel = "입양완료";
              break;
            default:
              stateLabel = "신규";
              break;
          }
          System.out.printf("%d : %s   %s/%s/%d살   ", 
              dogIds[i], dogPhotos[i], dogBreeds[i], dogGenders[i], dogAges[i]);
          System.out.printf("%s, %s, %s\n", dogDates[i], dogPlaces[i], stateLabel);
        }
        System.out.println();
      } else if (response.equalsIgnoreCase("/other/add")) {
        System.out.println("[기타동물 구조]");

        System.out.print("번호? ");
        otherIds[otherSize] = Integer.parseInt(keyScan.nextLine());

        System.out.print("종류? ");
        otherSpecies[otherSize] = keyScan.nextLine();

        System.out.print("사진? ");
        otherPhotos[otherSize] = keyScan.nextLine();

        System.out.print("품종? ");
        otherBreeds[otherSize] = keyScan.nextLine();

        System.out.print("성별? ");
        otherGenders[otherSize] = keyScan.nextLine();

        System.out.print("나이? ");
        otherAges[otherSize] = Integer.parseInt(keyScan.nextLine());

        System.out.print("구조일? ");
        otherDates[otherSize] = Date.valueOf(keyScan.nextLine());

        System.out.print("구조장소? ");
        otherPlaces[otherSize] = keyScan.nextLine();

        System.out.print("상태?(0:신규, 1:공고, 2:입양완료) ");
        otherStatus[otherSize] = Integer.parseInt(keyScan.nextLine());

        System.out.println();
        otherSize++;

      } else if (response.equalsIgnoreCase("/other/list")) {
        System.out.println("[기타동물 구조 목록]");

        for (int i = 0; i < otherSize; i++) {
          String stateLabel = null;
          switch (otherStatus[i]) {
            case 1:
              stateLabel = "공고중";
              break;
            case 2:
              stateLabel = "입양완료";
              break;
            default:
              stateLabel = "신규";
              break;
          }
          System.out.printf("%d : %s : %s   ", otherIds[i], otherSpecies[i], otherPhotos[i]);
          System.out.printf("%s/%s/%d살   ", otherBreeds[i], otherGenders[i], otherAges[i]);
          System.out.printf("%s, %s, %s\n", otherDates[i], otherPlaces[i], stateLabel);
        }
        System.out.println();
      } else {
        System.out.println("잘못 입력하셨습니다. ");
        System.out.println();
      }
    }
    keyScan.close();
  }
}
