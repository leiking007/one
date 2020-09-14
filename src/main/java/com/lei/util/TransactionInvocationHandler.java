package com.lei.util;

import org.apache.ibatis.session.SqlSession;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class TransactionInvocationHandler implements InvocationHandler {
    private Object target;

    public TransactionInvocationHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        SqlSession sqlSession=null;
        Object obj=null;
        try{
            sqlSession=SqlSessionUtil.getSqlSession();
            //处理业务逻辑
            obj=method.invoke(target,args);
            //业务逻辑完毕后，提交事务
            sqlSession.commit();
        }catch (Exception e){
            //出错回滚
            sqlSession.rollback();
            System.out.println(e.getMessage());
        }finally {
            SqlSessionUtil.closeSqlSession(sqlSession);
        }
        return obj;
    }

    /**
     * 取得代理对象
     * @return 代理对象
     */
    public Object getProxy(){
        return Proxy.newProxyInstance(target.getClass().getClassLoader(),
                target.getClass().getInterfaces(),
                this);
    }
}
