package com.leeheejin.myproject.handler;

import java.sql.Date;
import com.leeheejin.myproject.domain.Member;
import com.leeheejin.util.Prompt;

public class MemberHandler {
  final static int LENGTH = 100;

  static Member[] members = new Member[LENGTH];
  static int size = 0; 

  public static void signUp() {
    System.out.println("[ 홈 > 회원가입* ]");
    Member m = new Member();
    m.no = size + 1;
    m.id = Prompt.inputString("아이디: ");
    m.name = Prompt.inputString("이름: ");
    m.email = Prompt.inputString("이메일: ");
    m.tel = Prompt.inputString("전화번호: ");
    m.password = Prompt.inputString("비밀번호: ");
    m.registeredDate = new Date(System.currentTimeMillis());
    members[size++] = m;
    System.out.println("- 가입이 완료되었습니다. ");
    System.out.println();
  }
  public static int logIn() {
    System.out.println("[ 홈 > 로그인* ]");
    System.out.println("(뒤로가기| 빈 문자열)");
    while (true) {
      String id = Prompt.inputString("아이디: ");
      String password = Prompt.inputString("비밀번호: ");
      if(exist(id, password)) {
        System.out.println("- 로그인 되었습니다. ");
        System.out.println();
        return 1;
      } else if (id.length() == 0 && password.length() == 0) {
        break;
      } else {
        System.out.println("- 회원 정보를 찾을 수 없습니다. ");
        System.out.println();
      }
    }
    return 0;
  }

  public static void editInfo() {
    System.out.println("[ 홈 > 관리자 메뉴 > 회원정보수정* ]");
    for (int i = 0; i < size; i++) {
      Member m = members[i];
      System.out.printf("[ %s | %s ] %s | %s | %s (%s)\n", 
          m.id, m.password, m.name, m.email, m.tel, m.registeredDate);
    }
    int command = Prompt.inputInt("1: 상태수정 | 2: 삭제 | 3: 뒤로가기\n  >>");
    switch (command) {
      case 1:
        String id = Prompt.inputString("아이디: ");
        String password = Prompt.inputString("비밀번호: ");
        String name = Prompt.inputString("이름: ");
        String email = Prompt.inputString("이메일: ");
        String tel = Prompt.inputString("전화번호: ");

        for (int i = size - 1; i < size; i++) {
          Member m = members[i];
          m.id = id;
          m.password = password;
          m.name = name;
          m.email = email;
          m.tel = tel;
          System.out.printf("[ %s | %s ] %s | %s | %s (%s)\n", 
              m.id, m.password, m.name, m.email, m.tel, m.registeredDate);
        }
        System.out.println("- 회원정보가 변경되었습니다. ");
        System.out.println();
        break;
      case 2:
        String dcommand = Prompt.inputString("- 삭제하시겠습니까?(y/N) ");
        if (dcommand.isEmpty() || dcommand.equalsIgnoreCase("n")) {
          MenuHandler.managerMenu();
          break;
        } else if (dcommand.equalsIgnoreCase("y")) {
          for (int i = 0; i < size; i++) {
            members[i] = members[i + 1];
          }
          size--;
          System.out.println("- <삭제완료>");
        } else {
          System.out.println("- 잘못 입력하셨습니다. ");
          MenuHandler.managerMenu();
          break;
        }
        break;
      default:
        MenuHandler.managerMenu();
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
