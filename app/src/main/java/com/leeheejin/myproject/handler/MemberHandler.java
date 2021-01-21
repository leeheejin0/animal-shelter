package com.leeheejin.myproject.handler;

import java.sql.Date;
import com.leeheejin.util.Prompt;

public class MemberHandler {
  static class Member {
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

  public static void signUp() {
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
    System.out.println("ㄴ<관리자 로그인>");
    String id = Prompt.inputString("  아이디: ");
    String password = Prompt.inputString("  비밀번호: ");
    if(exist(id, password)) {
      System.out.println("  - 로그인되었습니다. ");
      MenuHandler.managerMenu();
    } else {
      System.out.println("  - 회원 정보를 찾을 수 없습니다. ");
      System.out.println();
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
