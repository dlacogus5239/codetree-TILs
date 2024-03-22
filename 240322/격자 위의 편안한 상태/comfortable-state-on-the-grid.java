import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		StringBuilder sb = new StringBuilder();
		int[][] map = new int[N][N];
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken()) - 1;
			int c = Integer.parseInt(st.nextToken()) - 1;
			map[r][c] = 1;

			int cnt = 0;
			for (int d = 0; d < 4; d++) {
				int nr = r + dr[d];
				int nc = c + dc[d];

				if (!isIn(nr, nc)) {
					continue;
				}
				if (map[nr][nc] == 1) {
					cnt++;
				}
			}

			if (cnt == 3) {
				sb.append(1).append("\n");
			} else {
				sb.append(0).append("\n");
			}
		}

		System.out.println(sb.toString());
	}

	public static boolean isIn(int r, int c) {
		return !(r < 0 || c < 0 || r >= N || c >= N);
	}

}