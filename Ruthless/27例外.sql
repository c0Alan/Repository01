二十七、oracle 例外

一、例外分类
oracle将例外分为预定义例外、非预定义例外和自定义例外三种。
1)、预定义例外用于处理常见的oracle错误。
2)、非预定义例外用于处理预定义例外不能处理的例外。
3)、自定义例外用于处理与oracle错误无关的其它情况。

下面通过一个小案例演示如果不处理例外看会出现什么情况？
编写一个存储过程，可接收雇员的编号，并显示该雇员的姓名。
sql代码如下：

复制代码
SET SERVEROUTPUT ON;
DECLARE
  V_ENAME EMP.ENAME%TYPE;
BEGIN
  SELECT ENAME INTO V_ENAME FROM EMP WHERE EMPNO = &GNO;
  DBMS_OUTPUT.PUT_LINE('名字：' || V_ENAME);
END;
/
复制代码
随便输入不存在的编号，回车，会抛出如下异常：
ORA-01403: 未找到数据
ORA-06512: 在line 6    

例外捕获的sql代码如下：

复制代码
SET SERVEROUTPUT ON;
DECLARE
  V_ENAME EMP.ENAME%TYPE;
BEGIN
  SELECT ENAME INTO V_ENAME FROM EMP WHERE EMPNO = &GNO;
  DBMS_OUTPUT.PUT_LINE('名字：' || V_ENAME);
EXCEPTION 
  WHEN no_data_found THEN 
    DBMS_OUTPUT.PUT_LINE('编号未找到！');
END;
/
复制代码
随便输入不存在的编号，回车，会友情提示：编号未找到！

二、处理预定义例外
预定义例外是由pl/sql所提供的系统例外。当pl/sql应用程序违反了oracle规定的限制时，则会隐含的触发一个内部例外。pl/sql为开发人员提供了二十多个预定义例外。我们给大家介绍常用的例外。
1)、case_not_found预定义例外
在开发pl/sql块中编写case语句时，如果在when子句中没有包含必须的条件分支，就会触发case_not_found例外：

复制代码
SET SERVEROUTPUT ON;
CREATE OR REPLACE PROCEDURE SP_PRO6(SPNO NUMBER) IS
  V_SAL EMP.SAL%TYPE;
BEGIN
  SELECT SAL INTO V_SAL FROM EMP WHERE EMPNO = SPNO;
  CASE
    WHEN V_SAL < 1000 THEN
      UPDATE EMP SET SAL = SAL + 100 WHERE EMPNO = SPNO;
    WHEN V_SAL < 2000 THEN
      UPDATE EMP SET SAL = SAL + 200 WHERE EMPNO = SPNO;
  END CASE;
EXCEPTION
  WHEN CASE_NOT_FOUND THEN
    DBMS_OUTPUT.PUT_LINE('case语句没有与' || V_SAL || '相匹配的条件');
END;
/

--调用存储过程
SQL> EXEC SP_PRO6(7369);
case语句没有与4444相匹配的条件
复制代码
2)、cursor_already_open预定义例外 
当重新打开已经打开的游标时，会隐含的触发cursor_already_open例外

复制代码
DECLARE
  CURSOR EMP_CURSOR IS
    SELECT ENAME, SAL FROM EMP;
BEGIN
  OPEN EMP_CURSOR; --声明时游标已打开，所以没必要再次打开
  FOR EMP_RECORD1 IN EMP_CURSOR LOOP
    DBMS_OUTPUT.PUT_LINE(EMP_RECORD1.ENAME);
  END LOOP;
EXCEPTION
  WHEN CURSOR_ALREADY_OPEN THEN
    DBMS_OUTPUT.PUT_LINE('游标已经打开');
END;
/
复制代码
3)、dup_val_on_index预定义例外 
在唯一索引所对应的列上插入重复的值时，会隐含的触发例外

复制代码
BEGIN
  INSERT INTO DEPT VALUES (10, '公关部', '北京');
EXCEPTION
  WHEN DUP_VAL_ON_INDEX THEN
    DBMS_OUTPUT.PUT_LINE('在deptno列上不能出现重复值');
END;
/
复制代码
4)、invalid_cursorn预定义例外 
当试图在不合法的游标上执行操作时，会触发该例外
例如：试图从没有打开的游标提取数据，或是关闭没有打开的游标。则会触发该例外

