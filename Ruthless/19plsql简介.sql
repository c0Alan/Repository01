十九、oracle pl/sql简介

一、pl/sql 是什么
pl/sql(procedural language/sql)是oracle在标准的sql语言上的扩展。
pl/sql不仅允许嵌入sql语言，还可以定义变量和常量，允许使用条件语句和循环语句，允许使用例外处理各种错误，这样使得它的功能变得更加强大。

二、为什么要学pl/sql
1.提高应用程序的运行性能
2.模块化的设计思想(分页的过程，订单的过程，转账的过程。。)
3.减少网络传输量
4.提高安全性(sql会包括表名，有时还可能有密码，传输的时候会泄露。PL/SQL就不会)

三、Oracle为什么在PL/SQL developer执行很快,用c# oracleclient执行就慢
因为PL/SQL这门语言是专门用于在各种环境下对Oracle数据库进行访问。由于该语言集成于数据库服务器中，所以PL/SQL代码可以对数据进行快速高效的处理。
而c#语言是微软的产品，它在连接ORACLE的时候先存到“连接池”中，所以第一次会慢点，但是当你的Web程序没有重起的时候，以后的速度就不会慢了。

四、使用pl/sql的缺点
移植性不好(换数据库就用不了)

五、pl/sql理解
1)、存储过程、函数、触发器是pl/sql编写的
2)、存储过程、函数、触发器是存在oracle中的
3)、pl/sql是非常强大的数据库过程语言
4)、存储过程、函数可以在java中调用

六、编写一个存储过程，该过程可以向某表中添加记录。

复制代码
1、创建一张简单的表
CREATE TABLE mytest(
   username VARCHAR2(30),
   pwd VARCHAR2(30)
);

2、创建过程(replace:表示如果有insert_proc，就替换)
CREATE OR REPLACE PROCEDURE insert_proc IS
BEGIN
   INSERT INTO mytest VALUES('林计钦', '123456');
END;
/

3、如何查看错误信息：show error; 
注意要在命令窗口执行

4、如何调用该过程：exec 过程名(参数值1，参数值2...); 
eg、exec insert_proc;
注意要在命令窗口执行
复制代码