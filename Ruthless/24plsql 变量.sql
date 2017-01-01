二十四、oracle pl/sql 变量

一、变量介绍
在编写pl/sql程序时，可以定义变量和常量；在pl/sql程序中包括有：
1)、标量类型(scalar)
2)、复合类型(composite) --用于操作单条记录
3)、参照类型(reference) --用于操作多条记录
4)、lob(large object)

二、标量(scalar)——常用类型
1)、在编写pl/sql块时，如果要使用变量，需在定义部分定义变量。
pl/sql中定义变量和常量的语法如下：
identifier [constant] datatype [not null] [:=| default expr]
identifier: 名称
constant：指定常量。需要指定它的初始值，且其值是不能改变的
datatype：数据类型
not null：指定变量值不能为null
:= 给变量或是常量指定初始值
default 用于指定初始值
expr ：指定初始值的pl/sql表达式，可以是文本值、其它变量、函数等。

2)、标量定义的案例
1.定义一个变长字符串
v_ename varchar2(10);
2.定义一个小数,范围-9999.99~9999.99
v_sal number(6,2);
3.定义一个小数并给一个初始值为5.4，:=是pl/sql的赋值号
v_sal2 number(6,2):=5.4;
4.定义一个日期类型的数据
v_hiredate date;
5.定义一个布尔变量，不能为空，初始值为false
v_valid boolean not null default false;

三、标量(scalar)——使用标量

在定义好变量后，就可以使用这些变量。这里需要说明的是pl/sql块为变量赋值不同于其它的编程语言，需要在等号前面加冒号(:=)。
下面以输入员工号，显示雇员姓名、工资、个人所得税(税率为0.03)为例。说明变量的使用，看看如何编写。

复制代码
set serveroutput on; --打开输出选项
DECLARE
  --税率为0.03
  C_TAX_RATE NUMBER(3, 2) :=0.03;
  --雇员姓名
  V_ENAME   VARCHAR2(5);
  --工资
  V_SAL     NUMBER(7, 2);
  --个人所得税
  V_TAX_SAL NUMBER(7, 2);
BEGIN
  --执行
  SELECT ENAME, SAL INTO V_ENAME, V_SAL FROM EMP WHERE EMPNO=&empno; --7369
  --计算所得税
  V_TAX_SAL := V_SAL * C_TAX_RATE;
  --输出
  DBMS_OUTPUT.PUT_LINE('雇员姓名：' || V_ENAME || '工资：' || V_SAL || ' 交税：' || V_TAX_SAL);
END;
/
复制代码

四、标量(scalar)——使用%type类型
对于上面的pl/sql块有一个问题：就是如果员工的姓名超过了5个字符的话，就会有“ORA-06502: PL/SQL: 数字或值错误 :  字符串缓冲区太小”错误，为了降低pl/sql程序的维护工作量，可以使用%type属性定义变量，这样它会按照数据库列来确定你定义的变量的类型和长度。
我们看看这个怎么使用：标识符名 表名.列名%type;
比如上例的v_ename，这样定义： v_ename emp.ename%type;

复制代码
set serveroutput on; --打开输出选项
DECLARE
  --税率为0.03
  C_TAX_RATE NUMBER(3, 2) :=0.03;
  --雇员姓名
  V_ENAME   emp.ename%TYPE;--推荐使用%type类型
  --工资
  V_SAL     NUMBER(7, 2);
  --个人所得税
  V_TAX_SAL NUMBER(7, 2);
BEGIN
  --执行
  SELECT ENAME, SAL INTO V_ENAME, V_SAL FROM EMP WHERE EMPNO=&empno; --7777
  --计算所得税
  V_TAX_SAL := V_SAL * C_TAX_RATE;
  --输出
  DBMS_OUTPUT.PUT_LINE('雇员姓名：' || V_ENAME || '工资：' || V_SAL || ' 交税：' || V_TAX_SAL);
END;
/
复制代码

五、复合变量(composite)——介绍
用于存放多个值的变量。主要包括这几种：
1)、pl/sql记录
2)、pl/sql表
3)、嵌套表
4)、varray

六、复合类型——pl/sql记录
类似于高级语言中的结构体，需要注意的是，当引用pl/sql记录成员时，必须要加记录变量作为前缀(记录变量.记录成员)如下：

复制代码
set serveroutput on; --打开输出选项
DECLARE
  --定义一个pl/sql记录类型emp_record_type，
  --类型包含3个数据NAME, SALARY, TITLE。说白了，就是一个类型可以存放3个数据，主要是为了方便管理 
  TYPE EMP_RECORD_TYPE IS RECORD(
    NAME   EMP.ENAME%TYPE,
    SALARY EMP.SAL%TYPE,
    TITLE  EMP.JOB%TYPE);
  --定义了一个sp_record变量，这个变量的类型是emp_record_type
  SP_RECORD EMP_RECORD_TYPE;
