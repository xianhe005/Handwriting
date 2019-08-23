package com.hxh.handwriting.javase;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

/**
 * Created by HXH at 2019/8/23
 * 反射测试
 */
public class ReflectTest2 {

    public static void main(String[] args) throws Exception {
        private_();
        System.out.println("-------------------------");

        privateFinal();
        System.out.println("-------------------------");

        //privateFinalStaticException();
        //System.out.println("-------------------------");

        privateFinalStaticModifier();
        System.out.println("-------------------------");

        privateFinal2();
        System.out.println("-------------------------");

        //privateFinalStatic2Exception();
        //System.out.println("-------------------------");

        privateFinalStatic2Modifier();
    }

    /**
     * 反射修改：private static final int c = 1; 这种类型
     * 会抛异常：can not access a member of class xx.ReflectTest2$MyClass with modifiers "private static final"
     */
    private static void privateFinalStaticException() throws Exception {
        Field cField = MyClass.class.getDeclaredField("c");
        cField.set(null, 2);
        System.out.println("c:" + MyClass.c);
    }

    /**
     * 反射修改：private static final int e; static { e = 1; } 这种类型
     * 同样会抛异常：can not access a member of class xx.ReflectTest2$MyClass with modifiers "private static final"
     */
    private static void privateFinalStatic2Exception() throws Exception {
        Field cField = MyClass.class.getDeclaredField("e");
        cField.set(null, 2);
        System.out.println("e:" + MyClass.e);
    }

    /**
     * 反射 private static final int c = 1; 这种类型,去掉final修饰符
     * 通过反射反射自己，去掉final,不不再抛异常,但也修改不了值
     */
    private static void privateFinalStaticModifier() throws Exception {
        Field field = MyClass.class.getDeclaredField("c");
        System.out.println("反射 private static final int c = 1;");
        System.out.println("c before:" + MyClass.c);

        field.setAccessible(true);
        System.out.println("c Modifiers before:" + field.getModifiers());
        System.out.println("Modifier.PRIVATE | Modifier.STATIC | Modifier.FINAL:"
                + (Modifier.PRIVATE | Modifier.STATIC | Modifier.FINAL));

        // 通过反射反射自己，去掉final,不再抛异常
        Field modifiersField = Field.class.getDeclaredField("modifiers");
        modifiersField.setAccessible(true);
        modifiersField.setInt(field, modifiersField.getInt(field) ^ Modifier.FINAL);

        field.setInt(null, 2);
        System.out.println("c Modifiers after:" + field.getModifiers());
        System.out.println("Modifier.PRIVATE | Modifier.STATIC:"
                + (Modifier.PRIVATE | Modifier.STATIC));
        System.out.println("c after:" + MyClass.c);
    }

    /**
     * 反射 private static final int e; static { e = 1; } 这种类型,去掉final修饰符
     * 通过反射反射自己，去掉final,不不再抛异常,且能修改值
     */
    private static void privateFinalStatic2Modifier() throws Exception {
        Field field = MyClass.class.getDeclaredField("e");
        System.out.println("反射 private static final int e; static { e = 1; }");
        System.out.println("e before:" + MyClass.e);

        field.setAccessible(true);
        System.out.println("e Modifiers before:" + field.getModifiers());
        System.out.println("Modifier.PRIVATE | Modifier.STATIC | Modifier.FINAL:"
                + (Modifier.PRIVATE | Modifier.STATIC | Modifier.FINAL));

        // 通过反射反射自己，去掉final,不再抛异常
        Field modifiersField = Field.class.getDeclaredField("modifiers");
        modifiersField.setAccessible(true);
        modifiersField.setInt(field, modifiersField.getInt(field) ^ Modifier.FINAL);

        field.setInt(null, 2);
        System.out.println("e Modifiers after:" + field.getModifiers());
        System.out.println("Modifier.PRIVATE | Modifier.STATIC:"
                + (Modifier.PRIVATE | Modifier.STATIC));
        System.out.println("e after:" + MyClass.e);
    }

    /**
     * 反射 private int a = 1; 像这种非final成员变量
     * 可以反射修改值
     */
    private static void private_() throws Exception {
        MyClass myClass = new MyClass();
        System.out.println("反射 private int a = 1;");
        System.out.println("a before:" + myClass.a);
        Field field = MyClass.class.getDeclaredField("a");
        field.setAccessible(true);
        field.setInt(myClass, 2);
        System.out.println("a after:" + myClass.a);
    }

    /**
     * 反射 private final int b = 1; 像这种final成员变量
     * 反射修改不了值，编译期间final类型的数据自动被优化了，即：所有用到该变量的地方都被替换成了常量。
     */
    private static void privateFinal() throws Exception {
        MyClass myClass = new MyClass();
        System.out.println("反射 private final int b = 1;");
        System.out.println("b before:" + myClass.b);
        Field field = MyClass.class.getDeclaredField("b");
        field.setAccessible(true);
        field.setInt(myClass, 2);
        System.out.println("b after:" + myClass.b);
    }

    /**
     * 反射 private final int d; MyClass() { d = 1; } 像这种final成员变量(在构造方法初始的值)
     * 可以反射修改值，当final修饰的成员变量在定义的时候并没有初始化值
     */
    private static void privateFinal2() throws Exception {
        MyClass myClass = new MyClass();
        System.out.println("反射 private final int d; MyClass() { d = 1; }");
        System.out.println("d before:" + myClass.d);
        Field field = MyClass.class.getDeclaredField("d");
        field.setAccessible(true);
        field.setInt(myClass, 2);
        System.out.println("d after:" + myClass.d);
    }

    private static class MyClass {
        private int a = 1;
        private final int b = 1;
        private static final int c = 1;
        private final int d;
        private static final int e;

        static {
            e = 1;
        }

        MyClass() {
            d = 1;
        }
    }
}
