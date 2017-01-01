十三、oracle 数据字典和动态性能视图

一、概念
数据字典是oracle数据库中最重要的组成部分，它提供了数据库的一些系统信息。
动态性能视图记载了例程启动后的相关信息。

二、数据字典
1)、数据字典记录了数据库的系统信息，它是只读表和视图的集合，数据字典的所有者为sys用户。
2)、用户只能在数据字典上执行查询操作(select语句)，而其维护和修改是由系统自动完成的。
3)、这里我们谈谈数据字典的组成：数据字典包括数据字典基表和数据字典视图，其中基表存储数据库的基本信息，普通用户不能直接访问数据字典的基表。数据字典视图是基于数据字典基表所建立的视图，普通用户可以通过查询数据字典视图取得系统信息。数据字典视图主要包括user_xxx，all_xxx，dba_xxx三种类型。

user_tables: 用于显示当前用户所拥有的所有表，它只返回用户所对应方案的所有表
比如：select table_name from user_tables;

all_tables: 用于显示当前用户可以访问的所有表，它不仅会返回当前用户方案的所有表，还会返回当前用户可以访问的其它方案的表
比如：select table_name from all_tables;

dba_tables: 它会显示所有方案拥有的数据库表。但是查询这种数据库字典视图，要求用户必须是dba角色或是有select any table 系统权限。
例如：当用system用户查询数据字典视图dba_tables时，会返回system，sys，scott...方案所对应的数据库表。

三、用户名，权限，角色
在建立用户时，oracle会把用户的信息存放到数据字典中，当给用户授予权限或是角色时，oracle会将权限和角色的信息存放到数据字典。
通过查询dba_users可以显示所有数据库用户的详细信息；
通过查询数据字典视图dba_sys_privs，可以显示用户所具有的系统权限；
通过查询数据字典视图dba_tab_privs，可以显示用户具有的对象权限；
通过查询数据字典dba_col_privs 可以显示用户具有的列权限；
通过查询数据库字典视图dba_role_privs 可以显示用户所具有的角色。

这里给大家讲讲角色和权限的关系。
1)、要查看scott具有的角色，可查询dba_role_privs；
SQL> select * from dba_role_privs where grantee='SCOTT';
2)、查询orale中所有的系统权限，一般是dba
select * from system_privilege_map order by name;
3)、查询oracle中所有对象权限，一般是dba
select distinct privilege from dba_tab_privs;
4)、查询oracle 中所有的角色，一般是dba
select * from dba_roles;
5)、查询数据库的表空间
select tablespace_name from dba_tablespaces;

问题1：如何查询一个角色包括的权限？
a.一个角色包含的系统权限
select * from dba_sys_privs where grantee='角色名'
另外也可以这样查看：
select * from role_sys_privs where role='角色名'
b.一个角色包含的对象权限
select * from dba_tab_privs where grantee='角色名'

问题2：oracle究竟有多少种角色？
SQL> select * from dba_roles;

问题3：如何查看某个用户，具有什么样的角色？
select * from dba_role_privs where grantee='用户名'

显示当前用户可以访问的所有数据字典视图。
select * from dict where comments like '%grant%';

显示当前数据库的全称
select * from global_name;

其它说明
数据字典记录有oracle数据库的所有系统信息。通过查询数据字典可以取得以下系统信息：比如
1.对象定义情况
2.对象占用空间大小
3.列信息
4.约束信息
...
但是因为这些个信息，可以通过pl/sql developer工具查询得到，所以这里我就飘过。

四、动态性能视图
动态性能视图用于记录当前例程的活动信息，当启动oracle server时，系统会建立动态性能视图；当停止oracle server时，系统会删除动态性能视图。oracle的所有动态性能视图都是以v_$开始的，并且oracle为每个动态性能视图都提供了相应的同义词，并且其同义词是以V$开始的，例如v_$datafile的同义词为v$datafile;动态性能视图的所有者为sys，一般情况下，由dba或是特权用户来查询动态性能视图。
因为这个在实际中用的较少，所以飞过