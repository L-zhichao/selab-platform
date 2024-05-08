package tyut.selab.bookservice.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 书籍信息表
 * @TableName book_info
 */
public class BookInfo implements Serializable {
    /**
     * 数据唯一标识
     */
    private Integer bookId;

    /**
     * 图书名称
     */
    private String bookName;

    /**
     * 书籍作者
     */
    private String bookAuthor;

    /**
     * 图书介绍
     */
    private String bookDetails;

    /**
     * 价格
     */
    private Integer price;

    /**
     * 书籍拥有者
     */
    private Long owner;

    /**
     * 书籍状态(0为可借阅 1为借阅 2为不可借阅)
     */
    private Integer status;

    /**
     * 书籍添加时间
     */
    private Date createTime;

    /**
     * 书籍信息修改时间
     */
    private Date updateTime;

    /**
     * 备注信息
     */
    private String remark;

    /**
     * 书籍编号(定位书籍位置)
     */
    private String bookRef;

    private static final long serialVersionUID = 1L;

    /**
     * 数据唯一标识
     */
    public Integer getBookId() {
        return bookId;
    }

    /**
     * 数据唯一标识
     */
    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    /**
     * 图书名称
     */
    public String getBookName() {
        return bookName;
    }

    /**
     * 图书名称
     */
    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    /**
     * 书籍作者
     */
    public String getBookAuthor() {
        return bookAuthor;
    }

    /**
     * 书籍作者
     */
    public void setBookAuthor(String bookAuthor) {
        this.bookAuthor = bookAuthor;
    }

    /**
     * 图书介绍
     */
    public String getBookDetails() {
        return bookDetails;
    }

    /**
     * 图书介绍
     */
    public void setBookDetails(String bookDetails) {
        this.bookDetails = bookDetails;
    }

    /**
     * 价格
     */
    public Integer getPrice() {
        return price;
    }

    /**
     * 价格
     */
    public void setPrice(Integer price) {
        this.price = price;
    }

    /**
     * 书籍拥有者
     */
    public Long getOwner() {
        return owner;
    }

    /**
     * 书籍拥有者
     */
    public void setOwner(Long owner) {
        this.owner = owner;
    }

    /**
     * 书籍状态(0为可借阅 1为借阅 2为不可借阅)
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 书籍状态(0为可借阅 1为借阅 2为不可借阅)
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 书籍添加时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 书籍添加时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 书籍信息修改时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 书籍信息修改时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 备注信息
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 备注信息
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * 书籍编号(定位书籍位置)
     */
    public String getBookRef() {
        return bookRef;
    }

    /**
     * 书籍编号(定位书籍位置)
     */
    public void setBookRef(String bookRef) {
        this.bookRef = bookRef;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        BookInfo other = (BookInfo) that;
        return (this.getBookId() == null ? other.getBookId() == null : this.getBookId().equals(other.getBookId()))
            && (this.getBookName() == null ? other.getBookName() == null : this.getBookName().equals(other.getBookName()))
            && (this.getBookAuthor() == null ? other.getBookAuthor() == null : this.getBookAuthor().equals(other.getBookAuthor()))
            && (this.getBookDetails() == null ? other.getBookDetails() == null : this.getBookDetails().equals(other.getBookDetails()))
            && (this.getPrice() == null ? other.getPrice() == null : this.getPrice().equals(other.getPrice()))
            && (this.getOwner() == null ? other.getOwner() == null : this.getOwner().equals(other.getOwner()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()))
            && (this.getRemark() == null ? other.getRemark() == null : this.getRemark().equals(other.getRemark()))
            && (this.getBookRef() == null ? other.getBookRef() == null : this.getBookRef().equals(other.getBookRef()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getBookId() == null) ? 0 : getBookId().hashCode());
        result = prime * result + ((getBookName() == null) ? 0 : getBookName().hashCode());
        result = prime * result + ((getBookAuthor() == null) ? 0 : getBookAuthor().hashCode());
        result = prime * result + ((getBookDetails() == null) ? 0 : getBookDetails().hashCode());
        result = prime * result + ((getPrice() == null) ? 0 : getPrice().hashCode());
        result = prime * result + ((getOwner() == null) ? 0 : getOwner().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        result = prime * result + ((getRemark() == null) ? 0 : getRemark().hashCode());
        result = prime * result + ((getBookRef() == null) ? 0 : getBookRef().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", bookId=").append(bookId);
        sb.append(", bookName=").append(bookName);
        sb.append(", bookAuthor=").append(bookAuthor);
        sb.append(", bookDetails=").append(bookDetails);
        sb.append(", price=").append(price);
        sb.append(", owner=").append(owner);
        sb.append(", status=").append(status);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", remark=").append(remark);
        sb.append(", bookRef=").append(bookRef);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}
