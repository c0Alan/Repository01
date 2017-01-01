十七、oracle 权限

一、介绍
这一部分我们主要看看oracle中如何管理权限和角色，权限和角色的区别在哪里。
当刚刚建立用户时，用户没有任何权限，也不能执行任何操作。如果要执行某种特定的数据库操作，则必须为其授予系统的权限；如果用户要访问其它方案的对象，则必须为其授予对象的权限。为了简化权限的管理，可以使用角色。这里我们会详细的介绍。

二、权限
权限是指执行特定类型sql命令或是访问其它方案对象的权利，包括系统权限和对象权限两种。

三、系统权限
1)、系统权限是指执行特定类型sql命令的权利。它用于控制用户可以执行的一个或是一组数据库操作。比如当用户具有create table权限时，可以在其方案中建表，当用户具有create any table权限时，可以在任何方案中建表。oracle提供了100多种系统权限。
常用的有：
create session 连接数据库 
create table 建表
create view 建视图 
create public synonym 建同义词
create procedure 建过程、函数、包 
create trigger 建触发器
create cluster 建簇

2)、显示系统权限
oracle提供了100多种系统权限，而且oracle的版本越高，提供的系统权限就越多，我们可以查询数据字典视图system_privilege_map，可以显示所有系统权限。
select * from system_privilege_map order by name;

3)、授予系统权限
一般情况，授予系统权限是由dba完成的，如果用其他用户来授予系统权限，则要求该用户必须具有grant any privilege的系统权限。在授予系统权限时，可以带有with admin option选项，这样，被授予权限的用户或是角色还可以将该系统权限授予其它的用户或是角色。为了让大家快速理解，我们举例说明：
1.创建两个用户ken，tom。初始阶段他们没有任何权限，如果登录就会给出错误的信息。
create user ken identified by ken;
2 给用户ken授权
1). grant create session, create table to ken with admin option;
2). grant create view to ken;
3 给用户tom授权
我们可以通过ken给tom授权，因为with admin option是加上的。当然也可以通过dba给tom授权，我们就用ken给tom授权：
1. grant create session, create table to tom;
2. grant create view to ken; --ok 吗？不ok

4)、回收系统权限
一般情况下，回收系统权限是dba来完成的，如果其它的用户来回收系统权限，要求该用户必须具有相应系统权限及转授系统权限的选项(with admin option)。回收系统权限使用revoke来完成。当回收了系统权限后，用户就不能执行相应的操作了，但是请注意，系统权限级联收回的问题?[不是级联回收！]
system --------->ken ---------->tom
(create session)(create session)( create session)
用system 执行如下操作：
revoke create session from ken; --请思考tom还能登录吗？
答案：能，可以登录

四、对象权限
1)、对象权限介绍
指访问其它方案对象的权利，用户可以直接访问自己方案的对象，但是如果要访问别的方案的对象，则必须具有对象的权限。
比如smith用户要访问scott.emp表(scott：方案，emp：表)
常用的有：
insert 添加
delete 删除 
alter 修改 
select 查询 
index 索引 
references 引用 
execute 执行

2)、显示对象权限
通过数据字段视图可以显示用户或是角色所具有的对象权限。视图为dba_tab_privs
SQL> conn system/manager;
SQL> select distinct privilege from dba_tab_privs;
SQL> select grantor, owner, table_name, privilege from dba_tab_privs where grantee = 'BLAKE';

3)、授予对象权限
在oracle9i前，授予对象权限是由对象的所有者来完成的，如果用其它的用户来操作，则需要用户具有相应的(with grant option)权限，从oracle9i 开始，dba用户(sys，system)可以将任何对象上的对象权限授予其它用户。授予对象权限是用grant 命令来完成的。对象权限可以授予用户，角色，和public。在授予权限时，如果带有with grantoption 选项，则可以将该权限转授给其它用户。但是要注意with grant option选项不能被授予角色。
1.monkey 用户要操作scott.emp 表，则必须授予相应的对象权限
1). 希望monkey可以查询scott.emp 表的数据，怎样操作？
grant select on emp to monkey;
2). 希望monkey可以修改scott.emp 的表数据，怎样操作？
grant update on emp to monkey;
3). 希望monkey可以删除scott.emp 的表数据，怎样操作？
grant delete on emp to monkey;
4). 有没有更加简单的方法，一次把所有权限赋给monkey？
grant all on emp to monkey;

2.能否对monkey访问权限更加精细控制。(授予列权限)
1). 希望monkey只可以修改scott.emp的表的sal字段，怎样操作？
grant update on emp(sal) to monkey
2).希望monkey 只可以查询scott.emp 的表的ename，sal 数据，怎样操作？
grant select on emp(ename,sal) to monkey

3.授予alter权限
如果black用户要修改scott.emp表的结构，则必须授予alter对象权限
SQL> conn scott/tiger
SQL> grant alter on emp to blake;
当然也可以用system，sys 来完成这件事。
4.授予execute权限
如果用户想要执行其它方案的包/过程/函数，则须有execute权限。
比如为了让ken可以执行包dbms_transaction，可以授予execute 权限。
SQL> conn system/manager
SQL> grant execute on dbms_transaction to ken;
5.授予index权限
如果想在别的方案的表上建立索引，则必须具有index 对象权限。
如果为了让black 可以在scott.emp 表上建立索引，就给其index 的对象权限
SQL> conn scott/tiger
SQL> grant index on scott.emp to blake;
6.使用with grant option 选项
该选项用于转授对象权限。但是该选项只能被授予用户，而不能授予角色
SQL> conn scott/tiger;
SQL> grant select on emp to blake with grant option;
SQL> conn black/shunping
SQL> grant select on scott.emp to jones;

4)、回收对象权限
在oracle9i 中，收回对象的权限可以由对象的所有者来完成，也可以用dba用户(sys，system)来完成。
这里要说明的是：收回对象权限后，用户就不能执行相应的sql命令，但是要注意的是对象的权限是否会被级联收回？【级联回收】
如：scott------------->blake-------------->jones
select on emp select on emp select on emp
SQL> conn scott/tiger@accp
SQL> revoke select on emp from blake
请大家思考，jones能否查询scott.emp表数据。
答案：查不了了(级联回收，和系统权限不一样，刚好1相反)