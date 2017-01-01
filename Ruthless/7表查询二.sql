七、oracle 表查询二

1、使用逻辑操作符号
问题：查询工资高于500或者是岗位为manager的雇员，同时还要满足他们的姓名首字母为大写的J？
select * from emp where (sal > 500 or job = 'MANAGER') and ename like 'J%';

2、使用order by字句 默认asc
问题：如何按照工资从低到高的顺序显示雇员的信息？
select * from emp order by sal;
问题：按照部门号升序而雇员的工资降序排列
select * from emp order by deptno, sal desc;

3、使用列的别名排序
问题：按年薪排序
select ename, (sal+nvl(comm,0))*12 "年薪" from emp order by "年薪" asc;
备注：别名需要使用“”号圈中,英文不需要“”号

4、聚合函数用法：max，min，avg，sum，count
问题：如何显示所有员工中最高工资和最低工资？
select max(sal),min(sal) from emp e;
最高工资那个人是谁？
错误写法：select ename, sal from emp where sal=max(sal);
正确写法：select ename, sal from emp where sal=(select max(sal) from emp);
注意：select ename, max(sal) from emp;这语句执行的时候会报错，说ora-00937：非单组分组函数。因为max是分组函数，而ename不是分组函数.......
但是select min(sal), max(sal) from emp;这句是可以执行的。因为min和max都是分组函数，就是说：如果列里面有一个分组函数，其它的都必须是分组函数，否则就出错。这是语法规定的

5、问题：如何显示所有员工的平均工资和工资总和？
select sum(e.sal), avg(e.sal)  from emp e;
查询最高工资员工的名字，工作岗位
select ename, job, sal from emp e where sal = (select max(sal) from emp);
显示工资高于平均工资的员工信息
select * from emp e where sal > (select avg(sal) from emp);

6、group by 和 having 子句
group by 用于对查询的结果分组统计，
having 子句用于限制分组显示结果。
问题：如何显示每个部门的平均工资和最高工资？
select avg(sal), max(sal), deptno from emp group by deptno;
注意：这里暗藏了一点，如果你要分组查询的话，分组的字段deptno一定要出现在查询的列表里面，否则会报错。
因为分组的字段都不出现的话，就没办法分组了
问题：显示每个部门的每种岗位的平均工资和最低工资？
select min(sal), avg(sal), deptno, job from emp group by deptno, job;
问题：显示平均工资低于2000的部门号和它的平均工资？
select avg(sal), max(sal), deptno from emp group by deptno having avg(sal)< 2000;

7、对数据分组的总结
1 分组函数只能出现在选择列表、having、order by子句中(不能出现在where中)
2 如果在select语句中同时包含有group by, having, order by 那么它们的顺序是group by, having, order by
3 在选择列中如果有列、表达式和分组函数，那么这些列和表达式必须有一个出现在group by 子句中，否则就会出错。
如select deptno, avg(sal), max(sal) from emp group by deptno having avg(sal) < 2000;这里deptno就一定要出现在group by中

8、多表查询
多表查询是指基于两个和两个以上的表或是视图的查询。
在实际应用中，查询单个表可能不能满足你的需求，
如显示sales部门位置和其员工的姓名，这种情况下需要使用到dept表和emp表。
1)、问题：显示雇员名，雇员工资及所在部门的名字【笛卡尔集】？
SELECT e.ename, e.sal, d.dname FROM emp e, dept d WHERE e.deptno = d.deptno;
规定：多表查询的条件是至少不能少于表的个数N-1才能排除笛卡尔集
如果有N张表联合查询，必须得有N-1个条件，才能避免笛卡尔集合

2)、问题：显示部门号为10的部门名、员工名和工资？
SELECT d.dname, e.ename, e.sal FROM emp e, dept d WHERE e.deptno = d.deptno and e.deptno = 10;

3)、问题：显示各个员工的姓名，工资及工资的级别？
SELECT e.ename, e.sal, s.grade FROM emp e, salgrade s WHERE e.sal BETWEEN s.losal AND s.hisal;

4)、问题：显示雇员名，雇员工资及所在部门的名字，并按部门排序？
SELECT e.ename, e.sal, d.dname FROM emp e, dept d WHERE e.deptno = d.deptno ORDER by e.deptno;
注意：如果用group by，一定要把e.deptno 放到查询列里面

5)、自连接
自连接是指在同一张表的连接查询
问题：显示某个员工的上级领导的姓名？
比如显示员工‘FORD’的上级
SELECT worker.ename, boss.ename FROM emp worker, emp boss WHERE worker.mgr = boss.empno AND worker.ename = 'FORD';

6)、子查询
什么是子查询？
子查询是指嵌入在其他sql语句中的select语句，也叫嵌套查询。

