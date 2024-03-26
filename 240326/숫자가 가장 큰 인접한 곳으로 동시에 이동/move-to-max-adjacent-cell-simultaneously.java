import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	// 우좌하상
	static int[] dr = { 0, 0, 1, -1 };
	static int[] dc = { 1, -1, 0, 0 };

	// 격자 크기, 구슬 개수, 시간
	static int N, M, T;
	static int[][] map, count, nextCount;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());

		map = new int[N][N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		count = new int[N][N];
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken()) - 1;
			int c = Integer.parseInt(st.nextToken()) - 1;

			count[r][c] = 1;
		}

		for (int time = 0; time < T; time++) {
//			PrintMap(count);
//			System.out.println();
			nextCount = new int[N][N];
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (count[i][j] == 1) {
						int max = 0;
						int[] maxIdx = new int[2];
						for (int d = 0; d < 4; d++) {
							int nr = i + dr[d];
							int nc = j + dc[d];
							if (!isIn(nr, nc)) {
								continue;
							}

							if (max <= map[nr][nc]) {
								maxIdx[0] = nr;
								maxIdx[1] = nc;
								max = map[nr][nc];
							}

						}
						nextCount[maxIdx[0]][maxIdx[1]]++;
					}
				}
			}
//			PrintMap(nextCount);
//			System.out.println();
			count = new int[N][N];
			// copy Map
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (nextCount[i][j] == 1) {
						count[i][j] = nextCount[i][j];
					} else if (nextCount[i][j] >= 2) {
						count[i][j] = 0;
					} else {
						count[i][j] = 0;
					}
				}
			}

		}

		int result = 0;

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (count[i][j] == 1) {
					result++;
				}
			}
		}

		System.out.println(result);

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