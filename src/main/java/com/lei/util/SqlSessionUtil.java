package com.lei.util;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class SqlSessionUtil {
    private static SqlSessionFactory sessionFactory;
    //静态代码块，只执行一次，类被加载时
    static {
        try {
            InputStream inputStream= Resources.getResourceAsStream("mybatis-config.xml");
            sessionFactory=new SqlSessionFactoryBuilder().build(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //私有化构造方法
    private SqlSessionUtil(){}

    //线程变量,意思是ThreadLocal中填充的变量属于当前线程，该变量对其他线程而言是隔离的；保证同一个SqlSession对象
    private static ThreadLocal<SqlSession> t=new ThreadLocal<>();

    //取得SqlSession对象
    public static SqlSession getSqlSession(){
        SqlSession sqlSession=t.get();
        if (sqlSession==null){
            sqlSession=sessionFactory.openSession();
            t.set(sqlSession);
        }
        return sqlSession;
    }

    //关闭SqlSession对象
    public static void closeSqlSession(SqlSession sqlSession){
        if (sqlSession!=null){
            sqlSession.close();
        //用户发送请求，服务器分配一条线程，执行结束后，线程回到线程池中，并没有被销毁；所以需要移除，保证线程的干净
            t.remove();
        }
    }
}
