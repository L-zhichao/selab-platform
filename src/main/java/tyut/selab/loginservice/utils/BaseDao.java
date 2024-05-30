package tyut.selab.loginservice.utils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 封装当前类的目的：为了简化创建statement,运输sql语句，返回结果集等的代码量，作为一个抽象类，用于简化Dao层的压力
 */
public abstract class BaseDao {
    //DAO(database operator)
    public int executeUpdate(String sql,Object... params) throws SQLException {
        //可变形参看作数组处理
        Connection connection = JdbcUtilsV2.getConnection();
        //造车
        PreparedStatement statement = connection.prepareStatement(sql);
        for (int i = 1; i <= params.length; i++) {
            statement.setObject(i,params[i-1]);
        }
        int rows = statement.executeUpdate();
        statement.close();
        if(connection.getAutoCommit()){
            JdbcUtilsV2.freeConnection();
        }
        return rows;
    }

    /**
     * 我们查询操作需要返回一个结果集，结果集中存入我们从数据库中获取的数据
     *
     */
    public <T> List<T> executeQuery(Class<T> clazz,String sql,Object... params) throws SQLException, InstantiationException, IllegalAccessException, NoSuchFieldException, NoSuchMethodException, InvocationTargetException {
        Connection connection = JdbcUtilsV2.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        if (params != null && params.length != 0) {//如果不进行判断到时候可能会抛出空指针异常，要让代码具有一些健壮性
            for (int i = 1; i <= params.length ; i++) {
                statement.setObject(i,params[i-1]);
            }
        }
        ResultSet resultSet = statement.executeQuery();
        ResultSetMetaData metaData = resultSet.getMetaData();
        List<T> arrayList = new ArrayList<>();
        //创建一个实现类进行结果接收，数据库中字段作为其属性通过反射进行赋值
        while (resultSet.next()) {
            T t = null;//使用 newInstance() 时，需要确保类具有公共的无参数构造方法或者符合预期的构造方法
            t = (T)clazz.getDeclaredConstructor().newInstance();
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                String propertyName = metaData.getColumnLabel(i);
                Object value = resultSet.getObject(i);
                Field field = clazz.getDeclaredField(propertyName);//如果类中没有相关的属性，那么就会抛出异常，所以我们得先创建一个javabean来存放数据库信息
                field.setAccessible(true);
                field.set(t,value);
            }
            arrayList.add(t);
        }
        return arrayList;
    }
    public <T> T executeQueryOne(Class<T> clazz, String sql, Object ... args) throws SQLException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchFieldException {
        T t = null;
        Connection connection = null;
        connection = JdbcUtilsV2.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        // 准备语句对象
        preparedStatement = connection.prepareStatement(sql);
        // 设置语句上的参数
        for (int i = 0; i < args.length; i++) {
            preparedStatement.setObject(i + 1, args[i]);
        }
        // 执行 查询
        resultSet = preparedStatement.executeQuery();
        ResultSetMetaData metaData = resultSet.getMetaData();
        int columnCount = metaData.getColumnCount();
        if (resultSet.next()) {
            Object obj =clazz.getDeclaredConstructor().newInstance();
            for (int i = 1; i <= columnCount; i++) {
                String columnName = metaData.getColumnLabel(i);
                Object value = resultSet.getObject(columnName);
                Field field = clazz.getDeclaredField(columnName);
                field.setAccessible(true);
                field.set(obj,value);
            }
            t = (T)obj;
        }
        if (null != resultSet) {
            resultSet.close();
        }
        if (null != preparedStatement) {
            preparedStatement.close();
        }
        JdbcUtilsV2.freeConnection();
        return t;
    }
}