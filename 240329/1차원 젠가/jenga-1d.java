import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] arr =new int[N];
        for(int i = 0; i < N; i++){
            arr[i] = Integer.parseInt(br.readLine());
        }

        StringTokenizer st;
        for(int i =0; i < 2; i++){
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken()) - 1;
            int end = Integer.parseInt(st.nextToken()) - 1;

            for(int k = start; k <=end; k++){
                arr[k] = 0;
            }
            
            Stack<Integer> s = new Stack<>();
            for(int k = N - 1; k >=0; k--){
                if(arr[k] != 0){
                    s.add(arr[k]);
                }
            }
            // System.out.println(s.toString());

            int cnt =0;
            arr = new int[N];
            while(!s.isEmpty()){
                arr[cnt++] = s.pop();
            }

            // for(int k = 0; k < N; k++){
            //     System.out.print(arr[k] + " ");
            // }
            // System.out.println();
        }

        StringBuilder sb = new StringBuilder();

        int cnt =0;
        for(int i=0; i< N; i++){
            if(arr[i] != 0){
                sb.append(arr[i]).append("\n");
                cnt++;
            }else{
                break;
            }
        }
        System.out.println(cnt);
        System.out.println(sb.toString());



    }
}