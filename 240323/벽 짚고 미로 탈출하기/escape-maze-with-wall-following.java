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
		loop: while (true) {
//			System.out.println("[" + r + ", " + c + "]");

			int nr = r + dr[dir];
			int nc = c + dc[dir];
			if (!isIn(nr, nc)) {// 탈출
				time++;
				break;
			}

			if (map[nr][nc] == '#') { // 벽인 경우
				int change = 1;
				for (change = 1; change <= 3; change++) {
					dir = (dir + 1) % 4;
					nr = r + dr[dir];
					nc = c + dc[dir];
					if (!isIn(nr, nc)) {
						time++;
						break loop;
					} else if (map[nr][nc] == '#') {
						continue;
					} else if (map[nr][nc] == '.') {
						break;
					}
				}

				if (change == 4) {
					time = -1;
					break loop;
				}
				continue;
			} else if (map[nr][nc] == '.') { // 빈공간인경우
				// 오른쪽 검사
				int tmpR = nr + dr[(dir + 1) % 4];
				int tmpC = nc + dc[(dir + 1) % 4];

				if (map[tmpR][tmpC] == '#') {// 오른쪽에 벽이 있으면
					// 이동
					r = nr;
					c = nc;
					time++;
				} else { // 벽이 없는 경우
					dir = (dir + 1) % 4; // 방향 바꿔주고
					r = nr + dr[dir]; // 앞에 이동한거 더해서
					c = nc + dc[dir]; // 이동 한번 더

					time += 2;
				}
			}

			if ((r == sR && c == sC) || time >= INF || !flag) {
				time = -1;
				break;
			}

		}

		System.out.println(time);

	}

	public static boolean isIn(int r, int c) {
		return !(r < 0 || c < 0 || r >= N || c >= N);
	}
}