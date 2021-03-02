package com.leeheejin.pms.domain;

import java.sql.Date;
import com.leeheejin.util.CsvObject;

public class Member implements CsvObject {
  private int no;
  private String id;
  private String name;
  private String email;
  private String tel;
  private String password;
  private Date registeredDate;

  @Override
  public String toCsvString() {
    return String.format("%d,%s,%s,%s,%s,%s,%s",
        this.getNo(),
        this.getId(),
        this.getName(),
        this.getEmail(),
        this.getTel(),
        this.getPassword(),
        this.getRegisteredDate().toString());
  }

  public static Member valueOfCsv(String csv) {
    String[] fields = csv.split(",");
    Member m = new Member();
    m.setNo(Integer.parseInt(fields[0]));
    m.setId(fields[1]);
    m.setName(fields[2]);
    m.setEmail(fields[3]);
    m.setTel(fields[4]);
    m.setPassword(fields[5]);
    m.setRegisteredDate(Date.valueOf(fields[6]));
    return m;
  }

  public int getNo() {
    return no;
  }
  public void setNo(int no) {
    this.no = no;
  }
  public String getId() {
    return id;
  }
  public void setId(String id) {
    this.id = id;
  }
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }
  public String getEmail() {
    return email;
  }
  public void setEmail(String email) {
    this.email = email;
  }
  public String getTel() {
    return tel;
  }
  public void setTel(String tel) {
    this.tel = tel;
  }
  public String getPassword() {
    return password;
  }
  public void setPassword(String password) {
    this.password = password;
  }
  public Date getRegisteredDate() {
    return registeredDate;
  }
  public void setRegisteredDate(Date registeredDate) {
    this.registeredDate = registeredDate;
  }
}
