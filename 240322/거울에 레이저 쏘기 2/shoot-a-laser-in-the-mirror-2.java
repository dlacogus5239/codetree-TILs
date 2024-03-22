import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	static int N;
	// 하 좌 상 우
	static int[] dr = { 1, 0, -1, 0 };
	static int[] dc = { 0, -1, 0, 1 };
	static int r, c, dir;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		char[][] map = new char[N][N];

		for (int i = 0; i < N; i++) {
			map[i] = br.readLine().toCharArray();
		}

		int K = Integer.parseInt(br.readLine());

		Init(K);

		int cnt = 0;
		while (isIn(r, c)) {

			if (map[r][c] == '/') {
				dir ^= 1;
			} else {
				dir = 3 - dir;
			}
			r = r + dr[dir];
			c = c + dc[dir];

			cnt++;
		}

		System.out.println(cnt);
	}

	public static void Init(int K) {
		if (K <= N) {
			r = 0;
			c = K - 1;
			dir = 0;
		} else if (K <= 2 * N) {
			r = K - N - 1;
			c = N - 1;
			dir = 1;
		} else if (K <= 3 * N) {
			r = N - 1;
			c = N - (K - 2 * N);
			dir = 2;
		} else {
			r = N - (K - 3 * N);
			c = 0;
			dir = 3;
		}
	}

	public static boolean isIn(int r, int c) {
		return !(r < 0 || c < 0 || r >= N || c >= N);
	}

}