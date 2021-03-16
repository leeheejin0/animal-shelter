package com.leeheejin.pms.listener;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.lang.reflect.Type;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.leeheejin.context.ApplicationContextListener;
import com.leeheejin.pms.domain.Board;
import com.leeheejin.pms.domain.Cat;
import com.leeheejin.pms.domain.Dog;
import com.leeheejin.pms.domain.Member;
import com.leeheejin.pms.domain.Other;
import com.leeheejin.util.CsvObject;

public class FileListener implements ApplicationContextListener{

  File memberFile = new File("members.json");
  File boardFile1 = new File("boards1.json");
  File boardFile2 = new File("boards2.json");
  File catFile = new File("cats.json");
  File dogFile = new File("dogs.json");
  File otherFile = new File("others.json");

  List<Member> memberList;
  List<Cat> catList;
  List<Dog> dogList;
  List<Other> otherList;
  List<Board> boardList1;
  List<Board> boardList2;

  @Override
  public void contextInitialized(Map<String, Object> context) {

    memberList = loadObjects(memberFile, Member.class);
    catList = loadObjects(catFile, Cat.class);
    dogList = loadObjects(dogFile, Dog.class);
    otherList =loadObjects(otherFile, Other.class);
    boardList1 = loadObjects(boardFile1, Board.class);
    boardList2 = loadObjects(boardFile2, Board.class);

    context.put("memberList", memberList);
    context.put("catList", catList);
    context.put("dogList", dogList);
    context.put("otherList", otherList);
    context.put("boardList1", boardList1);
    context.put("boardList2", boardList2);
  }

  @Override
  public void contextDestroyed(Map<String, Object> context) {
    saveObjects(memberFile, memberList);
    saveObjects(catFile, catList);
    saveObjects(dogFile, dogList);
    saveObjects(otherFile, otherList);
    saveObjects(boardFile1, boardList1);
    saveObjects(boardFile2, boardList2);
  }

  private <T> List<T> loadObjects(File file, Class<T> elementType) {

    try (BufferedReader in = new BufferedReader(new FileReader(file))) {

      StringBuilder strBuilder = new StringBuilder();
      String str = null;
      while ((str = in.readLine()) != null) {
        strBuilder.append(str);
      }

      Type listType = TypeToken.getParameterized(LinkedList.class,elementType).getType();
      List<T> list = new Gson().fromJson(strBuilder.toString(), listType);
      System.out.printf("%s 파일 데이터 로딩!\n", file.getName());

      return list;

    } catch (Exception e) {
      System.out.printf("%s 파일 데이터 로딩 중 오류 발생!\n", file.getName());
      return new LinkedList<T>();
    }
  }

  private <T extends CsvObject> void saveObjects(File file, List<T> list) {
    try (BufferedWriter out = new BufferedWriter(new FileWriter(file))){
      out.write(new Gson().toJson(list));
      System.out.printf("파일 %s 데이터 저장!\n", file.getName());
    } catch (Exception e) {
      System.out.printf("파일 %s에 데이터를 저장하는 중에 오류 발생!\n", file.getName());
    }
  }
}
