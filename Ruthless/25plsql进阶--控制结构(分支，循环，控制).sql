二十五、oracle pl/sql进阶--控制结构(分支，循环，控制)

一、pl/sql的进阶--控制结构
在任何计算机语言(c，java，pascal)都有各种控制语句(条件语句，循环结构，顺序控制结构...)，在pl/sql中也存在这样的控制结构。
在本部分学习完成后，希望大家达到：
1.使用各种if语句
2.使用循环语句
3.使用控制语句——goto和null(goto语句不推荐使用)；

二、条件分支语句
pl/sql中提供了三种条件分支语句if—then，if–then–else，if–then–else if–then。
这里我们可以和java语句进行一个比较。

1)、简单的条件判断if–then
问题：编写一个过程，可以输入一个雇员名，如果该雇员的工资低于2000，就给该员工工资增加10%。

复制代码
SET serveroutput ON;
CREATE OR REPLACE PROCEDURE SP_PRO6(SPNAME VARCHAR2) IS
  --定义
  V_SAL EMP.SAL%TYPE;
BEGIN
  --执行
  SELECT SAL INTO V_SAL FROM EMP WHERE ENAME = SPNAME;
  --判断
  IF V_SAL < 2000 THEN
    UPDATE EMP SET SAL = SAL + SAL * 0.1 WHERE ENAME = SPNAME;
    COMMIT;
  END IF;
END;
/

--调用存储过程
exec SP_PRO6('ALLEN');
复制代码
2)、二重条件分支 if–then–else
问题：编写一个过程，可以输入一个雇员名，如果该雇员的补助不是0就在原来的基础上增加100；如果补助为0就把补助设为200；

复制代码
CREATE OR REPLACE PROCEDURE SP_PRO6(SPNAME VARCHAR2) IS
  --定义
  V_COMM EMP.COMM%TYPE;
BEGIN
  --执行
  SELECT COMM INTO V_COMM FROM EMP WHERE ENAME = SPNAME;
  --判断
  IF V_COMM <> 0 THEN
    UPDATE EMP SET COMM = COMM + 100 WHERE ENAME = SPNAME;
  ELSE
    UPDATE EMP SET COMM = COMM + 200 WHERE ENAME = SPNAME;
  END IF;
  COMMIT;
END;
/

--调用存储过程
exec SP_PRO6('ALLEN');
复制代码
3)、多重条件分支 if–then–ELSIF–then
问题：编写一个过程，可以输入一个雇员编号，如果该雇员的职位是PRESIDENT就给他的工资增加1000，如果该雇员的职位是MANAGER 就给他的工资增加500，其它职位的雇员工资增加200。

复制代码
CREATE OR REPLACE PROCEDURE SP_PRO6(SPNO NUMBER) IS
  --定义
  V_JOB EMP.JOB%TYPE;
BEGIN
  --执行
  SELECT JOB INTO V_JOB FROM EMP WHERE EMPNO = SPNO;
  IF V_JOB = 'PRESIDENT' THEN
    UPDATE EMP SET SAL = SAL + 1000 WHERE EMPNO = SPNO;
  ELSIF V_JOB = 'MANAGER' THEN
    UPDATE EMP SET SAL = SAL + 500 WHERE EMPNO = SPNO;
  ELSE
    UPDATE EMP SET SAL = SAL + 200 WHERE EMPNO = SPNO;
  END IF;
  COMMIT;
END;
/
--调用存储过程
exec SP_PRO6(7499);
复制代码

三、循环语句–loop
是pl/sql中最简单的循环语句，这种循环语句以loop开头，以end loop结尾，这种循环至少会被执行一次。
案例：现有一张表users，表结构如下：
用户vid | 用户名 uname

CREATE TABLE USERS(
vid NUMBER(5),
uname VARCHAR2(30)
);
请编写一个过程，可以输入用户名，并循环添加10个用户到users表中，用户编号从1开始增加。

复制代码
CREATE OR REPLACE PROCEDURE SP_PRO6(SPNAME VARCHAR2) IS
  --定义 :=表示赋值
  V_NUM NUMBER := 1;
BEGIN
  LOOP
    INSERT INTO USERS VALUES (V_NUM, SPNAME);
    --判断是否要退出循环
    EXIT WHEN V_NUM = 10;
    --自增
    V_NUM := V_NUM + 1;
  END LOOP;
  COMMIT;
END;
/

--调用存储过程
EXEC SP_PRO6('ALLEN');
复制代码

四、循环语句–while循环
基本循环至少要执行循环体一次，而对于while循环来说，只有条件为true时，才会执行循环体语句，while循环以while...loop开始，以end loop 结束。
案例：现有一张表users，表结构如下：
用户vid | 用户名 uname
问题：请编写一个过程，可以输入用户名，并循环添加10个用户到users表中，用户编号从11开始增加。

复制代码
CREATE OR REPLACE PROCEDURE SP_PRO6(SPNAME VARCHAR2) IS
  --定义 :=表示赋值
  V_NUM NUMBER := 11;
BEGIN
  WHILE V_NUM <= 20 LOOP
    --执行
    INSERT INTO USERS VALUES (V_NUM, SPNAME);
    V_NUM := V_NUM + 1;
  END LOOP;
  COMMIT;
END;
/

--调用存储过程
EXEC SP_PRO6('ALLEN');
复制代码

五、循环语句–for循环
基本for循环的基本结构如下

复制代码
CREATE OR REPLACE PROCEDURE SP_PRO6 IS--注意如果无参记得不要加()
BEGIN
  FOR I IN REVERSE 1 .. 10 LOOP --REVERSE反转函数，表示I从10到1递减，去掉REVERSE表示I从1到10递增
    INSERT INTO USERS VALUES (I, 'shunping');
  END LOOP;
END;
/

--调用存储过程
EXEC SP_PRO6;
复制代码
我们可以看到控制变量i，在隐含中就在不停地增加。

六、顺序控制语句–goto、null
1)、goto语句
goto语句用于跳转到特定符号去执行语句。注意由于使用goto语句会增加程序的复杂性，并使得应用程序可读性变差，所以在做一般应用开发时，建议大家不要使用goto语句。
基本语法如下goto lable，其中lable是已经定义好的标号名

复制代码
set serveroutput on;
DECLARE
  I INT := 1;
BEGIN
  LOOP
    DBMS_OUTPUT.PUT_LINE('输出i=' || I);
    IF I = 1 THEN
      GOTO END_LOOP;
    END IF;
    I := I + 1;
  END LOOP;
  <<END_LOOP>>
  DBMS_OUTPUT.PUT_LINE('循环结束');
END;
/
复制代码

2)、null语句
null语句不会执行任何操作，并且会直接将控制传递到下一条语句。使用null语句的主要好处是可以提高pl/sql的可读性。

复制代码
SET serveroutput ON;
DECLARE
  V_SAL   EMP.SAL%TYPE;
  V_ENAME EMP.ENAME%TYPE;
BEGIN
  SELECT ENAME, SAL INTO V_ENAME, V_SAL FROM EMP WHERE EMPNO = &NO;
  IF V_SAL < 3000 THEN
    UPDATE EMP SET COMM = SAL * 0.1 WHERE ENAME = V_ENAME;
    dbms_output.put_line('1111');
  ELSE
    NULL;
    dbms_output.put_line('2222');--不会被执行
  END IF;
END;
/