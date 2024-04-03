import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static int N, M, T;

	static class Marble implements Comparable<Marble> {
		int num;
		int r, c;
		int weight;
		int direction;

		public Marble(int num, int r, int c, int weight, int direction) {
			super();
			this.num = num;
			this.r = r;
			this.c = c;
			this.weight = weight;
			this.direction = direction;
		}

		@Override
		public String toString() {
			return "Marble [num=" + num + ", r=" + r + ", c=" + c + ", weight=" + weight + ", direction=" + direction
					+ "]";
		}

		// 내림차순 정렬
		@Override
		public int compareTo(Marble o) {
			return o.num - this.num;
		}

	}

	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };
	static ArrayList<Marble> marbles = new ArrayList<>();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		for (int i = 1; i <= M; i++) {
			st = new StringTokenizer(br.readLine());

			int r = Integer.parseInt(st.nextToken()) - 1;
			int c = Integer.parseInt(st.nextToken()) - 1;
			int direction = parseDirection(st.nextToken().charAt(0));
			int weight = Integer.parseInt(st.nextToken());
			marbles.add(new Marble(i, r, c, weight, direction));
		}

		for (int t = 0; t < T; t++) {
			Move();
		}

		int max = -1;
		for (int i = 0; i < marbles.size(); i++) {
			max = Math.max(max, marbles.get(i).weight);
		}

		System.out.println(marbles.size() + " " + max);

	}

	public static void Move() {
		PriorityQueue<Marble>[][] nextMap = new PriorityQueue[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				nextMap[i][j] = new PriorityQueue<>();
			}
		}
		int len = marbles.size();
		// 방향 전환하거나, 다음 위치로 이동시키기
		for (int i = 0; i < len; i++) {
			Marble cur = marbles.get(i);

			int nr = cur.r + dr[cur.direction];
			int nc = cur.c + dc[cur.direction];

			if (!isIn(nr, nc)) {
				cur.direction ^= 1;
				nextMap[cur.r][cur.c].offer(new Marble(cur.num, cur.r, cur.c, cur.weight, cur.direction));
				continue;
			}
			nextMap[nr][nc].offer(new Marble(cur.num, nr, nc, cur.weight, cur.direction));
			marbles.get(i).r = nr;
			marbles.get(i).c = nc;
		}
		marbles.clear();

		// 우선순위 큐 배열 순회하면서 size가 있으면 처리
		// size == 1 이면 다시 marbles에 넣어주고,
		// size >=2 이면 가장 큰 번호 구슬로 합쳐진다

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (nextMap[i][j].size() == 0) {
					continue;
				} else if (nextMap[i][j].size() == 1) {
					Marble tmp = nextMap[i][j].poll();

					marbles.add(new Marble(tmp.num, tmp.r, tmp.c, tmp.weight, tmp.direction));
				} else if (nextMap[i][j].size() >= 2) {
					// 가장 처음에 있는 구슬의 번호가 가장 크다(PriorityQueue)
					Marble tmp = nextMap[i][j].poll();

					while (!nextMap[i][j].isEmpty()) {
						Marble cur = nextMap[i][j].poll();
						tmp.weight += cur.weight;
					}

					marbles.add(new Marble(tmp.num, tmp.r, tmp.c, tmp.weight, tmp.direction));
				}
			}
		}
	}

	public static int parseDirection(char d) {
		int direction = -1;
		switch (d) {
		case 'U':
			direction = 0;
			break;
		case 'D':
			direction = 1;
			break;
		case 'L':
			direction = 2;
			break;
		case 'R':
			direction = 3;
			break;
		}
		return direction;
	}

	public static boolean isIn(int r, int c) {
		return !(r < 0 || c < 0 || r >= N || c >= N);
	}

}