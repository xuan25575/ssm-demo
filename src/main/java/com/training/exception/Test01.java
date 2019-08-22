package com.training.exception;

/**
 * @Description 异常测试
 * @date 2019/8/16
 */
public class Test01 {
    public static void main(String[] args) throws Exception {
//	创建tc对象
        TestC tc = new TestC();
//	使用tc调用c方法，运行这个类
        tc.c();
//	A a = new A();
//	a.a();
    }
}

//A类
class A{
    //	创建一个a方法，在a方法中创建b对象，通过b对象调用b方法，因为b对象抛出了异常，所以在a方法中也要抛出异常
    public void a() {
        B b = new B();
        b.b();
    }
}

   //B类
    class B  {
        //	创建一个b方法，并抛出异常
        public void b(){
            int i = 2/0;
            System.out.println("我是B类中的b方法，我抛出异常！");
        }
    }
  //客户端类
    class TestC{
        public void c() {
    //		创建一个A对象
            A a = new A();
            a.a();
    //		调用A对象中的a方法，此时因为A方法中抛出了异常，所以这了必须处理，使用try...catch处理
            try {
             //   a.a();
            } catch (Exception e) {
                // TODO Auto-generated catch block
                System.out.println("我是捕获异常方法，"+e.toString());
            }
        }

}
