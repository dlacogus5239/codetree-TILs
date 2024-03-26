import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	// 12시부터 Clockwise
	static int[] dr = { -1, -1, 0, 1, 1, 1, 0, -1, };
	static int[] dc = { 0, 1, 1, 1, 0, -1, -1, -1 };
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
		for (int t = 0; t < M; t++) {

			findNumber: for (int num = 1; num <= N * N; num++) {
				for (int i = 0; i < N; i++) {
					for (int j = 0; j < N; j++) {
						if (map[i][j] == num) {
							int max = -1;
							int[] maxIdx = new int[2];

							for (int d = 0; d < 8; d++) {
								int nr = i + dr[d];
								int nc = j + dc[d];

								if (!isIn(nr, nc)) {
									continue;
								}

								if (map[nr][nc] > max) {
									max = map[nr][nc];
									maxIdx[0] = nr;
									maxIdx[1] = nc;
								}
							}

							map[i][j] = max;
							map[maxIdx[0]][maxIdx[1]] = num;
							continue findNumber;
						}
					}
				}
			}

		}

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