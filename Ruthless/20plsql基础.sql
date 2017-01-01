二十、oracle pl/sql基础

一、pl/sql developer开发工具
pl/sql developer是用于开发pl/sql块的集成开发环境(ide)，它是一个独立的产品，而不是oracle的一个附带品。

二、pl/sql介绍
开发人员使用pl/sql编写应用模块时，不仅需要掌握sql语句的编写方法，还要掌握pl/sql语句及语法规则。pl/sql编程可以使用变量和逻辑控制语句，从而可以编写非常有用的功能模块。比如：分页存储过程模块、订单处理存储过程模块、转账存储过程模块。而且如果使用pl/sql编程，我们可以轻松地完成非常复杂的查询要求。

三、pl/sql可以做什么
可以用来编写存储过程、函数、触发器、包等

四、编写规范

五、pl/sql块介绍
块(block)是pl/sql的基本程序单元，编写pl/sql程序实际上就是编写pl/sql块，要完成相对简单的应用功能，可能只需要编写一个pl/sql块，但是如果想要实现复杂的功能，可能需要在一个pl/sql块中嵌套其它的pl/sql块。

六、块结构示意图
pl/sql块由三个部分构成：定义部分，执行部分，例外处理部分。
如下所示：
declare
/*定义部分——定义常量、变量、游标、例外、复杂数据类型*/
begin
/*执行部分——要执行的pl/sql 语句和sql 语句*/
exception
/*例外处理部分——处理运行的各种错误*/
end;

说明：
定义部分是从declare开始的，该部分是可选的；
执行部分是从begin开始的，该部分是必须的；
例外处理部分是从exception开始的，该部分是可选的。
可以和java编程结构做一个简单的比较。

七、pl/sql块的实例一

实例一 只包括执行部分的pl/sql块

复制代码
set serveroutput on; --打开输出选项

begin 
   dbms_output.put_line('hello world');
end;
/ --执行

复制代码
相关说明：
dbms_output是oracle所提供的包(类似java 的开发包)，该包包含一些过程，put_line就是dbms_output包的一个过程。

八、pl/sql块的实例二

实例二 包含定义部分和执行部分的pl/sql块

复制代码
set serveroutput on; --打开输出选项
DECLARE
    --定义字符串变量
    v_ename varchar2(10); 
BEGIN
    --执行部分
    select ename into v_ename from emp where empno=&empno; --& 表示要接收从控制台输入的变量
    --在控制台显示雇员名

    dbms_output.put_line('雇员名：'||v_ename);
end;
/
复制代码

九、pl/sql块的实例三
实例三 包含定义部分，执行部分和例外处理部分
为了避免pl/sql程序的运行错误，提高pl/sql的健壮性，应该对可能的错误进行处理，这个很有必要。
1.比如在实例二中，如果输入了不存在的雇员号，应当做例外处理。
2.有时出现异常，希望用另外的逻辑处理，我们看看如何完成1的要求。

相关说明：oracle事先预定义了一些例外，no_data_found就是找不到数据的例外

复制代码
--打开输出选项
set serveroutput on; 
DECLARE
    --定义字符串变量
    v_ename varchar2(10); 
    v_sal NUMBER(7,2);
BEGIN
    --执行部分
    select ename, sal into v_ename, v_sal from emp where empno=&empno; 
    dbms_output.put_line('雇员名：'||v_ename||'，薪水：'||v_sal);
EXCEPTION
    --异常处理    
    WHEN no_data_found THEN dbms_output.put_line('朋友，您的编号输入有误！');
end;
/
复制代码