复制代码
DECLARE
  CURSOR EMP_CURSOR IS
    SELECT ENAME, SAL FROM EMP;
  EMP_RECORD EMP_CURSOR%ROWTYPE;
BEGIN
  --open emp_cursor; --打开游标
  FETCH EMP_CURSOR INTO EMP_RECORD;
  DBMS_OUTPUT.PUT_LINE(EMP_RECORD.ENAME);
  CLOSE EMP_CURSOR;
EXCEPTION
  WHEN INVALID_CURSOR THEN
    DBMS_OUTPUT.PUT_LINE('请检测游标是否打开');
END;
/
复制代码
5)、invalid_number预定义例外 
当输入的数据有误时，会触发该例外
比如：数字100写成了loo就会触发该例外

复制代码
SET SERVEROUTPUT ON;
BEGIN
  UPDATE EMP SET SAL = SAL + 'AAA';
EXCEPTION
  WHEN INVALID_NUMBER THEN
    DBMS_OUTPUT.PUT_LINE('输入的数字不正确');
END;
/
复制代码
6)、no_data_found预定义例外 
下面是一个pl/sql 块，当执行select into没有返回行，就会触发该例外

复制代码
SET serveroutput ON;
DECLARE
  V_SAL EMP.SAL%TYPE;
BEGIN
  SELECT SAL INTO V_SAL FROM EMP WHERE ENAME = 'ljq';
EXCEPTION
  WHEN NO_DATA_FOUND THEN
    DBMS_OUTPUT.PUT_LINE('不存在该员工');
END;
/
复制代码
7)、too_many_rows预定义例外 
当执行select into语句时，如果返回超过了一行，则会触发该例外。

复制代码
DECLARE
  V_ENAME EMP.ENAME%TYPE;
BEGIN
  SELECT ENAME INTO V_ENAME FROM EMP;
EXCEPTION
  WHEN TOO_MANY_ROWS THEN
    DBMS_OUTPUT.PUT_LINE('返回了多行');
END;
/
复制代码
8)、zero_divide预定义例外 
当执行2/0语句时，则会触发该例外
9)、value_error预定义例外
当在执行赋值操作时，如果变量的长度不足以容纳实际数据，则会触发该例外value_error

其它预定义例外(这些例外不是在pl/sql里触发的，而是在用oracle时触发的，所以取名叫其它预定义例外)
1、login_denied
当用户非法登录时，会触发该例外
2、not_logged_on
如果用户没有登录就执行dml操作，就会触发该例外
3、storage_error
如果超过了内存空间或是内存被损坏，就触发该例外
4、timeout_on_resource
如果oracle在等待资源时，出现了超时就触发该例外

三、非预定义例外
非预定义例外用于处理与预定义例外无关的oracle错误。使用预定义例外只能处理21个oracle 错误，而当使用pl/sql开发应用程序时，可能会遇到其它的一些oracle错误。比如在pl/sql块中执行dml语句时，违反了约束规定等等。在这样的情况下，也可以处理oracle的各种例外，因为非预定义例外用的不多，这里我就不举例了。

四、处理自定义例外
预定义例外和自定义例外都是与oracle错误相关的，并且出现的oracle 错误会隐含的触发相应的例外；而自定义例外与oracle 错误没有任何关联，它是由开发人员为特定情况所定义的例外.
问题：请编写一个pl/sql 块，接收一个雇员的编号，并给该雇员工资增加1000元，如果该雇员不存在，请提示。

复制代码
CREATE OR REPLACE PROCEDURE EX_TEST(SPNO NUMBER) IS
BEGIN
  UPDATE EMP SET SAL = SAL + 1000 WHERE EMPNO = SPNO;
END;
/

--调用存储过程，
EXEC EX_TEST(56);
复制代码
这里，编号为56 是不存在的，刚才的报异常了，为什么现在不报异常呢？
因为刚才的是select语句
怎么解决这个问题呢？ 修改代码，如下：

复制代码
--自定义例外
CREATE OR REPLACE PROCEDURE EX_TEST(SPNO NUMBER) IS
--定义一个例外
MYEX EXCEPTION;
BEGIN
--更新用户sal
UPDATE EMP SET SAL = SAL + 1000 WHERE EMPNO = SPNO;
--sql%notfound 这是表示没有update
--raise myex;触发myex
IF SQL%NOTFOUND THEN RAISE MYEX;
END IF;
EXCEPTION
WHEN MYEX THEN DBMS_OUTPUT.PUT_LINE('没有更新任何用户');
END;
/