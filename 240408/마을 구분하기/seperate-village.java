import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int[][] map;
	static int[][] villageMap;
	static boolean[][] isVisited;
	static int N;

	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		StringTokenizer st;
		map = new int[N][N];
		isVisited = new boolean[N][N];
		villageMap = new int[N][N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		// input end
		int cnt = 1;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j] == 1 && !isVisited[i][j]) {
					DFS(i, j, cnt++);
				}
			}
		}
		int[] village = new int[cnt];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (villageMap[i][j] == 0) {
					continue;
				} else {
					village[villageMap[i][j]]++;
				}
			}
		}
		Arrays.sort(village);
		System.out.println(cnt - 1);
		for (int i = 1; i < village.length; i++) {
			System.out.println(village[i]);
		}
	}

	public static void DFS(int r, int c, int cnt) {
		if (isVisited[r][c]) {
			return;
		}
		isVisited[r][c] = true;
		villageMap[r][c] = cnt;
		for (int d = 0; d < 4; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];
			if (!isIn(nr, nc)) {
				continue;
			}
			if (map[nr][nc] == 1) {
				DFS(nr, nc, cnt);
			}
		}

	}

	public static boolean isIn(int r, int c) {
		return !(r < 0 || c < 0 || r >= N || c >= N);
	}

}