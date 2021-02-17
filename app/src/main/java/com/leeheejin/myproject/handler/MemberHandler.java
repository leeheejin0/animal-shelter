package com.leeheejin.myproject.handler;

import java.sql.Date;
import java.util.LinkedList;
import com.leeheejin.myproject.domain.Member;
import com.leeheejin.util.Prompt;

public class MemberHandler {

  static LinkedList<Member> memberList = new LinkedList<>();

  static int loginAccount = -1;

  public static void signUp() {
    System.out.println("[ 홈 > 회원가입* ]");
    Member m = new Member();
    m.setNo(memberList.size() + 1);
    m.setId(Prompt.inputString("아이디: "));
    m.setName(Prompt.inputString("이름: "));
    m.setEmail(Prompt.inputString("이메일: "));
    m.setTel(Prompt.inputString("전화번호: "));
    m.setPassword(Prompt.inputString("비밀번호: "));
    m.setRegisteredDate(new Date(System.currentTimeMillis()));

    memberList.add(m);

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

  public static void updateInfo() {
    System.out.println("[ 홈 > 관리자 메뉴 > 회원정보수정* ]");
    Object obj = memberList.get(loginAccount);
    Member m = (Member) obj;
    System.out.printf("[ %s | %s ] %s | %s | %s (%s)\n", 
        m.getId(), m.getPassword(), m.getName(), m.getEmail(), 
        m.getTel(), m.getRegisteredDate());

    int command = Prompt.inputInt("1: 상태수정 | 2: 회원탈퇴 | 3: 뒤로가기\n>>");
    switch (command) {
      case 1:
        String id = Prompt.inputString("아이디: ");
        String password = Prompt.inputString("비밀번호: ");
        String name = Prompt.inputString("이름: ");
        String email = Prompt.inputString("이메일: ");
        String tel = Prompt.inputString("전화번호: ");

        m.setId(id);
        m.setPassword(password);
        m.setName(name);
        m.setEmail(email);
        m.setTel(tel);
        System.out.printf("[ %s | %s ] %s | %s | %s (%s)\n", 
            m.getId(), m.getPassword(), m.getName(), m.getEmail(), 
            m.getTel(), m.getRegisteredDate());
        System.out.println("- 회원정보가 변경되었습니다. ");
        System.out.println();
        break;
      case 2:
        String dcommand = Prompt.inputString("- 삭제하시겠습니까?(y/N) ");
        if (dcommand.isEmpty() || dcommand.equalsIgnoreCase("n")) {
          return;
        } else if (dcommand.equalsIgnoreCase("y")) {
          memberList.remove(m);
          System.out.println("- 회원정보가 삭제되었습니다. ");
          System.out.println();
          //return;
          //로그인 전 홈으로 돌아가기
        } else {
          System.out.println("- 잘못 입력하셨습니다. ");
          System.out.println();
          break;
        }
        break;
      default:
        break;
    }
  }

  static boolean exist(String inputId, String inputPw) {
    Member[] arr = memberList.toArray(new Member[0]);
    for (int i = 0; i < memberList.size(); i++) {
      Member m = arr[i];
      if(inputId.equals(m.getId()) &&
          inputPw.equals(m.getPassword())) {
        loginAccount = m.getNo();
        return true;
      }
    }
    return false;
  }
}
