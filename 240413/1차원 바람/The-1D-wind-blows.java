import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	static int N, M, Q;
	static int[][] map;
	static boolean[] isVisited;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		Q = Integer.parseInt(st.nextToken());
		map = new int[N][M];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		} // input END

		isVisited = new boolean[N];
		for (int q = 0; q < Q; q++) {
			st = new StringTokenizer(br.readLine());

			int row = Integer.parseInt(st.nextToken()) - 1;
			char dir = st.nextToken().charAt(0);
			Arrays.fill(isVisited, false);
			if (dir == 'L') {
				pushRight(map[row], row);
			} else {
				pushLeft(map[row], row);
			}
		}
		printMap();
	}

	public static void printMap() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
	}

	// 오른쪽 Shift
	public static void pushRight(int[] col, int row) {
		if (row < 0 || row >= N) {
			return;
		}
		if (isVisited[row]) {
			return;
		}
		isVisited[row] = true;
		int[] next = new int[col.length];
		for (int i = col.length - 1; i > 0; i--) {
			next[i] = col[i - 1];
		}

		boolean upGo = false, downGo = false;
		next[0] = col[col.length - 1];
		for (int i = 0; i < col.length; i++) {
			map[row][i] = next[i];
			if (row - 1 >= 0) {
				if (map[row - 1][i] == map[row][i]) {
					upGo = true;
				}
			}
			if (row + 1 < N) {
				if (map[row + 1][i] == map[row][i]) {
					downGo = true;
				}
			}
		}
		// Copy완료

		if (upGo) {
			pushLeft(map[row - 1], row - 1);
		}

		if (downGo) {
			pushLeft(map[row + 1], row + 1);
		}
	}

	// 왼쪽Shift
	public static void pushLeft(int[] col, int row) {
//		System.out.println("CUR ROW : " + row);
		if (row < 0 || row >= N) {
			return;
		}
		if (isVisited[row]) {
			return;
		}
		isVisited[row] = true;
		int[] next = new int[col.length];
		for (int i = 0; i < col.length - 1; i++) {
			next[i] = col[i + 1];
		}
		next[col.length - 1] = col[0];
		boolean upGo = false, downGo = false;
		for (int i = 0; i < col.length; i++) {
			map[row][i] = next[i];
			if (row - 1 >= 0) {
				if (map[row - 1][i] == map[row][i]) {
					upGo = true;
				}
			}
			if (row + 1 < N) {
				if (map[row + 1][i] == map[row][i]) {
					downGo = true;
				}
			}
		}

		if (upGo) {
			pushRight(map[row - 1], row - 1);
		}
		if (downGo) {
			pushRight(map[row + 1], row + 1);
		}

//		System.out.println(Arrays.toString(next));
	}
}