二十六、oracle pl/sql 分页

一、无返回值的存储过程

古人云：欲速则不达，为了让大家伙比较容易接受分页过程编写，我还是从简单到复杂，循序渐进的给大家讲解。首先是掌握最简单的存储过程，无返回值的存储过程。 案例：现有一张表book，表结构如下：书号、书名、出版社。

CREATE TABLE book(
   ID NUMBER(4),
   book_name VARCHAR2(30),
   publishing VARCHAR2(30)
);

请写一个过程，可以向book表添加书，要求通过java程序调用该过程。

复制代码
--注意：in->表示这是一个输入参数，默认为in --out->表示一个输出参数
CREATE OR REPLACE PROCEDURE ADD_BOOK(ID         IN NUMBER,
                                     NAME       IN VARCHAR2,
                                     PUBLISHING IN VARCHAR2) IS
BEGIN
  INSERT INTO BOOK VALUES (ID, NAME, PUBLISHING);
  COMMIT;
END;
/
复制代码

java程序调用该存储过程的代码

复制代码
package junit.test;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;

/**
 * 调用一个无返回值的存储过程
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
            CallableStatement cs = ct.prepareCall("call ADD_BOOK(?,?,?)");
            //给?赋值
            cs.setInt(1, 1);
            cs.setString(2, "java");
            cs.setString(3, "java出版社");
            // 4.执行
            cs.execute();
            //5、关闭
            cs.close();
            ct.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
复制代码

二、有返回值的存储过程(非列表)
案例：编写一个存储过程，可以输入雇员的编号，返回该雇员的姓名。

--输入和输出的存储过程
CREATE OR REPLACE PROCEDURE SP_PROC(SPNO IN NUMBER, SPNAME OUT VARCHAR2) IS
BEGIN
  SELECT ENAME INTO SPNAME FROM EMP WHERE EMPNO = SPNO;
END;
/

java程序调用该存储过程的代码

复制代码
package junit.test;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;

/**
 * 调用一个无返回值的存储过程
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
            CallableStatement cs = ct.prepareCall("{call sp_proc(?,?)}");
            //给第一个?赋值
            cs.setInt(1,7788);
            //给第二个?赋值
            cs.registerOutParameter(2,oracle.jdbc.OracleTypes.VARCHAR);
            //4、执行
            cs.execute();
            //取出返回值,要注意？的顺序
            String name=cs.getString(2);
            System.out.println("编号7788的名字："+name);
            //5、关闭
            cs.close();
            ct.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
复制代码

案例扩张：编写一个过程，可以输入雇员的编号，返回该雇员的姓名、工资和岗位。

复制代码
--输入和输出的存储过程
CREATE OR REPLACE PROCEDURE SP_PROC(SPNO   IN NUMBER,
                                    SPNAME OUT VARCHAR2,
                                    SPSAL  OUT NUMBER,
                                    SPJOB  OUT VARCHAR2) IS
BEGIN
  SELECT ENAME, SAL, JOB INTO SPNAME, SPSAL, SPJOB FROM EMP WHERE EMPNO = SPNO;
END;
/
复制代码

java程序调用该存储过程的代码

复制代码
package junit.test;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;

/**
 * 调用一个无返回值的存储过程
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
            CallableStatement cs = ct.prepareCall("{call sp_proc(?,?,?,?)}");
            //给第一个?赋值
            cs.setInt(1,7788);
            //给第二个?赋值
            cs.registerOutParameter(2,oracle.jdbc.OracleTypes.VARCHAR);
            //给第三个？赋值
            cs.registerOutParameter(3,oracle.jdbc.OracleTypes.DOUBLE);
            //给第四个？赋值
            cs.registerOutParameter(4,oracle.jdbc.OracleTypes.VARCHAR);
            //4、执行
            cs.execute();
            //取出返回值,要注意？的顺序
            String name=cs.getString(2);
            double sal=cs.getDouble(3);
            String job=cs.getString(4);
            System.out.println("编号7788的名字："+name+"，职位："+job+"，薪水："+sal+"");
            //5、关闭
            cs.close();
            ct.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
复制代码

三、有返回值的存储过程(列表[结果集])
案例：编写一个存储过程，输入部门号，返回该部门所有雇员信息。
该题分析如下：由于oracle存储过程没有返回值，它的所有返回值都是通过out参数来替代的，列表同样也不例外，但由于是集合，所以不能用一般的参数，必须要用pagkage了。所以要分两部分：
1)、建立一个包，在该包中我们定义类型test_cursor，它是个游标。

CREATE OR REPLACE PACKAGE TESTPACKAGE AS
  TYPE TEST_CURSOR IS REF CURSOR;
END TESTPACKAGE;
/

2)、建立存储过程。

复制代码
CREATE OR REPLACE PROCEDURE SP_PROC(SPNO     IN NUMBER,
                                    P_CURSOR OUT TESTPACKAGE.TEST_CURSOR) IS
BEGIN
  OPEN P_CURSOR FOR
    SELECT * FROM EMP WHERE DEPTNO = SPNO;
END SP_PROC;
/
复制代码

3)、如何在java 程序中调用该过程

复制代码
package junit.test;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

/**
 * 调用一个无返回值的存储过程
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
            CallableStatement cs = ct.prepareCall("{call sp_proc(?,?)}");
            //给第一个?赋值
            cs.setInt(1,10);
            //给第二个?赋值
            cs.registerOutParameter(2,oracle.jdbc.OracleTypes.CURSOR);
            //4、执行
            cs.execute();
            //得到结果集
            ResultSet rs = (ResultSet) cs.getObject(2);
            while (rs.next()) {
                System.out.println(rs.getInt(1) + " " + rs.getString(2));
            }
            //5、关闭
            rs.close();
            cs.close();
            ct.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
复制代码

四、编写分页过程
有了上面的基础，相信大家可以完成分页存储过程了。
要求，请大家编写一个存储过程，要求可以输入表名、每页显示记录数、当前页。返回总记录数，总页数，和返回的结果集。

--ROWNUM用法
SELECT o.*, ROWNUM RN FROM (SELECT * FROM EMP) o WHERE ROWNUM <= 10;
----oracle分页sql语句；在分页时，大家可以把下面的sql语句当做一个模板使用
SELECT *
FROM (SELECT o.*, ROWNUM RN FROM (SELECT * FROM EMP) o WHERE ROWNUM <= 10)
WHERE RN >= 6;

1)、开发一个包
建立一个包，在该包中定义类型为test_cursor的游标。 

复制代码
--建立一个包
CREATE OR REPLACE PACKAGE TESTPACKAGE AS
  TYPE TEST_CURSOR IS REF CURSOR;
END TESTPACKAGE;
/

--开始编写分页的过程
CREATE OR REPLACE PROCEDURE FENYE(TABLENAME IN VARCHAR2, 
                                  PAGESIZE IN NUMBER, --每页显示记录数
                                  PAGENOW IN NUMBER, --页数
                                  MYROWS OUT NUMBER, --总记录数
                                  MYPAGECOUNT OUT NUMBER, --总页数
                                  P_CURSOR OUT TESTPACKAGE.TEST_CURSOR) IS --返回的记录集

--定义部分
--定义sql语句字符串
V_SQL VARCHAR2(1000);
--定义两个整数
V_BEGIN NUMBER := (PAGENOW - 1) * PAGESIZE + 1; 
V_END NUMBER := PAGENOW * PAGESIZE;
BEGIN
--执行部分
V_SQL := 'select * from (select t1.*, rownum rn from (select * from ' || TABLENAME || ') t1 where rownum<=' || V_END || ') where rn>=' || V_BEGIN;
--把游标和sql关联
OPEN P_CURSOR FOR V_SQL;
--计算myrows和myPageCount
--组织一个sql语句
V_SQL := 'select count(*) from ' || TABLENAME;
--执行sql,并把返回的值，赋给myrows；
EXECUTE ImMEDIATE V_SQL INTO MYROWS; --它解析并马上执行动态的SQL语句或非运行时创建的PL/SQL块.动态创建和执行SQL语句性能超前，
                                     --EXECUTE IMMEDIATE的目标在于减小企业费用并获得较高的性能，较之以前它相当容易编码.
                                     --尽管DBMS_SQL仍然可用，但是推荐使用EXECUTE IMMEDIATE,因为它获的收益在包之上。 
--计算myPageCount
--if myrows%Pagesize=0 then 这样写是错的
IF MOD(MYROWS, PAGESIZE) = 0 THEN 
  MYPAGECOUNT := MYROWS/PAGESIZE; 
ELSE 
  MYPAGECOUNT := MYROWS/PAGESIZE + 1;
END IF;
--关闭游标
--CLOSE P_CURSOR; --不要关闭，否则java调用该存储过程会报错
END;
/
复制代码

java调用分页代码

复制代码
package junit.test;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

/**
 * 调用一个无返回值的存储过程
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
            CallableStatement cs = ct.prepareCall("{call fenye(?,?,?,?,?,?)}");
            cs.setString(1, "emp"); //表名
            cs.setInt(2, 5); //每页显示记录数
            cs.setInt(3, 1);//页数
            // 注册总记录数
            cs.registerOutParameter(4, oracle.jdbc.OracleTypes.INTEGER); //总记录数
            // 注册总页数
            cs.registerOutParameter(5, oracle.jdbc.OracleTypes.INTEGER); //总页数
            // 注册返回的结果集
            cs.registerOutParameter(6, oracle.jdbc.OracleTypes.CURSOR); //返回的记录集
            // 4、执行
            cs.execute();
            // 得到结果集
            // 取出总记录数 /这里要注意，getInt(4)中4，是由该参数的位置决定的
            int rowNum = cs.getInt(4);

            int pageCount = cs.getInt(5);
            ResultSet rs = (ResultSet) cs.getObject(6);
            // 显示一下，看看对不对
            System.out.println("rowNum=" + rowNum);
            System.out.println("总页数=" + pageCount);

            while (rs.next()) {
                System.out.println("编号：" + rs.getInt(1) + 
                        " 名字：" + rs.getString(2) + 
                        " 工资：" + rs.getFloat(6));
            }
            // 5、关闭
            //rs.close();
            cs.close();
            ct.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}