import java.util.*;
public class Main {
    public static void main(String[] args) {
        // 여기에 코드를 작성해주세요.

        Scanner sc = new Scanner(System.in);

        String[] input = sc.next().split("-");

        System.out.println(input[0] + "-" + input[2] + "-" + input[1]);
    }
}