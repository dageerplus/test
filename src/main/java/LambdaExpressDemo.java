interface Foo{
   // public void sayhello();
    public int add(int a,int b);

    default int div(int a,int b){
        return a/b;
    }

    public static int div2(int a,int b){
        return a/b/2;
    }
        }

public class LambdaExpressDemo {

    public static void main(String[] args) {
     //   Foo foo =new Foo() {
     //       public void sayhello() {
       //         System.out.println("hello``````````");
     //       }
    //    };
      //  foo.sayhello();
      //  Foo foo2 =() -> { System.out.println("hello111111");};
      //  foo2.sayhello();

        Foo foo=(a,b) ->{
            System.out.println("come in add:");
            return a+b;
        };
        System.out.println(foo.add(1,3));

        System.out.println(foo.div(8,2));

        System.out.println(Foo.div2(8,2));
   }
}
