import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	static class Bomb {
		int r, c;

		public Bomb(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}

		@Override
		public String toString() {
			return "Bomb [r=" + r + ", c=" + c + "]";
		}

	}

	static ArrayList<Bomb> bombs = new ArrayList<>();
	static int N, M;

	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	static int[][] bombMap;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		bombMap = new int[N][N];
		M = Integer.parseInt(st.nextToken());
		int r = Integer.parseInt(st.nextToken()) - 1;
		int c = Integer.parseInt(st.nextToken()) - 1;

		bombs.add(new Bomb(r, c));
		// 1이면 폭탄 있는 곳
		bombMap[r][c] = 1;

		for (int time = 1; time <= M; time++) {
			int len = bombs.size();
			// Generate Bomb
			for (int i = 0; i < len; i++) {
				Bomb cur = bombs.get(i);
				int distance = (int) Math.pow(2, time - 1);

				for (int d = 0; d < 4; d++) {
					int nr = cur.r + dr[d] * distance;
					int nc = cur.c + dc[d] * distance;

					if (!isIn(nr, nc)) {
						continue;
					}

					if (bombMap[nr][nc] != 0) {
						continue;
					}
					bombs.add(new Bomb(nr, nc));
					bombMap[nr][nc] = 1;
				}

			}
		}

		System.out.println(bombs.size());

	}

	public static boolean isIn(int r, int c) {
		return !(r < 0 || c < 0 || r >= N || c >= N);
	}

}