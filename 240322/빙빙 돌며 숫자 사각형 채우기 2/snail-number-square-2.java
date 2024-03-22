import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static int[][] map;

	// 하우상좌
	static int[] dr = { 1, 0, -1, 0 };
	static int[] dc = { 0, 1, 0, -1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		int dir = 0;

		int r = 0;
		int c = 0;

		map = new int[N][M];
		for (int i = 1; i <= N * M; i++) {
			map[r][c] = i;

			int nr = r + dr[dir];
			int nc = c + dc[dir];

			if (!IsIn(nr, nc)) {
				dir = (dir + 1) % 4;
			} else if (map[nr][nc] != 0) {
				dir = (dir + 1) % 4;
			}
			r += dr[dir];
			c += dc[dir];
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
	}

	public static boolean IsIn(int r, int c) {
		return !(r < 0 || c < 0 || r >= N || c >= M);
	}

}