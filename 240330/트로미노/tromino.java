import java.util.*;
import java.io.*;

public class Main {
    static int N, M;
    static int[][] map;
    static boolean[][] isVisited;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    static int result = Integer.MIN_VALUE;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];

        for(int i =0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j =0; j < M; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }// input END

        for(int i =0; i < N; i++){
            for(int j = 0; j < M; j++){
                isVisited = new boolean[N][M];
                DFS(i, j, 1, map[i][j]);
            }
        }

        System.out.println(result);

    }

    public static void DFS(int r, int c, int cnt, int sum){
        if(cnt == 3){
            result= Math.max(result, sum);
            // PrintMap(isVisited);
            return;
        }

        isVisited[r][c] = true;

        for(int d = 0; d < 4; d++){
            int nr = r + dr[d];
            int nc = c + dc[d];
            if(!isIn(nr, nc)){
                continue;
            }
            else if(!isVisited[nr][nc]){
                isVisited[nr][nc] = true;
                DFS(nr, nc, cnt + 1, sum + map[nr][nc]);
                isVisited[nr][nc] = false;
            }
        }
    }
    public static void PrintMap(boolean[][] map){
        for(int i =0; i < N; i++){
            for(int j =0 ;j < M; j++){
                System.out.print(map[i][j] ? 1 : 0);
                System.out.print(" ");
            }
            System.out.println();
        }
        System.out.println();
    }


    public static boolean isIn(int r, int c){
        return !(r <0 || c <0 || r>=N || c>=M);
    }
}