十八、oracle 角色

一、介绍
角色就是相关权限的命令集合，使用角色的主要目的就是为了简化权限的管理。
假定有用户a，b，c为了让他们都拥有如下权限
1. 连接数据库
2. 在scott.emp表上select，insert，update。
如果采用直接授权操作，则需要进行12次授权。
因为要进行12次授权操作，所以比较麻烦喔！怎么办？
如果我们采用角色就可以简化：
首先将creat session，select on scott.emp, insert on scott.emp, update on scott.emp 授予角色，然后将该角色授予a，b，c 用户，这样就可以三次授权搞定。

二、角色分为预定义和自定义角色两类

三、预定义角色
预定义角色是指oracle所提供的角色，每种角色都用于执行一些特定的管理任务，下面我们介绍常用的预定义角色connect、resource、dba。
1)、connect角色
connect角色具有一般应用开发人员需要的大部分权限，当建立了一个用户后，多数情况下，只要给用户授予connect和resource角色就够了，那么connect角色具有哪些系统权限呢？
create cluster
create database link
create session
alter session
create table
create view
create sequence

2)、resource角色
resource角色具有应用开发人员所需要的其它权限，比如建立存储过程，触发器等。这里需要注意的是resource角色隐含unlimited tablespace系统权限。
resource角色包含以下系统权限：
create cluster
create indextype
create table
create sequence
create type
create procedure
create trigger

3)、dba角色
dba角色具有所有的系统权限，及with admin option选项，默认的dba用户为sys和system，它们可以将任何系统权限授予其他用户。但是要注意的是dba角色不具备sysdba和sysoper的特权（启动和关闭数据库）。

四、自定义角色
1、顾名思义就是自己定义的角色，根据自己的需要来定义。一般是dba来建立，如果用别的用户来建立，则需要具有create role的系统权限。在建立角色时可以指定验证方式(不验证，数据库验证等)。
1)、建立角色（不验证）
如果角色是公用的角色，可以采用不验证的方式建立角色。
create role 角色名 not identified;
2)、建立角色（数据库验证）
采用这样的方式时，角色名、口令存放在数据库中。当激活该角色时，必须提供口令。在建立这种角色时，需要为其提供口令。
create role 角色名 identified by 密码;

2、角色授权
1)、给角色授权
给角色授予权限和给用户授权没有太多区别，但是要注意，系统权限的unlimited tablespace和对象权限的with grant option选项是不能授予角色的。
SQL> conn system/oracle;
SQL> grant create session to 角色名 with admin option
SQL> conn scott/oracle@orcl;
SQL> grant select on scott.emp to 角色名;
SQL> grant insert, update, delete on scott.emp to 角色名;
通过上面的步骤，就给角色授权了。

2)、分配角色给某个用户
一般分配角色是由dba来完成的，如果要以其它用户身份分配角色，则要求用户必须具有grant any role的系统权限。
SQL> conn system/oracle;
SQL> grant 角色名 to blake with admin option;
因为我给了with admin option选项，所以，blake可以把system分配给它的角色分配给别的用户。

3、删除角色
使用drop role，一般是dba来执行，如果其它用户则要求该用户具有drop any role系统权限。
SQL> conn system/oracle;
SQL> drop role 角色名;
问题：如果角色被删除，那么被授予角色的用户是否还具有之前角色里的权限？
答案：不具有了

4、显示角色信息
1)、显示所有角色
SQL> select * from dba_roles;
2)、显示角色具有的系统权限
SQL> select privilege, admin_option from role_sys_privs where role='角色名';
3)、显示角色具有的对象权限
通过查询数据字典视图dba_tab_privs可以查看角色具有的对象权限或是列的权限。
4)、显示用户具有的角色，及默认角色
当以用户的身份连接到数据库时，oracle 会自动的激活默认的角色，通过查询数据字典视图dba_role_privs 可以显示某个用户具有的所有角色及当前默认的角色。
SQL> select granted_role, default_role from dba_role_privs where grantee = ‘用户名’;

五、精细访问控制
精细访问控制是指用户可以使用函数，策略实现更加细微的安全访问控制。如果使用精细访问控制，则当在客户端发出sql语句(select，insert，update，delete)时，oracle会自动在sql语句后追加谓词(where子句)，并执行新的sql语句，通过这样的控制，可以使得不同的数据库用户在访问相同表时，返回不同的数据信息，如：
用户 scott blake jones
策略 emp_access
数据库表 emp
如上图所示，通过策略emp_access，用户scott，black，jones在执行相同的sql语句时，可以返回不同的结果。
例如：当执行select ename from emp时，根据实际情况可以返回不同的结果。