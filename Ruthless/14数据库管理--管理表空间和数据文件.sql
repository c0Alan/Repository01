十四、oracle 数据库管理--管理表空间和数据文件

一、概念
表空间是数据库的逻辑组成部分。
从物理上讲，数据库数据存放在数据文件中；
从逻辑上讲，数据库数据则是存放在表空间中，表空间由一个或多个数据文件组成。

二、数据库的逻辑结构
oracle中逻辑结构包括表空间、段、区和块。
说明一下数据库由表空间构成，而表空间又是由段构成，而段又是由区构成，而区又是由oracle块构成的这样的一种结构，可以提高数据库的效率。

三、表空间
1、概念
表空间用于从逻辑上组织数据库的数据。数据库逻辑上是由一个或是多个表空间组成的。通过表空间可以达到以下作用：
1)、控制数据库占用的磁盘空间
2)、dba可以将不同数据类型部署到不同的位置，这样有利于提高i/o性能，同时利于备份和恢复等管理操作。

2、建立表空间
建立表空间是使用crate tablespace命令完成的，需要注意的是，一般情况下，建立表空间是特权用户或是dba来执行的，如果用其它用户来创建表空间，则用户必须要具有create tablespace的系统权限。
1)、建立数据表空间
在建立数据库后，为便于管理表，最好建立自己的表空间
--路径D:\dev\oracle\product\10.2.0\要存在，否则创建不成功
create tablespace data01 datafile 'D:\dev\oracle\product\10.2.0\dada01.dbf' size 20m uniform size 128k; 
说明：执行完上述命令后，会建立名称为data01的表空间，并为该表空间建立名称为data01.dbf的数据文件，区的大小为128k
2)、使用数据表空间
create table mypart(
   deptno number(4), 
   dname varchar2(14), 
   loc varchar2(13)
) tablespace data01;

3、改变表空间的状态
当建立表空间时，表空间处于联机的(online)状态，此时该表空间是可以访问的，并且该表空间是可以读写的，即可以查询该表空间的数据，而且还可以在表空间执行各种语句。但是在进行系统维护或是数据维护时，可能需要改变表空间的状态。一般情况下，由特权用户或是dba来操作。
1)、使表空间脱机
alter tablespace 表空间名 offline;
eg、alter tablespace data01 offline;--表空间名不能加单引号
2)、使表空间联机
alter tablespace 表空间名 online;
eg、alter tablespace data01 online;
3)、只读表空间
当建立表空间时，表空间可以读写，如果不希望在该表空间上执行update，delete，insert操作，那么可以将表空间修改为只读
alter tablespace 表空间名 read only;
注意：修改为可写是alter tablespace 表空间名 read write;)

我们给大家举一个实例，说明只读特性：
1)、知道表空间名，显示该表空间包括的所有表
select * from all_tables where tablespace_name=’表空间名’;
eg、select * from all_tables where tablespace_name='DATA01'; --DATA01要大写格式
2)、 知道表名，查看该表属于那个表空间
select tablespace_name, table_name from user_tables where table_name='EMP';
通过2我们可以知道scott.emp是在system这个表空间上，现在我们可以将system改为只读的但是我们不会成功，因为system是系统表空间，如果是普通表空间，那么我们就可以将其设为只读的，给大家做一个演示，可以加强理解。
3)、
4)、使表空间可读写
alter tablespace 表空间名 read write;

4、删除表空间
一般情况下，由特权用户或是dba来操作，如果是其它用户操作，那么要求用户具有drop tablespace 系统权限。
drop tablespace ‘表空间’ including contents and datafiles;
eg、drop TABLESPACE DATA01 including contents and datafiles;
说明：including contents表示删除表空间时，删除该空间的所有数据库对象，而datafiles表示将数据库文件也删除。

5、扩展表空间
表空间是由数据文件组成的，表空间的大小实际上就是数据文件相加后的大小。那么我们可以想象，假定表employee存放到data01表空间上，初始大小就是2M，当数据满2M空间后，如果在向employee表插入数据，这样就会显示空间不足的错误。
案例说明：
1. 建立一个表空间sp01
eg、create tablespace sp01 datafile 'D:\dev\oracle\product\10.2.0\dada01.dbf' size 1m uniform size 128k; 
2. 在该表空间上建立一个普通表mydment其结构和dept一样
create table mypart(
   deptno number(4), 
   dname varchar2(14), 
   loc varchar2(13)
) tablespace sp01;
3. 向该表中加入数据insert into mydment select * from dept;
4. 当一定时候就会出现无法扩展的问题，怎么办？
5. 就扩展该表空间，为其增加更多的存储空间。
有三种方法：
1. 增加数据文件
SQL> alter tablespace sp01 add datafile 'D:\dev\oracle\product\10.2.0\dada02.dbf' size 1m;
2. 修改数据文件的大小
SQL> alter tablespace sp01 'D:\dev\oracle\product\10.2.0\dada01.dbf' resize 4m;
这里需要注意的是数据文件的大小不要超过500m。
3. 设置文件的自动增长。
SQL> alter tablespace sp01 'D:\dev\oracle\product\10.2.0\dada01.dbf' autoextend on next 10m maxsize 500m;

6、移动数据文件
有时，如果你的数据文件所在的磁盘损坏时，该数据文件将不能再使用，为了能够重新使用，需要将这些文件的副本移动到其它的磁盘，然后恢复。
下面以移动数据文件sp01.dbf为例来说明：
1. 确定数据文件所在的表空间
select tablespace_name from dba_data_files where file_name=upper('D:\dev\oracle\product\10.2.0\dada01.dbf');
2. 使表空间脱机
--确保数据文件的一致性，将表空间转变为offline的状态。
alter tablespace sp01 offline;
3. 使用命令移动数据文件到指定的目标位置
host move D:\dev\oracle\product\10.2.0\dada01.dbf c:\dada01.dbf;

4. 执行alter tablespace 命令
在物理上移动了数据后，还必须执行alter tablespace命令对数据库文件进行逻辑修改：
alter tablespace sp01 rename datafile 'D:\dev\oracle\product\10.2.0\dada01.dbf' to 'c:\sp01.dbf';
5. 使得表空间联机
在移动了数据文件后，为了使用户可以访问该表空间，必须将其转变为online状态。
alter tablespace sp01 online;

7、显示表空间信息
查询数据字典视图dba_tablespaces，显示表空间的信息：
select tablespace_name from dba_tablespaces;
显示表空间所包含的数据文件
查询数据字典视图dba_data_files,可显示表空间所包含的数据文件，如下：
select file_name, bytes from dba_data_files where tablespace_name='表空间';

四、表空间小结
1. 了解表空间和数据文件的作用
2. 掌握常用表空间，undo表空间和临时表空间的建立方法
3. 了解表空间的各个状态(online, offline, read write, read only)的作用，及如何改变表空间的状态的方法。
4. 了解移动数据文件的原因，及使用alter tablespace 和alter datatable命令移动数据文件的方法。

五、其它表空间
除了最常用的数据表空间外，还有其它类型表空间：
1. 索引表空间
2. undo表空间
3. 临时表空间
4. 非标准块的表空间
这几种表空间，大家伙可以自己参考书籍研究，这里我就不讲。

六、其它说明
关于表空间的组成部分 段/区/块，我们在后面给大家讲解。