package com.leeheejin.pms.domain;

import java.sql.Date;
import com.leeheejin.util.CsvObject;

public class Board implements CsvObject {
  private int no;
  private String name;
  private int password;
  private String title;
  private String content;
  private Date registeredDate;
  private int viewCount;
  private int like;

  public String toCsvString() {
    return String.format("%d,%s,%s,%s,%s,%s,%s",
        this.getNo(),
        this.getName(),
        this.getPassword(),
        this.getTitle(),
        this.getContent(),
        this.getRegisteredDate().toString(),
        this.getViewCount(),
        this.getLike());
  }

  public static Board valueOfCsv(String csv) {
    String[] fields = csv.split(",");
    Board b = new Board();
    b.setNo(Integer.parseInt(fields[0]));
    b.setName(fields[1]);
    b.setPassword(Integer.parseInt(fields[2]));
    b.setTitle(fields[3]);
    b.setContent(fields[4]);
    b.setRegisteredDate(Date.valueOf(fields[5]));
    b.setViewCount(Integer.parseInt(fields[6]));
    b.setLike(Integer.parseInt(fields[7]));
    return b;
  }

  public int getNo() {
    return no;
  }
  public void setNo(int no) {
    this.no = no;
  }
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }
  public int getPassword() {
    return password;
  }
  public void setPassword(int password) {
    this.password = password;
  }
  public String getTitle() {
    return title;
  }
  public void setTitle(String title) {
    this.title = title;
  }
  public String getContent() {
    return content;
  }
  public void setContent(String content) {
    this.content = content;
  }
  public Date getRegisteredDate() {
    return registeredDate;
  }
  public void setRegisteredDate(Date registeredDate) {
    this.registeredDate = registeredDate;
  }
  public int getViewCount() {
    return viewCount;
  }
  public void setViewCount(int viewCount) {
    this.viewCount = viewCount;
  }
  public int getLike() {
    return like;
  }
  public void setLike(int like) {
    this.like = like;
  }
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((content == null) ? 0 : content.hashCode());
    result = prime * result + like;
    result = prime * result + ((name == null) ? 0 : name.hashCode());
    result = prime * result + no;
    result = prime * result + password;
    result = prime * result + ((registeredDate == null) ? 0 : registeredDate.hashCode());
    result = prime * result + ((title == null) ? 0 : title.hashCode());
    result = prime * result + viewCount;
    return result;
  }
  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    Board other = (Board) obj;
    if (content == null) {
      if (other.content != null)
        return false;
    } else if (!content.equals(other.content))
      return false;
    if (like != other.like)
      return false;
    if (name == null) {
      if (other.name != null)
        return false;
    } else if (!name.equals(other.name))
      return false;
    if (no != other.no)
      return false;
    if (password != other.password)
      return false;
    if (registeredDate == null) {
      if (other.registeredDate != null)
        return false;
    } else if (!registeredDate.equals(other.registeredDate))
      return false;
    if (title == null) {
      if (other.title != null)
        return false;
    } else if (!title.equals(other.title))
      return false;
    if (viewCount != other.viewCount)
      return false;
    return true;
  }
}
