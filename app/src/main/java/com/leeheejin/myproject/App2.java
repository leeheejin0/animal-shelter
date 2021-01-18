package com.leeheejin.myproject;

import java.sql.Date;
import java.util.Scanner;

public class App2 {
  public static void main(String[] args) {
    Scanner keyScan = new Scanner(System.in);

    final int COFFEE_LENGTH = 100;
    String[] names = new String[COFFEE_LENGTH];
    int[] sourtastes = new int[COFFEE_LENGTH];
    String[] limited = new String[COFFEE_LENGTH];
    int[] nums = new int[COFFEE_LENGTH];
    Date[] pDate = new Date[COFFEE_LENGTH];
    int size = 0;

    final int MACHINE_LENGTH = 10;
    String[] modelName = new String[MACHINE_LENGTH];
    Date[] machineDate = new Date[MACHINE_LENGTH];
    Date[] lastClean = new Date[MACHINE_LENGTH];
    Date[] asDate = new Date[MACHINE_LENGTH];
    int[] mStatus = new int[MACHINE_LENGTH];
    int mSize = 0;

    while (true) {
      System.out.print("명령> ");
      String response = keyScan.nextLine();

      if (response.equalsIgnoreCase("exit") || response.equalsIgnoreCase("quit")) {
        System.out.println("종료합니다. ");
        break;
      } else if (response.equalsIgnoreCase("/coffee/add")) {
        System.out.println("[캡슐 커피 구매]");

        System.out.print("제품명? ");
        names[size] = keyScan.nextLine();

        System.out.print("산미?(0-10) ");
        sourtastes[size] = Integer.parseInt(keyScan.nextLine());

        System.out.print("한정판?(y/N) ");
        limited[size] = keyScan.nextLine();

        System.out.print("수량? ");
        nums[size] = Integer.parseInt(keyScan.nextLine());

        System.out.print("구입일? ");
        pDate[size] = Date.valueOf(keyScan.nextLine());

        System.out.println();
        size++;

      } else if (response.equalsIgnoreCase("/coffee/list")) {
        System.out.println("[캡슐 커피 구비 목록]");

        for (int i = 0; i < size; i++) {
          String status = null;
          if (limited[i].isEmpty() || limited[i].equalsIgnoreCase("n")) {
            status = "일반상품";
          } else {
            status = "한 정 판";
          }
          System.out.printf("<%s> %s 산미:%d | %d개 (%s)\n", 
              status, names[i], sourtastes[i], nums[i], pDate[i]);
        }
        System.out.println();
      } else if (response.equalsIgnoreCase("/machine/update")) {
        System.out.println("[커피머신 정보 업데이트]");

        System.out.print("모델명? ");
        modelName[mSize] = keyScan.nextLine();

        System.out.print("구입일? ");
        machineDate[mSize] = Date.valueOf(keyScan.nextLine());

        System.out.print("마지막 청소일? ");
        lastClean[mSize] = Date.valueOf(keyScan.nextLine());

        System.out.print("A/S 무료 서비스 기한? ");
        asDate[mSize] = Date.valueOf(keyScan.nextLine());

        System.out.print("상태?(0:정상작동 1:청소필요 2:A/S필요) ");
        mStatus[mSize] = Integer.parseInt(keyScan.nextLine());

        System.out.println();
        mSize++;

      } else if (response.equalsIgnoreCase("/machine/info")) {
        System.out.println("[커피머신 정보]");

        for (int i = 0; i < mSize; i++) {
          String stateLabel = null;
          switch (mStatus[i]) {
            case 1:
              stateLabel = "청소가 필요합니다. ";
              break;
            case 2:
              stateLabel = "A/S가 필요합니다. ";
              break;
            default:
              stateLabel = "정상적으로 작동 중입니다. ";
              break;
          }
          System.out.printf("- %s(%s)\nA/S 무료 서비스 : %s | 마지막 청소일 : %s\n\"%s\"\n", 
              modelName[i], machineDate[i], asDate[i], lastClean[i], stateLabel);
        }
        System.out.println();

      } else {
        System.out.println("잘못 입력하셨습니다. ");
        System.out.println();
      }
    }
    //    

    //
    //    
    //
    //    System.out.println("[커피머신 정보 업데이트]");
    //    

    //    
    //    System.out.println("[커피머신 정보]");



    keyScan.close();
  }
}
