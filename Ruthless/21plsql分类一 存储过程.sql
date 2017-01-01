二十一、oracle pl/sql分类一 存储过程

存储过程用于执行特定的操作，当建立存储过程时，既可以指定输入参数(in)，也可以指定输出参数(out)，通过在过程中使用输入参数，可以将数据传递到执行部分；通过使用输出参数，可以将执行部分的数据传递到应用环境。在sqlplus中可以使用create procedure命令来建立过程。
实例如下：
1.请考虑编写一个存储过程，可以输入雇员名，新工资，用来修改雇员的工资

--根据雇员名去修改工资
CREATE PROCEDURE sp_update(uname VARCHAR2, newsal NUMBER) IS
BEGIN
   update emp set sal=newsal where ename=uname;
END;
/

2.如何调用存储过程有两种方法：exec、call

--使用exec调用存储过程
SQL> exec sp_update('zhangsan', 888);
SQL> commit;

3.如何在java程序中调用一个存储过程

复制代码
package junit.test;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;

/**
 * 演示java程序调用oracle的存储过程案例
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

            // 3.创建CallableStatement
            CallableStatement cs = ct.prepareCall("{call sp_update(?,?)}");
            // 4.给?赋值
            cs.setString(1, "SMITH");
            cs.setInt(2, 4444);
            // 5.执行
            cs.execute();
            // 关闭
            cs.close();
            ct.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
复制代码

问题：如何使用过程返回值？
特别说明：对于存储过程我们会在以后给大家详细具体的介绍，现在请大家先有一个概念。