import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] input = new int[N * 2];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i =0; i < N * 2; i++){
            input[i] = Integer.parseInt(st.nextToken());
        }
        
        int[][] result = new int[N][2];
        Arrays.sort(input);
        
        for(int i =0; i < N; i++){
            result[i][0] = input[N * 2 - 1- i];
            result[i][1] = input[i];
        }
        int curMax = Integer.MIN_VALUE;
        for(int i =0; i < N; i++){
            int tmp =0;
            for(int j =0; j < 2; j++){
                tmp += result[i][j];
            }
            curMax = Math.max(tmp, curMax);
        }
        System.out.println(curMax);

    }
}