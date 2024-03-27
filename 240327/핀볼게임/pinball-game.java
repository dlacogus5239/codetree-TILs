import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	// 하좌상우 Clockwise
	static int[] dr = { 1, 0, -1, 0 };
	static int[] dc = { 0, -1, 0, 1 };

	static int N;
	static int[][] map;
	static int r, c;
	static int dir;
	static int result = Integer.MIN_VALUE;

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
		// input END

		for (int i = 0; i < 4 * N; i++) {
			// 구슬 던질 위치 정해주기
			StartPosInit(i);
//			System.out.printf("[R : %d, C : %d ] dir : %d\n", r, c, dir);

			int time = 1;
			// move START
			while (isIn(r, c)) {
				time++;

				// 하 0 <-> 좌 1
				// 상 2 <-> 우 3
				if (map[r][c] == 1) {
					// XOR
					dir ^= 1;
				} // 하 0 <-> 우 3
					// 상 2 <-> 좌 1
				else if (map[r][c] == 2) {
					dir ^= 3;
				}

				r = r + dr[dir];
				c = c + dc[dir];

			}
			result = Math.max(time, result);
		}

		System.out.println(result);

	}

	public static void StartPosInit(int start) {

		if (start < N) { // 위에서 아래
			dir = 0;
			r = 0;
			c = start;
		} else if (N <= start && start < N * 2) { // 오른쪽에서 왼쪽
			dir = 1;
			r = start % N;
			c = N - 1;
		} else if (N * 2 <= start && start < N * 3) { // 아래서 위
			dir = 2;
			r = N - 1;
			c = N - 1 - (start % N);
		} else { // 왼쪽에서 오른쪽
			dir = 3;
			r = (N - 1) - (start % N);
			c = 0;
		}
	}

	public static boolean isIn(int r, int c) {
		return !(r < 0 || c < 0 || r >= N || c >= N);
	}

}