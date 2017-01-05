这套面试题主要目的是帮助那些还没有java 软件开发实际工作经验, 而正在努力寻找java
软件开发工作的朋友在笔试时更好地赢得笔试和面试。由于这套面试题涉及的范围很泛, 很
广, 很杂, 大家不可能一天两天就看完和学完这套面试宝典, 即使你已经学过了有关的技术, 
那么至少也需要一个月的时间才能消化和掌握这套面试宝典, 所以, 大家应该早作准备, 从
拿到这套面试宝典之日起, 就要坚持在每天闲暇之余学习其中几道题目, 日积月累, 等到出
去面试时, 一切都水到渠成, 面试时就自然会游刃有余了。
答题时, 先答是什么, 再答有什么作用和要注意什么（这部分最重要, 展现自己的心得）
答案的段落分别, 层次分明, 条理清晰都非常重要, 从这些表面的东西也可以看出一个人的
习惯、办事风格、条理等。
要讲你做出答案的思路过程, 或者说你记住答案的思想都写下来。把答题想着是辩论赛。答
题就是给别人讲道理、摆事实。答题不局限于什么格式和形式, 就是要将自己的学识展现出
来！
别因为人家题目本来就模棱两可, 你就心里胆怯和没底气了, 不敢回答了。你要大胆地指出
对方题目很模糊和你的观点, 不要把面试官想得有多高, 其实他和你就是差不多的, 你想想, 
如果他把你招进去了, 你们以后就是同事了, 可不是差不多的吗？
关于就业薪水, 如果你是应届生, 那不能要高工资, 好比大饼的故事, 没有文凭还想拿高工
资, 就去中关村缺什么补什么吧！少数人基础确实很好, 在校期间确实又做过一些项目, 那
仍然是可以要到相对高的工资的。
1. Java 基础部分
基础部分的顺序：基本语法, 类相关的语法, 内部类的语法, 继承相关的语法, 异常的语法, 
线程的语法, 集合的语法, io 的语法, 虚拟机方面的语法。

1、一个".java"源文件中是否可以包括多个类（不是内部类）？有什么限制？
可以有多个类, 但只能有一个public 的类, 并且public 的类名必须与文件名相一致。

2、Java 有没有goto?
java 中的保留字, 现在没有在java 中使用。

3、说说&和&&的区别。
&和&&都可以用作逻辑与的运算符, 表示逻辑与（and）, 当运算符两边的表达式的结果都
为true 时, 整个运算结果才为true, 否则, 只要有一方为false, 则结果为false。
&&还具有短路的功能, 即如果第一个表达式为false, 则不再计算第二个表达式, 例如, 对
于if(str != null&& !str.equals(""))表达式, 当str 为null 时, 后面的表达式不会执行, 所以不
会出现NullPointerException 如果将&&改为&, 则会抛出NullPointerException 异常。
If(x==33 &++y>0) y 会增长, If(x==33 && ++y>0)不会增长
&还可以用作位运算符, 当&操作符两边的表达式不是boolean 类型时, &表示按位与操作, 
我们通常使用0x0f 来与一个整数进行&运算, 来获取该整数的最低4个bit 位, 例如, 0x31 &
0x0f 的结果为0x01。
备注：这道题先说两者的共同点, 再说出&&和&的特殊之处, 并列举一些经典的例子来表明
自己理解透彻深入、实际经验丰富。

4、在JAVA 中如何跳出当前的多重嵌套循环？
在Java 中, 要想跳出多重循环, 可以在外面的循环语句前定义一个标号, 然后在里层循环
体的代码中使用带有标号的break 语句, 即可跳出外层循环。例如, 
ok:
for(int i=0; i<10; i++) {
    for(int j=0; j<10; j++) {
        System.out.println("i=" + i + ",j=" + j);
        if(j == 5) break ok;
    }
}
另外, 我个人通常并不使用标号这种方式, 而是让外层的循环条件表达式的结果可以受到里
层循环体代码的控制, 例如, 要在二维数组中查找到某个数字。
int arr[][] = {{1,2,3},{4,5,6,7},{9}};
boolean found = false;
for(int i=0; i<arr.length&& !found; i++) {
    for(int j=0; j<arr[i].length; j++) {
        System.out.println("i=" + i + ",j=" + j);
        if(arr[i][j] ==5) {
            found = true;
            break;
        }
    }
}

5、switch 语句能否作用在byte 上, 能否作用在long 上, 能否作用在String上?
在switch（expr1）中, expr1只能是一个整数表达式或者枚举常量（更大字体）, 整数表达
式可以是int 基本类型或Integer 包装类型, 由于, byte,short,char 都可以隐含转换为int, 
所以, 这些类型以及这些类型的包装类型也是可以的。显然, long 和String 类型都不符合
switch 的语法规定, 并且不能被隐式转换成int 类型, 所以, 它们不能作用于swtich 语句中。

6、short s1 = 1; s1 = s1 + 1;有什么错? short s1 = 1; s1 += 1;有什么错?
对于short s1 = 1; s1 = s1 + 1;由于s1+1运算时会自动提升表达式的类型, 所以结果是int
型, 再赋值给short 类型s1时, 编译器将报告需要强制转换类型的错误。
对于short s1 = 1; s1 += 1;由于 +=是java 语言规定的运算符, java 编译器会对它进行特殊
处理, 因此可以正确编译。

7、char 型变量中能不能存贮一个中文汉字?为什么?
char 型变量是用来存储Unicode 编码的字符的, unicode 编码字符集中包含了汉字, 所以, 
char 型变量中当然可以存储汉字啦。不过, 如果某个特殊的汉字没有被包含在unicode 编
码字符集中, 那么, 这个char 型变量中就不能存储这个特殊汉字。补充说明：unicode 编
码占用两个字节, 所以, char 类型的变量也是占用两个字节。
备注：后面一部分回答虽然不是在正面回答题目, 但是, 为了展现自己的学识和表现自己对
问题理解的透彻深入, 可以回答一些相关的知识, 做到知无不言, 言无不尽。

8、用最有效率的方法算出2 乘以8 等于几?
2 << 3, 
因为将一个数左移n 位, 就相当于乘以了2的n 次方, 那么, 一个数乘以8只要将其左移3位
即可, 而位运算cpu 直接支持的, 效率最高, 所以, 2乘以8等於几的最效率的方法是2 << 3。

9、请设计一个一百亿的计算器
首先要明白这道题目的考查点是什么, 一是大家首先要对计算机原理的底层细节要清楚、要
知道加减法的位运算原理和知道计算机中的算术运算会发生越界的情况, 二是要具备一定的
面向对象的设计思想。
首先, 计算机中用固定数量的几个字节来存储的数值, 所以计算机中能够表示的数值是有一
定的范围的, 为了便于讲解和理解, 我们先以byte 类型的整数为例, 它用1个字节进行存储, 
表示的最大数值范围为-128到+127。-1在内存中对应的二进制数据为11111111, 如果两个-1
相加, 不考虑Java 运算时的类型提升, 运算后会产生进位, 二进制结果为1,11111110, 由
于进位后超过了byte 类型的存储空间, 所以进位部分被舍弃, 即最终的结果为11111110, 
也就是-2, 这正好利用溢位的方式实现了负数的运算。-128在内存中对应的二进制数据为
10000000, 如果两个-128相加, 不考虑Java 运算时的类型提升, 运算后会产生进位, 二
进制结果为1,00000000, 由于进位后超过了byte 类型的存储空间, 所以进位部分被舍弃, 
即最终的结果为00000000, 也就是0, 这样的结果显然不是我们期望的, 这说明计算机中的
算术运算是会发生越界情况的, 两个数值的运算结果不能超过计算机中的该类型的数值范
围。由于Java 中涉及表达式运算时的类型自动提升, 我们无法用byte 类型来做演示这种问
题和现象的实验, 大家可以用下面一个使用整数做实验的例子程序体验一下：
int a = Integer.MAX_VALUE;
int b = Integer.MAX_VALUE;
int sum = a + b;
System.out.println("a="+a+",b="+b+",sum="+sum);
先不考虑long 类型, 由于int 的正数范围为2的31次方, 表示的最大数值约等于
2*1000*1000*1000, 也就是20亿的大小, 所以, 要实现一个一百亿的计算器, 我们得自己
设计一个类可以用于表示很大的整数, 并且提供了与另外一个整数进行加减乘除的功能, 大
概功能如下：
（1）这个类内部有两个成员变量, 一个表示符号, 另一个用字节数组表示数值的二进制数
（2）有一个构造方法, 把一个包含有多位数值的字符串转换到内部的符号和字节数组中
（3）提供加减乘除的功能
public class BigInteger {
    int sign;
    byte[] val;
    public Biginteger(String val) {
        sign = ;
        val = ;
    }
    public BigInteger add(BigInteger other) {
    }
    public BigInteger subtract(BigInteger other) {
    }
    public BigInteger multiply(BigInteger other) {
    }
    public BigInteger divide(BigInteger other) {
    }
}
备注：要想写出这个类的完整代码, 是非常复杂的, 如果有兴趣的话, 可以参看jdk 中自带
的java.math.BigInteger 类的源码。面试的人也知道谁都不可能在短时间内写出这个类的完
整代码的, 他要的是你是否有这方面的概念和意识, 他最重要的还是考查你的能力, 所以, 
你不要因为自己无法写出完整的最终结果就放弃答这道题, 你要做的就是你比别人写得多, 
证明你比别人强, 你有这方面的思想意识就可以了, 毕竟别人可能连题目的意思都看不懂, 
什么都没写, 你要敢于答这道题, 即使只答了一部分, 那也与那些什么都不懂的人区别出来, 
拉开了距离, 算是矮子中的高个, 机会当然就属于你了。另外, 答案中的框架代码也很重要, 
体现了一些面向对象设计的功底, 特别是其中的方法命名很专业, 用的英文单词很精准, 这
也是能力、经验、专业性、英语水平等多个方面的体现, 会给人留下很好的印象, 在编程能
力和其他方面条件差不多的情况下, 英语好除了可以使你获得更多机会外, 薪水可以高出一
千元。

10、使用final 关键字修饰一个变量时, 是引用不能变, 还是引用的对象不能变？
使用final 关键字修饰一个变量时, 是指引用变量不能变, 引用变量所指向的对象中的内容还是可以改变的。
例如, 对于如下语句：
final StringBuffer a=new StringBuffer("immutable");
执行如下语句将报告编译期错误：
a=new StringBuffer("");
但是, 执行如下语句则可以通过编译：
a.append(" broken!");
有人在定义方法的参数时, 可能想采用如下形式来阻止方法内部修改传进来的参数对象：
public void method(final StringBuffer param){
}
实际上, 这是办不到的, 在该方法内部仍然可以增加如下代码来修改参数对象：
param.append("a");

11、"=="和equals 方法究竟有什么区别？
（单独把一个东西说清楚, 然后再说清楚另一个, 这样, 它们的区别自然就出来了, 混在一
起说, 则很难说清楚）
==操作符专门用来比较两个变量的值是否相等, 也就是用于比较变量所对应的内存中所存
储的数值是否相同, 要比较两个基本类型的数据或两个引用变量是否相等, 只能用==操作符。
如果一个变量指向的数据是对象类型的, 那么, 这时候涉及了两块内存, 对象本身占用一块
内存（堆内存）, 变量也占用一块内存, 例如Objet obj = new Object();变量obj 是一个内存, 
new Object()是另一个内存, 此时, 变量obj 所对应的内存中存储的数值就是对象占用的那
块内存的首地址。对于指向对象类型的变量, 如果要比较两个变量是否指向同一个对象, 即
要看这两个变量所对应的内存中的数值是否相等, 这时候就需要用==操作符进行比较。
equals 方法是用于比较两个独立对象的内容是否相同, 就好比去比较两个人的长相是否相
同, 它比较的两个对象是独立的。例如, 对于下面的代码：
String a=new String("foo");
String b=new String("foo");
两条new 语句创建了两个对象, 然后用a/b 这两个变量分别指向了其中一个对象, 这是两
个不同的对象, 它们的首地址是不同的, 即a 和b 中存储的数值是不相同的, 所以, 表达
式a==b 将返回false, 而这两个对象中的内容是相同的, 所以, 表达式a.equals(b)将返回true。
在实际开发中, 我们经常要比较传递进行来的字符串内容是否等, 例如, String input
= …;input.equals("quit"), 许多人稍不注意就使用==进行比较了, 这是错误的, 随便从网上
找几个项目实战的教学视频看看, 里面就有大量这样的错误。记住, 字符串的比较基本上都
是使用equals 方法。
如果一个类没有自己定义equals 方法, 那么它将继承Object 类的equals 方法, Object 类
的equals 方法的实现代码如下：
boolean equals(Object o){
return this==o;
}
这说明, 如果一个类没有自己定义equals 方法, 它默认的equals 方法（从Object 类继承
的）就是使用==操作符, 也是在比较两个变量指向的对象是否是同一对象, 这时候使用
equals 和使用==会得到同样的结果, 如果比较的是两个独立的对象则总返回false。如果你
编写的类希望能够比较该类创建的两个实例对象的内容是否相同, 那么你必须覆盖equals
方法, 由你自己写代码来决定在什么情况即可认为两个对象的内容是相同的。

12、静态变量和实例变量的区别？
在语法定义上的区别：静态变量前要加static 关键字, 而实例变量前则不加。
在程序运行时的区别：实例变量属于某个对象的属性, 必须创建了实例对象, 其中的实例变量才会被分配空间, 才能使用这个实例变量。
静态变量不属于某个实例对象, 而是属于类, 所以也称为类变量, 只要程序加载了类的字节码, 不用创建任何实例对象, 静态变量就会被分配空间, 静态变量就可以被使用了。
总之, 实例变量必须创建对象后才可以通过这个对象来使用, 静态变量则可以直接使用类名来引用。
例如, 对于下面的程序, 无论创建多少个实例对象, 永远都只分配了一个staticVar 变量, 
并且每创建一个实例对象, 这个staticVar 就会加1；但是, 每创建一个实例对象, 就会分配
一个instanceVar, 即可能分配多个instanceVar, 并且每个instanceVar 的值都只自加了1
次。
public class VariantTest {
    public static int staticVar = 0;
    public int instanceVar = 0;
    public VariantTest() {
        staticVar++;
        instanceVar++;
        System.out.println("staticVar=" + staticVar + ",instanceVar="+
                           instanceVar);
    }
}
备注：这个解答除了说清楚两者的区别外, 最后还用一个具体的应用例子来说明两者的差异, 
体现了自己有很好的解说问题和设计案例的能力, 思维敏捷, 超过一般程序员, 有写作能力！

13、是否可以从一个static 方法内部发出对非static 方法的调用？
不可以。因为非static 方法是要与对象关联在一起的, 必须创建一个对象后, 才可以在该对
象上进行方法调用, 而static 方法调用时不需要创建对象, 可以直接调用。也就是说, 当一
个static 方法被调用时, 可能还没有创建任何实例对象, 如果从一个static 方法中发出对非
static 方法的调用, 那个非static 方法是关联到哪个对象上的呢？这个逻辑无法成立, 所以, 
一个static 方法内部发出对非static 方法的调用。

14、Integer 与int 的区别
int 是java 提供的8种原始数据类型之一。Java 为每个原始类型提供了封装类, Integer 是java
为int 提供的封装类。int 的默认值为0, 而Integer 的默认值为null, 即Integer 可以区分出
未赋值和值为0的区别, int 则无法表达出未赋值的情况, 例如, 要想表达出没有参加考试和
考试成绩为0的区别, 则只能使用Integer。在JSP 开发中, Integer 的默认为null, 所以用
el 表达式在文本框中显示时, 值为空白字符串, 而int 默认的默认值为0, 所以用el 表达式
在文本框中显示时, 结果为0, 所以, int 不适合作为web 层的表单数据的类型。
在Hibernate 中, 如果将OID 定义为Integer 类型, 那么Hibernate 就可以根据其值是否为
null 而判断一个对象是否是临时的, 如果将OID 定义为了int 类型, 还需要在hbm 映射文
件中设置其unsaved-value 属性为0。
另外, Integer 提供了多个与整数相关的操作方法, 例如, 将一个字符串转换成整数, Integer
中还定义了表示整数的最大值和最小值的常量。

15、Math.round(11.5)等於多少? Math.round(-11.5)等於多少?
Math 类中提供了三个与取整有关的方法：ceil、floor、round, 这些方法的作用与它们的英
文名称的含义相对应, 例如, 
ceil 的英文意义是天花板, 该方法就表示向上取整, 
Math.ceil(11.3)的结果为12,
Math.ceil(-11.3)的结果是-11；
floor 的英文意义是地板, 该方法就表示向下取整, 
Math.ceil(11.6)的结果为11,
Math.ceil(-11.6)的结果是-12；最难掌握的是
round 方法, 它表示"四舍五入", 算法为Math.floor(x+0.5), 即将原来的数字加上0.5后再向下取整, 所以, 
Math.round(11.5)的结果为12, 
Math.round(-11.5)的结果为-11。

