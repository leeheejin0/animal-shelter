package com.leeheejin.myproject.handler;

import java.sql.Date;
import com.leeheejin.myproject.App;
import com.leeheejin.util.Prompt;

public class MemberHandler {
  static class Member{
    String id;
    String name;
    String email;
    String tel;
    String password;
    Date registeredDate;  
  }
  final static int LENGTH = 100;

  static Member[] members = new Member[LENGTH];
  static int size = 0; 

  public void signUp() {
    System.out.println("ㄴ<회원가입>");
    Member m = new Member();
    m.id = Prompt.inputString("  아이디: ");
    m.name = Prompt.inputString("  이름: ");
    m.email = Prompt.inputString("  이메일: ");
    m.tel = Prompt.inputString("  전화번호: ");
    m.password = Prompt.inputString("  비밀번호: ");
    m.registeredDate = new Date(System.currentTimeMillis());
    members[size++] = m;
    System.out.println("  - 가입이 완료되었습니다. ");
    System.out.println();
  }
  public static void logIn() {
    System.out.println("ㄴ<로그인(뒤로가기| 빈 문자열)>");
    while (true) {
      String id = Prompt.inputString("  아이디: ");
      String password = Prompt.inputString("  비밀번호: ");
      if(exist(id, password)) {
        System.out.println("  - 로그인 되었습니다. ");
        System.out.println();
        break;
      } else if (id.length() == 0 && password.length() == 0) {
        App.main(null);
      } else {
        System.out.println("  - 회원 정보를 찾을 수 없습니다. ");
        System.out.println();
      }
    }
  }

  public static void editInfo() {
    System.out.println("ㄴ<회원정보수정>");
    for (int i = 0; i < size; i++) {
      Member m = members[i];
      System.out.printf("  [ %s | %s ] %s | %s | %s (%s)\n", 
          m.id, m.password, m.name, m.email, m.tel, m.registeredDate);
    }
    int command = Prompt.inputInt("  1: 상태수정 | 2: 삭제 | 3: 뒤로가기\n  >>");
    switch (command) {
      case 1:
        break;
      case 2:
        String dcommand = Prompt.inputString("  - 삭제하시겠습니까?(y/N) ");
        if (dcommand.isEmpty() || dcommand.equalsIgnoreCase("n")) {
          MenuHandler.homeOutMenu();
          break;
        } else if (dcommand.equalsIgnoreCase("y")) {
          for (int i = 0; i < size; i++) {
            members[i] = members[i + 1];
          }
          size--;
          System.out.println("  - <삭제완료>");
        } else {
          System.out.println("  - 잘못 입력하셨습니다. ");
          MenuHandler.homeOutMenu();
          break;
        }
        break;
      default:
        MenuHandler.homeOutMenu();
        break;
    }
  }

  static boolean exist(String inputId, String inputPw) {
    for (int i = 0; i < size; i++) {
      if (inputId.equals(members[i].id) && inputPw.equals(members[i].password)) {
        return true;
      }
    }
    return false;
  }
}
