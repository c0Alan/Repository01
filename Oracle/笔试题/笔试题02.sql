1.选择部门30中的所有员工：

SELECT * FROM emp WHERE deptno=30;

2.列出所有办事员(CLERK)的姓名，编号和部门编号：

SELECT ename,empno,deptno FROM emp WHERE UPPER(job)='CLERK';

3.列出佣金(comm)高于薪金(sal)的员工：

SELECT * FROM emp WHERE comm>sal;

4.找出佣金（comm）高于薪金百分60的员工

SELECT * FROM emp WHERE comm>(sal*0.6);

5.找出部门10中所有经理(MANAGER) 和部门20中所有办事员（CLERK）的详细资料

SELECT * FROM emp WHERE (deptno=10 AND job='MANAGER') OR (deptno=20 AND job='CLERK');

6.找出部门10中所有经理，部门20中所有的办事员，既不是经理又不是办事员，但是薪金大于或等于2000的所有员工的资料：

SELECT * FROM emp WHERE

(deptno=10 AND job='MANAGER') OR

(deptno=20 AND job='CLERK') OR

(job NOT IN('MANAGER','CLERK') AND sal>=2000);

7.找出收取佣金（comm）的员工的不同工作：[DISTINCT->消除重复的关键字]

SELECT DISTINCT job FROM emp WHERE comm IS NOT NULL;

8.找出不收取佣金或者佣金小于100的员工：

SELECT * FROM emp WHERE comm IS NULL OR comm<100;

9.找个各月倒数第三天受雇的所有员工：

·使用LAST_DAY()函数

SELECT * FROM emp WHERE (LAST_DAY(hiredate)-2)=hiredate;

10.找出早于12年前受雇的员工：

·注意使用MONTHS_BETWEEN(今天,雇佣日期)

SELECT * FROM emp WHERE MONTHS_BETWEEN(SYSDATE,hiredate)/12>12;

11.按照首字母大写的方式显示员工姓名

SELECT INITCAP(ename) FROM emp;

12.显示正好为5个字符的员工的姓名

SELECT ename FROM emp WHERE LENGTH(ename)=5;

13.显示不带有"R"的员工姓名：

SELECT ename FROM emp WHERE ename NOT LIKE '%R%';

14.显示所有员工姓名的前3个字符：

SELECT SUBSTR(ename,0,3) FROM emp;

15.显示所有员工的姓名，并且用“x” 替换替换所有的 “A”；

SELECT REPLACE(ename,'A','x') FROM emp;

16.显示满十年服务年限的员工的姓名和受雇日期：

SELECT ename,hiredate FROM emp WHERE MONTHS_BETWEEN(sysdate,hiredate)/12 >10;

17.显示员工的详细资料，按姓名排序:

SELECT * FROM emp ORDER BY ename;

18.显示员工的姓名和受雇日期，并根据其服务年限，把资料最老的员工排在第在前面：

SELECT ename,hiredate FROM emp ORDER BY hiredate;

19.显示所有员工的姓名，工作和薪金，按工作的降序排序，若工作相同则按薪金排序：

SELECT ename,job,sal FROM emp ORDER BY job DESC,sal;

20.显示所有员工的姓名，加入公司的年份和月份，按受雇日期所在的年排序，若年份相同则讲最早月份的员工排在最前面：

·使用TO_CHAR()函数

SELECT ename,TO_CHAR(hiredate,'yyyy') year,TO_CHAR(hiredate,'MM') mon FROM emp ORDER BY year,mon;

21.显示在一个月为30天的情况所有员工的日薪金，并且忽略余数：

ROUND() 四舍五入

SELECT ename,ROUND(sal/30) 日薪金

FROM emp;

22.找出在（任何年份）的2月受聘的所有员工：

SELECT * FROM emp WHERE TO_CHAR(hiredate,'MM')=2;

23.对于每个员工，显示其加入公司的天数：

SELECT ROUND(sysdate-hiredate) FROM emp;

24.显示姓名字段的任何位置包含“A”的所有员工姓名：

SELECT ename FROM emp WHERE ename LIKE '%A%';

25.以年月的方式显示所有员工的服务年限：

