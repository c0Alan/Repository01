三、oracle 用户管理一
一、创建用户
概述：在oracle中要创建一个新的用户使用create user语句，一般是具有dba(数据库管理员)的权限才能使用。
create user 用户名 identified by 密码; 
注意：oracle有个毛病，密码必须以字母开头，如果以数字开头，它不会创建用户
eg、create user xiaoming identified by oracle;
           
二、给用户修改密码
概述：如果给自己修改密码可以直接使用
SQL> password 用户名或passw
如果给别人修改密码则需要具有dba的权限，或是拥有alter user的系统权限
SQL> alter user 用户名 identified by 新密码
                  
三、删除用户
概述：一般以dba的身份去删除某个用户，如果用其它用户去删除用户则需要具有drop user的权限。
比如drop user 用户名 【cascade】
注意：在删除用户时，如果要删除的用户，已经创建了表，那么就需要在删除的时候带一个参数cascade，即把该用户及表一同删除;
                 
四、权限
权限分为系统权限和对象权限。
何为系统权限？
用户对数据库的相关权限，connect、resource、dba等系统权限，如建库、建表、建索引、建存储过程、登陆数据库、修改密码等。
何为对象权限？
用户对其他用户的数据对象操作的权限，insert、delete、update、select、all等对象权限，数据对象有很多，比如表，索引，视图，触发器、存储过程、包等。
执行SELECT * FROM Dba_Object_Size;语句可得到oracle数据库对象。
             
五、角色
角色分为预定义角色和自定义角色。
           
六、用户管理的综合案例
概述：创建的新用户是没有任何权限的，甚至连登陆的数据库的权限都没有，需要为其指定相应的权限。给一个用户赋权限使用命令grant，回收权限使用命令revoke。
为了讲清楚用户的管理，这里我给大家举一个案例。
SQL> conn xiaoming/oracle
ERROR:
ORA-01045: user XIAOMING lacks CREATE SESSION privilege; logon denied
警告: 您不再连接到 ORACLE。
SQL> show user
USER 为 ""
SQL> conn system/oracle
已连接。
SQL> grant connect to xiaoming;
授权成功。
SQL> conn xiaoming/oracle
已连接。
SQL>
注意：grant connect to xiaoming;在这里，准确的讲，connect不是权限，而是角色。
                            
现在说下对象权限，现在要做这么件事情：
* 希望xiaoming用户可以去查询emp表
* 希望xiaoming用户可以去查询scott的emp表
grant select on scott.emp to xiaoming
* 希望xiaoming用户可以去修改scott的emp表
grant update on scott.emp to xiaoming
* 希望xiaoming 用户可以去修改/删除，查询，添加scott的emp表
grant all on scott.emp to xiaoming
* scott希望收回xiaoming对emp表的查询权限
revoke select on scott.emp from xiaoming
                  
七、权限的传递
//对权限的维护。
* 希望xiaoming用户可以去查询scott的emp表/还希望xiaoming可以把这个权限传递给别人。
--如果是对象权限，就加入with grant option
grant select on emp to xiaoming with grant option
我的操作过程：
SQL> conn scott/oracle;
已连接。
SQL> grant select on scott.emp to xiaoming with grant option;
授权成功。
SQL> conn system/oracle;
已连接。
SQL> create user xiaohong identified by oracle;
用户已创建。
SQL> grant connect to xiaohong;
授权成功。
SQL> conn xiaoming/oracle;
已连接。
SQL> grant select on scott.emp to xiaohong;
授权成功。
                             
--如果是系统权限。
system给xiaoming权限时：grant connect to xiaoming with admin option
问题：如果scott把xiaoming对emp表的查询权限回收，那么xiaohong会怎样？
答案：被回收。
下面是我的操作过程：
SQL> conn scott/oracle;
已连接。
SQL> revoke select on emp from xiaoming;
撤销成功。
SQL> conn xiaohong/oracle;
已连接。
SQL> select * from scott.emp;
select * from scott.emp
*
第 1 行出现错误:
ORA-00942: 表或视图不存在
结果显示：小红受到诛连了。。
   
八、with admin option与with grant option区别
1、with admin option用于系统权限授权，with grant option用于对象授权。
  
 
2、给一个用户授予系统权限带上with admin option时，此用户可把此系统权限授予其他用户或角色，但收回这个用户的系统权限时，这个用户已经授予其他用户或角色的此系统权限不会因传播无效，如授予A系统权限create session with admin option,然后A又把create session权限授予B,但管理员收回A的create session权限时，B依然拥有create session的权限，但管理员可以显式收回B create session的权限，即直接revoke create session from B.   
   
 
而with grant option用于对象授权时，被授予的用户也可把此对象权限授予其他用户或角色，不同的是但管理员收回用with grant option授权的用户对象权限时，权限会因传播而失效，如grant select on table with grant option to A,A用户把此权限授予B，但管理员收回A的权限时，B的权限也会失效，但管理员不可以直接收回B的SELECT ON TABLE 权限。