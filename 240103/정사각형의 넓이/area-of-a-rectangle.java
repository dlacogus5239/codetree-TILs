import java.util.*;

public class Main {
    public static void main(String[] args) {
        // 여기에 코드를 작성해주세요.
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();

        int area = N * N;
        
        System.out.println(area);
        if(N < 5){
            System.out.println("tiny");
        }
    }
}