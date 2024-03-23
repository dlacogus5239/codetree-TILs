import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static char[][] map;

	// 우하좌상(Clockwise)
	static int[] dr = { 0, 1, 0, -1 };
	static int[] dc = { 1, 0, -1, 0 };
	static int r, c;
	static int sR, sC;
	static int dir = 0;

	static final int INF = 10_001;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		r = Integer.parseInt(st.nextToken()) - 1;
		c = Integer.parseInt(st.nextToken()) - 1;
		sR = r;
		sC = c;
		map = new char[N][N];
		for (int i = 0; i < N; i++) {
			map[i] = br.readLine().toCharArray();
		}
		Move();
	}

	public static void Move() {
		int time = 0;
		boolean flag = true;
		loop: while (time < INF) {
			int nr = r + dr[dir];
			int nc = c + dc[dir];

			if (!isIn(nr, nc)) {
				time++;
				break loop;
			}
			if (map[nr][nc] == '#') {
				int change = 0;
				for (change = 0; change < 4; change++) {
					dir = (dir + 3) % 4;
					nr = r + dr[dir];
					nc = c + dc[dir];

					if (!isIn(nr, nc)) {
						time++;
						break loop;
					}
					if (map[nr][nc] == '.') {
						break;
					}
				}

				if (change == 4) {
					time = -1;
					break loop;
				}
			}
			if (map[nr][nc] == '.') {
				r = nr;
				c = nc;
				time++;

				int tmpR = r + dr[(dir + 1) % 4];
				int tmpC = c + dc[(dir + 1) % 4];

				if (map[tmpR][tmpC] == '#') {
					continue;
				} else {
					dir = (dir + 1) % 4;
					r = tmpR;
					c = tmpC;
					time++;
				}
			}

		}
		if (time >= INF) {
			time = -1;
		}
		System.out.println(time);

	}

	public static boolean isIn(int r, int c) {
		return !(r < 0 || c < 0 || r >= N || c >= N);
	}
}