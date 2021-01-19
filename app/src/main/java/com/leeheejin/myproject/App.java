package com.leeheejin.myproject;

import java.sql.Date;
import java.util.Scanner;

public class App {
  static Scanner keyScan = new Scanner(System.in);

  // 고양이
  final static int CAT_LENGTH = 100;
  static int[] catIds = new int[CAT_LENGTH];
  static String[] catPhotos = new String[CAT_LENGTH];
  static String[] catBreeds = new String[CAT_LENGTH];
  static String[] catGenders = new String[CAT_LENGTH];
  static int[] catAges = new int[CAT_LENGTH];
  static Date[] catDates = new Date[CAT_LENGTH];
  static String[] catPlaces = new String[CAT_LENGTH];
  static int[] catStatus = new int[CAT_LENGTH];
  static int catSize = 0;

  // 개
  final static int DOG_LENGTH = 100;
  static int[] dogIds = new int[DOG_LENGTH];
  static String[] dogPhotos = new String[DOG_LENGTH];
  static String[] dogBreeds = new String[DOG_LENGTH];
  static String[] dogGenders = new String[DOG_LENGTH];
  static int[] dogAges = new int[DOG_LENGTH];
  static Date[] dogDates = new Date[DOG_LENGTH];
  static String[] dogPlaces = new String[DOG_LENGTH];
  static int[] dogStatus = new int[DOG_LENGTH];
  static int dogSize = 0;

  // 기타
  final static int OTHER_LENGTH = 100;
  static int[] otherIds = new int[OTHER_LENGTH];
  static String[] otherSpecies = new String[OTHER_LENGTH];
  static String[] otherPhotos = new String[OTHER_LENGTH];
  static String[] otherBreeds = new String[OTHER_LENGTH];
  static String[] otherGenders = new String[OTHER_LENGTH];
  static int[] otherAges = new int[OTHER_LENGTH];
  static Date[] otherDates = new Date[OTHER_LENGTH];
  static String[] otherPlaces = new String[OTHER_LENGTH];
  static int[] otherStatus = new int[OTHER_LENGTH];
  static int otherSize = 0;

  static void addCat() {
    System.out.println("[고양이 구조]");

    catIds[catSize] = promptInt("번호? ");
    catPhotos[catSize] = promptString("사진? ");
    catBreeds[catSize] = promptString("품종? ");
    catGenders[catSize] = promptString("성별? ");
    catAges[catSize] = promptInt("나이? ");
    catDates[catSize] = promptDate("구조일? ");
    catPlaces[catSize] = promptString("구조장소? ");
    catStatus[catSize] = promptInt("상태?(0:신규, 1:공고, 2:입양완료) ");
    System.out.println();
    catSize++;
  }

  static void listCat() {
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
  }

  static void addDog() {
    System.out.println("[개 구조]");

    dogIds[dogSize] = promptInt("번호? ");
    dogPhotos[dogSize] = promptString("사진? ");
    dogBreeds[dogSize] = promptString("품종? ");
    dogGenders[dogSize] = promptString("성별? ");
    dogAges[dogSize] = promptInt("나이? ");
    dogDates[dogSize] = promptDate("구조일? ");
    dogPlaces[dogSize] = promptString("구조장소? ");
    dogStatus[dogSize] = promptInt("상태?(0:신규, 1:공고, 2:입양완료) ");
    System.out.println();
    dogSize++;

  }

  static void listDog() {
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
  }

  static void addOther() {
    System.out.println("[기타동물 구조]");

    otherIds[otherSize] = promptInt("번호? ");
    otherSpecies[otherSize] = promptString("종류? ");
    otherPhotos[otherSize] = promptString("사진? ");
    otherBreeds[otherSize] = promptString("품종? ");
    otherGenders[otherSize] = promptString("성별? ");
    otherAges[otherSize] = promptInt("나이? ");
    otherDates[otherSize] = promptDate("구조일? ");
    otherPlaces[otherSize] = promptString("구조장소? ");
    otherStatus[otherSize] = promptInt("상태?(0:신규, 1:공고, 2:입양완료) ");

    System.out.println();
    otherSize++;
  }

  static void listOther() {
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
  }

  public static void main(String[] args) {
    while (true) {
      System.out.print("명령> ");
      String response = keyScan.nextLine();
      if (response.equalsIgnoreCase("exit") || response.equalsIgnoreCase("quit")) {
        System.out.println("종료합니다. ");
        break;
      } else if (response.equalsIgnoreCase("/cat/add")) {
        addCat();
      } else if (response.equalsIgnoreCase("/cat/list")) {
        listCat();
      } else if (response.equalsIgnoreCase("/dog/add")) {
        addDog();
      } else if (response.equalsIgnoreCase("/dog/list")) {
        listDog();
      } else if (response.equalsIgnoreCase("/other/add")) {
        addOther();
      } else if (response.equalsIgnoreCase("/other/list")) {
        listOther();
      } else {
        System.out.println("잘못 입력하셨습니다. ");
        System.out.println();
      }
    }
    keyScan.close();
  }

  static String promptString(String title) {
    System.out.print(title);
    return keyScan.nextLine();
  }

  static int promptInt(String title) {
    System.out.print(title);
    return Integer.parseInt(keyScan.nextLine());
  }

  static Date promptDate(String title) {
    System.out.print(title);
    return Date.valueOf(keyScan.nextLine());
  }
}
