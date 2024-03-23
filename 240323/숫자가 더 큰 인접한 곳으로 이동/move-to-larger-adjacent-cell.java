import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	static int N;
	// 상하좌우
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	static int[][] map;
	static boolean[][] isVisited;

	static ArrayList<Integer> result = new ArrayList<>();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int r, c;
		N = Integer.parseInt(st.nextToken());
		r = Integer.parseInt(st.nextToken()) - 1;
		c = Integer.parseInt(st.nextToken()) - 1;
		map = new int[N][N];
		isVisited = new boolean[N][N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		result.add(map[r][c]);
		DFS(r, c);
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < result.size(); i++) {
			sb.append(result.get(i)).append(" ");
		}
		System.out.println(sb.toString());

	}

	public static void DFS(int r, int c) {
		for (int d = 0; d < 4; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];
			if (!isIn(nr, nc)) {
				continue;
			}
			if (!isVisited[nr][nc] && map[nr][nc] > map[r][c]) {
				result.add(map[nr][nc]);
				DFS(nr, nc);
				break;
			}
		}

		return;
	}

	public static boolean isIn(int r, int c) {
		return !(r < 0 || c < 0 || r >= N || c >= N);
	}

}