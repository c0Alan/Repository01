九、oracle 事务

一、什么是事务
事务用于保证数据的一致性，它由一组相关的dml语句组成，该组的dml(数据操作语言，增删改，没有查询)语句要么全部成功，要么全部失败。
如：网上转账就是典型的要用事务来处理，用于保证数据的一致性。

二、事务和锁
当执行事务操作时(dml语句)，oracle会在被作用的表上加锁，防止其它用户修改表的结构。这里对我们的用户来讲是非常重要的。

三、提交事务
当用commit语句执行时可以提交事务。当执行了commit语句之后，会确认事务的变化、结束事务。删除保存点、释放锁，当使用commit语句结束事务之后，其它会话将可以查看到事务变化后的新数据。保存点就是为回滚做的。保存点的个数没有限制。

四、回滚事务
在介绍回滚事务前，我们先介绍一下保存点(savepoint)的概念和作用。保存点是事务中的一点。用于取消部分事务，当结束事务时，会自动的删除该事务所定义的所有保存点。当执行rollback 时，通过指定保存点可以回退到指定的点，这里我们作图说明。

五、事务的几个重要操作
1.设置保存点 savepoint a
2.取消部分事务 rollback to a
3.取消全部事务 rollback
eg、
SQL> savepoint a; --创建保存点a
Savepoint created

SQL> delete from emp where empno=7782;
1 row deleted

SQL> savepoint b; --创建保存到b
Savepoint created

SQL> delete from emp where empno=7934;
1 row deleted

SQL> select * from emp where empno=7934; --无法查询到empno为7934这条记录，因为这条记录已被删除
EMPNO ENAME      JOB         MGR HIREDATE          SAL      COMM DEPTNO
----- ---------- --------- ----- ----------- --------- --------- ------

SQL> rollback to b; --通过保持点来恢复这条记录
Rollback complete

SQL> select * from emp where empno=7934; 
EMPNO ENAME      JOB         MGR HIREDATE          SAL      COMM DEPTNO
----- ---------- --------- ----- ----------- --------- --------- ------
 7934 MILLER     CLERK      7782 1982/1/23     1300.00               10

SQL> select * from emp where empno=7782; --无法查询到empno为7982这条记录，因为这条记录已被删除
EMPNO ENAME      JOB         MGR HIREDATE          SAL      COMM DEPTNO
----- ---------- --------- ----- ----------- --------- --------- ------

SQL> rollback to a; --通过保持点来恢复这条记录
Rollback complete

SQL> select * from emp where empno=7782;
EMPNO ENAME      JOB         MGR HIREDATE          SAL      COMM DEPTNO
----- ---------- --------- ----- ----------- --------- --------- ------
 7782 CLARK      MANAGER    7839 1981/6/9      2450.00               10

SQL> 
注意：这个回滚事务，必须是没有commit前使用的；如果事务提交了，那么无论你刚才做了多少个保存点，都统统没用。如果没有手动执行commit,而是exit了，那么会自动提交。
eg、
SQL> savepoint a;
Savepoint created

SQL> delete from emp where empno=7782;
1 row deleted

SQL> commit;
Commit complete

SQL> rollback to a;
rollback to a
ORA-01086: 从未创建保存点 'A'
SQL>

六、java程序中如何使用事务
在java操作数据库时，为了保证数据的一致性，比如账户操作(1)从一个账户中减掉10$(2)在另一个账户上加入10$,我们看看如何使用事务？

java代码

复制代码
package junit.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class TransationTest {

    public static void main(String[] args) {

        Connection conn = null;
        try {
            // 1.加载驱动
            Class.forName("oracle.jdbc.driver.OracleDriver");
            // 2.得到连接
            conn = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:orcl", "scott", "oracle");
            Statement sm = conn.createStatement();
            // 从scott的sal中减去100
            sm.executeUpdate("update emp set sal=sal-100 where ename='SCOTT'");
            int i = 7 / 0; //报java.lang.ArithmeticException: / by zero异常
            // 给smith的sal加上100
            sm.executeUpdate("update emp set sal=sal+100 where ename='SMITH'");
            // 关闭打开的资源
            sm.close();
            conn.close();
        } catch (Exception e) {
            // 如果发生异常，就回滚
            try {
                conn.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        }

    }

}
复制代码
运行，会出现异常，查看数据库，SCOTT 的sal 减了100，但是SMITH 的sal 却不变，很可怕。。。
我们怎样才能保证，这两个操作要么同时成功，要么同时失败呢？

Java 代码

复制代码
package junit.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class TransationTest {

    public static void main(String[] args) {

        Connection conn = null;
        try {
            // 1.加载驱动
            Class.forName("oracle.jdbc.driver.OracleDriver");
            // 2.得到连接
            conn = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:orcl", "scott", "oracle");
            // 加入事务处理
            conn.setAutoCommit(false);// 设置不能默认提交
            Statement sm = conn.createStatement();
            // 从scott的sal中减去100
            sm.executeUpdate("update emp set sal=sal-100 where ename='SCOTT'");
            int i = 7 / 0;
            // 给smith的sal加上100
            sm.executeUpdate("update emp set sal=sal+100 where ename='SMITH'");
            // 提交事务
            conn.commit();
            // 关闭打开的资源
            sm.close();
            conn.close();
        } catch (Exception e) {
            // 如果发生异常，就回滚
            try {
                conn.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        }

    }

}
复制代码
再运行一下，会出现异常，查看数据库，数据没变化。。

七、只读事务
只读事务是指只允许执行查询的操作，而不允许执行任何其它dml操作的事务，使用只读事务可以确保用户只能取得某时间点的数据。
假定机票代售点每天18点开始统计今天的销售情况，这时可以使用只读事务。在设置了只读事务后，尽管其它会话可能会提交新的事务，但是只读事务将不会取得最新数据的变化，从而可以保证取得特定时间点的数据信息。
设置只读事务: set transaction read only;

比如有两个用户system、scott各自用sqlplus登陆，操作如下：
第一步：用system用户登陆sqlplus，设置只读事务。
SQL> set transaction read only;
事务处理集。

第二步：用scott用户登陆sqlplus，操作如下：
SQL> select count(*) from emp; --查询emp表的总记录数
  COUNT(*)
----------
        13

SQL> insert into emp values (7777, 'zhangsan', 'MANAGER', 7782, to_date('1988-02-18', 'yyyy-mm-dd'), 38.38, 45.45, 10); --插入一条记录到emp表
1 row inserted

SQL> select count(*) from emp; --查询emp表的总记录数
  COUNT(*)
----------
        14

SQL> commit; --提交
Commit complete

第三步：用system用户查询scott.emp表
SQL> select count(*) from scott.emp;
  COUNT(*)
----------
        13
SQL>