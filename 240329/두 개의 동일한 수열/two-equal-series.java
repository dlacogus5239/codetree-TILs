import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        StringTokenizer st, st2;
        int[] A = new int[N];
        int[] B = new int[N];

        st = new StringTokenizer(br.readLine());
        st2 = new StringTokenizer(br.readLine());
        for(int i =0; i < N;i++){
            A[i] = Integer.parseInt(st.nextToken());
            B[i] = Integer.parseInt(st2.nextToken());
        }

        Arrays.sort(A);
        Arrays.sort(B);
        boolean result = true;
        for(int i =0; i < N; i++){
            if(A[i] != B[i]){
                result = false;
                break;
            }
        }

        System.out.println(result ? "Yes" : "No");
    }
}