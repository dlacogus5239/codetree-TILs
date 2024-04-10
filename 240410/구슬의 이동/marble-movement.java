import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static class Marble implements Comparable<Marble> {
		int num;
		int r, c;
		int dir;
		int speed;

		public Marble(int num, int r, int c, int dir, int speed) {
			super();
			this.num = num;
			this.r = r;
			this.c = c;
			this.dir = dir;
			this.speed = speed;
		}

		@Override
		public String toString() {
			return "Marble [num=" + num + ", dir=" + dir + "]";
		}

		@Override
		public int compareTo(Marble o) {
			if (this.speed != o.speed) {
				return o.speed - this.speed;
			}
			return o.num - this.num;
		}

	}

	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };
	static int N, M, T, K; // 격자 크기, 구슬개수, 시간, 최대 구슬 수
	static ArrayList<Marble> marbles = new ArrayList<>();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		for (int i = 1; i <= M; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken()) - 1;
			int c = Integer.parseInt(st.nextToken()) - 1;
			int dir = parseDirection(st.nextToken().charAt(0));
			int speed = Integer.parseInt(st.nextToken());
			marbles.add(new Marble(i, r, c, dir, speed));
		}

		for (int i = 0; i < T; i++) {
			Move();
		}

		System.out.println(marbles.size());

	}

	public static void Move() {
		PriorityQueue<Marble>[][] map = new PriorityQueue[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				map[i][j] = new PriorityQueue<>();
			}
		}

		for (int i = 0; i < marbles.size(); i++) {
			Marble cur = marbles.get(i);
			int nr = -1, nc = -1;
			for (int j = 0; j < cur.speed; j++) {
				nr = cur.r + dr[cur.dir];
				nc = cur.c + dc[cur.dir];

				if (!isIn(nr, nc)) {
					cur.dir ^= 1;
					nr = cur.r + dr[cur.dir];
					nc = cur.c + dc[cur.dir];
				}
				cur.r = nr;
				cur.c = nc;
			}
			map[nr][nc].offer(cur);
		} // 구슬 이동

		marbles.clear();
		// map에 기록된 구슬들 다시 넣기
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
//				System.out.print(map[i][j].toString());
				if (map[i][j].isEmpty()) {
					continue;
				}
				for (int j2 = 0; j2 < K; j2++) {
					marbles.add(map[i][j].poll());
					if (map[i][j].isEmpty()) {
						break;
					}
				}
			}
//			System.out.println();
		}

	}

	public static boolean isIn(int r, int c) {
		return !(r < 0 || c < 0 || r >= N || c >= N);
	}

	public static int parseDirection(char d) {
		int result = -1;
		switch (d) {
		case 'U':
			result = 0;
			break;
		case 'D':
			result = 1;
			break;
		case 'L':
			result = 2;
			break;
		case 'R':
			result = 3;
			break;

		}

		return result;
	}

}