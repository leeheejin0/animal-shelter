package com.leeheejin.myproject.handler;

import java.sql.Date;
import com.leeheejin.myproject.domain.Member;
import com.leeheejin.util.Prompt;

public class MemberHandler {
  static Node first;
  static Node last;

  static int size = 0; 
  static int loginAccount = -1;

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

    Node node = new Node(m);
    if (last == null) {
      first = node;
      last = node;
    } else {
      last.next = node;
      node.prev = last;
      last = node;
    }
    size++;

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
    Node cursor = first;
    while (cursor != null) {
      Member m = cursor.members;
      if (loginAccount == m.no) {
        System.out.printf("[ %s | %s ] %s | %s | %s (%s)\n", 
            m.id, m.password, m.name, m.email, m.tel, m.registeredDate);
        break;
      }
      cursor = cursor.next;
    }
    int command = Prompt.inputInt("1: 상태수정 | 2: 회원탈퇴 | 3: 뒤로가기\n>>");
    switch (command) {
      case 1:
        String id = Prompt.inputString("아이디: ");
        String password = Prompt.inputString("비밀번호: ");
        String name = Prompt.inputString("이름: ");
        String email = Prompt.inputString("이메일: ");
        String tel = Prompt.inputString("전화번호: ");
        cursor = first;
        while (cursor != null) {
          Member m = cursor.members;
          if (loginAccount == m.no) {
            m.id = id;
            m.password = password;
            m.name = name;
            m.email = email;
            m.tel = tel;
            System.out.printf("[ %s | %s ] %s | %s | %s (%s)\n", 
                m.id, m.password, m.name, m.email, m.tel, m.registeredDate);
            break;
          }
          cursor = cursor.next;
        }
        System.out.println("- 회원정보가 변경되었습니다. ");
        System.out.println();
        break;
      case 2:
        String dcommand = Prompt.inputString("- 삭제하시겠습니까?(y/N) ");
        if (dcommand.isEmpty() || dcommand.equalsIgnoreCase("n")) {
          // MenuHandler.managerMenu();
          return;
        } else if (dcommand.equalsIgnoreCase("y")) {
          cursor = first;
          while (cursor != null) {
            Member member = cursor.members;
            if (member.no == loginAccount) {
              member = null;
              break;
            }
            cursor = cursor.next;
          }

          cursor = first;
          while (cursor != null) {
            if (cursor.members.no == loginAccount) {
              size--;
              if (first == last) {
                first = last =null;
                break;
              }
              if (cursor == first) {
                first = cursor.next;
                cursor.prev = null;
              }else {
                cursor.prev.next = cursor.next;
                if (cursor.next != null) {
                  cursor.next.prev = cursor.prev;
                }
                if (cursor == last) {
                  last = cursor.prev;
                }
                break;
              }
            }
            cursor = cursor.next;
          }
          System.out.println("- 회원정보가 삭제되었습니다. ");
          //return;
          //App.main(null);
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
    Node cursor = first;
    while (cursor != null){
      if (inputId.equals(cursor.members.id) && inputPw.equals(cursor.members.password)) {
        loginAccount = cursor.members.no;
        return true;
      }
      cursor = cursor.next;
    }
    return false;
  }

  static class Node {
    Member members;
    Node next;
    Node prev;
    Node(Member m){
      this.members = m;
    }
  }
}
