四、oracle 用户管理二
一、使用profile管理用户口令
概述：profile是口令限制，资源限制的命令集合，当建立数据库时，oracle会自动建立名称为default的profile。当建立用户没有指定profile选项时，那么oracle就会将default分配给用户。
1.账户锁定
概述：指定该账户(用户)登陆时最多可以输入密码的次数，也可以指定用户锁定的时间(天)一般用dba的身份去执行该命令。
例子：指定scott这个用户最多只能尝试3次登陆，锁定时间为2天，让我们看看怎么实现。
创建profile文件
SQL> create profile lock_account limit failed_login_attempts 3 password_lock_time 2;
SQL> alter user scott profile lock_account;
2.给账户(用户)解锁
SQL> alter user scott account unlock;
3.终止口令
为了让用户定期修改密码可以使用终止口令的指令来完成，同样这个命令也需要dba的身份来操作。
例子：给前面创建的用户test创建一个profile文件，要求该用户每隔10天要修改自己的登陆密码，宽限期为2天。看看怎么做。
SQL> create profile myprofile limit password_life_time 10 password_grace_time 2;
SQL> alter user test profile myprofile;
 
二、口令历史
概述：如果希望用户在修改密码时，不能使用以前使用过的密码，可使用口令历史，这样oracle就会将口令修改的信息存放到数据字典中，这样当用户修改密码时，oracle就会对新旧密码进行比较，当发现新旧密码一样时，就提示用户重新输入密码。
例子：
1）建立profile
SQL>create profile password_history limit password_life_time 10 password_grace_time 2 
password_reuse_time 10 //password_reuse_time指定口令可重用时间即10天后就可以重用
2）分配给某个用户
SQL> alter user test profile password_history;
 
三、删除profile
概述：当不需要某个profile文件时，可以删除该文件。
SQL> drop profile password_history 【casade】
注意：文件删除后，用这个文件去约束的那些用户通通也都被释放了。。
加了casade，就会把级联的相关东西也给删除掉