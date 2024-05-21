package tyut.selab.bookservice.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 书籍借阅表
 * @TableName book_borrow
 */
public class BorrowBook implements Serializable {

    private int borrowId;

    /**
     * book_id
     */
    private Integer bookId;

    private Integer borrowUser;

    /**
     * 借阅时长(已天为单位)
     */
    private Integer borrowDuration;


    private Integer status;

    private Date borrowTime;
    /**
     * 归还时间
     */
    private Date returnTime;

    private static final long serialVersionUID = 1L;



    /**
     * book_id
     */
    public Integer getBookId() {
        return bookId;
    }

    /**
     * book_id
     */
    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }



    /**
     * 借阅时长(已天为单位)
     */
    public Integer getBorrowDuration() {
        return borrowDuration;
    }

    /**
     * 借阅时长(已天为单位)
     */
    public void setBorrowDuration(Integer borrowDuration) {
        this.borrowDuration = borrowDuration;
    }




    /**
     * 归还时间
     */
    public Date getReturnTime() {
        return returnTime;
    }

    /**
     * 归还时间
     */
    public void setReturnTime(Date returnTime) {
        this.returnTime = returnTime;
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
        BorrowBook other = (BorrowBook) that;
        return (this.getBookId() == null ? other.getBookId() == null : this.getBookId().equals(other.getBookId()))
            && (this.getBorrowDuration() == null ? other.getBorrowDuration() == null : this.getBorrowDuration().equals(other.getBorrowDuration()))
            && (this.getReturnTime() == null ? other.getReturnTime() == null : this.getReturnTime().equals(other.getReturnTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getBookId() == null) ? 0 : getBookId().hashCode());
        result = prime * result + ((getBorrowDuration() == null) ? 0 : getBorrowDuration().hashCode());
        result = prime * result + ((getReturnTime() == null) ? 0 : getReturnTime().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", bookId=").append(bookId);
        sb.append(", borrowDuration=").append(borrowDuration);
        sb.append(", returnTime=").append(returnTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

    public int getBorrowId() {
        return borrowId;
    }

    public void setBorrowId(Integer borrowId) {
        this.borrowId = borrowId;
    }

    public Integer getBorrowUser() {
        return borrowUser;
    }

    public void setBorrowUser(Integer borrowUser) {
        this.borrowUser = borrowUser;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getBorrowTime() {
        return borrowTime;
    }

    public void setBorrowTime(Date borrowTime) {
        this.borrowTime = borrowTime;
    }
}
