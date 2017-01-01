数据库：Oracle数据库select面试笔试经典题目 2013-12-13 11:21:55
分类： Oracle
number(10):存10位数字，即1234567890
number(10,3):123456.789
varchar2(20):最大存放20个字节的字符，其中每个字符占2个字节，如存入字符串‘abc’，则实际只占用6个字节空间。
null不能用==或者！=比较  ，必须用 is null 或者is not null
rownum  返回当前号                                                                                                                                                                                                                   
rowid是一个伪列，一般情况用不到，但是当需要具体锁定某一行时，它非常有用，
因为它的值绝对不会重复，rowid 类似于hashcode  定位列在内存中的数据。经典的删除表中重复记录：

1.这里不能写成select *, rowid from emp2而必须写成  select emp2.*, rowid from emp2)  ，因为有后面字段的存在
between and 是>=  <=，  效率低，用的少，一般是直接>= <=
exists、 not exists   当表内数据过多情况下用来替代 in 、 not  in，exists的执行效率高。
exists是通过索引(key-value)来检索数据库的，而in是遍历数据库的。
所以如果想增加对表中某个字段的查询速度，就可以为他们建立索引，
但是，索引的建立也有代价，索引是要占空间的，因此，除非必要，否则不要轻易添加索引(如非必要，不要轻易添加视图)
尽量不用all ，用max来替代他
where A and B     where A or B 都是先执行B，后执行A
n个表连接时，至少需要n-1个过滤条件
注释为/* */或者--
只要起了别名，接下来的操作就必须使用别名,别名不是永久更改
别名要么不加引号，要么加“”，  但是不能加‘’，   而在判断字符串的时候要加‘’，
不加引号的会自动全部转换为大写，而“”以及字符串‘’中的内容是区分大小写的。
sum(~)时自动将为null的转换为0求和，avg(~)时跳过null字段，不计入个数中
order by e.sal, e.deptno desc;(指先按e.sal升序，在按deptno降序)                                                     order by e.sal desc, e.deptno desc;(才是两个都按降序)
Savepoint的运用：
	delete from emp_copy e where e.deptno = 20;
	savepoint a;
	select * from emp_copy;
	rollback to a;
	select * from emp_copy;
2.high water mark故名思义为高水平线,一般是相对一个表而言的,当一个表有数据不断的插入时,
high water mark值不断增高,对那些全表扫描的select查询是以high water mark为终点的,
虽然表中可能只有一行记录.它是表的空间曾经扩充到的值.
truncate将重置高水平线，而delete还是原来的值
 
3.oracle中分页处理(必须掌握)：
oracle中的rownum只能使用<、<=，不能使用=、>、>=等比较操作符。譬如： select * from emp where rownum > 5;
而且当rownum和order by一起使用时，会首先选出符合rownum条件的记录，然后再排序。
这会给查询带来难度，得需要用到子查询。
rownum的另一个主要用途是用来分页，例如想求按薪水从高到低排列的第6个到第10个人的信息，
由于不能使用>、>=，必须使用多层嵌套的子查询：
select e2.* from  
(select e1.*, rownum r from  
(select e.* from emp e order by sal desc) e1 where rownum <= 10 ) e2
where r >= 5;
1、查询EMP表显示在1981年2月1日到1981年5月1日之间雇佣的雇员名、岗位及雇佣日期，并以雇佣日期进行排序。
(注意日期转换的知识点)
select ename, job, hiredate
from emp
where hiredate > to_date('1981/2/1', 'YYYY/MM/DD')
and hiredate < to_date('1981/5/1', 'YYYY/MM/DD')
order by hiredate;

2、列出至少有一个雇员的所有部门。   
select d.deptno, d.dname, count(e.empno)
from emp e, dept d
where e.deptno(+) = d.deptno  --(+) 的解释
group by d.deptno, d.dname
having count(e.empno) >= 1

-- 数据表的连接有: 
-- 1、内连接(自然连接): 只有两个表相匹配的行才能在结果集中出现 
-- 2、外连接: 包括 
-- （1）左外连接(左边的表不加限制) 
-- （2）右外连接(右边的表不加限制) 
-- （3）全外连接(左右两表都不加限制) 
-- 3、自连接(连接发生在一张基表内)
-- SELECT a.*, b.* from a(+) = b就是一个右连接，等同于select a.*, b.* from a right join b
-- SELECT a.*, b.* from a = b(+)就是一个左连接，等同于select a.*, b.* from a left join b

3、列出与“SCOTT”从事相同工作的所有雇员。(注意关于exist的东西，因为in的执行效率比较低，虽然写起来简单)
select  * from emp e where e.job in (select distinct p.job from emp p where p.ename='SCOTT');
-- exist 在查询的时候，每条记录都会进行一次子查询
select *  from emp e where exists (select 1 from emp  p  where p.job = e.job and p.ename='SCOTT');

