十、oracle 常用函数

一、字符函数
字符函数是oracle中最常用的函数，我们来看看有哪些字符函数：
lower(char)：将字符串转化为小写的格式。
upper(char)：将字符串转化为大写的格式。
length(char)：返回字符串的长度。
substr(char, m, n)：截取字符串的子串，n代表取n个字符的意思，不是代表取到第n个
replace(char1, search_string, replace_string)
instr(C1,C2,I,J) -->判断某字符或字符串是否存在，存在返回出现的位置的索引，否则返回小于1;在一个字符串中搜索指定的字符,返回发现指定的字符的位置;
C1 被搜索的字符串
C2 希望搜索的字符串
I 搜索的开始位置,默认为1
J 出现的位置,默认为1

问题：将所有员工的名字按小写的方式显示
SQL> select lower(ename) from emp;
问题：将所有员工的名字按大写的方式显示。
SQL> select upper(ename) from emp;
问题：显示正好为5个字符的员工的姓名。
SQL> select * from emp where length(ename)=5;
问题：显示所有员工姓名的前三个字符。
SQL> select substr(ename, 1, 3) from emp;
问题：以首字母大写,后面小写的方式显示所有员工的姓名。
SQL> select upper(substr(ename,1,1)) || lower(substr(ename,2,length(ename)-1)) from emp;
问题：以首字母小写,后面大写的方式显示所有员工的姓名。
SQL> select lower(substr(ename,1,1)) || upper(substr(ename,2,length(ename)-1)) from emp;
问题：显示所有员工的姓名，用“我是老虎”替换所有“A”
SQL> select replace(ename,'A', '我是老虎') from emp;
问题：instr(char1,char2,[,n[,m]])用法
SQL> select instr('azhangsanbcd', 'zhangsan') from dual; --返回2
SQL> select instr('oracle traning', 'ra', 1, 1) instring from dual; --返回2
SQL> select instr('oracle traning', 'ra', 1, 2) instring from dual; --返回9
SQL> select instr('oracle traning', 'ra', 1, 3) instring from dual; --返回0，根据条件，由于ra只出现二次，第四个参数3，就是说第3次出现ra的位置，显然第3次是没有再出现了，所以结果返回0。注意空格也算一个字符
SQL> select instr('abc','d') from dual;  --返回0

二、数学函数
数学函数的输入参数和返回值的数据类型都是数字类型的。数学函数包括cos，cosh，exp，ln, log，sin，sinh，sqrt，tan，tanh，acos，asin，atan，round等
我们讲最常用的：
round(n,[m]) 该函数用于执行四舍五入，
如果省掉m，则四舍五入到整数。
如果m是正数，则四舍五入到小数点的m位后。
如果m是负数，则四舍五入到小数点的m位前。
eg、SELECT round(23.75123) FROM dual; --返回24
SELECT round(23.75123, -1) FROM dual; --返回20
SELECT round(27.75123, -1) FROM dual; --返回30
SELECT round(23.75123, -3) FROM dual; --返回0
SELECT round(23.75123, 1) FROM dual; --返回23.8
SELECT round(23.75123, 2) FROM dual; --返回23.75
SELECT round(23.75123, 3) FROM dual; --返回23.751
trunc(n,[m]) 该函数用于截取数字。
如果省掉m，就截去小数部分，
如果m是正数就截取到小数点的m位后，
如果m是负数，则截取到小数点的前m位。
eg、SELECT trunc(23.75123) FROM dual; --返回23
SELECT trunc(23.75123, -1) FROM dual; --返回20
SELECT trunc(27.75123, -1) FROM dual; --返回20
SELECT trunc(23.75123, -3) FROM dual; --返回0
SELECT trunc(23.75123, 1) FROM dual; --返回23.7
SELECT trunc(23.75123, 2) FROM dual; --返回23.75
SELECT trunc(23.75123, 3) FROM dual; --返回23.751
mod(m,n)取余函数
eg、select mod(10,2) from dual; --返回0
SELECT MOD(10,3) FROM dual; --返回1
floor(n) 返回小于或是等于n的最大整数
ceil(n) 返回大于或是等于n的最小整数
eg、SELECT ceil(24.56) from dual; --返回25
SELECT floor(24.56) from dual; --返回24
abs(n) 返回数字n的绝对值
对数字的处理，在财务系统或银行系统中用的最多，不同的处理方法，对财务报表有不同的结果