16、下面的代码有什么不妥之处?
1. if(username.equals("zxx"){}
username 可能为NULL,会报空指针错误；改为"zxx".equals(username)
2. int x = 1;
return x==1?true:false; 这个改成return x==1;就可以!

17、请说出作用域public, private, protected, 以及不写时的区别
这四个作用域的可见范围如下表所示。
说明：如果在修饰的元素上面没有写任何访问修饰符, 则表示friendly。
 
备注：只要记住了有4种访问权限, 4个访问范围, 然后将全选和范围在水平和垂直方向上
分别按排从小到大或从大到小的顺序排列, 就很容易画出上面的图了。

18、Overload 和Override 的区别。Overloaded 的方法是否可以改变返回值的类型?
Overload 是重载的意思, Override 是覆盖的意思, 也就是重写。
重载Overload 表示同一个类中可以有多个名称相同的方法, 但这些方法的参数列表各不相
同（即参数个数或类型不同）。
重写Override 表示子类中的方法可以与父类中的某个方法的名称和参数完全相同, 通过子
类创建的实例对象调用这个方法时, 将调用子类中的定义方法, 这相当于把父类中定义的那
个完全相同的方法给覆盖了, 这也是面向对象编程的多态性的一种表现。子类覆盖父类的方
法时, 只能比父类抛出更少的异常, 或者是抛出父类抛出的异常的子异常, 因为子类可以解
决父类的一些问题, 不能比父类有更多的问题。子类方法的访问权限只能比父类的更大, 不
能更小。如果父类的方法是private 类型, 那么, 子类则不存在覆盖的限制, 相当于子类中
增加了一个全新的方法。
至于Overloaded 的方法是否可以改变返回值的类型这个问题, 要看你倒底想问什么呢？这
个题目很模糊。如果几个Overloaded 的方法的参数列表不一样, 它们的返回者类型当然也
可以不一样。但我估计你想问的问题是：如果两个方法的参数列表完全一样, 是否可以让它
们的返回值不同来实现重载Overload。这是不行的, 我们可以用反证法来说明这个问题, 
因为我们有时候调用一个方法时也可以不定义返回结果变量, 即不要关心其返回结果, 例如, 
我们调用map.remove(key)方法时, 虽然remove 方法有返回值, 但是我们通常都不会定义
接收返回结果的变量, 这时候假设该类中有两个名称和参数列表完全相同的方法, 仅仅是返
回类型不同, java 就无法确定编程者倒底是想调用哪个方法了, 因为它无法通过返回结果
类型来判断。
override 可以翻译为覆盖, 从字面就可以知道, 它是覆盖了一个方法并且对其重写, 以求达
到不同的作用。对我们来说最熟悉的覆盖就是对接口方法的实现, 在接口中一般只是对方法
进行了声明, 而我们在实现时, 就需要实现接口声明的所有方法。除了这个典型的用法以外, 
我们在继承中也可能会在子类覆盖父类中的方法。在覆盖要注意以下的几点：

1、覆盖的方法的标志必须要和被覆盖的方法的标志完全匹配, 才能达到覆盖的效果；

2、覆盖的方法的返回值必须和被覆盖的方法的返回一致；

3、覆盖的方法所抛出的异常必须和被覆盖方法的所抛出的异常一致, 或者是其子类；

4、被覆盖的方法不能为private, 否则在其子类中只是新定义了一个方法, 并没有对其进行覆盖。
overload 对我们来说可能比较熟悉, 可以翻译为重载, 它是指我们可以定义一些名称相同
的方法, 通过定义不同的输入参数来区分这些方法, 然后再调用时, VM 就会根据不同的参
数样式, 来选择合适的方法执行。在使用重载要注意以下的几点：

1、在使用重载时只能通过不同的参数样式。例如, 不同的参数类型, 不同的参数个数, 不
同的参数顺序（当然, 同一方法内的几个参数类型必须不一样, 例如可以是fun(int,float), 
但是不能为fun(int,int)）；

2、不能通过访问权限、返回类型、抛出的异常进行重载；

3、方法的异常类型和数目不会对重载造成影响；

4、对于继承来说, 如果某一方法在父类中是访问权限是priavte, 那么就不能在子类对其进
行重载, 如果定义的话, 也只是定义了一个新方法, 而不会达到重载的效果。

19、构造器Constructor 是否可被override?
构造器Constructor 不能被继承, 因此不能重写Override, 但可以被重载Overload。

20、接口是否可继承接口?抽象类是否可实现(implements)接口?抽象类是否可继承具体类(concrete class)?抽象类中是否可以有静态的main 方法？
接口可以继承接口。抽象类可以实现(implements)接口, 抽象类可以继承具体类。抽象类中可以有静态的main 方法。
备注：只要明白了接口和抽象类的本质和作用, 这些问题都很好回答, 你想想, 如果你是java
语言的设计者, 你是否会提供这样的支持, 如果不提供的话, 有什么理由吗？如果你没有道
理不提供, 那答案就是肯定的了。
只有记住抽象类与普通类的唯一区别：就是不能创建实例对象和允许有abstract 方法。

21、写clone()方法时, 通常都有一行代码, 是什么？
clone 有缺省行为, 
super.clone();
因为首先要把父类中的成员复制到位, 然后才是复制自己的成员。

22、面向对象的特征有哪些方面
计算机软件系统是现实生活中的业务在计算机中的映射, 而现实生活中的业务其实就是一个
个对象协作的过程。面向对象编程就是按现实业务一样的方式将程序代码按一个个对象进行
组织和编写, 让计算机系统能够识别和理解用对象方式组织和编写的程序代码, 这样就可以
把现实生活中的业务对象映射到计算机系统中。
面向对象的编程语言有, 吗等4个主要的特征。
//封装：
封装是保证软件部件具有优良的模块性的基础, 封装的目标就是要实现软件部件的"高内聚、
低耦合", 防止程序相互依赖性而带来的变动影响。在面向对象的编程语言中, 对象是封装
的最基本单位, 面向对象的封装比传统语言的封装更为清晰、更为有力。面向对象的封装就
是把描述一个对象的属性和行为的代码封装在一个"模块"中, 也就是一个类中, 属性用变量
定义, 行为用方法进行定义, 方法可以直接访问同一个对象中的属性。通常情况下, 只要记
住让变量和访问这个变量的方法放在一起, 将一个类中的成员变量全部定义成私有的, 只
有这个类自己的方法才可以访问到这些成员变量, 这就基本上实现对象的封装, 就很容易
找出要分配到这个类上的方法了, 就基本上算是会面向对象的编程了。把握一个原则：把
对同一事物进行操作的方法和相关的方法放在同一个类中, 把方法和它操作的数据放在同
一个类中。
例如, 人要在黑板上画圆, 这一共涉及三个对象：人、黑板、圆, 画圆的方法要分配给哪个
对象呢？由于画圆需要使用到圆心和半径, 圆心和半径显然是圆的属性, 如果将它们在类中
定义成了私有的成员变量, 那么, 画圆的方法必须分配给圆, 它才能访问到圆心和半径这两
个属性, 人以后只是调用圆的画圆方法、表示给圆发给消息而已, 画圆这个方法不应该分配
在人这个对象上, 这就是面向对象的封装性, 即将对象封装成一个高度自治和相对封闭的
个体, 对象状态（属性）由这个对象自己的行为（方法）来读取和改变。一个更便于理解
的例子就是, 司机将火车刹住了, 刹车的动作是分配给司机, 还是分配给火车, 显然, 应该
分配给火车, 因为司机自身是不可能有那么大的力气将一个火车给停下来的, 只有火车自己
才能完成这一动作, 火车需要调用内部的离合器和刹车片等多个器件协作才能完成刹车这个
动作, 司机刹车的过程只是给火车发了一个消息, 通知火车要执行刹车动作而已。
//抽象：
抽象就是找出一些事物的相似和共性之处, 然后将这些事物归为一个类, 这个类只考虑这些
事物的相似和共性之处, 并且会忽略与当前主题和目标无关的那些方面, 将注意力集中在与
当前目标有关的方面。例如, 看到一只蚂蚁和大象, 你能够想象出它们的相同之处, 那就是
抽象。抽象包括行为抽象和状态抽象两个方面。例如, 定义一个Person 类, 如下：
classPerson {
	String name;
	int age;
}
人本来是很复杂的事物, 有很多方面, 但因为当前系统只需要了解人的姓名和年龄, 所以上
面定义的类中只包含姓名和年龄这两个属性, 这就是一种抽像, 使用抽象可以避免考虑一些
与目标无关的细节。我对抽象的理解就是不要用显微镜去看一个事物的所有方面, 这样涉及
的内容就太多了, 而是要善于划分问题的边界, 当前系统需要什么, 就只考虑什么。
//继承：
在定义和实现一个类的时候, 可以在一个已经存在的类的基础之上来进行, 把这个已经存在
的类所定义的内容作为自己的内容, 并可以加入若干新的内容, 或修改原来的方法使之更适
合特殊的需要, 这就是继承。继承是子类自动共享父类数据和方法的机制, 这是类之间的一
种关系, 提高了软件的可重用性和可扩展性。
//多态：
多态是指程序中定义的引用变量所指向的具体类型和通过该引用变量发出的方法调用在编
程时并不确定, 而是在程序运行期间才确定, 即一个引用变量倒底会指向哪个类的实例对象, 
该引用变量发出的方法调用到底是哪个类中实现的方法, 必须在由程序运行期间才能决定。
因为在程序运行时才确定具体的类, 这样, 不用修改源程序代码, 就可以让引用变量绑定到
各种不同的类实现上, 从而导致该引用调用的具体方法随之改变, 即不修改程序代码就可以
改变程序运行时所绑定的具体代码, 让程序可以选择多个运行状态, 这就是多态性。多态性
增强了软件的灵活性和扩展性。例如, 下面代码中的UserDao 是一个接口, 它定义引用变
量userDao 指向的实例对象由daofactory.getDao()在执行的时候返回, 有时候指向的是
UserJdbcDao 这个实现, 有时候指向的是UserHibernateDao 这个实现, 这样, 不用修改
源代码, 就可以改变userDao 指向的具体类实现, 从而导致userDao.insertUser()方法调用
的具体代码也随之改变, 即有时候调用的是UserJdbcDao 的insertUser 方法, 有时候调用
的是UserHibernateDao 的insertUser 方法：
UserDao userDao =daofactory.getDao();
userDao.insertUser(user);
比喻：人吃饭, 你看到的是左手, 还是右手？

23、java 中实现多态的机制是什么？
靠的是父类或接口定义的引用变量可以指向子类或具体实现类的实例对象, 而程序调用的方
法在运行期才动态绑定, 就是引用变量所指向的具体实例对象的方法, 也就是内存里正在运
行的那个对象的方法, 而不是引用变量的类型中定义的方法。

24、abstract class 和interface 有什么区别?
含有abstract 修饰符的class 即为抽象类, abstract 类不能创建的实例对象。含有abstract
方法的类必须定义为abstract class, abstract class 类中的方法不必是抽象的。abstract class
类中定义抽象方法必须在具体(Concrete)子类中实现, 所以, 不能有抽象构造方法或抽象静
态方法。如果的子类没有实现抽象父类中的所有抽象方法, 那么子类也必须定义为abstract
类型。
接口（interface）可以说成是抽象类的一种特例, 接口中的所有方法都必须是抽象的。接口
中的方法定义默认为public abstract 类型, 接口中的成员变量类型默认为public static final。
下面比较一下两者的语法区别：
1.抽象类可以有构造方法, 接口中不能有构造方法。
2.抽象类中可以有普通成员变量, 接口中没有普通成员变量
3.抽象类中可以包含非抽象的普通方法, 接口中的所有方法必须都是抽象的, 不能有非抽象的普通方法。
4.抽象类中的抽象方法的访问类型可以是public, protected 和（默认类型,虽然eclipse 下不报错, 但应该也不行）, 
但接口中的抽象方法只能是public 类型的, 并且默认即为public abstract 类型。
5. 抽象类中可以包含静态方法, 接口中不能包含静态方法
6. 抽象类和接口中都可以包含静态成员变量, 抽象类中的静态成员变量的访问类型可以任意, 
但接口中定义的变量只能是public static final 类型, 并且默认即为public static final 类型。
7. 一个类可以实现多个接口, 但只能继承一个抽象类。
下面接着再说说两者在应用上的区别：
接口更多的是在系统架构设计方法发挥作用, 主要用于定义模块之间的通信契约。而抽象类
在代码实现方面发挥作用, 可以实现代码的重用, 例如, 模板方法设计模式是抽象类的一个
典型应用, 假设某个项目的所有Servlet 类都要用相同的方式进行权限判断、记录访问日志
和处理异常, 那么就可以定义一个抽象的基类, 让所有的Servlet 都继承这个抽象基类, 在
抽象基类的service 方法中完成权限判断、记录访问日志和处理异常的代码, 在各个子类中
只是完成各自的业务逻辑代码, 伪代码如下：
public abstract classBase Servlet extends HttpServlet {
	public final void service(HttpServletRequest request, HttpServletResponse
		response)throws IOExcetion, ServletException {
		// 记录访问日志
		// 进行权限判断
		if (具有权限) {
			try {
				doService(request, response);
			} catch (Excetpion e) {
				记录异常信息
			}
		}
	}
	protected abstract void doService(HttpServletRequest
		request, HttpServletResponse response)throws IOExcetion,
	ServletException;
	//注意访问权限定义成protected, 显得既专业, 又严谨, 因为它是专门给子类用的
}
public class MyServlet1 extendsBaseServlet {
	protected voiddoService(HttpServletRequest request, HttpServletResponse response)
	throwsIOExcetion,
	ServletException {
		本Servlet 只处理的具体业务逻辑代码
	}
}
父类方法中间的某段代码不确定, 留给子类干, 就用模板方法设计模式。
备注：这道题的思路是先从总体解释抽象类和接口的基本概念, 然后再比较两者的语法细节, 
最后再说两者的应用区别。比较两者语法细节区别的条理是：先从一个类中的构造方法、普
通成员变量和方法（包括抽象方法）, 静态变量和方法, 继承性等6个方面逐一去比较回答, 
接着从第三者继承的角度的回答, 特别是最后用了一个典型的例子来展现自己深厚的技术功
底。

25、abstract 的method 是否可同时是static,是否可同时是native, 是否可同时是synchronized?
1 abstract 的method 不可以是static 的, 因为抽象的方法是要被子类实现的, 而static 与子类扯不上关系！
2 native 方法表示该方法要用另外一种依赖平台的编程语言实现的, 不存在着被子类实现的问题, 所以, 它也不能是抽象的, 不能与abstract 混用。
例如, FileOutputSteam 类要硬件打交道, 底层的实现用的是操作系统相关的api 实现, 例如, 在windows 用c 语言实现的, 
所以, 查看jdk 的源代码, 可以发现FileOutputStream 的open 方法的定义如下：
private native void open(Stringname) throws FileNotFoundException;
如果我们要用java 调用别人写的c 语言函数, 我们是无法直接调用的, 我们需要按照java
的要求写一个c 语言的函数, 又我们的这个c 语言函数去调用别人的c 语言函数。由于我们
的c 语言函数是按java 的要求来写的, 我们这个c 语言函数就可以与java 对接上, java 那
边的对接方式就是定义出与我们这个c 函数相对应的方法, java 中对应的方法不需要写具体的代码, 但需要在前面声明native。
3 关于synchronized 与abstract 合用的问题, 我觉得也不行, 因为在我几年的学习和开发中, 
从来没见到过这种情况, 并且我觉得synchronized 应该是作用在一个具体的方法上才有意
义。而且, 方法上的synchronized 同步所使用的同步锁对象是this, 而抽象方法上无法确
定this 是什么。

26、什么是内部类？Static Nested Class 和Inner Class 的不同。
内部类就是在一个类的内部定义的类, 内部类中不能定义静态成员（静态成员不是对象的特
性, 只是为了找一个容身之处, 所以需要放到一个类中而已, 这么一点小事, 你还要把它放
到类内部的一个类中, 过分了啊！提供内部类, 不是为让你干这种事情, 无聊, 不让你干。
我想可能是既然静态成员类似c 语言的全局变量, 而内部类通常是用于创建内部对象用的, 
所以, 把"全局变量"放在内部类中就是毫无意义的事情, 既然是毫无意义的事情, 就应该被
禁止）, 内部类可以直接访问外部类中的成员变量, 内部类可以定义在外部类的方法外面, 
也可以定义在外部类的方法体中, 如下所示：
public class Outer {
    int out_x = 0;
    public void method() {
        Inner1 inner1 = new Inner1();
        public class Inner2 { //在方法体内部定义的内部类
            public method() {
                out_x = 3;
            }
        }
        Inner2 inner2 = new Inner2();
    }
    public class Inner1 { //在方法体外面定义的内部类
    }
} 
在方法体外面定义的内部类的访问类型可以是public,protecte,默认的, private 等4种类型, 
这就好像类中定义的成员变量有4种访问类型一样, 它们决定这个内部类的定义对其他类是
否可见；对于这种情况, 我们也可以在外面创建内部类的实例对象, 创建内部类的实例对象时, 
一定要先创建外部类的实例对象, 然后用这个外部类的实例对象去创建内部类的实例对象, 代码如下：
// Outer outer = new Outer();
// Outer.Inner1 inner1 = outer.new Innner1();
在方法内部定义的内部类前面不能有访问类型修饰符, 就好像方法中定义的局部变量一样, 
但这种内部类的前面可以使用final 或abstract 修饰符。这种内部类对其他类是不可见的其
他类无法引用这种内部类, 但是这种内部类创建的实例对象可以传递给其他类访问。这种内
部类必须是先定义, 后使用, 即内部类的定义代码必须出现在使用该类之前, 这与方法中的
局部变量必须先定义后使用的道理也是一样的。这种内部类可以访问方法体中的局部变量, 但是, 该局部变量前必须加final 修饰符。
对于这些细节, 只要在eclipse 写代码试试, 根据开发工具提示的各类错误信息就可以马上了解到。
// 匿名内部类
在方法体内部还可以采用如下语法来创建一种匿名内部类, 即定义某一接口或类的子类的同
时, 还创建了该子类的实例对象, 无需为该子类定义名称：
public class Outer {
    public void start() {
        new Thread(
        new Runable() {
            public void run() {};
        }
        ).start();
    }
}
// 静态内部类
最后, 在方法外部定义的内部类前面可以加上static 关键字, 从而成为Static Nested Class, 
它不再具有内部类的特性, 所有, 从狭义上讲, 它不是内部类。Static Nested Class 与普通
类在运行时的行为和功能上没有什么区别, 只是在编程引用时的语法上有一些差别, 它可以
定义成public、protected、默认的、private 等多种类型, 而普通类只能定义成public 和默
认的这两种类型。在外面引用Static Nested Class 类的名称为"外部类名.内部类名"。在外
面不需要创建外部类的实例对象, 就可以直接创建Static Nested Class, 例如, 假设Inner
是定义在Outer 类中的Static Nested Class, 那么可以使用如下语句创建Inner 类：
// Outer.Inner inner = newOuter.Inner();
由于static Nested Class 不依赖于外部类的实例对象, 所以, static Nested Class 能访问
外部类的非static 成员变量(不能直接访问, 需要创建外部类实例才能访问非静态变量)。当
在外部类中访问Static Nested Class 时, 可以直接使用Static Nested Class 的名字, 而不
需要加上外部类的名字了, 在Static Nested Class 中也可以直接引用外部类的static 的成员
变量, 不需要加上外部类的名字。
在静态方法中定义的内部类也是Static Nested Class, 这时候不能在类前面加static 关键字, 
静态方法中的Static Nested Class 与普通方法中的内部类的应用方式很相似, 它除了可以
直接访问外部类中的static 的成员变量, 还可以访问静态方法中的局部变量, 但是, 该局部变量前必须加final 修饰符。
备注：首先根据你的印象说出你对内部类的总体方面的特点：例如, 在两个地方可以定义, 
可以访问外部类的成员变量, 不能定义静态成员, 这是大的特点。然后再说一些细节方面的
知识, 例如, 几种定义方式的语法区别, 静态内部类, 以及匿名内部类。

27、内部类可以引用它的包含类的成员吗？有没有什么限制？
完全可以。如果不是静态内部类, 那没有什么限制！
如果你把静态嵌套类当作内部类的一种特例, 那在这种情况下不可以访问外部类的普通成员
变量, 而只能访问外部类中的静态成员, 例如, 下面的代码：
class Outer {
    static int x;
    static class Inner {
        void test() {
            syso(x);
        }
    }
}
答题时, 也要能察言观色, 揣摩提问者的心思, 显然人家希望你说的是静态内部类不能访问
外部类的成员, 但你一上来就顶牛, 这不好, 要先顺着人家, 让人家满意, 然后再说特殊情况, 让人家吃惊。

28、Anonymous Inner Class (匿名内部类)是否可以extends(继承)其它类, 
是否可以implements(实现)interface(接口)?
可以继承其他类或实现其他接口。不仅是可以, 而是必须!

29、super.getClass()方法调用
下面程序的输出结果是多少？
importjava.util.Date;
public class Test extends Date {
    public static void main(String[] args) {
        new Test().test();
    }
    public void test() {
        System.out.println(super.getClass().getName());  
    }
} 
很奇怪, 结果是Test
这属于脑筋急转弯的题目, 在一个qq 群有个网友正好问过这个问题, 我觉得挺有趣, 就研究了一下, 没想到今天还被你面到了, 哈哈。
在test 方法中, 直接调用getClass().getName()方法, 返回的是Test 类名
由于getClass()在Object 类中定义成了final, 子类不能覆盖该方法, 所以, 在
test 方法中调用getClass().getName()方法, 其实就是在调用从父类继承的getClass()方法, 
等效于调用super.getClass().getName()方法, 所以, super.getClass().getName()方法返回的也应该是Test。
如果想得到父类的名称, 应该用如下代码：
getClass().getSuperClass().getName();

30、String 是最基本的数据类型吗?
基本数据类型包括byte、int、char、long、float、double、boolean 和short。
java.lang.String 类是final 类型的, 因此不可以继承这个类、不能修改这个类。为了提高效
率节省空间, 我们应该用StringBuffer 类

31、String s = "Hello";s = s + " world!";这两行代码执行后, 原始的String对象中的内容到底变了没有？
没有。因为String 被设计成不可变(immutable)类, 所以它的所有对象都是不可变对象。在
这段代码中, s 原先指向一个String 对象, 内容是 "Hello", 然后我们对s 进行了+操作, 那
么s 所指向的那个对象是否发生了改变呢？答案是没有。这时, s 不指向原来那个对象了, 
而指向了另一个 String 对象, 内容为"Hello world!", 原来那个对象还存在于内存之中, 只
是s 这个引用变量不再指向它了。
通过上面的说明, 我们很容易导出另一个结论, 如果经常对字符串进行各种各样的修改, 或
者说, 不可预见的修改, 那么使用String 来代表字符串的话会引起很大的内存开销。因为
String 对象建立之后不能再改变, 所以对于每一个不同的字符串, 都需要一个String 对象来
表示。这时, 应该考虑使用StringBuffer 类, 它允许修改, 而不是每个不同的字符串都要生
成一个新的对象。并且, 这两种类的对象转换十分容易。
同时, 我们还可以知道, 如果要使用内容相同的字符串, 不必每次都new 一个String。例
如我们要在构造器中对一个名叫s 的String 引用变量进行初始化, 把它设置为初始值, 应
当这样做：
public class Demo {
	private String s;
	...
	public Demo {
		s = "Initial Value";
	}
	...
}
而非
s = new String("Initial Value");
后者每次都会调用构造器, 生成新对象, 性能低下且内存开销大, 并且没有意义, 因为String
对象不可改变, 所以对于内容相同的字符串, 只要一个String 对象来表示就可以了。也就
说, 多次调用上面的构造器创建多个对象, 他们的String 类型属性s 都指向同一个对象。
上面的结论还基于这样一个事实：对于字符串常量, 如果内容相同, Java 认为它们代表同
一个String 对象。而用关键字new 调用构造器, 总是会创建一个新的对象, 无论内容是否
相同。
至于为什么要把String 类设计成不可变类, 是它的用途决定的。其实不只String, 很多Java
标准类库中的类都是不可变的。在开发一个系统的时候, 我们有时候也需要设计不可变类, 
来传递一组相关的值, 这也是面向对象思想的体现。不可变类有一些优点, 比如因为它的对
象是只读的, 所以多线程并发访问也不会有任何问题。当然也有一些缺点, 比如每个不同的
状态都要一个对象来代表, 可能会造成性能上的问题。所以Java 标准类库还提供了一个可
变版本, 即 StringBuffer。

32、是否可以继承String 类?
String 类是final 类故不可以继承。

33、String s = new String("xyz");创建了几个String Object?二者之间有什么区别？
两个或一个, "xyz"对应一个对象, 这个对象放在字符串常量缓冲区, 常量"xyz"不管出现多
少遍, 都是缓冲区中的那一个。New String 每写一遍, 就创建一个新的对象, 它一句那个
常量"xyz"对象的内容来创建出一个新String 对象。如果以前就用过’xyz’, 这句代表就不会
创建"xyz"自己了, 直接从缓冲区拿。

34、String 和StringBuffer 的区别
JAVA 平台提供了两个类：String 和StringBuffer, 它们可以储存和操作字符串, 即包含多个
字符的字符数据。这个String 类提供了数值不可改变的字符串。而这个StringBuffer 类提供
的字符串进行修改。当你知道字符数据要改变的时候你就可以使用StringBuffer。典型地, 
你可以使用StringBuffers 来动态构造字符数据。另外, String 实现了equals 方法, new
String("abc").equals(newString("abc")的结果为true,而StringBuffer 没有实现equals 方法, 
所以, new StringBuffer("abc").equals(newStringBuffer("abc")的结果为false。
接着要举一个具体的例子来说明, 我们要把1到100的所有数字拼起来, 组成一个串。
StringBuffer sbf = new StringBuffer();
for (int i = 0; i < 100; i++) {
	sbf.append(i);
}
上面的代码效率很高, 因为只创建了一个StringBuffer 对象, 而下面的代码效率很低, 因为
创建了101个对象。
String str = new String();
for (int i = 0; i < 100; i++) {
	str = str + i;
}
在讲两者区别时, 应把循环的次数搞成10000, 然后用endTime-beginTime 来比较两者执
行的时间差异, 最后还要讲讲StringBuilder 与StringBuffer 的区别。
String 覆盖了equals 方法和hashCode 方法, 而StringBuffer 没有覆盖equals 方法和
hashCode 方法, 所以, 将StringBuffer 对象存储进Java 集合类中时会出现问题。

35、如何把一段逗号分割的字符串转换成一个数组?
如果不查jdk api, 我很难写出来！我可以说说我的思路：
1 用正则表达式, 代码大概为：String [] result = orgStr.split(",");
2 用 StingTokenizer ,代码为：StringTokenizer tokener =
StringTokenizer(orgStr,",");
String [] result =new String[tokener .countTokens()];
Int i=0;
while (tokener.hasNext()) {
	result[i++] = toker.nextToken();
}

36、数组有没有length()这个方法? String 有没有length()这个方法？
数组没有length()这个方法, 有length 的属性。String 有有length()这个方法。

37、下面这条语句一共创建了多少个对象：String s="a"+"b"+"c"+"d";
答：对于如下代码：
String s1 = "a";
String s2 = s1 + "b";
String s3 = "a" + "b";
System.out.println(s2 == "ab");
System.out.println(s3 == "ab");
第一条语句打印的结果为false, 第二条语句打印的结果为true, 这说明javac 编译可以对
字符串常量直接相加的表达式进行优化, 不必要等到运行期去进行加法运算处理, 而是在编
译时去掉其中的加号, 直接将其编译成一个这些常量相连的结果。
题目中的第一行代码被编译器在编译时优化后, 相当于直接定义了一个"abcd"的字符串, 
所以, 上面的代码应该只创建了一个String 对象。写如下两行代码, 
String s ="a" + "b" + "c" + "d";
System.out.println(s== "abcd");
最终打印的结果应该为true。

38、try {}里有一个return 语句, 那么紧跟在这个try 后的finally {}里的code会不会被执行, 什么时候被执行, 在return 前还是后?
也许你的答案是在return 之前, 但往更细地说, 我的答案是在return 中间执行, 请看下面
程序代码的运行结果： 
public classTest {
    /**
    * @paramargs add by zxx ,Dec 9, 2008
    */
    public static void main(String[] args) {
        System.out. println (new Test().test());;
    }
    static int test() {
        int x = 1;
        try {
            Return x;
        }
        finally {
            ++x;
        }
    }
}
---------执行结果 ---------
1
运行结果是1, 为什么呢？主函数调用子函数并得到结果的过程, 好比主函数准备一个空罐
子, 当子函数要返回结果时, 先把结果放在罐子里, 然后再将程序逻辑返回到主函数。所谓
返回, 就是子函数说, 我不运行了, 你主函数继续运行吧, 这没什么结果可言, 结果是在说
这话之前放进罐子里的。

39、下面的程序代码输出的结果是多少？
public class smallT {
    public static void main(String args[]) {
        smallT t = new smallT();
        int b = t.get();
        System.out.println(b);
    }
    public int get() {
        try {
            Return 1 ; 
        }
        finally {
            Return 2 ;
        }
    }
}
返回的结果是2。
我可以通过下面一个例子程序来帮助我解释这个答案, 从下面例子的运行结果中可以发现, 
try 中的return 语句调用的函数先于finally 中调用的函数执行, 也就是说return 语句先执行, 
finally 语句后执行, 所以, 返回的结果是2。Return 并不是让函数马上返回, 而是return 语
句执行后, 将把返回结果放置进函数栈中, 此时函数并不是马上返回, 它要执行finally 语句后才真正开始返回。
在讲解答案时可以用下面的程序来帮助分析：
public classTest {
    /**
    * @paramargs add by zxx ,Dec 9, 2008
    */
    public static void main(String[] args) {
        System.out.println(newTest().test());;
    }
    int test() {
        try {
            return func1();
        }
        finally {
            return func2();
        }
    }
    int func1() {
        System.out.println("func1");
        return 1;
    }
    int func2() {
        System.out.println("func2");
        return 2;
    } 
}
-----------执行结果-----------------
func1
func2
2
结论：finally 中的代码比return 和break 语句后执行

40、final, finally, finalize 的区别。
final 用于声明属性, 方法和类, 分别表示属性不可变, 方法不可覆盖, 类不可继承。
内部类要访问局部变量, 局部变量必须定义成final 类型, 例如, 一段代码……
finally 是异常处理语句结构的一部分, 表示总是执行。
finalize 是Object 类的一个方法, 在垃圾收集器执行的时候会调用被回收对象的此方法, 可
以覆盖此方法提供垃圾收集时的其他资源回收, 例如关闭文件等。JVM 不保证此方法总被调用

41、运行时异常与一般异常有何异同？
异常表示程序运行过程中可能出现的非正常状态, 运行时异常表示虚拟机的通常操作中可能
遇到的异常, 是一种常见运行错误。java 编译器要求方法必须声明抛出可能发生的非运行
时异常, 但是并不要求必须声明抛出未被捕获的运行时异常。

42、error 和exception 有什么区别?
error 表示恢复不是不可能但很困难的情况下的一种严重问题。比如说内存溢出。不可能指
望程序能处理这样的情况。 exception 表示一种设计或实现问题。也就是说, 它表示如果程
序运行正常, 从不会发生的情况。

43、Java 中的异常处理机制的简单原理和应用。
异常是指java 程序运行时（非编译）所发生的非正常情况或错误, 与现实生活中的事件很
相似, 现实生活中的事件可以包含事件发生的时间、地点、人物、情节等信息, 可以用一个
对象来表示, Java 使用面向对象的方式来处理异常, 它把程序中发生的每个异常也都分别
封装到一个对象来表示的, 该对象中包含有异常的信息。
Java 对异常进行了分类, 不同类型的异常分别用不同的Java 类表示, 所有异常的根类为
java.lang.Throwable, Throwable 下面又派生了两个子类：Error 和Exception, 
Error 表示应用程序本身无法克服和恢复的一种严重问题, 程序只有死的份了, 例如, 说内存溢出和线程死锁等系统问题。
Exception 表示程序还能够克服和恢复的问题, 其中又分为系统异常和普通异常, 
系统异常是软件本身缺陷所导致的问题, 也就是软件开发人员考虑不周所导致的问题, 软件使用者无法克服和恢复这种问题, 
但在这种问题下还可以让软件系统继续运行或者让软件死掉, 
例如, 数组脚本越界（ArrayIndexOutOfBoundsException）, 空指针异常（NullPointerException）、
类转换异常（ClassCastException）；
普通异常是运行环境的变化或异常所导致的问题, 是用户能够克服的问题, 
例如, 网络断线, 硬盘空间不够, 发生这样的异常后, 程序不应该死掉。
java 为系统异常和普通异常提供了不同的解决方案, 
编译器强制普通异常必须try..catch 处理或用throws 声明继续抛给上层调用方法处理, 所以普通异常也称为checked 异常, 
而系统异常可以处理也可以不处理, 所以, 编译器不强制用try..catch 处理或用throws 声明, 所以系统异常也称为unchecked 异常。
提示答题者：就按照三个级别去思考：虚拟机必须宕机的错误, 程序可以死掉也可以不死掉
的错误, 程序不应该死掉的错误；

44、请写出你最常见到的5 个runtime exception。
这道题主要考你的代码量到底多大, 如果你长期写代码的, 应该经常都看到过一些系统方面
的异常, 你不一定真要回答出5个具体的系统异常, 但你要能够说出什么是系统异常, 以及
几个系统异常就可以了, 当然, 这些异常完全用其英文名称来写是最好的, 如果实在写不出, 
那就用中文吧, 有总比没有强！
所谓系统异常, 就是….., 它们都是RuntimeException 的子类, 在jdk doc 中查
RuntimeException 类, 就可以看到其所有的子类列表, 也就是看到了所有的系统异常。我
比较有印象的系统异常有：NullPointerException、ArrayIndexOutOfBoundsException、
ClassCastException。

45、JAVA 语言如何进行异常处理, 关键字：throws,throw,try,catch,finally 分别代表什么意义？在try 块中可以抛出异常吗？
throws 捕获并向外抛出异常
throw 抛出异常
try catch 是内部捕获异常并做自定义处理
finally 是无论是否有异常都会被处理的语句, 除非在finally 前存在被执行的System.exit(int i)时除外

46、java 中有几种方法可以实现一个线程？用什么关键字修饰同步方法? stop()和suspend()方法为何不推荐使用？
java5以前, 有如下两种：
第一种：
new Thread(){}.start();这表示调用Thread 子类对象的run 方法, new Thread(){}表示一个
Thread 的匿名子类的实例对象, 子类加上run 方法后的代码如下：
new Thread() {
	public void run() {}
}
.start();
第二种：
new Thread(new Runnable() {}).start();
这表示调用Thread 对象接受的Runnable 对象的run方法, new Runnable() {}
表示一个Runnable 的匿名子类的实例对象, runnable 的子类加上run 方法后的代码如下：
new Thread(new Runnable() {
	public void run() {}
}).start();
从java5开始, 还有如下一些线程池创建多线程的方式：
ExecutorService pool = Executors.newFixedThreadPool(3)
for (int i = 0; i < 10; i++) {
	pool.execute(new Runable() {
		public void run() {}
	});
}
Executors.new CachedThreadPool().execute(new Runable() {
	public void run() {}
});
Executors.new SingleThreadExecutor().execute(new Runable() {
	public void run() {}
});
有两种实现方法, 分别使用new Thread()和new Thread(runnable)形式, 第一种直接调用
thread 的run 方法, 所以, 我们往往使用Thread 子类, 即new SubThread()。第二种调用
runnable 的run 方法。
有两种实现方法, 分别是继承Thread 类与实现Runnable 接口
用synchronized 关键字修饰同步方法
反对使用stop(), 是因为它不安全。它会解除由线程获取的所有锁定, 而且如果对象处于一
种不连贯状态, 那么其他线程能在那种状态下检查和修改它们。结果很难检查出真正的问题
所在。suspend()方法容易发生死锁。调用suspend()的时候, 目标线程会停下来, 但却仍
然持有在这之前获得的锁定。此时, 其他任何线程都不能访问锁定的资源, 除非被"挂起"的
线程恢复运行。对任何线程来说, 如果它们想恢复目标线程, 同时又试图使用任何一个锁定
的资源, 就会造成死锁。所以不应该使用suspend(), 而应在自己的Thread 类中置入一个
标志, 指出线程应该活动还是挂起。若标志指出线程应该挂起, 便用wait()命其进入等待状
态。若标志指出线程应当恢复, 则用一个notify()重新启动线程。

47、sleep()和 wait()有什么区别?
（网上的答案：sleep 是线程类（Thread）的方法, 导致此线程暂停执行指定时间, 
给执行机会给其他线程, 但是监控状态依然保持, 到时后会自动恢复。调用sleep 不会释放
对象锁。 wait 是Object 类的方法, 对此对象调用wait 方法导致本线程放弃对象锁, 进入
等待此对象的等待锁定池, 只有针对此对象发出notify 方法（或notifyAll）后本线程才进入
对象锁定池准备获得对象锁进入运行状态。）
sleep 就是正在执行的线程主动让出cpu, cpu 去执行其他线程, 在sleep 指定的时间过后, 
cpu 才会回到这个线程上继续往下执行, 如果当前线程进入了同步锁, sleep 方法并不会释
放锁, 即使当前线程使用sleep 方法让出了cpu, 但其他被同步锁挡住了的线程也无法得到
执行。wait 是指在一个已经进入了同步锁的线程内, 让自己暂时让出同步锁, 以便其他正在
等待此锁的线程可以得到同步锁并运行, 只有其他线程调用了notify 方法（notify 并不释放
锁, 只是告诉调用过wait 方法的线程可以去参与获得锁的竞争了, 但不是马上得到锁, 因
为锁还在别人手里, 别人还没释放。如果notify 方法后面的代码还有很多, 需要这些代码执
行完后才会释放锁, 可以在notfiy 方法后增加一个等待和一些代码, 看看效果）, 调用wait
方法的线程就会解除wait 状态和程序可以再次得到锁后继续向下运行。对于wait 的讲解一
定要配合例子代码来说明, 才显得自己真明白。
package com.huawei.interview;
public class MultiThread {
    public static void main(String[] args) {
        new Thread(new Thread1()).start();
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(new Thread2()).start();
    }
    private static class Thread1 implements Runnable
    {
        @Override
        public void run() {
//由于这里的Thread1和下面的Thread2内部run 方法要用同一对象作为监视器, 我们这里不能用this, 因为在Thread2里面的this 和这个Thread1的this 不是同一个对象。因为是静态类,所以我们用MultiThread.class 这个字节码对象, 当前虚拟机里引用这个变量时, 指向的都是同一个对象。
            synchronized (MultiThread.class) {
                System.out.println("enterthread1...");
                System.out.println("thread1is waiting");
                try {
//释放锁有两种方式, 第一种方式是程序自然离开监视器的范围, 也就是离开了synchronized 关键字管辖的代码范围, 另一种方式就是在synchronized 关键字管辖的代码内部调用监视器对象的wait 方法。这里, 使用wait 方法释放锁。
                    MultiThread.class.wait();
                } catch(InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("thread1is going on...");
                System.out.println("thread1is being over!");
            }
        }
    }
    private static class Thread2 implements Runnable
    {
        @Override
        public void run() {
            synchronized (MultiThread.class) {
                System.out.println("enterthread2...");
                System.out.println("thread2notify other thread can release wait status..");
//由于notify 方法并不释放锁, 即使thread2调用下面的sleep 方法休息了10毫秒, 但thread1仍然不会执行, 因为thread2没有释放锁, 所以Thread1无法得不到锁。
                MultiThread.class.notify();
                System.out.println("thread2is sleeping ten millisecond...");
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("thread2is going on...");
                System.out.println("thread2is being over!");
            }
        }
    }
}

48、同步和异步有何异同, 在什么情况下分别使用他们？举例说明。
如果数据将在线程间共享。例如正在写的数据以后可能被另一个线程读到, 
或者正在读的数据可能已经被另一个线程写过了, 那么这些数据就是共享数据, 必须进行同步存取。
当应用程序在对象上调用了一个需要花费很长时间来执行的方法, 并且不希望让程序等待方法的返回时, 
就应该使用异步编程, 在很多情况下采用异步途径往往更有效率。

49. 下面两个方法同步吗？（自己发明）
class Test {
    Synchronized static void say Hello3() {
    }
    Synchronized void getX() {}
}

50、多线程有几种实现方法?同步有几种实现方法?
多线程有两种实现方法, 分别是继承Thread 类与实现Runnable 接口
同步的实现方面有两种, 分别是synchronized,wait 与notify
wait():使一个线程处于等待状态, 并且释放所持有的对象的lock。
sleep():使一个正在运行的线程处于睡眠状态, 是一个静态方法, 调用此方法要捕捉
InterruptedException(中断异常)异常。
notify():唤醒一个处于等待状态的线程, 注意的是在调用此方法的时候, 并不能确切的唤醒
某一个等待状态的线程, 而是由JVM 确定唤醒哪个线程, 而且不是按优先级。
notifyAll():唤醒所有处入等待状态的线程, 注意并不是给所有唤醒线程一个对象的锁, 而是让它们竞争。

51、启动一个线程是用run()还是start()? .
启动一个线程是调用start()方法, 使线程就绪状态, 以后可以被调度为运行状态, 一个线程
必须关联一些具体的执行代码, run()方法是该线程所关联的执行代码。

52、当一个线程进入一个对象的一个synchronized 方法后, 其它线程是否可进入此对象的其它方法?
分几种情况：
1. 其他方法前是否加了synchronized 关键字, 如果没加, 则能。
2. 如果这个方法内部调用了wait, 则可以进入其他synchronized 方法。
3. 如果其他个方法都加了synchronized 关键字, 并且内部没有调用wait, 则不能。
4. 如果其他方法是static, 它用的同步锁是当前类的字节码, 与非静态的方法不能同步, 因为非静态的方法用的是this。

53、线程的基本概念、线程的基本状态以及状态之间的关系
一个程序中可以有多条执行线索同时执行, 一个线程就是程序中的一条执行线索, 每个线程
上都关联有要执行的代码, 即可以有多段程序代码同时运行, 每个程序至少都有一个线程, 
即main 方法执行的那个线程。如果只是一个cpu, 它怎么能够同时执行多段程序呢？这是
从宏观上来看的, cpu 一会执行a 线索, 一会执行b 线索, 切换时间很快, 给人的感觉是
a,b 在同时执行, 好比大家在同一个办公室上网, 只有一条链接到外部网线, 其实, 这条网
线一会为a 传数据, 一会为b 传数据, 由于切换时间很短暂, 所以, 大家感觉都在同时上网。
状态：就绪, 运行, synchronize 阻塞, wait 和sleep 挂起, 结束。
wait 必须在synchronized内部调用。
调用线程的start 方法后线程进入就绪状态, 线程调度系统将就绪状态的线程转为运行状态, 
遇到synchronized 语句时, 由运行状态转为阻塞, 当synchronized 获得锁后, 由阻塞转为运行, 
在这种情况可以调用wait 方法转为挂起状态, 当线程关联的代码执行完后, 线程变为结束状态。

54、简述synchronized 和java.util.concurrent.locks.Lock 的异同？
主要相同点：Lock 能完成synchronized 所实现的所有功能
主要不同点：Lock 有比synchronized 更精确的线程语义和更好的性能。synchronized 会自
动释放锁, 而Lock 一定要求程序员手工释放, 并且必须在finally 从句中释放。Lock 还有更
强大的功能, 例如, 它的tryLock 方法可以非阻塞方式去拿锁。
举例说明（对下面的题用lock 进行了改写）：
package com.huawei.interview;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
publicclass ThreadTest {
    private int j;
    private Lock lock =new ReentrantLock();
    public static void main(String[] args) {
        ThreadTest tt = new ThreadTest();
        for(int i=0; i<2; i++) {
            new Thread(tt.new Adder()).start();
            new Thread(tt.new Subtractor()).start();
        }
    }
    private class Subtractor implements Runnable
    {
        @Override
        public void run() {
            while(true) {
                /*synchronized (ThreadTest.this) {
                System.out.println("j--="+ j--);
                //这里抛异常了, 锁能释放吗？
                }*/
                lock.lock();
                try {
                    System.out.println("j--="+ j--);
                }
                finally {
                    lock.unlock();
                }
            }
        }
    }
    private class Adder implements Runnable
    {
        @Override
        public void run() {
            while(true) {
                /*synchronized (ThreadTest.this) {
                System.out.println("j++="+ j++);
                }*/
                lock.lock();
                try {
                    System.out.println("j++="+ j++);
                }
                finally {
                    lock.unlock();
                }
            }
        }
    }
}

55、设计4 个线程, 其中两个线程每次对j 增加1, 另外两个线程对j 每次减少1。写出程序。
以下程序使用内部类实现线程, 对j 增减的时候没有考虑顺序问题。
public class ThreadTest1 {
    private int j;
    public static void main(String args[]) {
        ThreadTest1 tt=new ThreadTest1();
        Inc inc=tt.new Inc();
        Dec dec=tt.new Dec();
        for(inti=0; i<2; i++) {
            Thread t=new Thread(inc);
            t.start();
            t=new Thread(dec);
            t.start();
        }
    }
    private synchronized void inc() {
        j++;
        System.out.println(Thread.currentThread().getName()+"-inc:"+j);
    }
    private synchronized void dec() {
        j--;
        System.out.println(Thread.currentThread().getName()+"-dec:"+j);
    }
    class Inc implements Runnable {
        public void run() {
            for(inti=0; i<100; i++) {
                inc();
            }
        }
    }
    class Dec implements Runnable {
        public void run() {
            for(inti=0; i<100; i++) {
                dec();
            }
        }
    }
}
----------随手再写的一个-------------
class A {
    JManger j =new JManager();
    main() {
        new A().call();
    }
    void call {
        for(int i=0; i<2; i++) {
            new Thread(
            new Runnable() {
                public void run() {
                    while(true) {
                        j.accumulate()
                    }
                }
            }
            ).start();
            new Thread(new Runnable() {
                public void run() {
                    while(true) {
                        j.sub()
                    }
                }
            }).start();
        }
    }
}
class JManager {
    private j = 0;
    public synchronized void subtract() {
        j--
    }
    public synchronized void accumulate() {
        j++;
    }
}

56、子线程循环10 次, 接着主线程循环100, 接着又回到子线程循环10 次, 接着再回到主线程又循环100, 如此循环50 次, 请写出程序。
最终的程序代码如下：
public class ThreadTest {
    public static void main(String[] args) {
        new ThreadTest().init();
    }
    public void init() {
        final Business business =new Business();
        new Thread(
        new Runnable() {
            public voidrun() {
                for(inti=0; i<50; i++) {
                    business.SubThread(i);
                }
            }
        }
        ).start();
        for(int i=0; i<50; i++) {
            business.MainThread(i);
        }
    }
    private class Business {
        Boolean bShouldSub =true;//这里相当于定义了控制该谁执行的一个信号灯
        public synchronized void MainThread(int i) {
            if(bShouldSub)
                try {
                    this.wait();
                } catch(InterruptedException e) {
                    e.printStackTrace();
                }
            for(int j=0; j<5; j++) {
                System.out.println(Thread.currentThread().getName()+ ":i=" + i +",j=" + j);
            }
            bShouldSub =true;
            this.notify();
        }
        public synchronized void SubThread(int i) {
            if(!bShouldSub)
                try {
                    this.wait();
                } catch (InterruptedExceptione) {
                    e.printStackTrace();
                }
            for(intj=0; j<10; j++) {
                System.out.println(Thread.currentThread().getName()+ ":i=" + i +",j=" + j);
            }
            bShouldSub =false;
            this.notify();
        }
    }
}
备注：不可能一上来就写出上面的完整代码, 最初写出来的代码如下, 问题在于两个线程的
代码要参照同一个变量, 即这两个线程的代码要共享数据, 所以, 把这两个线程的执行代码
搬到同一个类中去：
package com.huawei.interview.lym;
publicclass ThreadTest {
    private static booleanbShouldMain=false;
    public static void main(String[]args) {
        /*new Thread(){
        public void run()
        {
        for(int i=0;i<50;i++)
        {
        for(int j=0;j<10;j++)
        {
        System.out.println("i="+ i + ",j=" + j);
        }
        }
        }
        }.start();*/
//final String str = newString("");
        new Thread(
        new Runnable() {
            public voidrun() {
                for(inti=0; i<50; i++) {
                    synchronized(ThreadTest.class) {
                        if(bShouldMain) {
                            try {
                                ThreadTest.class.wait();
                            } catch(InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        for(intj=0; j<10; j++) {
                            System.out.println(
                                Thread.currentThread().getName()+
                                "i="+ i + ",j=" + j);
                        }
                        bShouldMain= true;
                        ThreadTest.class.notify();
                    }
                }
            }
        }
        ).start();
        for(int i=0; i<50; i++) {
            synchronized (ThreadTest.class) {
                if(!bShouldMain) {
                    try {
                        ThreadTest.class.wait();
                    } catch(InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                for(intj=0; j<5; j++) {
                    System.out.println(
                        Thread.currentThread().getName()+
                        "i=" + i +",j=" + j);
                }
                bShouldMain =false;
                ThreadTest.class.notify();
            }
        }
    }
}
下面使用jdk5中的并发库来实现的：
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Condition;
public class ThreadTest {
    private static Locklock = new ReentrantLock();
    private staticCondition subThreadCondition = lock.newCondition();
    private staticboolean bBhouldSubThread = false;
    public static voidmain(String [] args) {
        ExecutorServicethreadPool = Executors.newFixedThreadPool(3);
        threadPool.execute(newRunnable() {
            publicvoid run() {
                for(inti=0; i<50; i++) {
                    lock.lock();
                    try {
                        if(!bBhouldSubThread)
                            subThreadCondition.await();
                        for(intj=0; j<10; j++) {
                            System.out.println(Thread.currentThread().getName()+ ",j=" + j);
                        }
                        bBhouldSubThread= false;
                        subThreadCondition.signal();
                    } catch(Exceptione) {
                    }
                    finally {
                        lock.unlock();
                    }
                }
            }
        });
        threadPool.shutdown();
        for(inti=0; i<50; i++) {
            lock.lock();
            try {
                if(bBhouldSubThread)
                    subThreadCondition.await();
                for(intj=0; j<10; j++) {
                    System.out.println(Thread.currentThread().getName()+ ",j=" + j);
                }
                bBhouldSubThread= true;
                subThreadCondition.signal();
            } catch(Exceptione) {
            }
            finally {
                lock.unlock();
            }
        }
    }
}

57、介绍Collection 框架的结构
答：随意发挥题, 天南海北谁便谈, 只要让别觉得你知识渊博, 理解透彻即可。
Collection  是单列集合
List   元素是有序的、可重复
有序的 collection, 可以对列表中每个元素的插入位置进行精确地控制。
可以根据元素的整数索引（在列表中的位置）访问元素, 并搜索列表中的元素。 
可存放重复元素, 元素存取是有序的。
List接口中常用类
l Vector： 线程安全, 但速度慢, 已被ArrayList替代。
底层数据结构是数组结构
l ArrayList：线程不安全, 查询速度快。
            底层数据结构是数组结构
l LinkedList：线程不安全。增删速度快。
             底层数据结构是列表结构
 
Set(集) 元素无序的、不可重复。
取出元素的方法只有迭代器。不可以存放重复元素, 元素存取是无序的。
Set接口中常用的类
l HashSet：线程不安全, 存取速度快。
          它是如何保证元素唯一性的呢？依赖的是元素的hashCode方法和euqals方法。
l TreeSet：线程不安全, 可以对Set集合中的元素进行排序。
          它的排序是如何进行的呢？通过compareTo或者compare方法中的来保证元素的唯一性。元素是以二叉树的形式存放的。
 
 
Map  是一个双列集合
|--Hashtable:线程安全, 速度快。底层是哈希表数据结构。是同步的。
不允许null作为键, null作为值。
      |--Properties:用于配置文件的定义和操作, 使用频率非常高, 同时键和值都是字符串。
是集合中可以和IO技术相结合的对象。(到了IO在学习它的特有和io相关的功能。)
|--HashMap:线程不安全, 速度慢。底层也是哈希表数据结构。是不同步的。
允许null作为键, null作为值。替代了Hashtable.
      |--LinkedHashMap: 可以保证HashMap集合有序。存入的顺序和取出的顺序一致。
|--TreeMap：可以用来对Map集合中的键进行排序.
 
 
Collection 和 Collections的区别
 
Collection是集合类的上级接口, 子接口主要有Set 和List、Map。 
Collections是针对集合类的一个帮助类, 提供了操作集合的工具方法：一系列静态方法实现对各种集合的搜索、排序、线程安全化等操作。

58、Collection 框架中实现比较要实现什么接口
comparable/comparator

59、ArrayList 和Vector 的区别
答：
这两个类都实现了List 接口（List 接口继承了Collection 接口）, 他们都是有序集合, 
即存储在这两个集合中的元素的位置都是有顺序的, 相当于一种动态的数组, 
我们以后可以按位置索引号取出某个元素, 并且其中的数据是允许重复的, 
这是HashSet 之类的集合的最大不同处, HashSet 之类的集合不可以按索引号去检索其中的元素, 
也不允许有重复的元素（本来题目问的与hashset 没有任何关系, 但为了说清楚ArrayList 
与Vector 的功能, 我们使用对比方式, 更有利于说明问题）。
接着才说ArrayList 与Vector 的区别, 这主要包括两个方面：.
（1）同步性：
Vector 是线程安全的, 也就是说是它的方法之间是线程同步的, 而ArrayList 是线程序不安全的, 
它的方法之间是线程不同步的。如果只有一个线程会访问到集合, 那最好是使
用ArrayList, 因为它不考虑线程安全, 效率会高些；如果有多个线程会访问到集合, 那最
好是使用Vector, 因为不需要我们自己再去考虑和编写线程安全的代码。
备注：对于Vector&ArrayList、Hashtable&HashMap, 要记住线程安全的问题, 记住Vector
与Hashtable 是旧的, 是java 一诞生就提供了的, 它们是线程安全的, ArrayList 与HashMap
是java2时才提供的, 它们是线程不安全的。所以, 我们讲课时先讲老的。
（2）数据增长：
ArrayList 与Vector 都有一个初始的容量大小, 当存储进它们里面的元素的个数超过
了容量时, 就需要增加ArrayList 与Vector 的存储空间, 每次要增加存储空间时, 不是只增
加一个存储单元, 而是增加多个存储单元, 每次增加的存储单元的个数在内存空间利用与程
序效率之间要取得一定的平衡。Vector 默认增长为原来两倍, 而ArrayList 的增长策略在文
档中没有明确规定（从源代码看到的是增长为原来的1.5倍）。ArrayList 与Vector 都可以设
置初始的空间大小, Vector 还可以设置增长的空间大小, 而ArrayList 没有提供设置增长空间的方法。
总结：即Vector 增长原来的一倍, ArrayList 增加原来的0.5倍。

60、HashMap 和Hashtable 的区别
（条理上还需要整理, 也是先说相同点, 再说不同点）
HashMap 是Hashtable 的轻量级实现（非线程安全的实现）, 他们都完成了Map 接口, 主要区别在于
HashMap 允许空（null）键值（key）,由于非线程安全, 在只有一个线程访问的情况下, 效率要高于Hashtable。
HashMap 允许将null 作为一个entry 的key 或者value, 而Hashtable 不允许。
HashMap 把Hashtable 的contains 方法去掉了, 改成containsvalue 和containsKey。
因为contains 方法容易让人引起误解。
Hashtable 继承自Dictionary 类, 而HashMap 是Java1.2引进的Map interface 的一个实现。
最大的不同是, Hashtable 的方法是Synchronize 的, 而HashMap 不是, 在多个线程访问Hashtable 时, 
不需要自己为它的方法实现同步, 而HashMap 就必须为之提供外同步。
Hashtable 和HashMap 采用的hash/rehash 算法都大概一样, 所以性能不会有很大的差异。
就HashMap 与HashTable 主要从三方面来说。
一.历史原因:Hashtable 是基于陈旧的Dictionary 类的, HashMap 是Java 1.2引进的Map
接口的一个实现
二.同步性:Hashtable 是线程安全的, 也就是说是同步的, 而HashMap 是线程序不安全的, 
不是同步的
三.值：只有HashMap 可以让你将空值作为一个表的条目的key 或value

61、List 和 Map 区别?
一个是存储单列数据的集合, 另一个是存储键和值这样的双列数据的集合, 
List 中存储的数据是有顺序, 并且允许重复；
Map 中存储的数据是没有顺序的, 其键是不能重复的, 它的值是可以有重复的。

62、List, Set, Map 是否继承自Collection 接口?
List, Set 是, Map 不是

63、List、Map、Set 三个接口, 存取元素时, 各有什么特点？
这样的题属于随意发挥题：这样的题比较考水平, 两个方面的水平：一是要真正明白这些内容, 
二是要有较强的总结和表述能力。如果你明白, 但表述不清楚, 在别人那里则等同于不明白。
首先, List 与Set 具有相似性, 它们都是单列元素的集合, 所以, 它们有一个功共同的父接口Collection。
Set 里面不允许有重复的元素, 所谓重复, 即不能有两个相等（注意, 不是仅仅是相同）的对象, 
即假设Set 集合中有了一个A 对象, 现在我要向Set 集合再存入一个B 对象, 但B 对象与A 对象equals 相等, 
则B 对象存储不进去, 所以, Set 集合的add 方法有一个boolean 的返回值, 
当集合中没有某个元素, 此时add 方法可成功加入该元素时, 则返回true, 
当集合含有与某个元素equals 相等的元素时, 此时add 方法无法加入该元素, 返回结果为false。
Set 取元素时, 没法说取第几个, 只能以Iterator 接口取得所有的元素, 再逐一遍历各个元素。
List 表示有先后顺序的集合, 注意, 不是那种按年龄、按大小、按价格之类的排序。
当我们多次调用add(Obj e)方法时, 每次加入的对象就像火车站买票有排队顺序一样, 按先
来后到的顺序排序。有时候, 也可以插队, 即调用add(int index,Obj e)方法, 就可以指定当
前对象在集合中的存放位置。一个对象可以被反复存储进List 中, 每调用一次add 方法, 
这个对象就被插入进集合中一次, 其实, 并不是把这个对象本身存储进了集合中, 而是在集
合中用一个索引变量指向这个对象, 当这个对象被add 多次时, 即相当于集合中有多个索
引指向了这个对象, 如图x 所示。List 除了可以以Iterator 接口取得所有的元素, 再逐一遍
历各个元素之外, 还可以调用get(index i)来明确说明取第几个。
Map 与List 和Set 不同, 它是双列的集合, 其中有put 方法, 定义如下：put(objkey,objvalue), 
每次存储时, 要存储一对key/value, 不能存储重复的key, 这个重复的规则也是按equals 比较相等。
取则可以根据key 获得相应的value, 即get(Object key)返回值为key所对应的value。
另外, 也可以获得所有的key 的结合, 还可以获得所有的value的结合, 
还可以获得key 和value 组合成的Map.Entry 对象的集合。
List 以特定次序来持有元素, 可有重复元素。Set 无法拥有重复元素,内部排序。
Map 保存key-value 值, value 可多值。
HashSet 按照hashcode 值的某种运算方式进行存储, 而不是直接按hashCode 值的大小进
行存储。例如, "abc"---> 78, "def" ---> 62, "xyz" ---> 65在hashSet 中的存储顺序不是
62,65,78, 这些问题感谢以前一个叫崔健的学员提出, 最后通过查看源代码给他解释清楚, 
看本次培训学员当中有多少能看懂源码。LinkedHashSet 按插入的顺序存储, 那被存储对象
的hashcode 方法还有什么作用呢？学员想想!
hashset 集合比较两个对象是否相等, 首先看hashcode 方法是否相等, 然后看equals 方法是否相等。
new 两个Student 插入到HashSet中, 看HashSet 的size, 实现hashcode 和equals 方法后再看size。
同一个对象可以在Vector 中加入多次。往集合里面加元素, 相当于集合里用一根绳子连接
到了目标对象。往HashSet 中却加不了多次的。

64、说出ArrayList,Vector, LinkedList 的存储性能和特性
ArrayList 和Vector 都是使用数组方式存储数据, 此数组元素数大于实际存储的数据以便增
加和插入元素, 它们都允许直接按序号索引元素, 但是插入元素要涉及数组元素移动等内存
操作, 所以索引数据快而插入数据慢, Vector 由于使用了synchronized 方法（线程安全）, 
通常性能上较ArrayList 差, 而LinkedList 使用双向链表实现存储, 按序号索引数据需要进
行前向或后向遍历, 但是插入数据时只需要记录本项的前后项即可, 所以插入速度较快。
LinkedList 也是线程不安全的, LinkedList 提供了一些方法, 使得LinkedList 可以被当作堆
栈和队列来使用。

65、去掉一个Vector 集合中重复的元素
Vector newVector = new Vector();
For (int i=0; i<vector.size(); i++) {
    Object obj = vector.get(i);
    if(!new Vector.contains(obj);
            newVector.add(obj);
}
还有一种简单的方式, HashSet set = new HashSet(vector);

66、Collection 和 Collections 的区别。
Collection 是集合类的上级接口, 继承与他的接口主要有Set 和List.
Collections 是针对集合类的一个帮助类, 他提供一系列静态方法实现对各种集合的搜索、
排序、线程安全化等操作。

67、Set 里的元素是不能重复的, 那么用什么方法来区分重复与否呢?是用==还是equals()?它们有何区别?
Set 里的元素是不能重复的, 元素重复与否是使用equals()方法进行判断的。
equals()和==方法决定引用值是否指向同一对象,
equals()在类中被覆盖, 为的是当两个分离的对象的内容和类型相配的话, 返回真值。

68、你所知道的集合类都有哪些？主要方法？
最常用的集合类是 List 和 Map。 
List 的具体实现包括 ArrayList 和 Vector, 它们是可变大小的列表, 
比较适合构建、存储和操作任何类型对象的元素列表。 List 适用于按数值索引访问元素的情形。
Map 提供了一个更通用的元素存储方法。 Map 集合类用于存储元素对（称作"键"和"值"）, 
其中每个键映射到一个值。
ArrayList/Vector
List
Collection
HashSet/TreeSet
Set
Propeties/HashTable
Map
Treemap/HashMap
我记的不是方法名, 而是思想, 我知道它们都有增删改查的方法, 但这些方法的具体名称, 
我记得不是很清楚, 对于set, 大概的方法是add,remove, contains；对于map, 大概的方
法就是put,remove, contains 等, 因为, 我只要在eclispe 下按点操作符, 很自然的这些方
法就出来了。我记住的一些思想就是List 类会有get(int index)这样的方法, 因为它可以按
顺序取元素, 而set 类中没有get(int index)这样的方法。List 和set 都可以迭代出所有元素, 
迭代时先要得到一个iterator 对象, 所以, set 和list 类都有一个iterator 方法, 用于返回那
个iterator 对象。map 可以返回三个集合, 一个是返回所有的key 的集合, 另外一个返回的
是所有value 的集合, 再一个返回的key 和value 组合成的EntrySet 对象的集合, map 也
有get 方法, 参数是key, 返回值是key 对应的value。

69、两个对象值相同(x.equals(y) == true), 但却可有不同的hash code, 这句话对不对?
对。
如果对象要保存在HashSet或HashMap中, 它们的equals相等, 那么, 它们的hashcode值就必须相等。
如果不是要保存在HashSet或HashMap, 则与hashcode没有什么关系了, 这时候hashcode不等是可以的, 
例如arrayList 存储的对象就不用实现hashcode, 当然, 我们没有理由不实现, 通常都会去实现的。

70、TreeSet 里面放对象, 如果同时放入了父类和子类的实例对象, 那比较时使用的是父类的compareTo 方法, 还是使用的子类的compareTo 方法, 还是抛异常！
（应该是没有针对问题的确切的答案, 当前的add方法放入的是哪个对象, 就调用哪个对象的compareTo方法, 
至于这个compareTo 方法怎么做, 就看当前这个对象的类中是如何编写这个方法的）
实验代码：
public class Parent implements Comparable {
    private int age = 0;
    public Parent(int age) {
        this.age = age;
    }
    public int compareTo(Object o) {
        System.out.println("method ofparent");
        Parent o1 = (Parent)o;
        return age>o1.age?1:age<o1.age?-1:0;
    }
}
public class Child extends Parent {
    public Child() {
        super(3);
    }
    public int compareTo(Object o) {
        System.out.println("methodof child");
// Child o1 = (Child)o;
        return 1;
    }
}
public class TreeSetTest {
    public static voidmain(String[] args) {
        TreeSet set = new TreeSet();
        set.add(new Parent(3));
        set.add(new Child());
        set.add(new Parent(4));
        System.out.println(set.size());
    }
}

71、说出一些常用的类, 包, 接口, 请各举5 个
要让人家感觉你对java ee 开发很熟, 所以, 不能仅仅只列core java 中的那些东西, 要多
列你在做ssh 项目中涉及的那些东西。就写你最近写的那些程序中涉及的那些类。
常用的类：BufferedReader BufferedWriter FileReader FileWirter String Integer
java.util.Date, System, Class, List,HashMap
常用的包：java.lang java.io java.util
java.sql,javax.servlet,org.apache.strtuts.action,org.hibernate
常用的接口：Remote List Map Document
NodeList,Servlet,HttpServletRequest,HttpServletResponse,Transaction(Hibernate)、
Session(Hibernate),HttpSession

72、java 中有几种类型的流？JDK 为每种类型的流提供了一些抽象类以供继承, 请说出他们分别是哪些类？
字节流, 字符流。
字节流继承于InputStream OutputStream, 
字符流继承于InputStreamReader OutputStreamWriter。
在java.io 包中还有许多其他的流, 主要是为了提高性能和使用方便。

73、字节流与字符流的区别
要把一片二进制数据数据逐一输出到某个设备中, 或者从某个设备中逐一读取一片
二进制数据, 不管输入输出设备是什么, 我们要用统一的方式来完成这些操作, 用一种抽象
的方式进行描述, 这个抽象描述方式起名为IO 流, 对应的抽象类为OutputStream 和
InputStream, 不同的实现类就代表不同的输入和输出设备, 它们都是针对字节进行操作的。
在应用中, 经常要完全是字符的一段文本输出去或读进来, 用字节流可以吗？计算
机中的一切最终都是二进制的字节形式存在。对于"中国"这些字符, 首先要得到其对应的字
节, 然后将字节写入到输出流。读取时, 首先读到的是字节, 可是我们要把它显示为字符, 
我们需要将字节转换成字符。由于这样的需求很广泛, 人家专门提供了字符流的包装类。
底层设备永远只接受字节数据, 有时候要写字符串到底层设备, 需要将字符串转成字节再
进行写入。
字符流是字节流的包装, 字符流则是直接接受字符串, 它内部将串转成字节, 
再写入底层设备, 这为我们向IO 设别写入或读取字符串提供了一点点方便。
字符向字节转换时, 要注意编码的问题, 因为字符串转成字节数组, 
其实是转成该字符的某种编码的字节形式, 读取也是反之的道理。
讲解字节流与字符流关系的代码案例：
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.PrintWriter;
public class IOTest {
    public static void main(String[]args) throws Exception {
        String str = "中国人";
        /*FileOutputStreamfos = newFileOutputStream("1.txt");
        fos.write(str.getBytes("UTF-8"));
        fos.close();*/
        /*FileWriter fw =new FileWriter("1.txt");
        fw.write(str);
        fw.close();*/
        PrintWriter pw =new PrintWriter("1.txt","utf-8");
        pw.write(str);
        pw.close();
        /*FileReader fr =new FileReader("1.txt");
        char[] buf = newchar[1024];
        int len =fr.read(buf);
        String myStr = newString(buf,0,len);
        System.out.println(myStr);*/
        /*FileInputStream fr = new FileInputStream("1.txt");
        byte[] buf = new byte[1024];
        int len =fr.read(buf);
        String myStr = newString(buf,0,len,"UTF-8");
        System.out.println(myStr);*/
        BufferedReader br =new BufferedReader(
            New InputStreamReader(
                New FileInputStream("1.txt"),"UTF-8"
            )
        );
        String myStr =br.readLine();
        br.close();
        System.out.println(myStr);
    }
}

74、什么是java 序列化, 如何实现java 序列化？或者请解释Serializable 接口的作用。
我们有时候将一个java 对象变成字节流的形式传出去或者从一个字节流中恢复成一个java
对象, 例如, 要将java 对象存储到硬盘或者传送给网络上的其他计算机, 这个过程我们可
以自己写代码去把一个java 对象变成某个格式的字节流再传输, 但是, jre 本身就提供了这
种支持, 我们可以调用OutputStream 的writeObject 方法来做, 如果要让java 帮我们做, 
要被传输的对象必须实现serializable 接口, 这样, javac 编译时就会进行特殊处理, 编译
的类才可以被writeObject 方法操作, 这就是所谓的序列化。需要被序列化的类必须实现
Serializable 接口, 该接口是一个mini 接口, 其中没有需要实现的方法, 
Implements Serializable 只是为了标注该对象是可被序列化的。
例如, 在web 开发中, 如果对象被保存在了Session 中, tomcat 在重启时要把Session 对
象序列化到硬盘, 这个对象就必须实现Serializable 接口。如果对象要经过分布式系统进行
网络传输或通过rmi 等远程调用, 这就需要在网络上传输对象, 被传输的对象就必须实现Serializable 接口。

75、描述一下JVM 加载class 文件的原理机制?
JVM 中类的装载是由ClassLoader 和它的子类来实现的,Java ClassLoader 是一个重要的
Java 运行时系统组件。它负责在运行时查找和装入类文件的类。

76、heap 和stack 有什么区别。
java 的内存分为两类, 一类是栈内存, 一类是堆内存。栈内存是指程序进入一个方法时, 
会为这个方法单独分配一块私属存储空间, 用于存储这个方法内部的局部变量, 当这个方法结束时, 
分配给这个方法的栈会释放, 这个栈中的变量也将随之释放。
堆是与栈作用不同的内存, 一般用于存放不放在当前方法栈中的那些数据, 例如, 使用new
创建的对象都放在堆里, 所以, 它不会随方法的结束而消失。方法中的局部变量使用final
修饰后, 放在堆中, 而不是栈中。

77、GC 是什么?为什么要有GC?
GC 是垃圾收集的意思（Gabage Collection）,内存处理是编程人员容易出现问题的地方, 
忘记或者错误的内存回收会导致程序或系统的不稳定甚至崩溃, Java 提供的GC 功能可以
自动监测对象是否超过作用域从而达到自动回收内存的目的, Java 语言没有提供释放已分配内存的显示操作方法。

78、垃圾回收的优点和原理。并考虑2 种回收机制。
Java 语言中一个显著的特点就是引入了垃圾回收机制, 使c++程序员最头疼的内存管理的
问题迎刃而解, 它使得Java 程序员在编写程序的时候不再需要考虑内存管理。由于有个垃
圾回收机制, Java 中的对象不再有"作用域"的概念, 只有对象的引用才有"作用域"。
垃圾回收可以有效的防止内存泄露, 有效的使用可以使用的内存。垃圾回收器通常是作为一个单独
的低级别的线程运行, 不可预知的情况下对内存堆中已经死亡的或者长时间没有使用的对象进行清楚和回收, 
程序员不能实时的调用垃圾回收器对某个对象或所有对象进行垃圾回收。
回收机制有分代复制垃圾回收和标记垃圾回收, 增量垃圾回收。

79、垃圾回收器的基本原理是什么？垃圾回收器可以马上回收内存吗？有什么办法主动通知虚拟机进行垃圾回收？
对于GC 来说, 当程序员创建对象时, GC 就开始监控这个对象的地址、大小以及使用情况。
通常, GC 采用有向图的方式记录和管理堆(heap)中的所有对象。通过这种方式确定哪些对
象是"可达的", 哪些对象是"不可达的"。当GC 确定一些对象为"不可达"时, GC 就有责任回
收这些内存空间。可以。程序员可以手动执行System.gc(), 通知GC 运行, 但是Java 语
言规范并不保证GC 一定会执行。

80、什么时候用assert。
assertion(断言)在软件开发中是一种常用的调试方式, 很多开发语言中都支持这种机制。在
实现中, assertion 就是在程序中的一条语句, 它对一个boolean 表达式进行检查, 
一个正确程序必须保证这个boolean 表达式的值为true；如果该值为false, 说明程序已经处于不正确的状态下, 
assert 将给出警告或退出。一般来说, assertion 用于保证程序最基本、关键的正确性。
assertion 检查通常在开发和测试时开启。为了提高性能, 在软件发布后, assertion 检查通常是关闭的。
package com.huawei.interview;
publicclass AssertTest {
    public static void main(String[] args) {
        int i = 0;
        for(i=0; i<5; i++) {
            System.out.println(i);
        }
//假设程序不小心多了一句--i;
        --i;
        assert i==5;
    }
}

81、java 中会存在内存泄漏吗, 请简单描述。
所谓内存泄露就是指一个不再被程序使用的对象或变量一直被占据在内存中。java 中有垃
圾回收机制, 它可以保证一对象不再被引用的时候, 即对象编程了孤儿的时候, 对象将自动
被垃圾回收器从内存中清除掉。由于Java 使用有向图的方式进行垃圾回收管理, 可以消除
引用循环的问题, 例如有两个对象, 相互引用, 只要它们和根进程不可达的, 那么GC 也是
可以回收它们的, 例如下面的代码可以看到这种情况的内存回收：
package com.huawei.interview;
import java.io.IOException;
publicclass GarbageTest {
    /**
    * @paramargs
    * @throwsIOException
    */
    public static void main(String[] args)throws IOException {
        try {
            gcTest();
        } catch (IOException e) {
// TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println("hasexited gcTest!");
        System.in.read();
        System.in.read();
        System.out.println("out begingc!");
        for(int i=0; i<100; i++) {
            System.gc();
            System.in.read();
            System.in.read();
        }
    }
    private static void gcTest()throws IOException {
        System.in.read();
        System.in.read();
        Person p1 = new Person();
        System.in.read();
        System.in.read();
        Person p2 = new Person();
        p1.setMate(p2);
        p2.setMate(p1);
        System.out.println("beforeexit gctest!");
        System.in.read();
        System.in.read();
        System.gc();
        System.out.println("exitgctest!");
    }
    private static class Person
    {
        byte[] data =new byte[20000000];
        Person mate = null;
        public void setMate(Personother) {
            mate = other;
        }
    }
}
java 中的内存泄露的情况：长生命周期的对象持有短生命周期对象的引用就很可能发生内
存泄露, 尽管短生命周期对象已经不再需要, 但是因为长生命周期对象持有它的引用而导致
不能被回收, 这就是java 中内存泄露的发生场景, 通俗地说, 就是程序员可能创建了一个
对象, 以后一直不再使用这个对象, 这个对象却一直被引用, 即这个对象无用但是却无法被
垃圾回收器回收的, 这就是java 中可能出现内存泄露的情况, 例如, 缓存系统, 我们加载
了一个对象放在缓存中(例如放在一个全局map 对象中), 然后一直不再使用它, 这个对象
一直被缓存引用, 但却不再被使用。 
检查java 中的内存泄露, 一定要让程序将各种分支情况都完整执行到程序结束, 然后看某
个对象是否被使用过, 如果没有, 则才能判定这个对象属于内存泄露。
如果一个外部类的实例对象的方法返回了一个内部类的实例对象, 这个内部类对象被长期引
用了, 即使那个外部类实例对象不再被使用, 但由于内部类持久外部类的实例对象, 这个外
部类对象将不会被垃圾回收, 这也会造成内存泄露。
下面内容来自于网上（主要特点就是清空堆栈中的某个元素, 并不是彻底把它从数组中拿掉, 
而是把存储的总数减少, 本人写得可以比这个好, 在拿掉某个元素时, 顺便也让它从数组中
消失, 将那个元素所在的位置的值设置为null 即可）：
我实在想不到比那个堆栈更经典的例子了,以致于我还要引用别人的例子, 下面的例子不是
我想到的, 是书上看到的, 当然如果没有在书上看到, 可能过一段时间我自己也想的到, 可
是那时我说是我自己想到的也没有人相信的。
public class Stack {
    private Object[] elements=new Object[10];
    private int size = 0;
    public void push(Object e) {
        ensureCapacity();
        elements[size++] = e;
    }
    public Object pop() {
        if( size == 0)
            throw new EmptyStackException();
        return elements[--size];
    }
    private void ensureCapacity() {
        if(elements.length == size) {
            Object[] oldElements = elements;
            elements = new Object[2 * elements.length+1];
            System.arraycopy(oldElements,0, elements, 0, size);
        }
    }
}
上面的原理应该很简单, 假如堆栈加了10个元素, 然后全部弹出来, 虽然堆栈是空的, 
没有我们要的东西, 但是这是个对象是无法回收的, 这个才符合了内存泄露的两个条件：无
用, 无法回收。
但是就是存在这样的东西也不一定会导致什么样的后果, 如果这个堆栈用的比较少, 也
就浪费了几个K 内存而已, 反正我们的内存都上G 了, 哪里会有什么影响, 再说这个东西
很快就会被回收的, 有什么关系。下面看两个例子。
例子1
public class Bad{
public static Stack s=Stack();
static{
s.push(new Object());
s.pop(); //这里有一个对象发生内存泄露
s.push(new Object()); //上面的对象可以被回收了, 等于是自愈了
}
}
因为是static, 就一直存在到程序退出, 但是我们也可以看到它有自愈功能, 就是说如
果你的Stack 最多有100个对象, 那么最多也就只有100个对象无法被回收其实这个应该很
容易理解, Stack 内部持有100个引用, 最坏的情况就是他们都是无用的, 因为我们一旦放
新的进取, 以前的引用自然消失！
内存泄露的另外一种情况：当一个对象被存储进HashSet 集合中以后, 就不能修改这个对
象中的那些参与计算哈希值的字段了, 否则, 对象修改后的哈希值与最初存储进HashSet
集合中时的哈希值就不同了, 在这种情况下, 即使在contains 方法使用该对象的当前引用
作为的参数去HashSet 集合中检索对象, 也将返回找不到对象的结果, 这也会导致无法从
HashSet 集合中单独删除当前对象, 造成内存泄露。

82、能不能自己写个类, 也叫java.lang.String？
可以, 但在应用的时候, 需要用自己的类加载器去加载, 否则, 系统的类加载器永远只是去
加载jre.jar 包中的那个java.lang.String。由于在tomcat 的web 应用程序中, 都是由webapp
自己的类加载器先自己加载WEB-INF/classess 目录中的类, 然后才委托上级的类加载器加
载, 如果我们在tomcat 的web 应用程序中写一个java.lang.String, 这时候Servlet 程序加
载的就是我们自己写的java.lang.String, 但是这么干就会出很多潜在的问题, 原来所有用
了java.lang.String 类的都将出现问题。
虽然java 提供了endorsed 技术, 可以覆盖jdk 中的某些类, 具体做法是….。但是, 能够
被覆盖的类是有限制范围, 反正不包括java.lang 这样的包中的类。
（下面的例如主要是便于大家学习理解只用, 不要作为答案的一部分, 否则, 人家怀疑是题
目泄露了）例如, 运行下面的程序：
package java.lang;
public class String {
/**
* @paramargs
*/
public static void main(String[] args) {
System.out.println("string");
}
}
报告的错误如下：
java.lang.NoSuchMethodError:main
Exception inthread "main"
这是因为加载了jre 自带的java.lang.String, 而该类中没有main 方法。
83. Java 代码查错
1.
abstract class Name {
    private String name;
    public abstract boolean isStupidName(String name) {}
}
大侠们, 这有何错误?
答案: 错。abstract method 必须以分号结尾, 且不带花括号。
2.
public class Something {
    void doSomething () {
        private String s = "";
        int l = s.length();
    }
}
有错吗?
答案: 错。局部变量前不能放置任何访问修饰符 (private, public, 和protected)。final 可
以用来修饰局部变量
(final 如同abstract 和strictfp, 都是非访问修饰符, strictfp 只能修饰class 和method 而非
 variable)。
3.
abstract class Something {
    private abstract String doSomething ();
}
这好像没什么错吧?
答案: 错。abstract 的methods 不能以private 修饰。abstract 的methods 就是让子类
implement(实现)具体细节的, 怎么可以用private 把abstractmethod 封锁起来呢? 
(同理, abstract method 前不能加final)。
4.
public class Something {
    public int addOne(final int x) {
        return ++x;
    }
}
这个比较明显。
答案:
错。int x 被修饰成final, 意味着x 不能在addOne method 中被修改。
5.
public class Something {
    public static void main(String[] args) {
        Other o = new Other();
        new Something().addOne(o);
    }
    public void addOne(final Other o) {
        o.i++;
    }
}
class Other {
    public int i;
}
和上面的很相似, 都是关于final 的问题, 这有错吗?
答案: 正确。在addOne method 中, 参数o 被修饰成final。如果在addOne method 里我
们修改了o 的reference
(比如: o = new Other();), 那么如同上例这题也是错的。但这里修改的是o 的member
vairable
(成员变量), 而o 的reference 并没有改变。
6.
class Something {
    int i;
    public void doSomething() {
        System.out.println("i = "+ i);
    }
}
有什么错呢? 看不出来啊。
答案: 正确。输出的是"i = 0"。int i 属於instant variable (实例变量, 或叫成员变量)。instant
variable 有default value。int 的default value 是0。
7.
class Something {
    final int i;
    public void doSomething() {
        System.out.println("i = "+ i);
    }
}
和上面一题只有一个地方不同, 就是多了一个final。这难道就错了吗?
答案: 错。final int i 是个final 的instant variable (实例变量, 或叫成员变量)。final 的instant
variable 没有default value, 必须在constructor (构造器)结束之前被赋予一个明确的值。可
    以修改为"final int i =0;"。
    8.
    public class Something {
        public static void main(String[] args) {
            Something s = new Something();
            System.out.println("s.doSomething() returns " + doSomething());
        }
        public String doSomething() {
            return "Do something ...";
        }
    }
看上去很完美。
答案:
错。看上去在main 里call doSomething 没有什么问题, 毕竟两个methods 都在同一
个class 里。但仔细看, main 是static 的。static method 不能直接call non-staticmethods。
    可改成"System.out.println("s.doSomething()returns " + s.doSomething());"。同理, static
    method 不能访问non-static instant variable。
9.
此处, Something 类的文件名叫OtherThing.java
    class Something {
    private static void main(String[] something_to_do) {
        System.out.println("Dosomething ...");
    }
}
这个好像很明显。
答案:
正确。从来没有人说过Java 的Class 名字必须和其文件名相同。但public class 的
    名字必须和文件名相同。
10．
interface A {
int x = 0;
}
class B {
    int x =1;
}
class C extends B implements A {
    public void pX() {
        System.out.println(x);
    }
    public static void main(String[] args) {
        new C().pX();
    }
}
答案：错误。在编译时会发生错误(错误描述不同的JVM 有不同的信息, 意思就是未明确的
        x 调用, 两个x 都匹配（就象在同时import java.util 和java.sql 两个包时直接声明Date 一
        样）。对于父类的变量,可以用super.x 来明确, 而接口的属性默认隐含为 public static final.
        所以可以通过A.x 来明确。
11.
interface Playable {
    void play();
}
interface Bounceable {
    void play();
}
interface Rollable extends Playable, Bounceable {
    Ball ball = new Ball("PingPang");
}
class Ball implements Rollable {
    private String name;
    public String getName() {
        return name;
    }
    public Ball(String name) {
        this.name =name;
    }
    public void play() {
        ball = new Ball("Football");
        System.out.println(ball.getName());
    }
}
这个错误不容易发现。
答案: 错。"interface Rollable extends Playable, Bounceable"没有问题。interface 可继承多
个interfaces, 所以这里没错。问题出在interface Rollable 里的"Ball ball =new
Ball("PingPang");"。任何在interface 里声明的interface variable (接口变量, 也可称成员变量), 
默认为public static final。也就是说"Ball ball = new Ball("PingPang");"实际上是"public
Static final Ball ball = new Ball("PingPang");"。在Ball 类的Play()方法中, "ball =
newBall("Football");"改变了ball 的reference, 而这里的ball 来自Rollable interface, Rollable
interface 里的ball 是public static final 的, final 的object 是不能被改变reference 的。因此
编译器将在"ball = new Ball("Football");"这里显示有错。
二.算法与编程

1、编写一个程序, 将a.txt 文件中的单词与b.txt 文件中的单词交替合并到c.txt
文件中, a.txt 文件中的单词用回车符分隔, b.txt 文件中用回车或空格进行分隔。
答：
packagecn.itcast;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
public class MainClass {
	public static voidmain(String[]args)throws Exception {
		FileManager a = new FileManager("a.txt", new char[]{
				'\n'
			});
		FileManager b = new FileManager("b.txt", new char[]{
				'\n',
				' '
			});
		FileWriter c = new FileWriter("c.txt");
		String aWord = null;
		String bWord = null;
		while ((aWord = a.nextWord()) != null) {
			c.write(aWord + "\n");
			bWord = b.nextWord();
			if (bWord != null)
				c.write(bWord + "\n");
		}
		while ((bWord = b.nextWord()) != null) {
			c.write(bWord + "\n");
		}
		c.close();
	}
}
class FileManager {
	String[]words = null;
	int pos = 0;
	publicFileManager(String filename, char[]seperators)throws Exception {
		File f = newFile(filename);
		FileReaderreader = new FileReader(f);
		char[]buf = new char[(int)f.length()];
		int len = reader.read(buf);
		Stringresults = new String(buf, 0, len);
		String regex = null;
		if (seperators.length > 1) {
			regex = "" + seperators[0] + "|" + seperators[1];
		} else {
			regex = "" + seperators[0];
		}
		words = results.split(regex);
	}
	public StringnextWord() {
		if (pos == words.length)
			returnnull;
		returnwords[pos++];
	}
}

2、编写一个程序, 将d:\java 目录下的所有.java 文件复制到d:\jad 目录下, 并
将原来文件的扩展名从.java 改为.jad。
（大家正在做上面这道题, 网上迟到的朋友也请做做这道题, 找工作必须能编写这些简单问
题的代码！）
答：listFiles 方法接受一个FileFilter 对象, 这个FileFilter 对象就是过虑的策略对象, 不
同的人提供不同的FileFilter 实现, 即提供了不同的过滤策略。
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
public class Jad2Java {
	public static voidmain(String[]args)throws Exception {
		File srcDir = new File("java");
		if (!(srcDir.exists() && srcDir.isDirectory()))
			thrownew Exception("目录不存在");
		File[]files = srcDir.listFiles(
				newFilenameFilter() {
				publicboolean accept(File dir, String name) {
					returnname.endsWith(".java");
				}
			});
		System.out.println(files.length);
		File destDir = new File("jad");
		if (!destDir.exists())
			destDir.mkdir();
		for (File f : files) {
			FileInputStream fis = new FileInputStream(f);
			StringdestFileName = f.getName().replaceAll("\\.java$", ".jad");
			FileOutputStreamfos = new FileOutputStream(new
					File(destDir, destFileName));
			copy(fis, fos);
			fis.close();
			fos.close();
		}
	}
	private static voidcopy(InputStream ips, OutputStream ops)throws Exception {
		int len = 0;
		byte[]buf = new byte[1024];
		while ((len = ips.read(buf)) != -1) {
			ops.write(buf, 0, len);
		}
	}
}
由本题总结的思想及策略模式的解析：
1.
class jad2java{
1. 得到某个目录下的所有的java 文件集合
1.1 得到目录 File srcDir = newFile("d:\\java");
1.2 得到目录下的所有java 文件：
File[]files = srcDir.listFiles(new MyFileFilter());
1.3 只想得到.java 的文件： 
class MyFileFilterimplememyts FileFilter {
	publicboolean accept(File pathname) {
		returnpathname.getName().endsWith(".java")
	}
}
2.将每个文件复制到另外一个目录, 并改扩展名
2.1 得到目标目录, 如果目标目录不存在, 则创建之
2.2 根据源文件名得到目标文件名, 注意要用正则表达式, 注意.的转义。
2.3 根据表示目录的File 和目标文件名的字符串, 得到表示目标文件的File。
//要在硬盘中准确地创建出一个文件, 需要知道文件名和文件的目
录。
2.4 将源文件的流拷贝成目标文件流, 拷贝方法独立成为一个方法, 方法的
参数采用抽象流的形式。
//方法接受的参数类型尽量面向父类, 越抽象越好, 这样适应面更
宽广。
}
分析listFiles 方法内部的策略模式实现原理
File[]listFiles(FileFilter filter) {
	File[]files = listFiles();
	//ArraylistacceptedFilesList = new ArrayList();
	File[]acceptedFiles = new File[files.length];
	int pos = 0;
	for (File file : files) {
		booleanaccepted = filter.accept(file);
		if (accepted) {
			//acceptedFilesList.add(file);
			acceptedFiles[pos++] = file;
		}
	}
	Arrays.copyOf(acceptedFiles, pos);
	//return(File[])accpetedFilesList.toArray();
}

3、编写一个截取字符串的函数, 输入为一个字符串和字节数, 输出为按字节截
取的字符串, 但要保证汉字不被截取半个, 如"我ABC", 4, 应该截取"我AB", 
输入"我ABC 汉DEF", 6, 应该输出"我ABC", 而不是"我ABC+汉的半个"。
答：
首先要了解中文字符有多种编码及各种编码的特征。
假设n 为要截取的字节数。
public static voidmain(String[]args)throws Exception {
	String str = "我a 爱中华abc 我爱传智def';
	String str =" 我ABC 汉 ";
	int num =trimGBK(str.getBytes(" GBK "),5);
	System.out.println(str.substring(0,num));
}
public staticint trimGBK(byte[]buf, int n) {
	int num = 0;
	booleanbChineseFirstHalf = false;
	for (inti = 0; i < n; i++) {
		if (buf[i] < 0 && !bChineseFirstHalf) {
			bChineseFirstHalf = true;
		} else {
			num++;
			bChineseFirstHalf = false;
		}
	}
	return num;
}

4、有一个字符串, 其中包含中文字符、英文字符和数字字符, 请统计和打印出
各个字符的个数。
答：哈哈, 其实包含中文字符、英文字符、数字字符原来是出题者放的烟雾弹。
String content = "中国aadf 的111萨bbb 菲的zz 萨菲";
HashMap map = new HashMap();
for (int i = 0; i < content.length; i++) {
	char c = content.charAt(i);
	Integer num = map.get(c);
	if (num == null)
		num = 1;
	else
		num = num + 1;
	map.put(c, num);
}
for (Map.EntrySet entry : map) {
	system.out.println(entry.getkey() + " : " + entry.getValue());
}
估计是当初面试的那个学员表述不清楚, 问题很可能是：
如果一串字符如"aaaabbc 中国1512"要分别统计英文字符的数量, 中文字符的数量, 和数字
字符的数量, 假设字符中没有中文字符、英文字符、数字字符之外的其他特殊字符。
int engishCount;
int chineseCount;
int digitCount;
for (int i = 0; i < str.length; i++) {
	charch = str.charAt(i);
	if (ch >= ’0’ && ch <= ’9’) {
		digitCount++
	}
	elseif((ch >= ’a’ && ch <= ’z’) || (ch >= ’A’ && ch <= ’Z’)) {
		engishCount++;
	}
	else {
		chineseCount++;
	}
}
System.out.println(……………);

5、说明生活中遇到的二叉树, 用java 实现二叉树
这是组合设计模式。
我有很多个(假设10万个)数据要保存起来, 以后还需要从保存的这些数据中检索是否存在某
个数据, （我想说出二叉树的好处, 该怎么说呢？那就是说别人的缺点）, 假如存在数组中, 
那么, 碰巧要找的数字位于99999那个地方, 那查找的速度将很慢, 因为要从第1个依次往
后取, 取出来后进行比较。平衡二叉树（构建平衡二叉树需要先排序, 我们这里就不作考虑
了）可以很好地解决这个问题, 但二叉树的遍历（前序, 中序, 后序）效率要比数组低很多, 
原理如下图：
代码如下：
package com.huawei.interview;
publicclass Node {
	public int value;
	public Node left;
	public Node right;
	public void store(intvalue) {
		if (value < this.value) {
			if (left == null) {
				left = new Node();
				left.value = value;
			} else {
				left.store(value);
			}
		} else if (value > this.value) {
			if (right == null) {
				right = new Node();
				right.value = value;
			} else {
				right.store(value);
			}
		}
	}
	public boolean find(intvalue) {
		System.out.println("happen" + this.value);
		if (value == this.value) {
			return true;
		} else if (value > this.value) {
			if (right == null)
				returnfalse;
			return right.find(value);
		} else {
			if (left == null)
				returnfalse;
			return left.find(value);
		}
	}
	public void preList() {
		System.out.print(this.value + ",");
		if (left != null)
			left.preList();
		if (right != null)
			right.preList();
	}
	public void middleList() {
		if (left != null)
			left.preList();
		System.out.print(this.value + ",");
		if (right != null)
			right.preList();
	}
	public void afterList() {
		if (left != null)
			left.preList();
		if (right != null)
			right.preList();
		System.out.print(this.value + ",");
	}
	public static voidmain(String[]args) {
		int[]data = new int[20];
		for (inti = 0; i < data.length; i++) {
			data[i] = (int)(Math.random() * 100) + 1;
			System.out.print(data[i] + ",");
		}
		System.out.println();
		Node root = new Node();
		root.value = data[0];
		for (inti = 1; i < data.length; i++) {
			root.store(data[i]);
		}
		root.find(data[19]);
		root.preList();
		System.out.println();
		root.middleList();
		System.out.println();
		root.afterList();
	}
}
---------------- - 又一次临场写的代码-------------------------- -
importjava.util.Arrays;
importjava.util.Iterator;
public class Node {
	private Node left;
	private Node right;
	private int value;
	//private int num;
	public Node(int value) {
		this.value = value;
	}
	public void add(int value) {
		if (value > this.value) {
			if (right != null)
				right.add(value);
			else {
				Node node = new Node(value);
				right = node;
			}
		} else {
			if (left != null)
				left.add(value);
			else {
				Node node = new Node(value);
				left = node;
			}
		}
	}
	public boolean find(int value) {
		if (value == this.value)
			return true;
		else if (value > this.value) {
			if (right == null)
				return false;
			else
				return right.find(value);
		} else {
			if (left == null)
				return false;
			else
				return left.find(value);
		}
	}
	public void display() {
		System.out.println(value);
		if (left != null)
			left.display();
		if (right != null)
			right.display();
	}
	/*public Iterator iterator(){
	}*/
	public static void main(String[]args) {
		int[]values = new int[8];
		for (int i = 0; i < 8; i++) {
			int num = (int)(Math.random() * 15);
			//System.out.println(num);
			//if(Arrays.binarySearch(values,num)<0)
			if (!contains(values, num))
				values[i] = num;
			else
				i--;
		}
		System.out.println(Arrays.toString(values));
		Node root = new Node(values[0]);
		for (int i = 1; i < values.length; i++) {
			root.add(values[i]);
		}
		System.out.println(root.find(13));
		root.display();
	}
	public static boolean contains(int[]arr, int value) {
		int i = 0;
		for (; i < arr.length; i++) {
			if (arr[i] == value)
				return true;
		}
		return false;
	}
}

6、从类似如下的文本文件中读取出所有的姓名, 并打印出重复的姓名和重复的次数, 并按重复次数排序：
1,张三,28
2,李四,35
3,张三,28
4,王五,35
5,张三,28
6,李四,35
7,赵六,28
8,田七,35
程序代码如下（答题要博得用人单位的喜欢, 包名用该公司, 面试前就提前查好该公司的网
址, 如果查不到, 现场问也是可以的。还要加上实现思路的注释）：
package com.huawei.interview;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeSet;
publicclass GetNameTest {

	public static void main(String[]args) {
		InputStream ips = GetNameTest.class.getResourceAsStream("/com/huawei/interview/info.txt");
		//用上一行注释的代码和下一行的代码都可以, 因为info.txt 与GetNameTest 类在同一包下面, 
		// 所以,可以用下面的相对路径形式
		Map results = new HashMap();
		InputStream ips = GetNameTest.class.getResourceAsStream("info.txt");
		BufferedReader in = new BufferedReader(new InputStreamReader(ips));
		String line = null;
		try {
			while ((line = in.readLine()) != null) {
				dealLine(line, results);
			}
			sortResults(results);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	static class User{
		public String name;
		public Integer value;
		public User(String name, Integervalue) {
			this.name = name;
			this.value = value;
		}
		 @ Override
		public boolean equals(Object obj) {
			//下面的代码没有执行, 说明往treeset 中增加数据时, 不会使用到equals 方
			法。
			boolean result = super.equals(obj);
			System.out.println(result);
			return result;
		}
	}
	private static void sortResults(Map results) {
		TreeSet sortedResults = new TreeSet( new Comparator() {
				public intcompare(Object o1, Object o2) {
					
					User user1 = (User)o1;
					User user2 = (User)o2;
					/*如果compareTo 返回结果0, 则认为两个对象相等, 新的对象不会增加到集合中去
					 * 所以, 不能直接用下面的代码, 否则, 那些个数相同的其他姓名就打印不出来。
					 * */
					//returnuser1.value-user2.value;
					//returnuser1.value<user2.value?-1:user1.value==user2.value?0:1;
					if (user1.value < user2.value) {
						return -1;
					} else if (user1.value > user2.value) {
						return 1;
					} else {
						return user1.name.compareTo(user2.name);
					}
				}
			});
		Iterator iterator = results.keySet().iterator();
		while (iterator.hasNext()) {
			String name = (String)iterator.next();
			Integer value = (Integer)results.get(name);
			if (value > 1) {
				sortedResults.add(new User(name, value));
			}
		}
		printResults(sortedResults);
	}
	private static void printResults(TreeSet sortedResults) {
		Iterator iterator = sortedResults.iterator();
		while (iterator.hasNext()) {
			User user = (User)iterator.next();
			System.out.println(user.name + ":" + user.value);
		}
	}
	public static void dealLine(String line, Map map) {
		if (!"".equals(line.trim())) {
			String[]results = line.split(",");
			if (results.length == 3) {
				String name = results[1];
				Integer value = (Integer)map.get(name);
				if (value == null)
					value = 0;
				map.put(name, value + 1);
			}
		}
	}
}

7、写一个Singleton 出来。
第一种：饱汉模式
public classSingleTon {
	private SingleTon() {}
	//实例化放在静态代码块里可提高程序的执行效率, 但也可能更占用空间
	private final static SingleTon instance = new SingleTon();
	public static SingleTon getInstance() {
		return instance;
	}
}
第二种：饥汉模式
public classSingleTon {
	private SingleTon() {}
	private static instance = null; //newSingleTon();
	public static synchronized SingleTon getInstance() {
		if (instance == null)
			instance = new SingleTon();
		return instance;
	}
}
第三种：用枚举
public enum SingleTon {
	ONE;
}
第三：更实际的应用（在什么情况用单例）
public class SequenceGenerator {
	//下面是该类自身的业务功能代码
	private int count = 0;
	public synchronized int getSequence() {
		++count;
	}
	//下面是把该类变成单例的代码
	private SequenceGenerator() {}
	private final static instance = new SequenceGenerator();
	public static SingleTon getInstance() {
		return instance;
	}
}
第四：
public class MemoryDao{
	private HashMap map = new HashMap();
	publicvoid add(Student stu1) {
		map.put(SequenceGenerator.getInstance().getSequence(), stu1);
	}
	//把MemoryDao 变成单例
}
Singleton 模式主要作用是保证在Java 应用程序中, 一个类Class 只有一个实例存在。
一般Singleton 模式通常有几种种形式:
第一种形式: 定义一个类, 它的构造函数为private 的, 它有一个static 的private 的该类变
量, 在类初始化时实例话, 通过一个public 的getInstance 方法获取对它的引用,继而调用其
中的方法。
public class Singleton {
	private Singleton() {}
	//在自己内部定义自己一个实例, 是不是很奇怪？
	//注意这是private 只供内部调用
	private staticSingleton instance = new Singleton();
	//这里提供了一个供外部访问本class 的静态方法, 可以直接访问
	public staticSingleton getInstance() {
		return instance;
	}
}
第二种形式 :
public class Singleton {
	private static Singleton instance = null;
	public static synchronized Singleton getInstance() {
		//这个方法比上面有所改进, 不用每次都进行生成对象, 只是第一次
		//使用时生成实例, 提高了效率！
		if (instance == null)
			instance＝new Singleton();
		return instance;
	}
}
其他形式:
定义一个类, 它的构造函数为private 的, 所有方法为static 的。
一般认为第一种形式要更加安全些

8、递归算法题1
一个整数, 大于0, 不用循环和本地变量, 按照n, 2n, 4n, 8n 的顺序递增, 当值大于5000
时, 把值按照指定顺序输出来。
例：n=1237
则输出为：
1237, 
2474, 
4948, 
9896, 
9896, 
4948, 
2474, 
1237, 
提示：写程序时, 先致谢按递增方式的代码, 写好递增的以后, 再增加考虑递减部分。
public static void doubleNum(int n) {
	System.out.println(n);
	if (n <= 5000)
		doubleNum(n * 2);
	System.out.println(n);
}
Gaibaota(N) = Gaibaota(N-1) + n;

9、递归算法题2
第1个人10, 第2个比第1个人大2岁, 依次递推, 请用递归方式计算出第8个人多大？
package cn.itcast;
import java.util.Date;
publicclass A1 {
	public static void main(String[]args) {
		System.out.println(computeAge(8));
	}
	public static int computeAge(int n) {
		if (n == 1)
			return 10;
		return computeAge(n - 1) + 2;
	}
}
public static void toBinary(int n, StringBuffer result) {
	if (n / 2 != 0)
		toBinary(n / 2, result);
	result.append(n % 2);
}

10、排序都有哪几种方法？请列举。用JAVA 实现一个快速排序。
本人只研究过冒泡排序、选择排序和快速排序, 下面是快速排序的代码：
public class QuickSort {
	/**
	 * 快速排序
	 * @param strDate
	 * @param left
	 * @param right
	 */
	public void quickSort(String[]strDate, int left, int right) {
		String middle,
		tempDate;
		int i, j;
		i = left;
		j = right;
		middle = strDate[(i + j) / 2];
		do {
			while (strDate[i].compareTo(middle) < 0 && i < right)
				i++; //找出左边比中间值大的数
			while (strDate[j].compareTo(middle) > 0 && j > left)
				j--; //找出右边比中间值小的数
			if (i <= j) { //将左边大的数和右边小的数进行替换
				tempDate = strDate[i];
				strDate[i] = strDate[j];
				strDate[j] = tempDate;
				i++;
				j--;
			}
		} while (i <= j); //当两者交错时停止
		if (i < right) {
			quickSort(strDate, i, right); //从
		}
		if (j > left) {
			quickSort(strDate, left, j);
		}
	}
	/**
	 * @param args
	 */
	public static void main(String[]args) {
		String[] strVoid = new String[]{"11","66","22","0","55","22","0","32"};
		QuickSort sort = new QuickSort();
		sort.quickSort(strVoid, 0, strVoid.length - 1);
		for (int i = 0; i < strVoid.length; i++) {
			System.out.println(strVoid[i] + " ");
		}
	}
}

11、有数组a[n], 用java 代码将数组元素顺序颠倒
//用下面的也可以
//for(inti=0,int j=a.length-1;i<j;i++,j--)是否等效于for(int i=0;i<a.length/2;i++)呢？
importjava.util.Arrays;
public classSwapDemo {
	public static void main(String[]args) {
		int[]a = new int[]{
			(int)(Math.random() * 1000),
			(int)(Math.random() * 1000),
			(int)(Math.random() * 1000),
			(int)(Math.random() * 1000),
			(int)(Math.random() * 1000)
		};
		System.out.println(a);
		System.out.println(Arrays.toString(a));
		swap(a);
		System.out.println(Arrays.toString(a));
	}
	public static void swap(int a[]) {
		int len = a.length;
		for (int i = 0; i < len / 2; i++) {
			int tmp = a[i];
			a[i] = a[len - 1 - i];
			a[len - 1 - i] = tmp;
		}
	}
}

12．金额转换, 阿拉伯数字的金额转换成中国传统的形式如：（￥1011）－>（一
千零一拾一元整）输出。
去零的代码：
returnsb.reverse().toString().replaceAll("零[拾佰仟]","零").replaceAll("零+万","万
").replaceAll("零+元","元").replaceAll("零+","零");
public class RenMingBi {
	/**
	* @param args add by zxx ,Nov 29, 2008
	*/
	private static finalchar[] data = new char[]{
	'零','壹','贰','叁','肆','伍','陆','柒','捌','玖'
	};
	private static finalchar[] units = new char[]{
	'元','拾','佰','仟','万','拾','佰','仟','亿'
	};
	public static voidmain(String[]args) {

		System.out.println(
			convert(135689123));
	}
	public static Stringconvert(int money) {
		StringBuffersbf = new StringBuffer();
		int unit = 0;
		while (money != 0) {
			sbf.insert(0, units[unit++]);
			intnumber = money % 10;
			sbf.insert(0, data[number]);
			money /= 10;
		}
		returnsbf.toString();
	}
}
三. html&JavaScript&ajax 部分
1. 判断第二个日期比第一个日期大
如何用脚本判断用户输入的的字符串是下面的时间格式2004-11-21必须要保证用户
的输入是此格式, 并且是时间, 比如说月份不大于12等等, 另外我需要用户输入两个, 并
且后一个要比前一个晚, 只允许用JAVASCRIPT, 请详细帮助作答, ,
//这里可用正则表达式判断提前判断一下格式, 然后按下提取各时间字段内容
<script type="text/javascript">
window.onload = function () {
	//这么写是为了实现js 代码与html 代码的分离, 当我修改js 时, 不能影响html 代
	码。
	document.getElementById("frm1").onsubmit =
	function () {
		vard1 = this.d1.value;
		vard2 = this.d2.value;
		if (!verifyDate(d1)) {
			alert("第一个日期格式不对");
			return false;
		}
		if (!verifyDate(d2)) {
			alert("第二个日期格式不对");
			return false;
		}
		if (!compareDate(d1, d2)) {
			alert("第二个日期比第一日期小");
			return
			false;
		}
	};
}
functioncompareDate(d1, d2) {
	var arrayD1 = d1.split("-");
	var date1 = new Date(arrayD1[0], arrayD1[1], arrayD1[2]);
	var arrayD2 = d2.split("-");
	var date2 = new Date(arrayD2[0], arrayD2[1], arrayD2[2]);
	if (date1 > date2)
		return false;
	return true;
}
functionverifyDate(d) {
	vardatePattern = /^\d{4}-(0?[1-9]|1[0-2])-(0?[1-9]|[1-2]\d|3[0-1])$/;
	returndatePattern.test(d);
}
</script>
<form id="frm1" action="xxx.html">
<input type="text" name="d1" />
<input type="text" name="d2" />
<input type="submit"/>
</form>
2. 用table 显示n 条记录, 每3 行换一次颜色, 即1, 2, 3 用红色字体, 4, 5, 6 用绿色字体, 7, 8, 9 用红颜色字体。
<body>
<table id="tbl">
<tr><td>1</td></tr>
<tr><td>2</td></tr>
<tr><td>3</td></tr>
<tr><td>4</td></tr>
<tr><td>5</td></tr>
<tr><td>6</td></tr>
<tr><td>7</td></tr>
<tr><td>8</td></tr>
<tr><td>9</td></tr>
<tr><td>10</td></tr>
</table>
</body>
<script type="text/javascript">
window.onload = function () {
	var tbl = document.getElementById("tbl");
	rows = tbl.getElementsByTagName("tr");
	for (i = 0; i < rows.length; i++) {
		var j = parseInt(i / 3);
		if (j % 2 == 0)
			rows[i].style.backgroundColor = "#f00";
		else
			rows[i].style.backgroundColor = "#0f0";
	}
}
</script>

3、HTML 的 form 提交之前如何验证数值文本框的内容全部为数字?否则的话提示用户并终止提交?
<form onsubmit=’return chkForm(this)’>
<input type="text" name="d1"/>
<input type="submit"/>
</form>
<script type="text/javascript" />
function chkForm(this) {
	var value = thist.d1.value;
	var len = value.length;
	for (vari = 0; i < len; i++) {
		if (value.charAt(i) > "9" || value.charAt(i) < "0") {
			alert("含有非数字字符");
			returnfalse;
		}
	}
	return true;
}
</script>

4、请写出用于校验HTML 文本框中输入的内容全部为数字的javascript 代码
<input type="text" id="d1" onblur=" chkNumber(this)"/>
<script type="text/javascript" />
function chkNumber(eleText) {
	var value = eleText.value;
	var len = value.length;
	for (vari = 0; i < len; i++) {
		if (value.charAt(i) > "9" || value.charAt(i) < "0") {
			alert("含有非数字字符");
			eleText.focus();
			break;
		}
	}
}
</script>
除了写完代码, 还应该在网页上写出实验步骤和在代码中加入实现思路, 让面试官一看就明
白你的意图和检查你的结果。

5、说说你用过那些ajax 技术和框架, 说说它们的区别
四. Java web 部分

1、Tomcat 的优化经验
答:去掉对web.xml 的监视, 把jsp 提前编辑成Servlet。
有富余物理内存的情况, 加大tomcat 使用的jvm 的内存

2、HTTP 请求的GET 与POST 方式的区别
答:servlet 有良好的生存期的定义, 包括加载和实例化、初始化、处理请求以及服务结束。
这个生存期由javax.servlet.Servlet 接口的init,service 和destroy 方法表达。

3、解释一下什么是servlet;
答:servlet 有良好的生存期的定义, 包括加载和实例化、初始化、处理请求以及服务结束。
这个生存期由javax.servlet.Servlet 接口的init,service 和destroy 方法表达。

4、说一说Servlet 的生命周期?
答:servlet 有良好的生存期的定义, 包括加载和实例化、初始化、处理请求以及服务结束。
这个生存期由javax.servlet.Servlet 接口的init,service 和destroy 方法表达。
Servlet 被服务器实例化后, 容器运行其init 方法, 请求到达时运行其service 方法, service
方法自动派遣运行与请求对应的doXXX 方法（doGet, doPost）等, 当服务器决定将实例
销毁的时候调用其destroy 方法。
web 容器加载servlet, 生命周期开始。通过调用servlet 的init()方法进行servlet 的初始化。
通过调用service()方法实现, 根据请求的不同调用不同的do***()方法。结束服务, web 容
器调用servlet 的destroy()方法。

5、Servlet 的基本架构
public class ServletName extends HttpServlet {
    public void doPost(HttpServletRequest request,HttpServletResponse response) throws
    ServletException, IOException {
    }
    public void doGet(HttpServletRequest request,HttpServletResponse response) throws
    ServletException, IOException {
    }
}

6、SERVLET API 中forward()与redirect()的区别？
答:前者仅是容器中控制权的转向, 在客户端浏览器地址栏中不会显示出转向后的地址；后
者则是完全的跳转, 浏览器将会得到跳转的地址, 并重新发送请求链接。这样, 从浏览器的
地址栏中可以看到跳转后的链接地址。所以, 前者更加高效, 在前者可以满足需要时, 尽量
使用forward()方法, 并且, 这样也有助于隐藏实际的链接。在有些情况下, 比如, 需要跳
转到一个其它服务器上的资源, 则必须使用
sendRedirect()方法。

7、什么情况下调用doGet()和doPost()？
Jsp 页面中的FORM 标签里的method 属性为get 时调用doGet(), 为post 时调用doPost()。

8、Request 对象的主要方法：
setAttribute(String name,Object)：设置名字为name 的request 的参数值
getAttribute(String name)：返回由name 指定的属性值
getAttributeNames()：返回request 对象所有属性的名字集合, 结果是一个枚举的实例
getCookies()：返回客户端的所有Cookie 对象, 结果是一个Cookie 数组
getCharacterEncoding()：返回请求中的字符编码方式
getContentLength()：返回请求的Body 的长度
getHeader(String name)：获得HTTP 协议定义的文件头信息
getHeaders(String name)：返回指定名字的request Header 的所有值, 结果是一个枚举的
实例
getHeaderNames()：返回所以request Header 的名字, 结果是一个枚举的实例
getInputStream()：返回请求的输入流, 用于获得请求中的数据
getMethod()：获得客户端向服务器端传送数据的方法
getParameter(String name)：获得客户端传送给服务器端的有name 指定的参数值
getParameterNames()：获得客户端传送给服务器端的所有参数的名字, 结果是一个枚举的
实例
getParametervalues(String name)：获得有name 指定的参数的所有值
getProtocol()：获取客户端向服务器端传送数据所依据的协议名称
getQueryString()：获得查询字符串
getRequestURI()：获取发出请求字符串的客户端地址
getRemoteAddr()：获取客户端的IP 地址
getRemoteHost()：获取客户端的名字
getSession([Boolean create])：返回和请求相关Session
getServerName()：获取服务器的名字
getServletPath()：获取客户端所请求的脚本文件的路径
getServerPort()：获取服务器的端口号
removeAttribute(String name)：删除请求中的一个属性

9、forward 和redirect 的区别
forward 是服务器请求资源, 服务器直接访问目标地址的URL, 把那个URL 的响应内容读
取过来, 然后把这些内容再发给浏览器, 浏览器根本不知道服务器发送的内容是从哪儿来的, 
所以它的地址栏中还是原来的地址。
redirect 就是服务端根据逻辑,发送一个状态码,告诉浏览器重新去请求那个地址, 一般来
说浏览器会用刚才请求的所有参数重新请求, 所以session,request 参数都可以获取。

10、request.getAttribute()和 request.getParameter()有何区别?
getParameter 得到的都是String 类型的。或者是http://a.jsp?id=123 中的123, 或者是某个表
单提交过去的数据。
getAttribute 则可以是对象。
getParameter()是获取POST/GET 传递的参数值；
getAttribute()是获取对象容器中的数据值；
getParameter：用于客户端重定向时, 即点击了链接或提交按扭时传值用, 即用于在用表单
或url 重定向传值时接收数据用。
getAttribute：用于服务器端重定向时, 即在sevlet 中使用了forward 函数,或struts 中使用了
mapping.findForward。getAttribute 只能收到程序用setAttribute 传过来的值。
getParameter()是获取POST/GET 传递的参数值；
getAttribute()是获取SESSION 的值；
另外, 可以用setAttribute,getAttribute 发送接收对象.而getParameter 显然只能传字符串。
setAttribute 是应用服务器把这个对象放在该页面所对应的一块内存中去, 当你的页面服务器
重定向到另一个页面时, 应用服务器会把这块内存拷贝另一个页面所对应的内存中。这样
getAttribute 就能取得你所设下的值, 当然这种方法可以传对象。session 也一样, 只是对象
在内存中的生命周期不一样而已。getParameter 只是应用服务器在分析你送上来的request
页面的文本时, 取得你设在表单或url 重定向时的值。
getParameter 返回的是String, 用于读取提交的表单中的值;
getAttribute 返回的是Object, 需进行转换,可用setAttribute 设置成任意对象, 使用很灵活, 
可随时用；

11. jsp 有哪些内置对象?作用分别是什么?分别有什么方法？
答:JSP 共有以下9个内置的对象：
request 用户端请求, 此请求会包含来自GET/POST 请求的参数
response 网页传回用户端的回应
pageContext 网页的属性是在这里管理
session 与请求有关的会话期
application servlet 正在执行的内容
out 用来传送回应的输出
config servlet 的构架部件
page JSP 网页本身
exception 针对错误网页, 未捕捉的例外
request 表示HttpServletRequest 对象。它包含了有关浏览器请求的信息, 并且提供了几个
用于获取cookie, header,和session 数据的有用的方法。
response 表示HttpServletResponse 对象, 并提供了几个用于设置送回浏览器的响应的
方法（如cookies,头信息等）
out 对象是javax.jsp.JspWriter 的一个实例, 并提供了几个方法使你能用于向浏览器回送
输出结果。
pageContext 表示一个javax.servlet.jsp.PageContext 对象。它是用于方便存取各种范
围的名字空间、servlet 相关的对象的API, 并且包装了通用的servlet 相关功能的方法。
session 表示一个请求的javax.servlet.http.HttpSession 对象。Session 可以存贮用户的
状态信息
applicaton 表示一个javax.servle.ServletContext 对象。这有助于查找有关servlet 引擎
和servlet 环境的信息
config 表示一个javax.servlet.ServletConfig 对象。该对象用于存取servlet 实例的初始
化参数。
page 表示从该页面产生的一个servlet 实例

12. jsp 有哪些动作?作用分别是什么?
（这个问题似乎不重要, 不明白为何有此题）
答:JSP 共有以下6种基本动作
jsp:include：在页面被请求的时候引入一个文件。
jsp:useBean：寻找或者实例化一个JavaBean。
jsp:setProperty：设置JavaBean 的属性。
jsp:getProperty：输出某个JavaBean 的属性。
jsp:forward：把请求转到一个新的页面。
jsp:plugin：根据浏览器类型为Java 插件生成OBJECT 或EMBED 标记

13、JSP 的常用指令
isErrorPage(是否能使用Exception 对象), isELIgnored(是否忽略表达式)

14. JSP 中动态INCLUDE 与静态INCLUDE 的区别？
答：动态INCLUDE 用jsp:include 动作实现
<jsp:include page=included.jsp flush=true />它总是会检查所含文件中的变化, 适合用于包
含动态页面, 并且可以带参数 静态INCLUDE 用include 伪码实现,定不会检查所含文件的
变化, 适用于包含静态页面 <%@include file=included.htm %>

15、两种跳转方式分别是什么?有什么区别?
（下面的回答严重错误, 应该是想问forward 和sendRedirect 的区别, 毕竟出题的人不是
专业搞文字艺术的人, 可能表达能力并不见得很强, 用词不一定精准, 加之其自身的技术面
也可能存在一些问题, 不一定真正将他的意思表达清楚了, 严格意思上来讲, 一些题目可能
根本就无人能答, 所以, 答题时要掌握主动, 只要把自己知道的表达清楚就够了, 而不要去
推敲原始题目的具体含义是什么, 不要一味想着是在答题）
答：有两种, 分别为：
<jsp:include page=included.jsp flush=true>
<jsp:forward page= nextpage.jsp/>
前者页面不会转向include 所指的页面, 只是显示该页的结果, 主页面还是原来的页面。执
行完后还会回来, 相当于函数调用。并且可以带参数.后者完全转向新页面, 不会再回来。
相当于go to 语句。

16、页面间对象传递的方法
request, session, application, cookie 等

17、JSP 和Servlet 有哪些相同点和不同点, 他们之间的联系是什么？
JSP 是Servlet 技术的扩展, 本质上是Servlet 的简易方式, 更强调应用的外表表达。JSP
编译后是"类servlet"。Servlet 和JSP 最主要的不同点在于, Servlet 的应用逻辑是在Java
文件中, 并且完全从表示层中的HTML 里分离开来。而JSP 的情况是Java 和HTML 可以
组合成一个扩展名为.jsp 的文件。JSP 侧重于视图, Servlet 主要用于控制逻辑。

18、MVC 的各个部分都有那些技术来实现?如何实现?
答:MVC 是Model－View－Controller 的简写。Model 代表的是应用的业务逻辑（通过
JavaBean, EJB 组件实现）, View 是应用的表示面（由JSP 页面产生）, Controller 是提供
应用的处理过程控制（一般是一个Servlet）, 通过这种设计模型把应用逻辑, 处理过程和显
示逻辑分成不同的组件实现。这些组件可以进行交互和重用。

19、我们在web 应用开发过程中经常遇到输出某种编码的字符, 如iso8859-1等, 如何输出一个某种编码的字符串？
Public String translate(String str) {
	String tempStr = "";
	try {
		tempStr = new String(str.getBytes("ISO-8859-1"), "GBK");
		tempStr = tempStr.trim();
	} catch (Exception e) {
		System.err.println(e.getMessage());
	}
	return tempStr;
}

20、现在输入n 个数字, 以逗号, 分开；然后可选择升或者降序排序；按提交键就在另一页面显示按什么排序, 结果为, 提供reset

21、注册Jdbc 驱动程序的三种方式

22、用JDBC 如何调用存储过程
代码如下：
package com.huawei.interview.lym;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;
publicclass JdbcTest {
    public static voidmain(String[] args) {
        Connection cn = null;
        CallableStatement cstmt =null;
        try {
//这里最好不要这么干, 因为驱动名写死在程序中了
            Class.forName("com.mysql.jdbc.Driver");
//实际项目中, 这里应用DataSource 数据, 如果用框架, 
//这个数据源不需要我们编码创建, 我们只需Datasource ds =context.lookup()
//cn = ds.getConnection();
            cn = DriverManager.getConnection("jdbc:mysql:///test","root","root");
            cstmt = cn.prepareCall("{callinsert_Student(?,?,?)}");
            cstmt.registerOutParameter(3,Types.INTEGER);
            cstmt.setString(1,"wangwu");
            cstmt.setInt(2, 25);
            cstmt.execute();
//get 第几个, 不同的数据库不一样, 建议不写
            System.out.println(cstmt.getString(3));
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            /*try{cstmt.close();}catch(Exceptione){}
            try{cn.close();}catch(Exceptione){}*/
            try {
                if(cstmt !=null)
                    cstmt.close();
                if(cn !=null)
                    cn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}

23、JDBC 中的PreparedStatement 相比Statement 的好处
答：一个sql 命令发给服务器去执行的步骤为：语法检查, 语义分析, 编译成内部指令, 缓存指令, 执行指令等过程。
select * from student where id =3----缓存--àxxxxx 二进制命令
select * from student where id =3----直接取-àxxxxx 二进制命令
select * from student where id =4--- -à 会怎么干？
如果当初是select * from student where id =?--- -à 又会怎么干？
上面说的是性能提高
可以防止sql 注入。

24. 写一个用jdbc 连接并访问oracle 数据的程序代码

25、Class.forName 的作用?为什么要用?
答：按参数中指定的字符串形式的类名去搜索并加载相应的类, 如果该类字节码已经被加载过, 
则返回代表该字节码的Class 实例对象, 否则, 按类加载器的委托机制去搜索和加载该类, 
如果所有的类加载器都无法加载到该类, 则抛出ClassNotFoundException。加载完这个Class 字节码后, 
接着就可以使用Class 字节码的new Instance 方法去创建该类的实例对象了。
有时候, 我们程序中所有使用的具体类名在设计时（即开发时）无法确定, 只有程序运行时才能确定, 
这时候就需要使用Class.forName 去动态加载该类, 这个类名通常是在配置文件中配置的, 
例如, spring 的ioc 中每次依赖注入的具体类就是这样配置的, jdbc 的驱动类
名通常也是通过配置文件来配置的, 以便在产品交付使用后不用修改源程序就可以更换驱动类名。

26、大数据量下的分页解决方法。
答：最好的办法是利用sql 语句进行分页, 这样每次查询出的结果集中就只包含某页的数据内容。
再sql 语句无法实现分页的情况下, 可以考虑对大的结果集通过游标定位方式来获取某页的数据。
sql 语句分页, 不同的数据库下的分页方案各不一样, 下面是主流的三种数据库的分页sql：
sql server:
String sql =
	"select top" + pageSize + " * from students where id not in" +
	"(select top " + pageSize * (pageNumber - 1) + " id from students order by id)" +
	"order by id";
mysql :
String sql =
	"select * fromstudents order by id limit " + pageSize * (pageNumber - 1) + ","
	+pageSize;
oracle :
String sql =
	"select * from " +
	(select * , rownum rid from(select * fromstudents order by postime desc)where
		rid <= " + pagesize*pagenumber +")as t " +
	" where t > " +pageSize*(pageNumber-1);

27、用 JDBC 查询学生成绩单,把主要代码写出来（考试概率极大）.
Connection cn = null;
PreparedStatement pstmt =null;
Resultset rs = null;
try {
    Class.forname(driveClassName);
    cn = DriverManager.getConnection(url,username,password);
    pstmt =cn.prepareStatement("select score.* fromscore ,student " +
                               "wherescore.stuId = student.id and student.name = ?");
    pstmt.setString(1,studentName);
    Resultset rs =pstmt.executeQuery();
    while(rs.next()) {
        system.out.println(rs.getInt("subject") + " " +
                           rs.getFloat("score") );
    }
} catch(Exception e) {
    e.printStackTrace();
}
finally {
    if(rs != null) try{rs.close() }
        catch(exception e) {}
    if(pstmt != null)try{pstmt.close()}
        catch(exception e) {}
    if(cn != null) try{cn.close() }
        catch(exception e) {} 
}

28、这段代码有什么不足之处?
try {
    Connection conn = ...;
    Statement stmt = ...;
    ResultSet rs =stmt.executeQuery("select * from table1");
    while(rs.next()) {
    }
} catch(Exception ex) {
}
答：没有finally 语句来关闭各个对象, 另外, 使用finally 之后, 要把变量的定义放在try 语
句块的外面, 以便在try 语句块之外的finally 块中仍可以访问这些变量。

29、说出数据连接池的工作机制是什么?
J2EE 服务器启动时会建立一定数量的池连接, 并一直维持不少于此数目的池连接。客户端程序需要连接时, 
池驱动程序会返回一个未使用的池连接并将其表记为忙。如果当前没有空闲连接, 池驱动程序就新建一定数量的连接, 
新建连接的数量有配置参数决定。当使用的池连接调用完成后, 池驱动程序将此连接表记为空闲, 
其他调用就可以使用这个连接。实现方式, 返回的Connection 是原始Connection 的代理, 
代理Connection 的close 方法不是真正关连接, 而是把它代理的Connection 对象还回到连接池中。

30、为什么要用 ORM? 和 JDBC 有何不一样?
orm 是一种思想, 就是把object 转变成数据库中的记录, 或者把数据库中的记录转变成objecdt, 
我们可以用jdbc 来实现这种思想, 其实, 如果我们的项目是严格按照oop 方式编写的话, 
我们的jdbc 程序不管是有意还是无意, 就已经在实现orm 的工作了。
现在有许多orm 工具, 它们底层调用jdbc 来实现了orm 工作, 我们直接使用这些工具, 就
省去了直接使用jdbc 的繁琐细节, 提高了开发效率, 现在用的较多的orm 工具是hibernate。
也听说一些其他orm 工具, 如toplink,ojb 等。
六. XML 部分

1、xml 有哪些解析技术?区别是什么?
答:有DOM,SAX,STAX 等
DOM:处理大型文件时其性能下降的非常厉害。这个问题是由DOM 的树结构所造成的, 这
种结构占用的内存较多, 而且DOM 必须在解析文件之前把整个文档装入内存,适合对XML
的随机访问SAX:不现于DOM,SAX 是事件驱动型的XML 解析方式。它顺序读取XML 文件, 
不需要一次全部装载整个文件。当遇到像文件开头, 文档结束, 或者标签开头与标签结束时, 
它会触发一个事件, 用户通过在其回调事件中写入处理代码来处理XML 文件, 适合对XML
的顺序访问
STAX:Streaming API for XML (StAX)
讲解这些区别是不需要特别去比较, 就像说传智播客与其他培训机构的区别时, 我们只需说
清楚传智播客有什么特点和优点就行了, 这就已经间接回答了彼此的区别。

2、你在项目中用到了xml 技术的哪些方面?如何实现的?
答:用到了数据存贮, 信息配置两方面。在做数据交换平台时, 将不能数据源的数据组装成
XML 文件, 然后将XML 文件压缩打包加密后通过网络传送给接收者, 接收解密与解压缩后
再同XML 文件中还原相关信息进行处理。在做软件配置时, 利用XML 可以很方便的进行, 
软件的各种配置参数都存贮在XML 文件中。

3、用jdom 解析xml 文件时如何解决中文问题?如何解析?
答:看如下代码,用编码方式加以解决
package test;
import java.io.*;
public class DOMTest {
    private String inFile = "c:\\people.xml"
                            private String outFile = "c:\\people.xml"
    public static void main(String args[]) {
        new DOMTest();
    }
    public DOMTest() {
        try {
            javax.xml.parsers.DocumentBuilder builder =
                javax.xml.parsers.DocumentBuilderFactory.newInstance().newDocumentBuilder();
            org.w3c.dom.Document doc = builder.newDocument();
            org.w3c.dom.Element root = doc.createElement("老师");
            org.w3c.dom.Element wang = doc.createElement("王");
            org.w3c.dom.Element liu = doc.createElement("刘");
            wang.appendChild(doc.createTextNode("我是王老师"));
            root.appendChild(wang);
            doc.appendChild(root);
            javax.xml.transform.Transformer transformer =
                javax.xml.transform.TransformerFactory.newInstance().newTransformer();
            transformer.setOutputProperty(javax.xml.transform.OutputKeys.ENCODING,"gb2312");
            transformer.setOutputProperty(javax.xml.transform.OutputKeys.INDENT,"yes");
            transformer.transform(newjavax.xml.transform.dom.DOMSource(doc),
                                  new
                                  javax.xml.transform.stream.StreamResult(outFile));
        } catch (Exception e) {
            System.out.println (e.getMessage());
        }
    }
}

4、编程用JAVA 解析XML 的方式.
答:用SAX 方式解析XML, XML 文件如下：
<?xml version=1.0 encoding=gb2312?>
<person>
    <name>王小明</name>
    <college>信息学院</college>
    <telephone>6258113</telephone>
    <notes>男,1955年生,博士, 95年调入海南大学</notes>
</person>
事件回调类SAXHandler.java
import java.io.*;
import java.util.Hashtable;
import org.xml.sax.*;
public class SAXHandler extends HandlerBase {
    private Hashtable table = new Hashtable();
    private String currentElement = null;
    private String currentValue = null;
    public void setTable(Hashtable table) {
        this.table = table;
    }
    public Hashtable getTable() {
        return table;
    }
    public void startElement(String tag, AttributeList attrs)
    throws SAXException {
        currentElement = tag;
    }
    public void characters(char[] ch, int start, int length)
    throws SAXException {
        currentValue = new String(ch, start, length);
    }
    public void endElement(String name) throws SAXException {
        if (currentElement.equals(name))
            table.put(currentElement, currentValue);
    }
}
JSP 内容显示源码,SaxXml.jsp:
<HTML>
<HEAD>
<TITLE>剖析XML 文件people.xml</TITLE>
</HEAD>
<BODY>
<%@ page errorPage=ErrPage.jsp
contentType=text/html;charset=GB2312 %>
<%@ page import=java.io.* %>
<%@ page import=java.util.Hashtable %>
<%@ page import=org.w3c.dom.* %>
<%@ page import=org.xml.sax.* %>
<%@ page import=javax.xml.parsers.SAXParserFactory %>
<%@ page import=javax.xml.parsers.SAXParser %>
<%@ page import=SAXHandler %>
<%
File file = new File(c:\people.xml);
FileReader reader = new FileReader(file);
Parser parser;
SAXParserFactory spf = SAXParserFactory.newInstance();
SAXParser sp = spf.newSAXParser();
SAXHandler handler = new SAXHandler();
sp.parse(new InputSource(reader), handler);
Hashtable hashTable = handler.getTable();
out.println(<TABLE BORDER=2><CAPTION>教师信息表</CAPTION>);
out.println(<TR><TD>姓名</TD> + <TD> +
(String)hashTable.get(new String(name)) +</TD></TR>);
out.println(<TR><TD>学院</TD> + <TD> +
(String)hashTable.get(newString(college))+</TD></TR>);
out.println(<TR><TD>电话</TD> + <TD> +
(String)hashTable.get(new String(telephone)) +</TD></TR>);
out.println(<TR><TD>备注</TD> + <TD> +
(String)hashTable.get(new String(notes)) +</TD></TR>);
out.println(</TABLE>);
%>
</BODY>
</HTML>

5、XML 文档定义有几种形式？它们之间有何本质区别？解析XML 文档有哪几种方式？
a: 两种形式 dtd schema, b:本质区别:schema 本身是xml 的, 可以被XML 解析器解析(这
也是从DTD 上发展schema 的根本目的), c:有DOM,SAX,STAX 等
DOM:处理大型文件时其性能下降的非常厉害。这个问题是由DOM 的树结构所造成的, 
这种结构占用的内存较多, 而且DOM必须在解析文件之前把整个文档装入内存,适合对XML
的随机访问
SAX:不现于DOM,SAX 是事件驱动型的XML 解析方式。它顺序读取XML 文件, 不需要一
次全部装载整个文件。当遇到像文件开头, 文档结束, 或者标签开头与标签结束时, 它会触
发一个事件, 用户通过在其回调事件中写入处理代码来处理XML 文件, 适合对XML 的顺
序访问
STAX:Streaming API forXML (StAX)
七.流行的框架与新技术

1、谈谈你对Struts 的理解。
答:
1. struts 是一个按MVC 模式设计的Web 层框架, 其实它就是一个大大的servlet, 这个
Servlet 名为ActionServlet, 或是ActionServlet 的子类。我们可以在web.xml 文件中将符
合某种特征的所有请求交给这个Servlet 处理, 这个Servlet 再参照一个配置文件（通常为
/WEB-INF/struts-config.xml）将各个请求分别分配给不同的action 去处理。
一个扩展知识点：struts 的配置文件可以有多个, 可以按模块配置各自的配置文件, 这样可
以防止配置文件的过度膨胀；
2.ActionServlet 把请求交给action 去处理之前, 会将请求参数封装成一个formbean 对象（就是一个java 类, 这个类中的每个属性对应一个请求参数）, 封装成一个什么样的formbean对象呢？看配置文件。
3.要说明的是,  ActionServlet 把formbean 对象传递给action 的execute 方法之前, 可能
会调用formbean 的validate 方法进行校验, 只有校验通过后才将这个formbean 对象传递
给action 的execute 方法, 否则, 它将返回一个错误页面, 这个错误页面由input 属性指定, 
（看配置文件）作者为什么将这里命名为input 属性, 而不是error 属性, 我们后面结合实
际的运行效果进行分析。
4.action 执行完后要返回显示的结果视图, 这个结果视图是用一个ActionForward 对象来表
示的, actionforward 对象通过struts-config.xml 配置文件中的配置关联到某个jsp 页面, 因
为程序中使用的是在struts-config.xml 配置文件为jsp 页面设置的逻辑名, 这样可以实现
action 程序代码与返回的jsp 页面名称的解耦。
你对struts 可能还有自己的应用方面的经验, 那也要一并说出来。

2、谈谈你对Hibernate 的理解。
答:
1. 面向对象设计的软件内部运行过程可以理解成就是在不断创建各种新对象、建立对象之
间的关系, 调用对象的方法来改变各个对象的状态和对象消亡的过程, 不管程序运行的过程
和操作怎么样, 本质上都是要得到一个结果, 程序上一个时刻和下一个时刻的运行结果的差
异就表现在内存中的对象状态发生了变化。
2.为了在关机和内存空间不够的状况下, 保持程序的运行状态, 需要将内存中的对象状态保
存到持久化设备和从持久化设备中恢复出对象的状态, 通常都是保存到关系数据库来保存大
量对象信息。从Java 程序的运行功能上来讲, 保存对象状态的功能相比系统运行的其他功
能来说, 应该是一个很不起眼的附属功能, java 采用jdbc 来实现这个功能, 这个不起眼的
功能却要编写大量的代码, 而做的事情仅仅是保存对象和恢复对象, 并且那些大量的jdbc
代码并没有什么技术含量, 基本上是采用一套例行公事的标准代码模板来编写, 是一种苦活
和重复性的工作。
3.通过数据库保存java 程序运行时产生的对象和恢复对象, 其实就是实现了java 对象与关
系数据库记录的映射关系, 称为ORM（即Object RelationMapping）, 人们可以通过封装
JDBC 代码来实现了这种功能, 封装出来的产品称之为ORM 框架, Hibernate 就是其中的
一种流行ORM 框架。使用Hibernate 框架, 不用写JDBC 代码, 仅仅是调用一个save 方
法, 就可以将对象保存到关系数据库中, 仅仅是调用一个get 方法, 就可以从数据库中加载
出一个对象。
4.使用Hibernate 的基本流程是：配置Configuration 对象、产生SessionFactory、创建
session 对象, 启动事务, 完成CRUD 操作, 提交事务, 关闭session。
5.使用Hibernate 时, 先要配置hibernate.cfg.xml 文件, 其中配置数据库连接信息和方言等, 
还要为每个实体配置相应的hbm.xml 文件, hibernate.cfg.xml 文件中需要登记每个hbm.xml
文件。
6.在应用Hibernate 时, 重点要了解Session 的缓存原理, 级联, 延迟加载和hql 查询。

3、AOP 的作用。

4、你对Spring 的理解。
1.Spring 实现了工厂模式的工厂类（在这里有必要解释清楚什么是工厂模式）, 这个类名为
BeanFactory（实际上是一个接口）, 在程序中通常BeanFactory 的子类ApplicationContext。
Spring 相当于一个大的工厂类, 在其配置文件中通过<bean>元素配置用于创建实例对象的
类名和实例对象的属性。
2. Spring 提供了对IOC 良好支持, IOC 是一种编程思想, 是一种架构艺术, 利用这种思想
可以很好地实现模块之间的解耦。IOC 也称为DI（Depency Injection）, 什么叫依赖注入呢？
譬如, Class Programmer {
    Computer computer =null;
    public void code() {
//Computer computer = new IBMComputer();
//Computer computer = beanfacotry.getComputer();
        computer.write();
    }
    public void setComputer(Computer computer) {
        this.computer= computer;
    }
}
另外两种方式都由依赖, 第一个直接依赖于目标类, 第二个把依赖转移到工厂上, 第三个彻
底与目标和工厂解耦了。在spring 的配置文件中配置片段如下：
<bean id="computer" class="cn.itcast.interview.Computer">
</bean>
<bean id="programmer" class="cn.itcast.interview.Programmer">
<property name="computer" ref="computer"></property> 
</bean>
3. Spring 提供了对AOP 技术的良好封装,  AOP 称为面向切面编程, 就是系统中有很多各
不相干的类的方法, 在这些众多方法中要加入某种系统功能的代码, 例如, 加入日志, 加入
权限判断, 加入异常处理, 这种应用称为AOP。实现AOP 功能采用的是代理技术, 客户端
程序不再调用目标, 而调用代理类, 代理类与目标类对外具有相同的方法声明, 有两种方式
可以实现相同的方法声明, 一是实现相同的接口, 二是作为目标的子类在, JDK 中采用Proxy
类产生动态代理的方式为某个接口生成实现类, 如果要为某个类生成子类, 则可以用CGLI
B。在生成的代理类的方法中加入系统功能和调用目标类的相应方法, 系统功能的代理以
Advice 对象进行提供, 显然要创建出代理对象, 至少需要目标类和Advice 类。spring 提供
了这种支持, 只需要在spring 配置文件中配置这两个元素即可实现代理和aop 功能, 例如, 
<bean id="proxy" type="org.spring.framework.aop.ProxyBeanFactory">
<property name="target"ref=""></property>
<property name="advisor"ref=""></property>
</bean>

5、谈谈Struts 中的Action servlet。

6、Struts 优缺点
优点：
1. 实现MVC 模式, 结构清晰,使开发者只关注业务逻辑的实现.
2．有丰富的tag 可以用 ,Struts 的标记库(Taglib), 如能灵活动用, 则能大大提高开发效率
3. 页面导航
使系统的脉络更加清晰。通过一个配置文件, 即可把握整个系统各部分之间的联系, 
这对于后期的维护有着莫大的好处。尤其是当另一批开发者接手这个项目时, 这种优势体现
得更加明显。
4. 提供Exception 处理机制 .
5. 数据库链接池管理
6. 支持I18N
缺点
一、 转到展示层时, 需要配置forward, 如果有十个展示层的jsp, 需要配置十次struts, 
而且还不包括有时候目录、文件变更, 需要重新修改forward, 注意, 每次修改配置之后, 
要求重新部署整个项目, 而tomcate 这样的服务器, 还必须重新启动服务器
二、 二、 Struts 的Action 必需是thread－safe 方式, 它仅仅允许一个实例去处理所有
的请求。所以action 用到的所有的资源都必需统一同步, 这个就引起了线程安全的问题。
三、 测试不方便. Struts 的每个Action 都同Web 层耦合在一起, 这样它的测试依赖于
Web 容器, 单元测试也很难实现。不过有一个Junit 的扩展工具Struts TestCase 可以实现
它的单元测试。
四、 类型的转换. Struts 的FormBean 把所有的数据都作为String 类型, 它可以使用工
具Commons-Beanutils 进行类型转化。但它的转化都是在Class 级别, 而且转化的类型是
不可配置的。类型转化时的错误信息返回给用户也是非常困难的。
五、 对Servlet 的依赖性过强. Struts 处理Action 时必需要依赖ServletRequest 和
ServletResponse, 所有它摆脱不了Servlet 容器。
六、 前端表达式语言方面.Struts 集成了JSTL, 所以它主要使用JSTL 的表达式语言来
获取数据。可是JSTL 的表达式语言在Collection 和索引属性方面处理显得很弱。
七、 对Action 执行的控制困难. Struts 创建一个Action, 如果想控制它的执行顺序将会
非常困难。甚至你要重新去写Servlet 来实现你的这个功能需求。
八、 对Action 执行前和后的处理. Struts 处理Action 的时候是基于class 的hierarchies, 
很难在action 处理前和后进行操作。
九、 对事件支持不够.在struts 中, 实际是一个表单Form 对应一个Action 类(或DispatchAction), 
换一句话说：在Struts 中实际是一个表单只能对应一个事件, struts 这种事件方式称为
application event, application event 和component event 相比是一种粗粒度的事件

7、STRUTS 的应用(如STRUTS 架构)
Struts 是采用Java Servlet/JavaServer Pages 技术, 开发Web 应用程序的开放源码的
framework。采用Struts 能开发出基于MVC(Model-View-Controller)设计模式的应用构架。
Struts 有如下的主要功能：一.包含一个controller servlet, 能将用户的请求发送到相应的
Action 对象。二.JSP 自由tag 库, 并且在controller servlet 中提供关联支持, 帮助开发员
创建交互式表单应用。三.提供了一系列实用对象：XML 处理、通过Java reflection APIs 自
动处理JavaBeans 属性、国际化的提示和消息。

8、说说struts1 与struts2 的区别。
1.都是MVC 的WEB 框架,
2 struts1的老牌框架, 应用很广泛, 有很好的群众基础, 使用它开发风险很小, 成本更低！
struts2虽然基于这个框架, 但是应用群众并多, 相对不成熟, 未知的风险和变化很多, 开发
人员相对不好招, 使用它开发项目的风险系数更大, 用人成本更高！
3.struts2毕竟是站在前辈的基础设计出来, 它会改善和完善struts1中的一些缺陷, struts1
中一些悬而未决问题在struts2得到了解决。
4.struts1的前端控制器是一个Servlet, 名称为ActionServlet, struts2的前端控制器是一个
filter, 在struts2.0中叫FilterDispatcher, 在struts2.1中叫StrutsPrepareAndExecuteFilter。
5.struts1的action 需要继承Action 类, struts2的action 可以不继承任何类；struts1对同一
个路径的所有请求共享一个Action 实例, struts2对同一个路径的每个请求分别使用一个独
立Action 实例对象, 所有对于struts2的Action 不用考虑线程安全问题。
6.在struts1中使用formbean 封装请求参数, 在struts2中直接使用action 的属性来封装请
求参数。
7.struts1中的多个业务方法放在一个Action 中时（即继承DispatchAction 时）, 要么都校验, 
要么都不校验；对于struts2, 可以指定只对某个方法进行校验, 当一个Action 继承了
ActionSupport 且在这个类中只编写了validateXxx()方法, 那么则只对Xxx()方法进行校验。
（一个请求来了的执行流程进行分析, struts2是自动支持分模块开发, 并可以不同模块设置
不同的url 前缀, 这是通过package 的namespace 来实现的；struts2是支持多种类型的视
图；struts2的视图地址可以是动态的, 即视图的名称是支持变量方式的, 举例, 论坛发帖失
败后回来还要传递boardid。视图内容显示方面：它的标签用ognl, 要el 强大很多, 在国际
化方面支持分模块管理, 两个模块用到同样的key, 对应不同的消息；）
与Struts1不同, Struts2对用户的每一次请求都会创建一个Action, 所以Struts2中的Action
是线程安全的。
给我印象最深刻的是：struts 配置文件中的redirect 视图的url 不能接受参数, 而struts2配
置文件中的redirect 视图可以接受参数。

9、hibernate 中的update()和saveOrUpdate()的区别, session 的load()和get()的区别。

10、简述 Hibernate 和 JDBC 的优缺点?如何书写一个 one to many 配置文件.

11、iBatis 与Hibernate 有什么不同?
相同点：屏蔽jdbc api 的底层访问细节, 使用我们不用与jdbc api 打交道, 就可以访问数据。
jdbc api 编程流程固定, 还将sql 语句与java 代码混杂在了一起, 经常需要拼凑sql 语句, 
细节很繁琐。
ibatis 的好处：屏蔽jdbc api 的底层访问细节；将sql 语句与java 代码进行分离;提供了将结
果集自动封装称为实体对象和对象的集合的功能, queryForList 返回对象集合, 用
queryForObject 返回单个对象；提供了自动将实体对象的属性传递给sql 语句的参数。
Hibernate 是一个全自动的orm 映射工具, 它可以自动生成sql 语句,ibatis 需要我们自己在
xml 配置文件中写sql 语句, hibernate 要比ibatis 功能负责和强大很多。因为hibernate 自
动生成sql 语句, 我们无法控制该语句, 我们就无法去写特定的高效率的sql。对于一些不
太复杂的sql 查询, hibernate 可以很好帮我们完成, 但是, 对于特别复杂的查询, hibernate
就很难适应了, 这时候用ibatis 就是不错的选择, 因为ibatis 还是由我们自己写sql 语句。

12、写Hibernate 的一对多和多对一双向关联的orm 配置?
12.1、hibernate 的inverse 属性的作用?
解决方案一, 按照Object[]数据取出数据, 然后自己组bean
解决方案二, 对每个表的bean 写构造函数, 比如表一要查出field1,field2两个字段, 那么有
一个构造函数就是Bean(type1filed1,type2
field2) , 然后在hql 里面就可以直接生成这个bean 了。

13、在DAO 中如何体现DAO 设计模式?
解决方案一, 按照Object[]数据取出数据, 然后自己组bean
解决方案二, 对每个表的bean 写构造函数, 比如表一要查出field1,field2两个字段, 那么有
一个构造函数就是Bean(type1filed1,type2
field2) , 然后在hql 里面就可以直接生成这个bean 了。

14、spring+Hibernate 中委托方案怎么配置?
解决方案一, 按照Object[]数据取出数据, 然后自己组bean
解决方案二, 对每个表的bean 写构造函数, 比如表一要查出field1,field2两个字段, 那么有
一个构造函数就是Bean(type1filed1,type2
field2) , 然后在hql 里面就可以直接生成这个bean 了。

15、spring+Hibernate 中委托方案怎么配置?
解决方案一, 按照Object[]数据取出数据, 然后自己组bean
解决方案二, 对每个表的bean 写构造函数, 比如表一要查出field1,field2两个字段, 那么有
一个构造函数就是Bean(type1filed1,type2
field2) , 然后在hql 里面就可以直接生成这个bean 了。
16. hibernate 进行多表查询每个表中各取几个字段, 也就是说查询出来的结果集没有一个实体类与之对应如何解决；
解决方案一, 按照Object[]数据取出数据, 然后自己组bean
解决方案二, 对每个表的bean 写构造函数, 比如表一要查出field1,field2两个字段, 那么有
一个构造函数就是Bean(type1filed1,type2field2) , 然后在hql 里面就可以直接生成这个bean 了。
17.介绍一下Hibernate 的二级缓存
按照以下思路来回答：
（1）首先说清楚什么是缓存, 
（2）再说有了hibernate 的Session 就是一级缓存, 即有了一级缓存, 为什么还要有二级缓存, 
（3）最后再说如何配置Hibernate的二级缓存。
（1）缓存就是把以前从数据库中查询出来和使用过的对象保存在内存中（一个数据结构中）, 
这个数据结构通常是或类似Hashmap, 当以后要使用某个对象时, 先查询缓存中是否有这个对象, 
如果有则使用缓存中的对象, 如果没有则去查询数据库, 并将查询出来的对象保存在缓存中, 
以便下次使用。下面是缓存的伪代码：
引出hibernate 的第二级缓存, 用下面的伪代码分析了Cache 的实现原理
Dao {
    hashmap map = newmap();
    User getUser(integer id) {
        User user =map.get(id)
        if(user ==null) {
            user =session.get(id);
            map.put(id,user);
        }
        return user;
    }
}
Dao {
    Cache cache = null
    setCache(Cache cache) {
        this.cache =cache
    }
    User getUser(int id) {
        if(cache!=null) {
            User user = cache.get(id);
            if(user==null) {
                user= session.get(id);
                cache.put(id,user);
            }
            return user;
        }
        return session.get(id);
    }
}
（2）Hibernate 的Session 就是一种缓存, 我们通常将之称为Hibernate 的一级缓存, 当想
使用session 从数据库中查询出一个对象时, Session 也是先从自己内部查看是否存在这个
对象, 存在则直接返回, 不存在才去访问数据库, 并将查询的结果保存在自己内部。由于 
Session 代表一次会话过程, 一个Session 与一个数据库连接相关连, 所以Session 最好不
要长时间保持打开, 通常仅用于一个事务当中, 在事务结束时就应关闭。并且Session 是
线程不安全的, 被多个线程共享时容易出现问题。通常只有那种全局意义上的缓存才是真正
的缓存应用, 才有较大的缓存价值, 因此, Hibernate 的Session 这一级缓存的缓存作用并
不明显, 应用价值不大。Hibernate 的二级缓存就是要为Hibernate 配置一种全局缓存, 让
多个线程和多个事务都可以共享这个缓存。我们希望的是一个人使用过, 其他人也可以使用, 
session 没有这种效果。
（3）二级缓存是独立于Hibernate 的软件部件, 属于第三方的产品, 多个厂商和组织都提
供有缓存产品, 例如, EHCache 和OSCache 等等。在Hibernate 中使用二级缓存, 首先
就要在hibernate.cfg.xml 配置文件中配置使用哪个厂家的缓存产品, 接着需要配置该缓存
产品自己的配置文件, 最后要配置Hibernate 中的哪些实体对象要纳入到二级缓存的管理
中。明白了二级缓存原理和有了这个思路后, 很容易配置起Hibernate 的二级缓存。扩展知
识：一个SessionFactory 可以关联一个二级缓存, 也即一个二级缓存只能负责缓存一个数
据库中的数据, 当使用Hibernate 的二级缓存后, 注意不要有其他的应用或SessionFactory
来更改当前数据库中的数据, 这样缓存的数据就会与数据库中的实际数据不一致。

18、Spring 的依赖注入是什么意思?给一个 Bean 的 message 属性,字符串类型,注入值为"Hello"的 XML 配置文件该怎么写?

19、Jdo 是什么?
JDO 是Java 对象持久化的新的规范, 为java data object 的简称,也是一个用于存取某种数
据仓库中的对象的标准化API。JDO 提供了透明的对象存储, 因此对开发人员来说, 存储
数据对象完全不需要额外的代码（如JDBC API 的使用）。这些繁琐的例行工作已经转移到
JDO 产品提供商身上, 使开发人员解脱出来, 从而集中时间和精力在业务逻辑上。另外, 
JDO 很灵活, 因为它可以在任何数据底层上运行。JDBC 只是面向关系数据库（RDBMS）
JDO 更通用, 提供到任何数据底层的存储功能, 比如关系数据库、文件、XML 以及对象数
据库（ODBMS）等等, 使得应用可移植性更强。

20、什么是spring 的IOC AOP

21、STRUTS 的工作流程！

22、spring 与EJB 的区别！！
八.软件工程与设计模式

1、UML 方面
标准建模语言UML。用例图,静态图(包括类图、对象图和包图),行为图,交互图(顺序图,合作图),实现图。

2、j2ee 常用的设计模式？说明工厂模式。
总共23种, 分为三大类：创建型, 结构型, 行为型
我只记得其中常用的6、7种, 分别是：
创建型（工厂、工厂方法、抽象工厂、单例）
结构型（包装、适配器, 组合, 代理）
行为（观察者, 模版, 策略）
然后再针对你熟悉的模式谈谈你的理解即可。
Java 中的23种设计模式：
Factory（工厂模式）,  Builder（建造模式）,  Factory Method（工厂方法模式）, 
Prototype（原始模型模式）, Singleton（单例模式）,  Facade（门面模式）, 
Adapter（适配器模式）,  Bridge（桥梁模式）,  Composite（合成模式）, 
Decorator（装饰模式）,  Flyweight（享元模式）,  Proxy（代理模式）, 
Command（命令模式）,  Interpreter（解释器模式）,  Visitor（访问者模式）, 
Iterator（迭代子模式）,  Mediator（调停者模式）,  Memento（备忘录模式）, 
Observer（观察者模式）,  State（状态模式）,  Strategy（策略模式）, 
Template Method（模板方法模式）,  Chain Of Responsibleity（责任链模式）
工厂模式：工厂模式是一种经常被使用到的模式, 根据工厂模式实现的类可以根据提供的数
据生成一组类中某一个类的实例, 通常这一组类有一个公共的抽象父类并且实现了相同的方
法, 但是这些方法针对不同的数据进行了不同的操作。首先需要定义一个基类, 该类的子类
通过不同的方法实现了基类中的方法。然后需要定义一个工厂类, 工厂类可以根据条件生成
不同的子类实例。当得到子类的实例后, 开发人员可以调用基类中的方法而不必考虑到底返
回的是哪一个子类的实例。

3、开发中都用到了那些设计模式?用在什么场合?
每个模式都描述了一个在我们的环境中不断出现的问题, 然后描述了该问题的解决方案的核
心。通过这种方式, 你可以无数次地使用那些已有的解决方案, 无需在重复相同的工作。主
要用到了MVC 的设计模式。用来开发JSP/Servlet 或者J2EE 的相关应用。简单工厂模式
等。
九. j2ee 部分

1、BS 与CS 的联系与区别。
C/S 是Client/Server 的缩写。服务器通常采用高性能的PC、工作站或小型机, 并采用大型
数据库系统, 如Oracle、Sybase、InFORMix 或 SQL Server。客户端需要安装专用的客户
端软件。
B/Ｓ是Brower/Server 的缩写, 客户机上只要安装一个浏览器（Browser）, 如Netscape
Navigator 或Internet Explorer, 服务器安装Oracle、Sybase、InFORMix 或 SQL Server
等数据库。在这种结构下, 用户界面完全通过WWW 浏览器实现, 一部分事务逻辑在前端
实现, 但是主要事务逻辑在服务器端实现。浏览器通过Ｗeb Server 同数据库进行数据交互。
C/S 与 B/S 区别：
１．硬件环境不同:
C/S 一般建立在专用的网络上,小范围里的网络环境,局域网之间再通过专门服务器提供连
接和数据交换服务.
B/S 建立在广域网之上的,不必是专门的网络硬件环境,例与电话上网,租用设备.信息自己管
理.有比C/S 更强的适应范围,一般只要有操作系统和浏览器就行
２．对安全要求不同
C/S 一般面向相对固定的用户群,对信息安全的控制能力很强.一般高度机密的信息系统采
用C/S 结构适宜.可以通过B/S 发布部分可公开信息.
B/S 建立在广域网之上,对安全的控制能力相对弱,可能面向不可知的用户。
３．对程序架构不同
C/S 程序可以更加注重流程,可以对权限多层次校验,对系统运行速度可以较少考虑.
B/S 对安全以及访问速度的多重的考虑,建立在需要更加优化的基础之上.比C/S 有更高的
要求 B/S 结构的程序架构是发展的趋势,从MS 的.Net 系列的BizTalk 2000Exchange 2000
等,全面支持网络的构件搭建的系统. SUN 和IBM 推的JavaBean 构件技术等,使 B/S 更加
成熟.
４．软件重用不同
C/S 程序可以不可避免的整体性考虑,构件的重用性不如在B/S 要求下的构件的重用性好.
B/S 对的多重结构,要求构件相对独立的功能.能够相对较好的重用.就入买来的餐桌可以再
利用,而不是做在墙上的石头桌子
５．系统维护不同
C/S 程序由于整体性,必须整体考察,处理出现的问题以及系统升级.升级难.可能是再做一个
全新的系统
B/S 构件组成,方面构件个别的更换,实现系统的无缝升级.系统维护开销减到最小.用户从网
上自己下载安装就可以实现升级.
６．处理问题不同
C/S 程序可以处理用户面固定,并且在相同区域,安全要求高需求,与操作系统相关.应该都是
相同的系统
B/S 建立在广域网上,面向不同的用户群,分散地域,这是C/S 无法作到的.与操作系统平台关
系最小.
７．用户接口不同
C/S 多是建立的Window 平台上,表现方法有限,对程序员普遍要求较高
B/S 建立在浏览器上,有更加丰富和生动的表现方式与用户交流.并且大部分难度减低,减低
开发成本.
８．信息流不同
C/S 程序一般是典型的中央集权的机械式处理,交互性相对低
B/S 信息流向可变化, B-B B-C B-G 等信息、流向的变化,更像交易中心。

2、应用服务器与WEB SERVER 的区别？
应用服务器：Weblogic、Tomcat、Jboss
WEB SERVER：IIS、 Apache

3、应用服务器有那些？
BEA WebLogic Server, IBM WebSphere Application Server, Oracle9i ApplicationServer, 
jBoss, Tomcat

4、J2EE 是什么？
答:Je22是Sun 公司提出的多层(multi-diered),分布式(distributed),基于组件
(component-base)的企业级应用模型(enterpriese applicationmodel).在这样的一个应用系
统中, 可按照功能划分为不同的组件, 这些组件又可在不同计算机上, 并且处于相应的层次
(tier)中。所属层次包括客户层(clietn tier)组件,web 层和组件,Business 层和组件,企业信息系
统(EIS)层。
一个另类的回答：j2ee 就是增删改查。

5、J2EE 是技术还是平台还是框架？什么是J2EE
J2EE 本身是一个标准, 一个为企业分布式应用的开发提供的标准平台。
J2EE 也是一个框架, 包括JDBC、JNDI、RMI、JMS、EJB、JTA 等技术。

6、请对以下在J2EE 中常用的名词进行解释(或简单描述)
web 容器：给处于其中的应用程序组件（JSP, SERVLET）提供一个环境, 使JSP,SERVLET
直接更容器中的环境变量接口交互, 不必关注其它系统问题。主要有WEB 服务器来实现。
例如：TOMCAT,WEBLOGIC,WEBSPHERE 等。该容器提供的接口严格遵守J2EE 规范中
的WEB APPLICATION 标准。我们把遵守以上标准的WEB 服务器就叫做J2EE 中的WEB
容器。
EJB 容器：Enterprise java bean 容器。更具有行业领域特色。他提供给运行在其中的组件
EJB 各种管理功能。只要满足J2EE 规范的EJB 放入该容器, 马上就会被容器进行高效率
的管理。并且可以通过现成的接口来获得系统级别的服务。例如邮件服务、事务管理。
JNDI：（Java Naming & Directory Interface）JAVA 命名目录服务。主要提供的功能是：提
供一个目录系统, 让其它各地的应用程序在其上面留下自己的索引, 从而满足快速查找和定
位分布式应用程序的功能。
JMS：（Java Message Service）JAVA 消息服务。主要实现各个应用程序之间的通讯。包
括点对点和广播。
JTA：（Java Transaction API）JAVA 事务服务。提供各种分布式事务服务。应用程序只需
调用其提供的接口即可。
JAF：（Java Action FrameWork）JAVA 安全认证框架。提供一些安全控制方面的框架。让
开发者通过各种部署和自定义实现自己的个性安全控制策略。
RMI/IIOP:（Remote Method Invocation /internet 对象请求中介协议）他们主要用于通过远
程调用服务。例如, 远程有一台计算机上运行一个程序, 它提供股票分析服务, 我们可以在
本地计算机上实现对其直接调用。当然这是要通过一定的规范才能在异构的系统之间进行通
信。RMI 是JAVA 特有的。

7、如何给weblogic 指定大小的内存?
（这个问题不作具体回答, 列出来只是告诉读者可能会遇到什么问题, 你不需要面面俱到, 
什么都精通。）
在启动Weblogic 的脚本中（位于所在Domian 对应服务器目录下的startServerName）, 增
加set MEM_ARGS=-Xms32m-Xmx200m, 可以调整最小内存为32M, 最大200M

8、如何设定的weblogic 的热启动模式(开发模式)与产品发布模式?
可以在管理控制台中修改对应服务器的启动模式为开发或产品模式之一。或者修改服务的启
动文件或者commenv 文件, 增加setPRODUCTION_MODE=true。

9、如何启动时不需输入用户名与密码?
修改服务启动文件, 增加 WLS_USER 和WLS_PW 项。也可以在boot.properties 文件中
增加加密过的用户名和密码.

10、在weblogic 管理制台中对一个应用域(或者说是一个网站,Domain)进行jms 及ejb 或连接池等相关信息进行配置后,实际保存在什么文件中?
保存在此Domain 的config.xml 文件中, 它是服务器的核心配置文件。

11、说说weblogic 中一个Domain 的缺省目录结构?
比如要将一个简单的helloWorld.jsp 放入何目录下,然的在浏览器上就可打入
http://主机:端口号//helloword.jsp 就可以看到运行结果了?
又比如这其中用到了一个自己写的javaBean 该如何办?
Domain 目录服务器目录applications, 将应用目录放在此目录下将可以作为应用访问, 如
果是Web 应用, 应用目录需要满足Web 应用目录要求, jsp 文件可以直接放在应用目录中, 
Javabean 需要放在应用目录的WEB-INF 目录的classes 目录中, 设置服务器的缺省应用
将可以实现在浏览器上无需输入应用名。

12、在weblogic 中发布ejb 需涉及到哪些配置文件
不同类型的EJB 涉及的配置文件不同, 都涉及到的配置文件包括
ejb-jar.xml,weblogic-ejb-jar.xmlCMP 实体Bean 一般还需要weblogic-cmp-rdbms-jar.xml

13、如何在weblogic 中进行ssl 配置与客户端的认证配置或说说j2ee(标准)进行ssl 的配置?
缺省安装中使用DemoIdentity.jks 和DemoTrust.jks KeyStore 实现SSL, 需要配置服务器
使用Enable SSL, 配置其端口, 在产品模式下需要从CA 获取私有密钥和数字证书, 创建
identity 和trust keystore, 装载获得的密钥和数字证书。可以配置此SSL 连接是单向还是
双向的。

14、如何查看在weblogic 中已经发布的EJB?
可以使用管理控制台, 在它的Deployment 中可以查看所有已发布的EJB
十. EJB 部分

1、EJB 是基于哪些技术实现的？并说出SessionBean 和EntityBean 的区别, StatefulBean 和StatelessBean 的区别。
EJB 包括Session Bean、Entity Bean、Message Driven Bean, 基于JNDI、RMI、JAT
等技术实现。
SessionBean 在J2EE 应用程序中被用来完成一些服务器端的业务操作, 例如访问数据库、
调用其他EJB 组件。EntityBean 被用来代表应用系统中用到的数据。
对于客户机, SessionBean 是一种非持久性对象, 它实现某些在服务器上运行的业务逻辑。
对于客户机, EntityBean 是一种持久性对象, 它代表一个存储在持久性存储器中的实体的
对象视图, 或是一个由现有企业应用程序实现的实体。
Session Bean 还可以再细分为 Stateful Session Bean 与 Stateless SessionBean, 这两种
的 Session Bean 都可以将系统逻辑放在 method 之中执行, 不同的是 Stateful
SessionBean 可以记录呼叫者的状态, 因此通常来说, 一个使用者会有一个相对应的
Stateful SessionBean 的实体。Stateless SessionBean 虽然也是逻辑组件, 但是他却不负
责记录使用者状态, 也就是说当使用者呼叫 Stateless SessionBean 的时候, EJB Container
并不会找寻特定的 Stateless SessionBean 的实体来执行这个 method。换言之, 很可能数
个使用者在执行某个 Stateless SessionBean 的 methods 时, 会是同一个 Bean 的
Instance 在执行。从内存方面来看,  Stateful SessionBean 与Stateless SessionBean 比较, 
Stateful SessionBean 会消耗 J2EE Server 较多的内存, 然而 Stateful SessionBean 的优
势却在于他可以维持使用者的状态。

2、简要讲一下 EJB 的 7 个 Transaction Level?

3、EJB 与JAVA BEAN 的区别？
Java Bean 是可复用的组件, 对Java Bean 并没有严格的规范, 理论上讲, 任何一个Java
类都可以是一个Bean。但通常情况下, 由于Java Bean 是被容器所创建（如Tomcat）的, 
所以Java Bean 应具有一个无参的构造器, 另外, 通常Java Bean 还要实现Serializable
接口用于实现Bean 的持久性。Java Bean 实际上相当于微软COM 模型中的本地进程内
COM 组件, 它是不能被跨进程访问的。Enterprise Java Bean 相当于DCOM, 即分布式组
件。它是基于Java 的远程方法调用（RMI）技术的, 所以EJB 可以被远程访问（跨进程、
跨计算机）。但EJB 必须被布署在诸如Webspere、WebLogic 这样的容器中, EJB 客户从
不直接访问真正的EJB 组件, 而是通过其容器访问。EJB 容器是EJB 组件的代理, EJB 组
件由容器所创建和管理。客户通过容器来访问真正的EJB 组件。

4、EJB 包括（SessionBean,EntityBean）说出他们的生命周期, 及如何管理事务的？
SessionBean：Stateless Session Bean 的生命周期是由容器决定的, 当客户机发出请求要
建立一个Bean 的实例时, EJB 容器不一定要创建一个新的Bean 的实例供客户机调用, 而
是随便找一个现有的实例提供给客户机。当客户机第一次调用一个Stateful SessionBean
时, 容器必须立即在服务器中创建一个新的Bean 实例, 并关联到客户机上, 以后此客户机
调用Stateful SessionBean 的方法时容器会把调用分派到与此客户机相关联的Bean 实例。
EntityBean：Entity Beans 能存活相对较长的时间, 并且状态是持续的。只要数据库中的数
据存在, Entity beans 就一直存活。而不是按照应用程序或者服务进程来说的。即使EJB
容器崩溃了, Entity beans 也是存活的。Entity Beans 生命周期能够被容器或者 Beans 自
己管理。
EJB 通过以下技术管理实务：对象管理组织（OMG）的对象实务服务（OTS）, Sun
Microsystems 的Transaction Service（JTS）、Java Transaction AP（I JTA）, 开发组（X/Open）
的XA 接口。

5、EJB 容器提供的服务
主要提供声明周期管理、代码产生、持续性管理、安全、事务管理、锁和并发行管理等服务。

6、EJB 的激活机制
以Stateful Session Bean 为例：其Cache 大小决定了内存中可以同时存在的Bean 实例的
数量, 根据MRU 或NRU 算法, 实例在激活和去激活状态之间迁移, 激活机制是当客户端
调用某个EJB 实例业务方法时, 如果对应EJB Object 发现自己没有绑定对应的Bean 实例
则从其去激活Bean 存储中（通过序列化机制存储实例）回复（激活）此实例。状态变迁前
会调用对应的ejbActive 和ejbPassivate 方法。

7、EJB 的几种类型
会话（Session）Bean, 实体（Entity）Bean 消息驱动的（Message Driven）Bean
会话Bean 又可分为有状态（Stateful）和无状态（Stateless）两种
实体Bean 可分为Bean 管理的持续性（BMP）和容器管理的持续性（CMP）两种

8、客服端调用EJB 对象的几个基本步骤
设置JNDI 服务工厂以及JNDI 服务地址系统属性, 查找Home 接口, 从Home 接口调用
Create 方法创建Remote 接口, 通过Remote 接口调用其业务方法。
十一. webservice 部分

1、WEB SERVICE 名词解释。JSWDL 开发包的介绍。JAXP、JAXM 的解释。
SOAP、UDDI,WSDL 解释。
Web ServiceWeb Service 是基于网络的、分布式的模块化组件, 它执行特定的任务, 遵守
具体的技术规范, 这些规范使得Web Service 能与其他兼容的组件进行互操作。
JAXP(Java API for XML Parsing) 定义了在Java 中使用DOM, SAX, XSLT 的通用的接口。
这样在你的程序中你只要使用这些通用的接口, 当你需要改变具体的实现时候也不需要修改
代码。
JAXM(Java API for XML Messaging) 是为SOAP 通信提供访问方法和传输机制的API。
WSDL 是一种 XML 格式, 用于将网络服务描述为一组端点, 这些端点对包含面向文档信息
或面向过程信息的消息进行操作。这种格式首先对操作和消息进行抽象描述, 然后将其绑定
到具体的网络协议和消息格式上以定义端点。相关的具体端点即组合成为抽象端点（服务）。
SOAP 即简单对象访问协议(Simple Object Access Protocol), 它是用于交换XML 编码信息
的轻量级协议。
UDDI 的目的是为电子商务建立标准；UDDI 是一套基于Web 的、分布式的、为Web Service
提供的、信息注册中心的实现标准规范, 同时也包含一组使企业能将自身提供的Web
Service 注册, 以使别的企业能够发现的访问协议的实现标准。

2、CORBA 是什么?用途是什么?
CORBA 标准是公共对象请求代理结构(Common Object Request Broker Architecture), 由
对象管理组织 (Object ManagementGroup, 缩写为 OMG)标准化。它的组成是接口定义语
言(IDL),语言绑定(binding:也译为联编)和允许应用程序间互操作的协议。其目的为：用不同
的程序设计语言书写在不同的进程中运行, 为不同的操作系统开发。
3. Linux

4、LINUX 下线程, GDI 类的解释。
LINUX 实现的就是基于核心轻量级进程的"一对一"线程模型, 一个线程实体对应一个核心轻
量级进程, 而线程之间的管理在核外函数库中实现。
GDI 类为图像设备编程接口类库。
5. 问得稀里糊涂的题

6、四种会话跟踪技术
会话作用域ServletsJSP 页面描述
page 否是代表与一个页面相关的对象和属性。一个页面由一个编译好的 Java servlet 类（可
以带有任何的include 指令, 但是没有 include 动作）表示。这既包括 servlet 又包括被编
译成 servlet 的 JSP 页面
request 是是代表与 Web 客户机发出的一个请求相关的对象和属性。一个请求可能跨越多
个页面, 涉及多个Web 组件（由于 forward 指令和 include 动作的关系）
session 是是代表与用于某个 Web 客户机的一个用户体验相关的对象和属性。一个 Web
会话可以也经常会跨越多个客户机请求
application 是是代表与整个 Web 应用程序相关的对象和属性。这实质上是跨越整个 Web
应用程序, 包括多个页面、请求和会话的一个全局作用域

7、简述逻辑操作(&,|,^)与条件操作(&&,||)的区别。
区别主要答两点：a.条件操作只能操作布尔型的,而逻辑操作不仅可以操作布尔型,而且可以
操作数值型
b.逻辑操作不会产生短路
十二.其他

1、请用英文简单介绍一下自己.

4、WEB SERVICE 名词解释。JSWDL 开发包的介绍。JAXP、JAXM 的解释。SOAP、
UDDI,WSDL 解释。

2、请把http://tomcat.apache.org/ 首页的这一段话用中文翻译一下?
Apache Tomcat is the servlet container that is used in the officialReference
Implementation for theJava ServletandJavaServerPages technologies. The Java Servlet
and JavaServer Pagesspecifications are developed by Sun under theJavaCommunity
Process.
Apache Tomcat is developed in an open andparticipatory environment and released under
theApacheSoftware License. Apache Tomcat is intended to be a collaboration ofthe
best-of-breed developers from around the world. We invite you toparticipate in this open
development project. To learn more about gettinginvolved,click here.
ApacheTomcat powers numerous large-scale, mission-critical web applications across
adiverse range of industries and organizations. Some of these users and theirstories are
listed on thePoweredBy wiki page.

3、美资软件公司JAVA 工程师电话面试题目
1. Talk about overriding, overloading.
2. Talk about JAVA design patterns you known.
3. Talk about the difference between LinkList, ArrayList and Victor.
4. Talk about the difference between an Abstract class and an Interface.
5. Class a = new Class(); Class b = new Class();
if(a == b) returns true or false, why?
6. Why we use StringBuffer when concatenating strings?
7. Try to explain Singleton to us? Is it thread safe? If no, how to make itthread safe?
8. Try to explain Ioc?
9. How to set many-to-many relationship in Hibernate?
10. Talk about the difference between INNER JOIN and LFET JOIN.
11. Why we use index in database? How many indexes is the maximum in one tableas
your suggestion?
12. When ‘Final’ is used in class, method and property, what dose it mean?
13. Do you have any experience on XML? Talk about any XML tool youused ,e.g. JAXB,
JAXG.
14. Do you have any experience on Linux?
15. In OOD what is the reason when you create a Sequence diagram?
Administrator 10:34:20
1, 堆和栈的区别, 有一个64k 的字符串, 是放到堆上, 还是放到栈上, 为什么？
2, 什么时候用到接口, 什么时候用到抽象类, 二者区别
3, 有一个100万的数组, 里边有两个市重复的, 如何设计算法找到。
4, 设计数据库时, n 维, 如何设计。