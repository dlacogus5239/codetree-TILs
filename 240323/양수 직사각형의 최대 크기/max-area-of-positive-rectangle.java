import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int[][] map;
	static int N, M;

	static int answer = -1;
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		int answer = -1;
		for (int h = 1; h <= N; h++) { // 직사각형 높이
			for (int w = 1; w <= M; w++) { // 직사각형 너비

				for (int i = 0; i <= N - h; i++) { // 처음좌표부터 탐색 시작
					for (int j = 0; j <= M - w; j++) {
						if (isPositive(h, w, i, j)) {
							answer = Math.max(answer, h * w);
						}
					}
				}
			}
		}

		System.out.println(answer);
	}

	public static boolean isPositive(int h, int w, int r, int c) {
		boolean flag = true;
		Check: for (int i = r; i < r + h; i++) {
			for (int j = c; j < c + w; j++) {
				if (map[i][j] < 0) {
					flag = false;
					break Check;
				}
			}
		}

		return flag;
	}

	public static boolean IsIn(int r, int c) {
		return !(r < 0 || c < 0 || r >= N || c >= M);
	}

}