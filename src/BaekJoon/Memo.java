package BaekJoon;

public class Memo {
    public static void main(String[] args) {
        boolean[] bo = new boolean[5];
        bo[0] = true;
        for(boolean b : bo)  {
            System.out.println(b);
        }
        System.out.println("==============");
        boolean[] bo1 = bo;
        bo1[1] = true;
        for(boolean b : bo1) {
            System.out.println(b);
        }
        System.out.println("================");
        for(boolean b : bo) {
            System.out.println(b);
        }



    }
}
