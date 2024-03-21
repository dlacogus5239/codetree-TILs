import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	// 우하좌상
	static int[] dr = { 0, 1, 0, -1 };
	static int[] dc = { 1, 0, -1, 0 };

	static int N, M;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		int[][] map = new int[N][M];

		int dir = 0;
		int[] pos = { 0, 0 };
		map[0][0] = 1;

		for (int i = 2; i <= N * M; i++) {

			int nr = pos[0] + dr[dir];
			int nc = pos[1] + dc[dir];

			if (!isIn(nr, nc) || map[nr][nc] != 0) {
				dir = (dir + 1) % 4;
				nr = pos[0] + dr[dir];
				nc = pos[1] + dc[dir];
			}
			map[nr][nc] = i;
			pos[0] = nr;
			pos[1] = nc;

		}
//		System.out.println(pos[0] + " " + pos[1]);
//		System.out.println(dir);
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
	}

	public static boolean isIn(int r, int c) {
		return !(r < 0 || c < 0 || r >= N || c >= M);
	}

}