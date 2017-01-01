二十二、oracle pl/sql分类二 函数

函数用于返回特定的数据，当建立函数时，在函数头部必须包含return子句。而在函数体内必须包含return语句返回的数据。我们可以使用create function来建立函数。

1）、接下来通过一个案例来模拟函数的用法

复制代码
--输入雇员的姓名，返回该雇员的年薪
CREATE FUNCTION annual_incomec(uname VARCHAR2)
RETURN NUMBER IS 
annual_salazy NUMBER(7,2);
BEGIN 
   SELECT a.sal*13 INTO annual_salazy FROM emp a WHERE a.ename=uname;
   RETURN annual_salazy;
END;
/
复制代码

2）、在sqlplus中调用函数 

SQL> var income NUMBER;
SQL> call annual_incomec('SCOTT') into:income;
SQL> print income;

3）、在java程序中调用oracle函数：select annual_incomec('SCOTT') income from dual;

复制代码
package junit.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * 演示java程序调用oracle的函数案例
 * 
 * @author jiqinlin
 *
 */
public class ProcedureTest {

    public static void main(String[] args) {

        try {
            // 1.加载驱动
            Class.forName("oracle.jdbc.driver.OracleDriver");
            // 2.得到连接
            Connection ct = DriverManager.getConnection(
                    "jdbc:oracle:thin:@127.0.0.1:1521:orcl", "scott", "oracle");
            // 3.创建PreparedStatement
            PreparedStatement ps = ct.prepareStatement("select annual_incomec('SCOTT') annual from dual");
            // 4.执行
            ResultSet rs=ps.executeQuery();
            if(rs.next()){
                Float annual=rs.getFloat("annual");
                System.out.println(annual);
            }
            //5、关闭
            rs.close();
            ps.close();
            ct.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
复制代码