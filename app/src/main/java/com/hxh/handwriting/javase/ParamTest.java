package com.hxh.handwriting.javase;

/**
 * Created by HXH at 2019/8/12
 * java方法参数理解
 */
public class ParamTest {

    public static void main(String[] args) {
        Student s1 = new Student("zhangsan"); //[1] s1叫引用对象,而new的对象叫是内存地址中的实际对象[对象1],s1指向[对象1]
        System.out.println("------开始的s1------");
        System.out.println(s1);//[2] s1指向的内存地址[对象1]
        System.out.println(s1.name);//[3] s1的名字,即[对象1]的name
        method1(s1);//[4] 将s1指向的内存地址值[对象1]传入到方法method1参数中
        System.out.println("------结束的s1------");
        System.out.println(s1);//这里的s1还是指向的[对象1]
        System.out.println(s1.name);//变成lisi了,参看[6]

        int a1 = 2;
        System.out.println("------开始的a1------");
        System.out.println(a1);//2
        method2(a1);//[7] 传原生据类型参数,值的拷贝
        System.out.println("------结束的a1------");
        System.out.println(a1);//结果还是2,a1不会因为传入到方法参数中而有任何有影响
    }

    private static void method1(Student s2) {//[5] 执行到[4]的位置，方法入栈，执行到这里，这里s2等价于：Student s2 = [4]传入的s1
        System.out.println("------开始的s2------");
        System.out.println(s2);//s2与上面的s1指向同一个内存地址[对象1]
        System.out.println(s2.name);//s2.name和上面s1.name相同,即[对象1]的name
        System.out.println("------改变名字的s2------");
        s2.name = "lisi";//[6] 改变s2的名字,s2与s1还是指向的同一个内存地址[对象1]，只要其中任意一个引用对象操纵[对象1]的属性改变，都会反映到指向同一[对象1]的其他引用对象上
        System.out.println(s2);//s2与上面的s1的内存地址还是一样
        System.out.println(s2.name);//s2.name的值发生改变，变成lisi,同理s1.name此时也是lisi了
        System.out.println("------new的s2------");
        s2 = new Student("lisi");//原来的情况是：s2与s1指向同一对象[对象1](见[5]),这里new相当于s2与之前指向的[对象1]断开指向关系，s2重新指向了另一个新内存地址[对象2]，
        System.out.println(s2);//s2指向的内存地址[对象2],观察控制台输出,这里的内存地址和上面的不一样了
        System.out.println(s2.name);//s2.name即[对象2]的name
        // 方法结束,引用对象s2等待GC(垃圾回收,java虚拟机认为s2在该方法结束后发现s2不可达,会按情况回收s2,其效果等同于:s2 = null)
        //s2 = null;
    }

    private static class Student{
        private String name;

        public Student(String name) {
            this.name = name;
        }
    }

    private static void method2(int a2) { //[7]处的值2传入,a2为2,相当于:int a2 = 2;
        System.out.println("------开始的a2------");
        System.out.println(a2);//结果2
        System.out.println("------结束的a2------");
        a2 = a2+1;// 改变a2的值,不会对[7]处a1的值有任何影响
        System.out.println(a2); //结果3
    }
}
