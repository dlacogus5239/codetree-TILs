import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static class Cannon {
		int num;
		int r, c;
		int attack;
		int lastTurn;
		boolean isAttacked;

		public Cannon(int num, int r, int c, int attack, int lastTurn, boolean isAttacked) {
			super();
			this.num = num;
			this.r = r;
			this.c = c;
			this.attack = attack;
			this.lastTurn = lastTurn;
			this.isAttacked = isAttacked;
		}

		@Override
		public String toString() {
			return "Cacnnon [num=" + num + ", r=" + r + ", c=" + c + ", attack=" + attack + ", lastTurn=" + lastTurn
					+ ", isAttacked=" + isAttacked + "]";
		}

	}

	static int N, M, K;
	static int[][] map;
	static ArrayList<Cannon> cannons = new ArrayList<>();
	static PriorityQueue<Cannon> doAttack, toAttack; // 공격자 우선, 공격당할 캐논 우선
	static Cannon curAttack, curToAttack;
	// 레이저 공격시 경로 결정
	// 우하좌상 우선순위
	static int[] dr = { 0, 1, 0, -1 };
	static int[] dc = { 1, 0, -1, 0 };

	// 포탑 공격(던지기) 시 8방향 Attack
	static int[] throwDr = { -1, -1, 0, 1, 1, 1, 0, -1 };
	static int[] throwDc = { 0, 1, 1, 1, 0, -1, -1, -1 };

	// 경로 저장용
	static int[][] backR, backC;

	// 현재 턴 저장용
	static int turn;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		int cnt = 1;
		map = new int[N][M];
		doAttack = new PriorityQueue<>(new Comparator<Cannon>() {
			@Override
			public int compare(Cannon o1, Cannon o2) {
				// 공격력이 가장 작은
				if (o1.attack != o2.attack) {
					return o1.attack - o2.attack;
				}
				// 가장 최근에 공격한
				else if (o1.lastTurn != o2.lastTurn) {
					return o2.lastTurn - o1.lastTurn;
				}

				// r + c값이 가장 큰
				else if ((o1.r + o1.c) != (o2.r + o2.c)) {
					return (o2.r + o2.c) - (o1.r + o1.c);
				}

				// 열 값이 가장 큰
				else if (o1.c != o2.c) {
					return o2.c - o1.c;
				}
				return 0;
			}

		});

		toAttack = new PriorityQueue<>(new Comparator<Cannon>() {
			@Override
			public int compare(Cannon o1, Cannon o2) {
				// 공격력이 가장 큰
				if (o1.attack != o2.attack) {
					return o2.attack - o1.attack;
				}
				// 가장 이전에에 공격한
				else if (o1.lastTurn != o2.lastTurn) {
					return o1.lastTurn - o2.lastTurn;
				}

				// r + c값이 가장 작은
				else if ((o1.r + o1.c) != (o2.r + o2.c)) {
					return (o1.r + o1.c) - (o2.r + o2.c);
				} else if (o1.c != o2.c) {
					// 열 값이 가장 작은
					return o1.c - o2.c;
				}
				return 0;
			}

		});

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) { // 처음에는 Cannon 입력 받고, map에 num을 입력하자
				int tmp = Integer.parseInt(st.nextToken());

				if (tmp != 0) {
					cannons.add(new Cannon(cnt, i, j, tmp, -1, false));
					map[i][j] = cnt++;
				} else {
					map[i][j] = tmp;
				}
			}
		}
		for (int t = 1; t <= K; t++) {
			turn = t;
			setAttackerAndTo();
			Laser();

		}
		setAttackerAndTo();
		System.out.println(curToAttack.attack);

	}

	public static void Laser() {
		Queue<int[]> q = new LinkedList<>();
		q.offer(new int[] { curAttack.r, curAttack.c });
		boolean canAttack = false;
		boolean[][] isVisited = new boolean[N][M];
		isVisited[curAttack.r][curAttack.c] = true;
		backR = new int[N][M];
		backC = new int[N][M];
		while (!q.isEmpty()) {
			int[] cur = q.poll();
			if (cur[0] == curToAttack.r && cur[1] == curToAttack.c) {
				canAttack = true;
				break;
			}

			for (int d = 0; d < 4; d++) {
				int nr = (cur[0] + N + dr[d]) % N;
				int nc = (cur[1] + M + dc[d]) % M;

				if (map[nr][nc] == 0) {
					continue;
				} else if (!isVisited[nr][nc]) {
					isVisited[nr][nc] = true;
					backR[nr][nc] = cur[0];
					backC[nr][nc] = cur[1];
					q.offer(new int[] { nr, nc });
				}
			}
		}

		if (canAttack) {
			curToAttack.attack -= curAttack.attack;
			curToAttack.lastTurn = turn;

			if (curToAttack.attack < 0) {
				map[curToAttack.r][curToAttack.c] = 0;
				curToAttack.isAttacked = true;

			}
			int br = backR[curToAttack.r][curToAttack.c];
			int bc = backC[curToAttack.r][curToAttack.c];
			ArrayList<Integer> wayAttack = new ArrayList<>();
			while (!(br == curAttack.r && bc == curAttack.c)) {
				wayAttack.add(map[br][bc]);
				int nextR = backR[br][bc];
				int nextC = backC[br][bc];

				br = nextR;
				bc = nextC;
			}
			for (int i = 0; i < wayAttack.size(); i++) {
				Cannon cur;
				for (int j = 0; j < cannons.size(); j++) {
					if (cannons.get(j).num == wayAttack.get(i)) {
						cur = cannons.get(j);

						cur.attack -= (curAttack.attack) / 2;
						if (cur.attack < 0) {
							cur.attack = 0;
						}
						cur.isAttacked = true;
						cur.lastTurn = turn;
					}

				}
			}

		} else {
			Throw();
		}

		// 하고 다시 맵에 기록

		RefreshCannon();
//		printCannon();
//		printMap(map);
	}

	public static void RefreshCannon() {
		map = new int[N][M];

		for (int i = 0; i < cannons.size(); i++) {
			Cannon cur = cannons.get(i);
			if (cur.attack == 0) {
				cannons.remove(i);
				i--;
			} else {
				if (!cur.isAttacked) {
					cur.attack += 1;
				}
				cur.isAttacked = false;
				map[cur.r][cur.c] = cur.num;
			}
		}
	}

	public static void Throw() {
		System.out.println("THROW");
		curToAttack.attack -= curAttack.attack;
		curToAttack.lastTurn = turn;
		if (curToAttack.attack < 0) {
			curToAttack.attack = 0;
		}

		ArrayList<Integer> wayAttack = new ArrayList<>();
		for (int d = 0; d < 8; d++) {
			int nr = (curToAttack.r + N + dr[d]) % N;
			int nc = (curToAttack.c + M + dc[d]) % M;
			if (map[nr][nc] != 0) {
				wayAttack.add(map[nr][nc]);
			}
		}
		for (int i = 0; i < wayAttack.size(); i++) {
			Cannon cur;
			for (int j = 0; j < cannons.size(); j++) {
				if (cannons.get(j).num == wayAttack.get(i)) {
					cur = cannons.get(j);

					cur.attack -= (curAttack.attack) / 2;
					if (cur.attack < 0) {
						cur.attack = 0;
					}
					cur.isAttacked = true;
					cur.lastTurn = turn;
				}

			}
		}

	}

	// 공격자와 방어?자 선정
	public static void setAttackerAndTo() {
		toAttack.clear();
		doAttack.clear();
		for (int i = 0; i < cannons.size(); i++) {
			if (cannons.get(i).attack == 0) {
				continue;
			}
			toAttack.offer(cannons.get(i));
			doAttack.offer(cannons.get(i));

		}
		curAttack = doAttack.poll();
		curAttack.attack = curAttack.attack + N + M;
		curAttack.isAttacked = true;
		curToAttack = toAttack.poll();
		curToAttack.isAttacked = true;
		// 선정 완료
//		System.out.println(curAttack.toString());
//		System.out.println(curToAttack.toString());
	}

	public static void printCannon() {
		for (int i = 0; i < cannons.size(); i++) {
			System.out.println(cannons.get(i).toString());
		}

	}

	public static void printMap(int[][] map) {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
	}

}