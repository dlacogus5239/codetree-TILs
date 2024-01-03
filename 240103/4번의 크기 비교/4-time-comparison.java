import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int a = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < 4; i++){
            int next = Integer.parseInt(st.nextToken());

            if(a > next){
                sb.append(1);
            }else{
                sb.append(0);
            }
            sb.append("\n");
        }
         System.out.println(sb.toString());
    }
}