单行子查询？
单行子查询是指只返回一行数据的子查询语句
请思考：显示与SMITH同部门的所有员工？
思路：
1))、查询出SMITH的部门号(返回单行结果集)
select deptno from emp WHERE ename = 'SMITH';
2))、显示
SELECT * FROM emp WHERE deptno = (select deptno from emp WHERE ename = 'SMITH');
数据库在执行sql是从左到右扫描的，如果有括号的话，括号里面的先被优先执行。

多行子查询
多行子查询指返回多行数据的子查询
请思考：如何查询和部门10的工作相同的雇员的名字、岗位、工资、部门号
1))、查询出部门10的所有工作(返回多行结果集)
SELECT DISTINCT job FROM emp WHERE deptno = 10;
2))、显示
SELECT * FROM emp WHERE job IN (SELECT DISTINCT job FROM emp WHERE deptno = 10);
注意：不能用job=..，因为等号=是一对一的

在多行子查询中使用all操作符
问题：如何显示工资比部门30的所有员工的工资高的员工的姓名、工资和部门号？
--方法一
SELECT ename, sal, deptno FROM emp WHERE sal > all(SELECT sal FROM emp WHERE deptno = 30);
--方法二(执行效率最高，使用聚合函数)
SELECT ename, sal, deptno FROM emp WHERE sal > (SELECT max(sal) FROM emp WHERE deptno = 30);

在多行子查询中使用any操作符
问题：如何显示工资比部门30的任意一个员工的工资高的员工姓名、工资和部门号？
--方法一
SELECT ename, sal, deptno FROM emp WHERE sal > ANY (SELECT sal FROM emp WHERE deptno = 30);
--方法二(执行效率最高，使用聚合函数)
SELECT ename, sal, deptno FROM emp WHERE sal > (SELECT min(sal) FROM emp WHERE deptno = 30);

多列子查询
单行子查询是指子查询只返回单列、单行数据，多行子查询是指返回单列多行数据，都是针对单列而言的，而多列子查询是指查询返回多个列数据的子查询语句。

请思考如何查询与SMITH 的部门和岗位完全相同的所有雇员。
SELECT deptno, job FROM emp WHERE ename = 'SMITH';
SELECT * FROM emp WHERE (deptno, job) = (SELECT deptno, job FROM emp WHERE ename = 'SMITH');

在from子句中使用子查询
请思考：如何显示高于自己部门平均工资的员工的信息
思路：
1. 查出各个部门的平均工资和部门号
SELECT deptno, AVG(sal) mysal FROM emp GROUP by deptno;
2. 把上面的查询结果看做是一张子表
SELECT e.ename, e.deptno, e.sal, ds.mysal 
FROM emp e, (SELECT deptno, AVG(sal) mysal FROM emp GROUP by deptno) ds 
WHERE e.deptno = ds.deptno AND e.sal > ds.mysal;

小总结：
在这里需要说明的当在from子句中使用子查询时，该子查询会被作为一个视图来对待，因此叫做内嵌视图，当在from 子句中使用子查询时，必须给子查询指定别名。
注意：别名不能用as，如：SELECT e.ename, e.deptno, e.sal, ds.mysal FROM emp e, (SELECT deptno, AVG(sal) mysal FROM emp GROUP by deptno) as ds WHERE e.deptno = ds.deptno AND e.sal > ds.mysal;
在ds前不能加as，否则会报错给表取别名的时候，不能加as；但是给列取别名，是可以加as的

如何衡量一个程序员的水平？
网络处理能力，数据库，程序代码的优化程序的效率要很高

7)、用查询结果创建新表，这个命令是一种快捷的建表方式
CREATE TABLE mytable (id, name, sal, job, deptno) as SELECT empno, ename, sal, job, deptno FROM emp;

8)、合并查询
有时在实际应用中，为了合并多个select语句的结果，可以使用集合操作符号union，union all，intersect，minus。
多用于数据量比较大的数据局库，运行速度快。
1). union
该操作符用于取得两个结果集的并集。当使用该操作符时，会自动去掉结果集中重复行。
SELECT ename, sal, job FROM emp WHERE sal >2500
UNION
SELECT ename, sal, job FROM emp WHERE job = 'MANAGER';

2).union all
该操作符与union相似，但是它不会取消重复行，而且不会排序。
SELECT ename, sal, job FROM emp WHERE sal >2500
UNION ALL
SELECT ename, sal, job FROM emp WHERE job = 'MANAGER';
该操作符用于取得两个结果集的并集。当使用该操作符时，不会自动去掉结果集中重复行。

3). intersect
使用该操作符用于取得两个结果集的交集。
SELECT ename, sal, job FROM emp WHERE sal >2500
INTERSECT
SELECT ename, sal, job FROM emp WHERE job = 'MANAGER';

4). minus
使用该操作符用于取得两个结果集的差集，他只会显示存在第一个集合中，而不存在第二个集合中的数据。
SELECT ename, sal, job FROM emp WHERE sal >2500
MINUS
SELECT ename, sal, job FROM emp WHERE job = 'MANAGER';
MINUS就是减法的意思