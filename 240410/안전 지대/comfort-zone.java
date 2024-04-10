import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static int[][] map, nextMap;
	static boolean[][] isVisited;
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		int maxHeight = 0;
		map = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				maxHeight = Math.max(maxHeight, map[i][j]);
			}
		}
		int cntMax = 0;
		int mink = 0;
		int cnt;
		for (int k = maxHeight; k >= 1; k--) {
			cnt = 1;
			isVisited = new boolean[N][M];
			nextMap = new int[N][M];
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if (!isVisited[i][j] && map[i][j] > k) {
						DFS(i, j, cnt++, k);
					}
				}
			}
			if (cntMax <= cnt) {
				mink = k;
				cntMax = cnt;
			}
		}
		System.out.printf("%d %d\n", mink, cntMax - 1);
	}

	public static void DFS(int r, int c, int cnt, int k) {
		if (isVisited[r][c]) {
			return;
		}
		nextMap[r][c] = cnt;
		isVisited[r][c] = true;
		for (int d = 0; d < 4; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];
			if (!isIn(nr, nc)) {
				continue;
			}
			if (map[nr][nc] > k) {
				DFS(nr, nc, cnt, k);
			}
		}

	}

	public static void PrintMap() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				System.out.print(nextMap[i][j] + " ");
			}
			System.out.println();
		}
	}

	public static boolean isIn(int r, int c) {
		return !(r < 0 || c < 0 || r >= N || c >= M);
	}

}