4、列出薪金等于在部门30工作的所有雇员的薪金的雇员的姓名和薪金。
(注意考虑e.deptno is null这种情况及exist的写法)
select *  from emp e where e.sal in (select p.sal from emp p where p.deptno = 30) and (e.deptno <> 30 or e.deptno is null);
select * from emp e where exists (select 1 from emp p where p.sal = e.sal and p.deptno=30 and (e.deptno<>30 or e.deptno is null) );

5、列出在每个部门工作的雇员的数量以及其他信息。
select  d.deptno,d.loc,d.dname ,count(e.empno) from emp e ,dept d 
where e.deptno (+)= d.deptno group by d.deptno,d.loc,d.dname;

select  e.*,d.deptno,count(e.empno) over(partition by d.deptno) from emp e ,dept d 
where e.deptno (+)= d.deptno 

6、列出分配有雇员数量的所有部门的详细信息即使是分配有0个雇员。
select  d.deptno,d.loc,d.dname ,count(e.empno) from emp e ,dept d 
where e.deptno (+)= d.deptno group by d.deptno,d.loc,d.dname;

7、找出各月倒数第3天受雇的所有员工.
select e.* from emp e where last_day(e.hiredate)= e.hiredate+2;

8、找出早于12年前受雇的员工.
select * from emp
where hiredate < trunc(sysdate)+(INTERVAL '-12' YEAR);

9、以首字母大写的方式显示所有员工的姓名.
select  initcap(ename) from emp;
select Upper(substr(ename,1,1))||Lower(substr(ename,2)) from emp

10、显示正好为5个字符的员工的姓名.                                            
     select ename from emp where length(ename) = 5;
	 
11、显示所有员工姓名的前三个字符.
     select substr(ename,1,3) from emp;
12、显示所有员工的姓名,用a替换所有"A"
     select replace(e.ename,'A','a') from emp e;
     select translate(ename,'A','a') from emp;
13、显示所有员工的姓名、工作和薪金,按工作的降序排序,若工作相同则按薪金排序.
     select ename, job, sal from emp order by job desc, sal;
14、显示所有员工的姓名、加入公司的年份和月份,按受雇日期所在月排序,若月份相同则将最早年份的员工排在最前面.
     select ename,
            extract(YEAR from hiredate) 年,
            extract(MONTH from hiredate) 月
       from emp
      order by 月, 年;
      
      select e.ename,
             to_char(e.hiredate, 'yyyy') 年,
             to_char(e.hiredate, 'mm') 月
        from emp e
       order by 月, 年;
15、找出在(任何年份的)2月受聘的所有员工。
    select * from emp where extract(MONTH from hiredate) = 2;
    select * from emp where to_char(hiredate,'mm') = '02';
16、对于每个员工,显示其加入公司的天数.  (注意如果select后面的字段中除了emp表中原有字段还有其他字段，那么需要将emp重命名)
    select e.*,ceil(sysdate-e.hiredate) "入职天数"  from emp e ;
    select ename, trunc(sysdate-hiredate) from emp; 
如果我们需要将两个select语句的结果作为一个整体显示出来，我们就需要用到union或者union all关键字。union(或称为联合)的作用是将多个结果合并在一起显示出来。
union和union all的区别是,union会自动压缩多个结果集合中的重复结果，而union all则将所有的结果全部显示出来，不管是不是重复。  
Union：对两个结果集进行并集操作，不包括重复行，同时进行默认规则的排序；
Union All：对两个结果集进行并集操作，包括重复行，不进行排序；
Select1 union select2 取两个select 去除重复的
Select1 union all select  不去除重复的
Select1 intersect select2  取交集
Select1 minus select2 取差集
17、用一个查询语句，实现查询各个岗位的总工资和各个部门的总工资和所有雇员的总工资   
 (方法1) --  union all ！！
  select e.job||'岗位总工资' "总工资",sum(e.sal) from emp e group by e.job
  union all
  select e.deptno||'部门总工资',sum(e.sal) from emp e group by e.deptno
  union all
  select '雇员总工资',sum(e.sal) from emp e;
 (方法2) grouping sets！！
  select e.job,e.deptno,sum(e.sal) from emp e group by grouping sets((e.job),(e.deptno),(null));
 
 (方法3)
  select distinct
       sum(e.sal) over(partition by e.job) 岗位总工资,
       sum(e.sal) over(partition by e.deptno) 部门总工资,
       sum(e.sal) over() 雇员总工资
  from emp e;
 (方法3)grouping sets(不重要)
  select sum(e.sal),
       decode(grouping_id(e.job), 1, 'deptno合计', e.job) job,
       decode(grouping_id(e.deptno), 1, 'job合计', e.deptno) deptno,
       decode(grouping_id(e.job),
              1,
              decode(grouping_id(e.deptno), 1, '总计')) sal_sum
  from emp e
 group by grouping sets((e.job),(e.deptno),(null));
  
