package tyut.selab.bookservice.dao;

import tyut.selab.utils.JdbcUtil;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * ClassName: BaseDao
 * Package: tyut.selab.bookservice.dao
 * Description:
 *
 * @Author The_fish
 * @Create 2024/5/12 20:34
 * @Version 1.0
 */
public class BaseDao {
    //公共修改方法
    public int baseUpdate(String sql,Object... params) throws SQLException {
        //获取连接
        Connection connection = JdbcUtil.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        //占位符赋值
        for (int i = 1; i <= params.length; i++) {
            preparedStatement.setObject(i, params[i - 1]);
        }
        //发送sql语句
        int rows = preparedStatement.executeUpdate();
        preparedStatement.close();
        //是否回收连接，需要考虑是不是事务
        if (connection.getAutoCommit()){
            //没有开启事务
            JdbcUtil.freeConnection();
        }
        return rows;
    }

    //公共查询方法
    public <T> List<T> baseQuery(Class<T> clazz, String sql, Object... params) throws SQLException, InstantiationException, IllegalAccessException, NoSuchFieldException {
        Connection connection = JdbcUtil.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        if(params != null && params.length != 0){
            for (int i = 1; i <= params.length; i++) {
                preparedStatement.setObject(i,params[i-1]);
            }
        }
        ResultSet resultSet = preparedStatement.executeQuery();
        List<T> list = new ArrayList<>();
        ResultSetMetaData metaData = resultSet.getMetaData();
        int columnCount = metaData.getColumnCount();
        while(resultSet.next()){
            T t =clazz.newInstance(); //调用类的无参构造函数实例化对象
            for (int i = 1; i <= columnCount; i++) {
                Object object = resultSet.getObject(i);
                String columnLabel = metaData.getColumnLabel(i);
                //反射给对象的属性值赋值
                Field declaredField = clazz.getDeclaredField(columnLabel);
                declaredField.setAccessible(true); //属性可以设置
                declaredField.set(t,object);

            }
            list.add(t);
        }
        resultSet.close();
        preparedStatement.close();
        if (connection.getAutoCommit()){
            JdbcUtil.freeConnection();
        }
        return list;
    }
}
