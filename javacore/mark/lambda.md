# Lambda 表达式

## 1 概述
> lambda表达式是一个可传递的代码块，可以在以后执行一次或多次。

## 2 语法
> 我们传入代码来检查一个字符串是否比另一个字符串短。这里要计算：
```text
first.length() - second.length();
```
> first和second是什么？它们都是字符串。Java是一种强类型语言，所以我们还要指定它们的类型：
```text
(String first, String second)
    -> first.length() - second.length();
```
> 这就是你看到的第一个lambda表达式。lambda表达式就是一个代码块，以及必须传入代码的变量规范。       
> **表达式形式：参数，箭头（->）以及一个表达式。**       
> 如果代码要完成的计算无法放在一个表达式中，就可以像写方法一样，把这些代码放在{}中，并包含显式的return语句。例如：
```text
(String first, String second) -> {
    if(first.length() < second.length())
        return -1;
    else if(first.length() > second.length()
        return 1;
    else
        return 0;
}
```
> 即使lambda表达式没有参数，仍然要提供空括号，就像无参数方法一样：
```text
() -> { for(int i = 0; i < 10; i++) System.out.println(i); }
```
> 如果可以推导出一个lambda表达式的参数类型，则可以忽略其类型。例如：
```text
Comparator<String> comparator = (first, second) -> first.length() - second.length();
```
> 在这里，编译器可以推导出first和second必然是字符串，因为这个lambda表达式将赋给一个字符串比较器。      
> 如果方法只有一个参数，而且这个参数的类型可以推导得出，那么甚至还可以省略小括号：
```text
ActionListener listener = event -> System.out.println("The time is " + new Date());
```
> 无需指定lambda表达式的返回类型。lambda表达式的返回类型总是会由上下文推导得出。例如，下面的表达式:
```text
(String first, String second)
    -> first.length() - second.length();
```
> 可以在需要int类型结果的上下文中使用。      
> _注释：如果一个lambda表达式只在某些分支返回一个值，而在另外一些分支不返回值，这是不合法的。      
> 例如，(int x) -> { if (x >= 0) return 1; }就不合法。_     
> lambda 的简单使用：
```java
public class LambdaTest {
    public static void main(String[] args) {
        String[] planets = {"Mercury", "Venus", "Earth", "Mars", "Jupiter", "Saturn", "Uranus", "Neptune"};
        System.out.println("self toString : " + planets.toString());
        System.out.println("Arrays toString : " + Arrays.toString(planets));
        Arrays.sort(planets);
        System.out.println("Sorted in dictionary order : " + Arrays.toString(planets));
        Arrays.sort(planets, (first, second) -> first.length() - second.length());
        System.out.println("Sorted in length order : " + Arrays.toString(planets));
        Timer timer = new Timer(1000, e -> System.out.println("The time is " + new Date()));
        timer.start();

        JOptionPane.showMessageDialog(null, "Exit?");
        System.exit(0);
    }
}
```

## 3 函数式接口
> 对于只有一个抽象方法的接口，需要这种接口的对象时，就可以提供一个lambda表达式。这种接口称为函数式接口（functional interface）。