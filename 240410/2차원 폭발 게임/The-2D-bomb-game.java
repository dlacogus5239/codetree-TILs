import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	static int N, M, K;
	static int[][] map;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		for (int i = 0; i < K; i++) {
			Bomb();
			Gravity();
			Rotate();
			Gravity();
		}
		Bomb();
		int answer = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j] != 0) {
					answer++;
				}
			}
		}
		System.out.println(answer);
//		PrintMap();
	}

	public static void Bomb() {
		for (int c = 0; c < N; c++) {
			int cnt = 1;
			for (int r = 0; r < N - 1; r++) {
				if (map[r][c] == map[r + 1][c]) {
					cnt++;
					continue;
				}
				if (map[r][c] != map[r + 1][c]) {
					if (cnt >= M) {
						for (int i = r; i > r - cnt; i--) {
							map[i][c] = 0;
						}
					}
					cnt = 1;
				}
			}
			if (cnt >= M) {
				for (int i = N - 1; i > N - 1 - cnt; i--) {
					map[i][c] = 0;
				}
			}

		}

	}

	public static void Gravity() {
		Stack<Integer> s = new Stack<>();
		for (int c = 0; c < N; c++) {
			for (int r = 0; r < N; r++) {
				if (map[r][c] != 0) {
					s.add(map[r][c]);
				}
			}

			for (int r = N - 1; r >= 0; r--) {
				if (!s.isEmpty()) {
					map[r][c] = s.pop();
				} else {
					map[r][c] = 0;
				}
			}

		}

	}

	/***
	 * Rotation은 기억해 두자
	 */
	public static void Rotate() {
		int[][] nextMap = new int[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				nextMap[i][j] = map[N - 1 - j][i];
			}
		}
		CopyMap(nextMap);

	}

	public static void CopyMap(int[][] nextMap) {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				map[i][j] = nextMap[i][j];
			}
		}
	}

	public static boolean isIn(int r, int c) {
		return !(r < 0 || c < 0 || r >= N || c >= N);
	}

	public static void PrintMap() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				sb.append(map[i][j] + " ");
			}
			sb.append("\n");
		}
		System.out.println(sb.toString());

		System.out.println();
	}

}