·年：求出总共的月/12 -> 产生小数，并不能四舍五入

·月：对12取余

SELECT ename,

TRUNC(MONTHS_BETWEEN(sysdate,hiredate)/12) year,

TRUNC(MOD(MONTHS_BETWEEN(sysdate,hiredate),12)) mon

FROM emp;

/*-------------复杂查询，子查询，多表关联--------------*/

26.列出至少有三个员工的所有部门和部门信息。[!!]

SELECT d.*,ed.count FROM dept d,(

SELECT deptno,COUNT(empno) count FROM emp GROUP BY deptno HAVING COUNT(empno)>3) ed

WHERE d.deptno=ed.deptno;

27.列出薪金比“ALLEN”多的所有员工

SELECT sal FROM emp WHERE ename='ALLEN'; //子查询

SELECT * FROM emp

WHERE sal>(SELECT sal FROM emp WHERE ename='ALLEN');

28.列出所有员工的姓名及其上级的姓名：

SELECT e.ename 员工,m.ename 上级

FROM emp e,emp m

WHERE e.mgr=m.empno(+);

由于KING并没有上级，所以添加一个(+)号表示左连接

29.列出受雇日期早于直接上级的所有员工的编号，姓名，部门名称

SELECT e.ename,e.empno,d.dname

FROM emp e,emp m,dept d

WHERE e.mgr=m.empno AND e.deptno=d.deptno AND e.hiredate<m.hiredate

;

30.列出部门名称和这些部门员工的信息，同时列出那些没有员工的部门。

·左右关联的问题，即使没有员工也要显示

SELECT d.deptno,d.dname,e.empno,e.ename

FROM dept d,emp e

WHERE d.deptno=e.deptno(+);

31.列出“CLERK”的姓名和部门名称，部门人数：

·找出所有办事员的姓名和部门编号：

SELECT e.ename,d.dname,ed.cou

FROM emp e,dept d,(SELECT deptno,COUNT(empno) cou FROM emp GROUP BY deptno) ed

WHERE e.deptno=d.deptno AND job='CLERK' AND ed.deptno=d.deptno;

32.列出最低薪金大于1500的各种工作以及从事此工作的全部雇员人数

·按工作分组，分组条件是最低工资大于1500

SELECT job,MIN(sal) FROM emp

GROUP BY job HAVING MIN(sal)>1500;

·在按照

SELECT e.job,COUNT(empno)

FROM emp e

WHERE job IN(

SELECT job FROM emp

GROUP BY job HAVING MIN(sal)>1500

)

GROUP BY e.job;

33.列出在部门销售部工作的员工姓名，假设不知道销售部的部门编号

·根据DEPT表查询销售部的部门编号(子查询)

SELECT deptno

FROM dept

WHERE dname='SALES';

·上述为子查询

SELECT ename FROM emp

WHERE deptno=(

SELECT deptno

FROM dept

WHERE dname='SALES');

34.列出薪金高于工资平均薪金的所有员工，所在部门，上级领导，公司的工资等级。

·求出平均工资：

SELECT AVG(sal) FROM emp;

·列出薪金高于平均工资的所有

雇员信息

SELECT * FROM emp WHERE sal>(

SELECT AVG(sal) FROM emp

);

·和部门表关联，查询所在部门的信息(注意KING 是没有上级的 注意右连接)

SELECT e.*,d.dname,d.loc,m.ename

FROM emp e,dept d,emp m

WHERE

e.mgr=m.empno(+) AND

e.deptno=d.deptno AND

e.sal>(

SELECT AVG(sal) FROM emp

);

·求出雇员的工资等级

SELECT e.*,d.dname,d.loc,m.ename,s.grade

FROM emp e,dept d,emp m,salgrade s

WHERE

e.mgr=m.empno(+) AND

e.deptno=d.deptno AND

e.sal>(SELECT AVG(sal) FROM emp)

AND e.sal BETWEEN s.losal AND s.hisal

;

35.列出和“SCOTT”从事相同工作的所有员工及部门名称：

·SCOTT从事的工作

SELECT job FROM emp WHERE ename='SCOTT';

·做子查询

