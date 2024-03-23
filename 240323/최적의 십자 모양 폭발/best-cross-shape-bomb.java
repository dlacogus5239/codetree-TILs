import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	static int[][] map;
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	static int N;
	static int answer = Integer.MIN_VALUE;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		map = new int[N][N];

		StringTokenizer st;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {

				map[i][j] = Integer.parseInt(st.nextToken());

			}
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				Bomb(i, j);
			}
		}
		System.out.println(answer);
	}

	public static void Bomb(int r, int c) {
		int amount = map[r][c];
		int[][] nextMap = new int[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				nextMap[i][j] = map[i][j];

			}
		}

		nextMap[r][c] = 0;

		for (int k = 1; k < amount; k++) {
			for (int d = 0; d < 4; d++) {
				int nr = r + dr[d] * k;
				int nc = c + dc[d] * k;

				if (!isIn(nr, nc)) {
					continue;
				} else {
					nextMap[nr][nc] = 0;
				}
			}
		}
		Gravity(nextMap);

	}

	public static void Gravity(int[][] curMap) {
		int[][] nextMap = new int[N][N];
		Stack<Integer> s;
		for (int i = 0; i < N; i++) {
			s = new Stack<>();
			for (int j = 0; j < N; j++) {
				if (curMap[j][i] != 0) {
					s.add(curMap[j][i]);
				}
			}

			for (int j = N - 1; j >= 0; j--) {
				if (s.isEmpty()) {
					break;
				}
				nextMap[j][i] = s.pop();
			}
		}
//		PrintMap(nextMap);

		int cnt = 0;
		for (int c = 0; c < N; c++) {
			for (int r = 1; r < N; r++) {
				if (nextMap[r - 1][c] == 0) {
					continue;
				}
				if (nextMap[r - 1][c] == nextMap[r][c]) {
					cnt++;
				}
			}
		}

		for (int r = 0; r < N; r++) {
			for (int c = 1; c < N; c++) {
				if (nextMap[r][c - 1] == 0) {
					continue;
				}
				if (nextMap[r][c - 1] == nextMap[r][c]) {
					cnt++;
				}
			}
		}

//		System.out.println(cnt);
//		System.out.println();
		answer = Math.max(answer, cnt);

	}

	public static void PrintMap(int[][] map) {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
	}

	public static boolean isIn(int r, int c) {
		return !(r < 0 || c < 0 || r >= N || c >= N);
	}
}