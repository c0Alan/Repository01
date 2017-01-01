十二、oracle 数据库(表)的逻辑备份与恢复

一、介绍
逻辑备份是指使用工具export将数据对象的结构和数据导出到文件的过程。
逻辑恢复是指当数据库对象被误操作而损坏后使用工具import利用备份的文件把数据对象导入到数据库的过程。
物理备份即可在数据库open的状态下进行也可在关闭数据库后进行，但是逻辑备份和恢复只能在open的状态下进行。

二、备份(导出)
导出分为导出表、导出方案、导出数据库三种方式。
导出使用exp命令来完成的，该命令常用的选项有：
userid：用于指定执行导出操作的用户名，口令，连接字符串
tables：用于指定执行导出操作的表
owner：用于指定执行导出操作的方案
full=y：用于指定执行导出操作的数据库
inctype：用于指定执行导出操作的增量类型
rows：用于指定执行导出操作是否要导出表中的数据
file：用于指定导出文件名

注意：特别说明-->在导入和导出的时候，要到oracle目录的bin目录下。

1)、导出表
1.导出自己的表
exp userid=scott/oracle@orcl tables=(emp) file=d:\emp.dmp --导出单个表
exp userid=scott/oracle@orcl tables=(emp,dept) file=d:\emp.dmp --导出多个表
eg、
C:\Users\jiqinlin>cd D:\dev\oracle\product\10.2.0\db_1\bin
C:\Users\jiqinlin>d:
D:\dev\oracle\product\10.2.0\db_1\bin>exp userid=scott/oracle@orcl tables=(emp) file=d:\emp.dmp

2.导出其它方案的表
如果用户要导出其它方案的表，则需要dba的权限或是exp_full_database的权限，比如system就可以导出scott的表
D:\dev\oracle\product\10.2.0\db_1\bin>exp userid=system/oracle@orcl tables=(scott.emp) file=d:\emp.emp
D:\dev\oracle\product\10.2.0\db_1\bin>exp userid=system/oracle@orcl tables=(scott.emp,scott.dept) file=d:\emp.emp

3. 导出表的结构
exp userid=scott/oracle@orcl tables=(emp) file=d:\emp.dmp rows=n

4. 使用直接导出方式
exp userid=scott/oracle@orcl tables=(emp) file=d:\emp.dmp direct=y
这种方式比默认的常规方式速度要快，当数据量大时，可以考虑使用这样的方法。
这时需要数据库的字符集要与客户端字符集完全一致，否则会报错...

2)、导出方案
导出方案是指使用export工具导出一个方案或是多个方案中的所有对象(表，索引，约束...)和数据，并存放到文件中。
1. 导出自己的方案
exp userid=scott/oracle@orcl owner=scott file=d:\scott.dmp
2. 导出其它方案
如果用户要导出其它方案，则需要dba的权限或是exp_full_database的权限，
比如system 用户就可以导出任何方案
exp userid=system/oracle@orcl owner=(system,scott) file=d:\system.dmp

3)、导出数据库
导出数据库是指利用export导出所有数据库中的对象及数据，要求该用户具有dba的权限或者是exp_full_database权限
增量备份（好处是第一次备份后，第二次备份就快很多了）
exp userid=system/oracle@orcl full=y inctype=complete file=d:\all.dmp

三、恢复(导入)
导入就是使用工具import将文件中的对象和数据导入到数据库中，但是导入要使用的文件必须是export所导出的文件。与导出相似，导入也分为导入表，导入方案，导入数据库三种方式。
imp常用的选项有
userid：用于指定执行导入操作的用户名，口令，连接字符串
tables：用于指定执行导入操作的表
formuser：用于指定源用户
touser：用于指定目标用户
file 用于指定导入文件名
full=y：用于指定执行导入整个文件
inctype：用于指定执行导入操作的增量类型
rows：指定是否要导入表行（数据）
ignore：如果表存在，则只导入数据

1)导入表
1. 导入自己的表
imp userid=scott/oracle@orcl tables=(emp) file=d:\xx.dmp
2. 导入表到其它用户
要求该用户具有dba的权限，或是imp_full_database
imp userid=system/oracle@orcl tables=(emp) file=d:\xx.dmp touser=scott
3. 导入表的结构
只导入表的结构而不导入数据
imp userid=scott/oracle@orcl tables=(emp) file=d:\xx.dmp rows=n
4. 导入数据
如果对象（如比表）已经存在可以只导入表的数据
imp userid=scott/oracle@orcl tables=(emp) file=d:\xx.dmp ignore=y

2)导入方案
导入方案是指使用import工具将文件中的对象和数据导入到一个或是多个方案中。如果要导入其它方案，要求该用户具有dba 的权限，或者imp_full_database
1．导入自身的方案
imp userid=scott/oracle@orcl file=d:\xxx.dmp
2．导入其它方案
要求该用户具有dba的权限
imp userid=system/oracle@orcl file=d:\xxx.dmp fromuser=system touser=scott

3)导入数据库(相当于数据库迁移)
在默认情况下，当导入数据库时，会导入所有对象结构和数据，案例如下：
imp userid=system/oracle@orcl full=y file=d:\xxx.dmp