import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	static int[] dr = { 0, -1, 0, 1 };
	static int[] dc = { 1, 0, -1, 0 };
	static int[][] map;
	static int r, c;
	static int d = 0;
	static int N;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];

		r = N / 2;
		c = N / 2;
		int next = 1;
		int moveCnt = 0;
		int changeDir = 0;
		int cnt = 1;
		map[r][c] = cnt++;
		if (N == 1) {
			printMap();
			return;
		}
		r += dr[d];
		c += dc[d];
		map[r][c] = cnt++;
		d = 1;
		changeDir = 1;
		while (cnt <= N * N) {
			if (moveCnt == next) {
				changeDir++;
				d = (d + 1) % 4;
				moveCnt = 0;
			}

			if (changeDir == 2) {
				next += 1;
				changeDir = 0;
			}

			r = r + dr[d];
			c = c + dc[d];
			moveCnt++;
			map[r][c] = cnt++;
		}
		printMap();

	}

	public static void printMap() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
	}

}