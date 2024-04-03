import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int[][] map;
	static int bombCnt = 0;

	static class Bomb {
		int r, c;
		int type;

		public Bomb(int r, int c, int type) {
			super();
			this.r = r;
			this.c = c;
			this.type = type;
		}

		@Override
		public String toString() {
			return "Bomb [r=" + r + ", c=" + c + ", type=" + type + "]";
		}

	}

	// 2번 폭탄 터뜨리기
	static int[] dr2 = { -1, 1, 0, 0 };
	static int[] dc2 = { 0, 0, -1, 1 };

	// 3번 폭탄 터뜨리기
	static int[] dr3 = { -1, -1, 1, 1 };
	static int[] dc3 = { -1, 1, -1, 1 };
	static ArrayList<Bomb> bombs = new ArrayList<>();

	static int result = 0;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		StringTokenizer st;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 1) {
					bombCnt++;
					bombs.add(new Bomb(i, j, -1));
				}
			}
		} // input END
		setBomb(0);

		System.out.println(result);
	}

	public static void setBomb(int cnt) {
		if (cnt == bombCnt) {
//			System.out.println();
//			for (int i = 0; i < bombCnt; i++) {
//				System.out.println(bombs.get(i));
//			}

			Boom();
			return;
		}

		for (int i = 1; i <= 3; i++) {
			bombs.get(cnt).type = i;
			setBomb(cnt + 1);
		}

	}

	public static void Boom() {
		int[][] bombMap = new int[N][N];

		for (int i = 0; i < bombCnt; i++) {
			Bomb cur = bombs.get(i);
			bombMap[cur.r][cur.c] = 1;

			switch (cur.type) {
			case 1:
				for (int j = 0; j <= 2; j++) {
					if (!isIn(cur.r + j, cur.c)) {
						continue;
					} else {
						bombMap[cur.r + j][cur.c] = 1;
					}
				}
				for (int j = 0; j <= 2; j++) {
					if (!isIn(cur.r - j, cur.c)) {
						continue;
					} else {
						bombMap[cur.r - j][cur.c] = 1;
					}
				}
				break;
			case 2:
				for (int d = 0; d < 4; d++) {
					int nr = cur.r + dr2[d];
					int nc = cur.c + dc2[d];

					if (!isIn(nr, nc)) {
						continue;
					}

					bombMap[nr][nc] = 1;
				}
				break;
			case 3:
				for (int d = 0; d < 4; d++) {
					int nr = cur.r + dr3[d];
					int nc = cur.c + dc3[d];

					if (!isIn(nr, nc)) {
						continue;
					}

					bombMap[nr][nc] = 1;
				}
				break;

			}
		}

		int isBoom = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
//				System.out.print(bombMap[i][j] + " ");
				if (bombMap[i][j] == 1) {
					isBoom++;
				}
			}
//			System.out.println();
		}

//		System.out.println("BOMBED => " + isBoom);

		result = Math.max(isBoom, result);
	}

	public static boolean isIn(int r, int c) {
		return !(r < 0 || c < 0 || r >= N || c >= N);
	}

}