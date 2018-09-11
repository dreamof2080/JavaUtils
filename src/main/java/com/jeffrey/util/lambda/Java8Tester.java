package com.jeffrey.util.lambda;

/** lambda语法
 * @author Jeffrey.Liu
 * @date 2018-09-11 8:39
 **/
public class Java8Tester {
    public static void main(String[] args) {
        Java8Tester tester = new Java8Tester();
        //类型声明
        MathOperation addition = (int a,int b) -> a+b;
        //不用类型声明
        MathOperation subtraction = (a,b) -> a -b;
        //大括号的返回语句
        MathOperation multiplication = (int a,int b) -> {return a * b;};
        //没有大括号及返回语句
        MathOperation division = (int a,int b) -> a / b;
        System.out.println("10+5=" + tester.operate(10,5,addition));
        System.out.println("10-5=" + tester.operate(10,5,subtraction));
        System.out.println("10*5=" + tester.operate(10,5, multiplication));
        System.out.println("10/5=" + tester.operate(10,5,division));
    }

    interface MathOperation{
        int operation(int a,int b);
    }

    interface GreetingService{
        void sayMessage(String message);
    }

    private int operate(int a,int b,MathOperation mathOperation){
        return mathOperation.operation(a,b);
    }

}
