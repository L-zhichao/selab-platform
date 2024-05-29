package tyut.selab.utils;

import java.util.List;

/**
 *   分页查询包装类
 * @param <T>
 */
public class Page<T> {
    // 分页返回list数据
    private List<T> data;
    // 数据数量 （仅第一次返回即可）
    private Integer total;
    // 每页返回数量
    private Integer size;
    // 返回第几页 ( 从1开始）
    private Integer cur;

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Integer getCur() {
        return cur;
    }

    public void setCur(Integer cur) {
        this.cur = cur;
    }


}