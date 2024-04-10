import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	static int N, M;
	static int[][] map;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		for (int i = 0; i < M; i++) {
			int col = Integer.parseInt(br.readLine()) - 1;
			SelectBomb(col);
		}
		PrintMap();

	}

	public static void SelectBomb(int col) {
		for (int i = 0; i < N; i++) {
			if (map[i][col] != 0) {
				Bomb(i, col);
				break;
			}
		}

	}

	public static void Bomb(int r, int c) {
		int amount = map[r][c];
		map[r][c] = 0;
		for (int i = 1; i < amount; i++) {
			for (int d = 0; d < 4; d++) {
				int nr = r + dr[d] * i;
				int nc = c + dc[d] * i;

				if (!isIn(nr, nc)) {
					continue;
				}

				map[nr][nc] = 0;
			}
		}

		Gravity();

	}

	public static void PrintMap() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
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

	public static boolean isIn(int r, int c) {
		return !(r < 0 || c < 0 || r >= N || c >= N);
	}
}