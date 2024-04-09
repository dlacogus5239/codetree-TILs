import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	static int N, M;
	static int[][] map;

	// 우 하 상 좌
	static int[] dr = { 0, 1, 0, -1 };
	static int[] dc = { 1, 0, -1, 0 };
	static int r = 0, c = 0;
	static int d = 0;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];

		for (int i = 0; i < N; i++) {
			Arrays.fill(map[i], -1);
		}
		int cnt = 0;
		map[r][c] = cnt++;
		while (cnt < N * M) {
			int nr = r + dr[d];
			int nc = c + dc[d];
			if (!isIn(nr, nc)) {
				d = (d + 1) % 4;
				continue;
			}

			if (map[nr][nc] != -1) {
				d = (d + 1) % 4;
				continue;
			}

			r = nr;
			c = nc;
			map[r][c] = cnt++;
		}
		printMap();
	}

	public static void printMap() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				char tmp = (char) (map[i][j] % 26 + 'A');
				System.out.print(tmp + " ");
			}

			System.out.println();
		}
	}

	public static boolean isIn(int r, int c) {
		return !(r < 0 || c < 0 || r >= N || c >= M);
	}

}