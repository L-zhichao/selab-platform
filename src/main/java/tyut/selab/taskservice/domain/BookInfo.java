package com.sample;


public class BookInfo {

  private long bookId;
  private String bookName;
  private String bookAuthor;
  private String bookDetails;
  private long price;
  private long owner;
  private long status;
  private java.sql.Timestamp createTime;
  private java.sql.Timestamp updateTime;
  private String remark;
  private String bookRef;


  public long getBookId() {
    return bookId;
  }

  public void setBookId(long bookId) {
    this.bookId = bookId;
  }


  public String getBookName() {
    return bookName;
  }

  public void setBookName(String bookName) {
    this.bookName = bookName;
  }


  public String getBookAuthor() {
    return bookAuthor;
  }

  public void setBookAuthor(String bookAuthor) {
    this.bookAuthor = bookAuthor;
  }


  public String getBookDetails() {
    return bookDetails;
  }

  public void setBookDetails(String bookDetails) {
    this.bookDetails = bookDetails;
  }


  public long getPrice() {
    return price;
  }

  public void setPrice(long price) {
    this.price = price;
  }


  public long getOwner() {
    return owner;
  }

  public void setOwner(long owner) {
    this.owner = owner;
  }


  public long getStatus() {
    return status;
  }

  public void setStatus(long status) {
    this.status = status;
  }


  public java.sql.Timestamp getCreateTime() {
    return createTime;
  }

  public void setCreateTime(java.sql.Timestamp createTime) {
    this.createTime = createTime;
  }


  public java.sql.Timestamp getUpdateTime() {
    return updateTime;
  }

  public void setUpdateTime(java.sql.Timestamp updateTime) {
    this.updateTime = updateTime;
  }


  public String getRemark() {
    return remark;
  }

  public void setRemark(String remark) {
    this.remark = remark;
  }


  public String getBookRef() {
    return bookRef;
  }

  public void setBookRef(String bookRef) {
    this.bookRef = bookRef;
  }

}
