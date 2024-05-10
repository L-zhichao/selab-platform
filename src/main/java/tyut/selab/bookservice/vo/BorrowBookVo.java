package tyut.selab.bookservice.vo;

import tyut.selab.bookservice.domain.BorrowBook;

import java.util.Date;

/**
 * @className: BorrowDto
 * @author: lizhichao
 * @description: TODO 书籍借阅Vo
 * @date: 2024/5/7 21:52
 * @version: 1.0
 */
public class BorrowBookVo {
    /**
     * 主键唯一标识
     */
    private Long borrowId;

    /**
     * book_id
     */
    private Integer bookId;
    /**
     * book_name
     */
    private String bookName;

    /**
     * 借阅用户id
     */
    private Integer borrowUser;
    /**
     * 借阅用户名称
     */
    private Integer borrowUserName;

    /**
     * 借阅时长(已天为单位)
     */
    private Integer borrowDuration;

    /**
     * 状态(0为未归还 1为已归还)
     */
    private Integer status;

    /**
     * 借阅时间
     */
    private Date borrowTime;

    /**
     * 归还时间
     */
    private Date returnTime;

    private static final long serialVersionUID = 1L;

    /**
     * 主键唯一标识
     */
    public Long getBorrowId() {
        return borrowId;
    }

    /**
     * 主键唯一标识
     */
    public void setBorrowId(Long borrowId) {
        this.borrowId = borrowId;
    }

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
     * 借阅用户id
     */
    public Integer getBorrowUser() {
        return borrowUser;
    }

    /**
     * 借阅用户id
     */
    public void setBorrowUser(Integer borrowUser) {
        this.borrowUser = borrowUser;
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
     * 状态(0为未归还 1为已归还)
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 状态(0为未归还 1为已归还)
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 借阅时间
     */
    public Date getBorrowTime() {
        return borrowTime;
    }

    /**
     * 借阅时间
     */
    public void setBorrowTime(Date borrowTime) {
        this.borrowTime = borrowTime;
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
        result = prime * result + ((getBorrowId() == null) ? 0 : getBorrowId().hashCode());
        result = prime * result + ((getBookId() == null) ? 0 : getBookId().hashCode());
        result = prime * result + ((getBorrowUser() == null) ? 0 : getBorrowUser().hashCode());
        result = prime * result + ((getBorrowDuration() == null) ? 0 : getBorrowDuration().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getBorrowTime() == null) ? 0 : getBorrowTime().hashCode());
        result = prime * result + ((getReturnTime() == null) ? 0 : getReturnTime().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", borrowId=").append(borrowId);
        sb.append(", bookId=").append(bookId);
        sb.append(", borrowUser=").append(borrowUser);
        sb.append(", borrowDuration=").append(borrowDuration);
        sb.append(", status=").append(status);
        sb.append(", borrowTime=").append(borrowTime);
        sb.append(", returnTime=").append(returnTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}