三、日期函数
日期函数用于处理date类型的数据。默认情况下日期格式是dd-mon-yy 即“12-7 月-12”
(1)sysdate 返回系统时间
eg、SQL> select sysdate from dual;
(2)oracle add_months函数
oracle add_months(time,months)函数可以得到某一时间之前或之后n个月的时间
eg、select add_months(sysdate,-6) from dual; --该查询的结果是当前时间半年前的时间
select add_months(sysdate,6) from dual; --该查询的结果是当前时间半年后的时间
(3)last_day(d)：返回指定日期所在月份的最后一天
问题：查找已经入职8个月多的员工
SQL> select * from emp where sysdate>=add_months(hiredate,8);
问题：显示满10年服务年限的员工的姓名和受雇日期。
SQL> select ename, hiredate from emp where sysdate>=add_months(hiredate,12*10);
问题：对于每个员工，显示其加入公司的天数。
SQL> select floor(sysdate-hiredate) "入职天数",ename from emp;
或者
SQL> select trunc(sysdate-hiredate) "入职天数",ename from emp;
问题：找出各月倒数第3天受雇的所有员工。
SQL> select hiredate,ename from emp where last_day(hiredate)-2=hiredate;

四、转换函数
转换函数用于将数据类型从一种转为另外一种。在某些情况下，oracle server允许值的数据类型和实际的不一样，这时oracle server会隐含的转化数据类型
比如：
create table t1(id int);
insert into t1 values('10');--这样oracle会自动的将'10' -->10
create table t2 (id varchar2(10));
insert into t2 values(1); --这样oracle就会自动的将1 -->'1'；
我们要说的是尽管oracle可以进行隐含的数据类型的转换，但是它并不适应所有的情况，为了提高程序的可靠性，我们应该使用转换函数进行转换。

to_char()函数
你可以使用select ename, hiredate, sal from emp where deptno = 10;显示信息，可是，在某些情况下，这个并不能满足你的需求。
问题：日期是否可以显示 时/分/秒
SQL> select ename, to_char(hiredate, 'yyyy-mm-dd hh24:mi:ss') from emp;
问题：薪水是否可以显示指定的货币符号
SQL>
yy：两位数字的年份 2004-->04
yyyy：四位数字的年份 2004年
mm：两位数字的月份 8 月-->08
dd：两位数字的天 30 号-->30
hh24： 8点-->20
hh12：8点-->08
mi、ss-->显示分钟\秒
9：显示数字，并忽略前面0
0：显示数字，如位数不足，则用0补齐
.：在指定位置显示小数点
,：在指定位置显示逗号
$：在数字前加美元
L：在数字前面加本地货币符号
C：在数字前面加国际货币符号
G：在指定位置显示组分隔符、
D：在指定位置显示小数点符号(.)

问题：显示薪水的时候，把本地货币单位加在前面
SQL> select ename, to_char(hiredate, 'yyyy-mm-dd hh24:mi:ss'), to_char(sal,'L99999.99') from emp;
问题：显示1980年入职的所有员工
SQL> select * from emp where to_char(hiredate, 'yyyy')=1980;
问题：显示所有12月份入职的员工
SQL> select * from emp where to_char(hiredate, 'mm')=12;

to_date()函数
函数to_date用于将字符串转换成date类型的数据。
问题：能否按照中国人习惯的方式年—月—日添加日期。
eg、SELECT to_date('2012-02-18 09:25:30','yyyy-mm-dd hh24:mi:ss') FROM dual;

五、sys_context()系统函数
1)terminal：当前会话客户所对应的终端的标示符，如计算机名
2)language: 语言
3)db_name： 当前数据库名称
4)nls_date_format： 当前会话客户所对应的日期格式
5)session_user： 当前会话客户所对应的数据库用户名
6)current_schema： 当前会话客户所对应的默认方案名
7)host： 返回数据库所在主机的名称
通过该函数，可以查询一些重要信息，比如你正在使用哪个数据库？
select sys_context('USERENV','db_name') from dual;
注意：USERENV是固定的，不能改的，db_name可以换成其它,
eg、select sys_context('USERENV','language') from dual;
select sys_context('USERENV','current_schema') from dual;