BEGIN
  SELECT ENAME, SAL, JOB INTO SP_RECORD FROM EMP WHERE EMPNO = 7788;
  DBMS_OUTPUT.PUT_LINE('员工名:' || SP_RECORD.NAME || '工资：' || SP_RECORD.SALARY);
END;
/
复制代码

七、复合类型——pl/sql表
相当于高级语言中的数组，但是需要注意的是在高级语言中数组的下标不能为负数，而pl/sql是可以为负数的，并且表元素的下标没有限制。实例如下：

复制代码
方法一(推荐)：
set serveroutput on; --打开输出选项
DECLARE
  --定义了一个pl/sql表类型sp_table_type，该类型是用于存放EMP.ENAME%TYPE
  --INDEX BY VARCHAR2(20)表示下标是字符串
  TYPE SP_TABLE_TYPE IS TABLE OF EMP.ENAME%TYPE INDEX BY VARCHAR2(20);
  --定义了一个sp_table变量，这个变量的类型是sp_table_type
  SP_TABLE SP_TABLE_TYPE;
BEGIN
  SELECT ENAME, sal INTO SP_TABLE('ename'), SP_TABLE('sal') FROM EMP WHERE EMPNO = 7788;
  DBMS_OUTPUT.PUT_LINE('员工名:' || SP_TABLE('ename')||'工资：'||SP_TABLE('sal'));
END;
/

方法二：
set serveroutput on; --打开输出选项
DECLARE
  --定义了一个pl/sql 表类型sp_table_type，该类型是用于存放EMP.ENAME%TYPE
  --index by binary_integer表示下标是整数
  TYPE SP_TABLE_TYPE IS TABLE OF EMP.ENAME%TYPE INDEX BY BINARY_INTEGER; --注意binary_integer如果换为integer就会报错，知道的朋友欢迎告诉我下
  --定义了一个sp_table变量，这个变量的类型是sp_table_type
  SP_TABLE SP_TABLE_TYPE;
BEGIN
  SELECT ENAME,sal INTO SP_TABLE(-1),SP_TABLE(-2) FROM EMP WHERE EMPNO = 7788;
  DBMS_OUTPUT.PUT_LINE('员工名:' || SP_TABLE(-1)||'工资：'||SP_TABLE(-2));
END;
/
复制代码
说明：
sp_table_type是pl/sql表类型
emp.ename%type 指定了表的元素的类型和长度
sp_table 为pl/sql表变量
sp_table(0) 则表示下标为0的元素
注意：如果把select ename into sp_table(-1) from emp where empno = 7788；变成select ename into sp_table(-1) from emp;则运行时会出现错误，错误如下：ORA-01422:实际返回的行数超出请求的行数
解决方法是：使用参照变量(这里不讲)

八、复合变量——嵌套表(nested table)
      复合变量——变长数组(varray)
省略

九、参照变量——介绍
参照变量是指用于存放数值指针的变量。通过使用参照变量，可以使得应用程序共享相同对象，从而降低占用的空间。在编写pl/sql程序时，可以使用游标变量(ref cursor)和对象类型变量(ref obj_type)两种参照变量类型。推荐使用游标变量。

十、参照变量——ref cursor游标变量
使用游标时，当定义游标时不需要指定相应的select语句，但是当使用游标时（open 时）需要指定select语句，这样一个游标与一个select语句结合了。实例如下：
1.请使用pl/sql编写一个块，可以输入部门号，并显示该部门所有员工姓名和他的工资。
2.在1的基础上，如果某个员工的工资低于200元，就添加100元。

复制代码
SET serveroutput ON;
DECLARE 
  --定义游标
  TYPE sp_emp_cursor IS REF CURSOR;
  --定义一个游标变量
  sp sp_emp_cursor;
  --定义变量
  v_ename emp.ename%TYPE;
  v_sal emp.sal%TYPE;
BEGIN
  OPEN sp FOR SELECT e.ename, e.sal FROM emp e WHERE e.deptno=10;
  --方法一 loop循环
  /*
  LOOP 
  FETCH sp INTO v_ename, v_sal;
  EXIT WHEN sp%NOTFOUND;
  DBMS_OUTPUT.PUT_LINE('名字：' || V_ENAME || ' 工资：' || V_SAL);
  END LOOP;*/
  --方法二 while循环
  /*
  WHILE 1=1 LOOP
    FETCH sp INTO v_ename, v_sal;
    EXIT WHEN sp%NOTFOUND;
    DBMS_OUTPUT.PUT_LINE('名字：' || V_ENAME || ' 工资：' || V_SAL);
  END LOOP;*/
  --方法三 for循环
  FOR cur IN (SELECT e.ename, e.sal FROM emp e WHERE e.deptno=10) LOOP
    DBMS_OUTPUT.PUT_LINE('名字：' || cur.ename || ' 工资：' || cur.sal);
  END LOOP;
END;
/
复制代码