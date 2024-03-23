import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, 1, 0, -1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		int T = Integer.parseInt(st.nextToken());

		char[] operation = br.readLine().toCharArray();

		int[][] map = new int[N][N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		int r = N / 2;
		int c = N / 2;
		int d = 0;

		int answer = map[r][c];
		for (int i = 0; i < T; i++) {
			char cur = operation[i];

			switch (cur) {
			case 'F':
				int nr = r + dr[d];
				int nc = c + dc[d];

				if (!IsIn(nr, nc)) {
					break;
				} else {
					answer += map[nr][nc];
					r = nr;
					c = nc;
				}
				break;

			case 'L':
				d = (d + 3) % 4;

				break;
			case 'R':
				d = (d + 1) % 4;
				break;
			}
		}

		System.out.println(answer);
	}

	public static boolean IsIn(int r, int c) {
		return !(r < 0 || c < 0 || r >= N || c >= N);
	}
}