SELECT e.*,d.dname FROM emp e,dept d

WHERE e.job=(SELECT job FROM emp WHERE ename='SCOTT')

AND e.deptno=d.deptno;

·以上的结果存在SCOTT，应该去掉

SELECT e.*,d.dname FROM emp e,dept d

WHERE e.job=(SELECT job FROM emp WHERE ename='SCOTT')

AND e.deptno=d.deptno

AND e.ename!='SCOTT';

36.列出薪金等于部门30中员工薪金的所有员工的姓名和薪金

·求出部门30中的员工薪金

SELECT sal FROM emp WHERE deptno=30;

·子查询

SELECT ename,sal FROM emp

WHERE sal IN (SELECT sal FROM emp WHERE deptno=30)

AND deptno!=30;

37.列出薪金高于在部门30工作的所有员工的薪金的员工姓名和薪金、部门名称

·在之前的程序进行修改使用>ALL ，比最大还大

SELECT ename,sal FROM emp

WHERE sal >ALL(SELECT sal FROM emp WHERE deptno=30)

AND deptno!=30;

·再和dept关联，求出部门名称

SELECT e.ename,e.sal,d.dname

FROM emp e,dept d

WHERE e.sal >ALL(SELECT sal FROM emp WHERE deptno=30)

AND e.deptno!=30

AND e.deptno=d.deptno;

38.列出每个部门工作的员工数量、平均工资和平均服务期限

·每个部门工作的员工数量：

SELECT d.dname,COUNT(e.empno)

FROM emp e,dept d

WHERE e.deptno=d.deptno

GROUP BY d.dname;

·求出平均工资和服务年限

SELECT d.dname,COUNT(e.empno),AVG(sal),AVG(MONTHS_BETWEEN(sysdate,hiredate)/12) 年

FROM emp e,dept d

WHERE e.deptno=d.deptno

GROUP BY d.dname;

39.列出所有员工的姓名、部门和工资

SELECT e.ename,d.dname,e.sal FROM emp e,dept d WHERE e.deptno=d.deptno;

40.列出所有部门的相信信息和部门人数

·列出所有部门的人数

SELECT deptno dno,COUNT(empno) cou

FROM emp

GROUP BY deptno;

·把上表当成临时表：【由于40部门没有雇员，所以应该使用0表示】

SELECT d.*,NVL(ed.cou,0) FROM dept d,(SELECT deptno dno,COUNT(empno) cou

FROM emp

GROUP BY deptno) ed

WHERE d.deptno=ed.dno(+);

41、列出各种工作的最低工资以及从事此工作的雇员姓名：

·按工作分组求出最低工资

SELECT MIN(sal) m FROM emp GROUP BY job;

·子查询

SELECT e.ename FROM emp e

WHERE e.sal IN(SELECT MIN(sal) m FROM emp GROUP BY job);

42、列出各个部门的MANAGER 的最低薪金：

·求出各个

部门MANAGER的工资，按照部门分组

SELECT deptno,MIN(sal) FROM emp WHERE job='MANAGER' GROUP BY deptno;

43、列出所有员工的年工资，按照年薪从低到高排序：

·注意奖金，奖金要用NVL函数处理

SELECT ename,(sal+NVL(comm,0))*12 income FROM emp ORDER BY income

44、查询出某个员工的上级主管，并要求这些主管中的薪水超过3000

SELECT DISTINCT m.*

FROM emp e,emp m

WHERE e.mgr=m.empno AND m.sal>3000;

45、求出部门名称中带有’S‘字符的部门员工的工资合计，部门人数

·查询部门表中的部门名称，使用模糊查询，以确定部门编号

SELECT deptno FROM dept WHERE dname LIKE '%S%';

·再根据上面作为子查询，求出工资合计和部门人数

SELECT SUM(sal),COUNT(empno)

FROM emp e

WHERE e.deptno IN (SELECT deptno FROM dept WHERE dname LIKE '%S%')

GROUP BY deptno;

46、给任职日期超过10年的人加薪10%；

UPDATE emp SET sal=sal*1.1 WHERE MONTHS_BETWEEN(sysdate,hiredate)/12>10;