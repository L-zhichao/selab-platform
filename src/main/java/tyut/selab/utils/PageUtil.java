package tyut.selab.utils;

//#  Page
//
//## 一个专门用于返回分页数据的类。
//
//拥有`total`属性用于在第一次做查询时<即查询cur=1时>返回数据总量。前端通过total属性去确定查询cur的大小。
//
//🎗️用法： "在Dao层中做`Page`类的封装，service接口需要修改的直接修改就好，Page类中的方法按需添加即可."
//
//~~~java

import java.util.List;

/**
 *   分页查询包装类
 * @author 21017
 * @param <T>
 */
public class PageUtil<T>{
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

