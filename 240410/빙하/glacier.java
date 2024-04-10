import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };
	static int[][] map, nextMap;
	static boolean[][] isSurrounded; // isSurrounded => true : 빙하에 둘러쌓이지 않음
	static int N, M;
	static int iceCnt = 0, curCnt;
	static Queue<int[]> q;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		nextMap = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 1) {
					iceCnt++;
				}
			}
		}
		q = new LinkedList<>();
		int cnt = 1;
		while (iceCnt > 0) {
			curCnt = 0;
			isSurrounded();
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if (map[i][j] == 0 && isSurrounded[i][j]) {
						q.offer(new int[] { i, j });
					}
				}
			}
			while (!q.isEmpty()) {
				int[] cur = q.poll();
				for (int d = 0; d < 4; d++) {
					int nr = cur[0] + dr[d];
					int nc = cur[1] + dc[d];
					if (!isIn(nr, nc)) {
						continue;
					}
					if (map[nr][nc] == 1) {
						map[nr][nc] = 0;
						curCnt++;
						nextMap[nr][nc] = cnt;
					}
				}
			}
			iceCnt -= curCnt;
			cnt++;
//			PrintMap();
		}
		System.out.printf("%d %d\n", cnt - 1, curCnt);

	}

	// 빙하에 둘러싸이지 않은 곳 구함
	public static void isSurrounded() {
		isSurrounded = new boolean[N][M];
		Queue<int[]> q = new LinkedList<>();

		q.offer(new int[] { 0, 0 });
		while (!q.isEmpty()) {
			int[] cur = q.poll();
			isSurrounded[cur[0]][cur[1]] = true;

			for (int d = 0; d < 4; d++) {
				int nr = cur[0] + dr[d];
				int nc = cur[1] + dc[d];
				if (!isIn(nr, nc)) {
					continue;
				}
				if (!isSurrounded[nr][nc] && map[nr][nc] == 0) {
					isSurrounded[nr][nc] = true;
					q.offer(new int[] { nr, nc });
				}
			}
		}
//		PrintSroundMap();

	}

	public static void PrintSroundMap() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				System.out.print(isSurrounded[i][j] ? 1 + " " : 0 + " ");
			}
			System.out.println();
		}
		System.out.println();
	}

	public static void PrintMap() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				System.out.print(nextMap[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}

	public static boolean isIn(int r, int c) {
		return !(r < 0 || c < 0 || r >= N || c >= M);
	}

}