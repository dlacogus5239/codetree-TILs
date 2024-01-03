import java.util.*;

public class Main {
    public static void main(String[] args) {
        // 여기에 코드를 작성해주세요.
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int book = 3000;
        int mask = 1000;

        if(n >= book){
            System.out.println("book");
        }else if(n >= mask){
            System.out.println("mask");
        }else{
            System.out.println("no");
        }

    }
}