(方法4)cube (不重要)
   select *
   from (select sum(e.sal),
                decode(grouping_id(e.job), 1, '合计', e.job) job,
                decode(grouping_id(e.deptno), 1, '合计', e.deptno) deptno
           from emp e
          group by cube(e.job, e.deptno)) a
  where a.job = '合计' 
        or(a.job is not null and a.deptno ='合计')
18、一道SQL语句面试题，关于group by
表内容：
2005-05-09 胜
2005-05-09 胜
2005-05-09 负
2005-05-09 负
2005-05-10 胜
2005-05-10 负
2005-05-10 负
如果要生成下列结果, 该如何写sql语句?
            胜 负
2005-05-09 2 2
2005-05-10 1 2
------------------------------------------
create table tmp(rq varchar2(10),shengfu varchar2(1))
insert into tmp values('2005-05-09','胜')
insert into tmp values('2005-05-09','胜')
insert into tmp values('2005-05-09','负')
insert into tmp values('2005-05-09','负')
insert into tmp values('2005-05-10','胜')
insert into tmp values('2005-05-10','负')
insert into tmp values('2005-05-10','负')
答案：
(方法1)：
select tt.rq " ",
       (select count(*) from tmp where tt.rq = rq and shengfu = '胜') 胜,
       (select count(*) from tmp where tt.rq = rq and shengfu = '负') 负
  from tmp tt group by tt.rq order by tt.rq;
(方法2)：
select rq " ",
       sum(case when shengfu = '胜' then 1 else 0 end) 胜,
       sum(case when shengfu = '负' then 1 else 0 end) 负
  from tmp group by rq order by rq;
19、2.请教一个面试中遇到的SQL语句的查询问题
  --表中有A B C三列,用SQL语句实现：当A列大于B列时选择A列否则选择B列，当B列大于C列时选择B列否则选择C列。
create table compare(A number(2),B number(2), C number(2));
insert into compare values (70, 80, 98);
select * from compare;
答案：
select (case when a > b then a else b end),
       (case when b > c then b else c end)
  from compare
20、有一张表，里面有3个字段：语文，数学，英语。其中有3条记录分别表示语文70分，数学80分，英语58分，请用一条sql语句查询出这三条记录并按以下条件显示出来(并写出您的思路)：  
   大于或等于80表示优秀，大于或等于60表示及格，小于60分表示不及格。  
       显示格式：  
       语文              数学                英语  
       及格              优秀                不及格  
*/  
------------------------------------------
create table exam("语文" number(2),"数学" number(2), "英语" number(2));
insert into exam values (70, 80, 58);
select * from exam;
答案：
select case
         when 语文 >= 80 then '优秀'
         when 语文 >= 60 then '及格'
         else '不及格'
       end 语文,
       case
         when 数学 >= 80 then '优秀'
         when 数学 >= 60 then '及格'
         else '不及格'
       end 数学,
       case
         when 英语 >= 80 then '优秀'
         when 英语 >= 60 then '及格'
         else '不及格'
       end 英语
  from exam;
21、请用一个sql语句得出结果
从table1,table2中取出如table3所列格式数据(如使用存储过程也可以)
table1
月份mon 部门dep 业绩yj
-------------------------------
一月份      01      10
一月份      02      10
一月份      03      5
二月份      02      8
二月份      04      9
三月份      03      8
table2
部门dep      部门名称dname
--------------------------------
      01      国内业务一部
      02      国内业务二部
      03      国内业务三部
      04      国际业务部
table3 (result)
部门dep 一月份      二月份      三月份
--------------------------------------
      01      10             
      02      10      8        
      03      5                   8
      04              9      
------------------------------------------
*/
create table table1(mon varchar2(10), dep number(2), yj number(2));
insert into table1 values ('一月份', 01, 10);
insert into table1 values ('一月份', 02, 10);
insert into table1 values ('一月份', 03, 5);
insert into table1 values ('二月份', 02, 8);
insert into table1 values ('二月份', 04, 9);
insert into table1 values ('三月份', 03, 8);
select * from table1;
create table table2(dep number(2), dname varchar2(20));
insert into table2 values(01, '国内业务一部');
insert into table2 values(02, '国内业务二部');
insert into table2 values(03, '国内业务三部');
insert into table2 values(04, '国际业务部'); 
select * from table2;
答案：
(方法1)：
select dep,
       (select yj from table1 where mon = '一月份' and dep = table2.dep) 一月份,
       (select yj from table1 where mon = '二月份' and dep = table2.dep) 二月份,
       (select yj from table1 where mon = '三月份' and dep = table2.dep) 三月份
  from table2;
(方法2)：
select a.dep,
       sum(case when b.mon = '一月份' then b.yj else 0 end)  一月份,
       sum(case when b.mon = '二月份' then b.yj else 0 end)  二月份,
       sum(case when b.mon = '三月份' then b.yj else 0 end)  三月份
  from table2 a left join table1 b on a.dep=b.dep group by a.dep order by a.dep 