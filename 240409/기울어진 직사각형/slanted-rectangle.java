import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int[] dr = { -1, -1, 1, 1 };
	static int[] dc = { 1, -1, -1, 1 };
	static int N;
	static int[][] map;
	static int result = 0;
	static int sr, sc;

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

		for (int i = N - 1; i >= 2; i--) {
			for (int j = N - 2; j > 0; j--) {
				sr = i;
				sc = j;
				DFS(i, j, 0, 0);
			}
		}
		System.out.println(result);
	}

	public static void DFS(int r, int c, int direction, int sum) {
		// 들어온 값이 격자 밖이면 이동못하는 곳
		if (!isIn(r, c)) {
			return;
		}

		// 현재 map의 값 더하기
		sum += map[r][c];

		if (direction == 3) { // 마지막 방향일 경우
			if (r == sr && c == sc) { // 처음 시작점으로 돌아왔을 경우
				result = Math.max(result, sum - map[r][c]);
				return;
			}
			int nr = r + dr[direction];
			int nc = c + dc[direction];
			DFS(nr, nc, direction, sum);
		} else {
			// 현재 방향으로 이동
			int nr = r + dr[direction];
			int nc = c + dc[direction];
			DFS(nr, nc, direction, sum);
			// 꺾어서 이동
			nr = r + dr[direction + 1];
			nc = c + dc[direction + 1];
			DFS(nr, nc, direction + 1, sum);
		}

	}

	public static boolean isIn(int r, int c) {
		return !(r < 0 || c < 0 || r >= N || c >= N);
	}

}