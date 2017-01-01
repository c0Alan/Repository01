六、表查询一

通过scott用户下的表来演示如何使用select语句，接下来对emp、dept、salgrade表结构进行解说。

emp 雇员表
字段名称   数据类型       是否为空   备注
--------   -----------   --------   --------
EMPNO    NUMBER(4)                 员工编号                
ENAME    VARCHAR2(10) Y         员工名称        
JOB        VARCHAR2(9)   Y         职位                
MGR       NUMBER(4)       Y         上级的编号            
HIREDATE DATE             Y         入职日期               
SAL         NUMBER(7,2)   Y         月工资            
COMM      NUMBER(7,2)   Y         奖金                
DEPTNO   NUMBER(2)      Y         所属部门
-------------------------------------------
job字段：
clerk 普员工
salesman 销售
manager 经理
analyst 分析师
president 总裁

dept 部门表
字段名称    数据类型          是否为空   备注
--------    -----------      --------   --------
DEPTNO   NUMBER(2)                    部门编号               
DNAME    VARCHAR2(14)    Y          部门名称        
LOC         VARCHAR2(13)   Y          部门所在地点
-------------------------------------------
DNAME字段：
accounting 财务部
research 研发部
operations 业务部

salgrade 工资级别表
字段名称  数据类型   是否为空  备注
--------  ---------  --------  --------
GRADE   NUMBER     Y         级别                
LOSAL    NUMBER     Y         最低工资               
HISAL     NUMBER     Y         最高工资

1、查看表结构
desc emp;

2、查询所有列
select * from dept;
备注:切忌动不动就用select *，使用*效率比较低，特别在大表中要注意。

3、set timing on/off;
打开显示操作时间的开关，在底部显示操作时间。
eg、sql> insert into tb_stu values('0001', 'zhangsan', 24); 
1 row inserted
executed in 0.015 seconds

4、insert into...select...表复制语句
语法：insert into table2(field1,field2,...) select value1,value2,... from table1

--创建tb_dept表
create table tb_dept
(
  deptno number(4) not null,
  dname  varchar2(14),
  loc    varchar2(13)
)
--添加主键约束
alter table tb_dept add constraint tb_dept primary key (deptno);

--insert into...select...用法
insert into tb_dept (deptno, dname, loc) select a.deptno, a.dname, a.loc from dept a;

5、统计
select count (*) from emp;

6、查询指定列
select ename, sal, job, deptno from emp;

7、如何取消重复行distinct
select distinct deptno, job from emp;

8、查询smith所在部门，工作，薪水
select deptno, job, sal from emp where ename = 'smith';
注意：oracle对内容的大小写是敏感的，所以ename='smith'和ename='smith'是不同的

9、nvl函数
格式为：nvl(string1, replace_with) 　　
功能：如果string1为null，则nvl函数返回replace_with的值，否则返回string1的值。 　
注意事项：string1和replace_with必须为同一数据类型，除非显示的使用to_char函数。 　
eg、如何显示每个雇员的年工资？
select sal*13+nvl(comm, 0)*13 "年薪" , ename, comm from emp;

10、使用列的别名
select ename "姓名", sal*12 as "年收入" from emp;

11、如何处理null值
使用nvl函数来处理

12、如何连接字符串(||)
select ename || ' is a ' || job from emp;

13、使用where子句
问题：如何显示工资高于3000的员工？
select * from emp where sal > 3000;
问题：如何查找1982.1.1后入职的员工？
select ename,hiredate from emp where hiredate >'1-1 月-1982';
问题：如何显示工资在2000到3000的员工？
select ename,sal from emp where sal>=2000 and sal<=3000;

14、如何使用like操作符
%：表示0到多个字符 _：表示任意单个字符
问题：如何显示首字符为s的员工姓名和工资？
select ename,sal from emp where ename like 's%';
如何显示第三个字符为大写o的所有员工的姓名和工资？
select ename,sal from emp where ename like '__o%';

15、在where条件中使用in
问题：如何显示empno为7844,7839,123,456的雇员情况？
select * from emp where empno in (7844, 7839, 123, 456);

16、使用is null的操作符
问题：如何显示没有上级的雇员的情况？
错误写法：select * from emp where mgr = '';
正确写法：select * from emp where mgr is null;