import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	// Start --> 1

	static int N, M, r, c;

	static int[][] map;

	static int topSide = 1, leftSide = 4, upSide = 5;

	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		r = Integer.parseInt(st.nextToken()) - 1;
		c = Integer.parseInt(st.nextToken()) - 1;

		map = new int[N][N];

		map[r][c] = 6;
		st = new StringTokenizer(br.readLine());

		for (int o = 0; o < M; o++) {
			char oper = st.nextToken().charAt(0);
			int dir = -1;
			switch (oper) {
			case 'U':
				dir = 0;
				break;
			case 'D':
				dir = 1;
				break;
			case 'R':
				dir = 3;
				break;
			case 'L':
				dir = 2;
				break;
			}
			RollDice(dir);
		}
		map[r][c] = 7 - topSide;

		int result = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
//				System.out.print(map[i][j] + " ");
				if (map[i][j] != 0) {
					result += map[i][j];
				}
			}
//			System.out.println();
		}

		System.out.println(result);

	}

	public static void RollDice(int dir) {
		int nr = r + dr[dir];
		int nc = c + dc[dir];

		if (!isIn(nr, nc)) {
			return;
		}
//		System.out.printf("BEFOR : [ %d , %d ]\n", r, c);
		map[r][c] = 7 - topSide;
		r = nr;
		c = nc;
//		System.out.printf("AFTER : [ %d , %d ]\n", r, c);
		int tmp = -1;
		tmp = topSide;
		switch (dir) {
		case 0: // case Up
			topSide = 7 - upSide;
			upSide = tmp;
			break;
		case 1: // case Down
			topSide = upSide;
			upSide = 7 - tmp;
			break;
		case 2: // case Left
			topSide = 7 - leftSide;
			leftSide = tmp;
			break;
		case 3: // case Right
			topSide = leftSide;
			leftSide = 7 - tmp;
			break;
		default:
			System.out.println("ERROR CASE");
			break;
		}
	}

	public static boolean isIn(int r, int c) {
		return !(r < 0 || c < 0 || r >= N || c >= N);
	}

}