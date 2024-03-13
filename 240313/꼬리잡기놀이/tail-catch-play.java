import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Stack;
import java.util.StringTokenizer;

// 삼성 기출 꼬리잡기놀이 
public class Main {

	static int N, M, K; // 격자의 크기, 팀의 개수, 라운드 수
	static int[][] map; // 현재 맵
	static int[][] RoadMap; // 길만 저장할 map(팀이 있는 부분도 길로 침)
	static int[][] TeamMap;
	static int[][] PersonMap;
	static ArrayList<Person>[] Team;

	// 4방향 탐색(상우하좌) -> Clockwise
	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, 1, 0, -1 };

	static boolean[][] isVisited;
	static int result = 0;

	static class Person {
		int r, c;
		int nth; // n번째

		public Person(int r, int c, int nth) {
			super();
			this.r = r;
			this.c = c;
			this.nth = nth;
		}

		@Override
		public String toString() {
			return "Person [r=" + r + ", c=" + c + ", nth=" + nth + "]";
		}

	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		Team = new ArrayList[M];

		for (int i = 0; i < M; i++) {
			Team[i] = new ArrayList<>();
		}
		map = new int[N][N];
		RoadMap = new int[N][N]; // 길은 한번 정해지면 변하지 않는다
		PersonMap = new int[N][N];
		TeamMap = new int[N][N];
		int num = 0;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] != 0) {
					RoadMap[i][j] = 4;
					if (map[i][j] != 4) {
						PersonMap[i][j] = map[i][j];
					}
				}
			}
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j] == 1) {
					isVisited = new boolean[N][N];
					MakeTeam(i, j, num++, 1);
				}
			}
		}

//		for (int i = 0; i < M; i++) {
//			System.out.println(Team[i].toString());
//		}
		int direction = 1;
		int r = 0, c = 0;
		for (int k = 0; k < K; k++) {
			Move();
			if (k != 0 && k % N == 0) {
				direction = (direction + 3) % 4;
			}

			switch (direction) {
			case 0:
				r = N - 1;
				c = 0 + k % N;
				break;
			case 1:
				r = 0 + k % N;
				c = 0;
				break;

			case 2:
				r = 0;
				c = N - 1 - k % N;
				break;
			case 3:
				r = N - 1 - k % N;
				c = N - 1;

				break;

			}

//			System.out.println("R : " + r + " C : " + c + " direction : " + direction);
			ThrowBall(r, c, direction);

		}
//		PrintMap(PersonMap);
		System.out.println(result);

	}

	/**
	 * DFS로 팀 만들기
	 * 
	 * @param r   현재 위치
	 * @param c   현재 위치
	 * @param num 팀 번호
	 * @param cnt n번째
	 * 
	 */
	public static void MakeTeam(int r, int c, int num, int cnt) {
		// 마지막 꼬리면 탈출
		if (map[r][c] == 3) {
//			System.out.println("END");
			Team[num].add(new Person(r, c, cnt));
			return;
		}
		isVisited[r][c] = true;
		Team[num].add(new Person(r, c, cnt++));

		for (int d = 0; d < 4; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];

			if (!isIn(nr, nc)) {
				continue;
			}

			if (map[nr][nc] == 2 || map[nr][nc] == 3) {
				if (!isVisited[nr][nc]) {
					MakeTeam(nr, nc, num, cnt);
				}
			}
		}
	}

	// 사람 이동
	public static void Move() {
		int[][] nextMap = new int[N][N];
		// 현재 탐색할 Team[p]
		for (int p = 0; p < M; p++) {
			Person pre = new Person(Team[p].get(0).r, Team[p].get(0).c, Team[p].get(0).nth);
			// 머리 사람 먼저 이동하고 나서
			for (int d = 0; d < 4; d++) {
				int nr = Team[p].get(0).r + dr[d];
				int nc = Team[p].get(0).c + dc[d];
				if (!isIn(nr, nc)) {
					continue;
				}

				if (RoadMap[nr][nc] == 4 && PersonMap[nr][nc] != 2) {
					Team[p].get(0).r = nr;
					Team[p].get(0).c = nc;
					break;
				}
			}

			for (int i = 1; i < Team[p].size(); i++) {
				Person cur = new Person(Team[p].get(i).r, Team[p].get(i).c, Team[p].get(i).nth);

				Team[p].get(i).r = pre.r;
				Team[p].get(i).c = pre.c;

				pre.r = cur.r;
				pre.c = cur.c;
				pre.nth = cur.nth;
			}
			TeamMap = new int[N][N];
			for (int i = 0; i < Team[p].size(); i++) {
				Person cur = Team[p].get(i);
				if (cur.nth == 1) {
					nextMap[cur.r][cur.c] = 1;
				} else if (cur.nth == Team[p].size()) {
					nextMap[cur.r][cur.c] = 3;
				} else {
					nextMap[cur.r][cur.c] = 2;
				}
				TeamMap[cur.r][cur.c] = p;
			}

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					PersonMap[i][j] = nextMap[i][j];
				}
			}
		}
	}

	// 공 던지기(맞으면 머리, 꼬리 바뀜)
	// 점수 합산
	public static void ThrowBall(int r, int c, int direction) {
		for (int i = 0; i < N; i++) {
			int nr = r + dr[direction] * i;
			int nc = r + dc[direction] * i;

			if (PersonMap[nr][nc] != 0) {
				int teamNum = TeamMap[nr][nc];

				for (int p = 0; p < Team[teamNum].size(); p++) {
					if (Team[teamNum].get(p).r == nr && Team[teamNum].get(p).c == nc) {
						result += Math.pow(Team[teamNum].get(p).nth, 2);
						break;
					}
				}

				// 순서 뒤집기
				Stack<Person> s = new Stack<>();
				for (int j = 0; j < Team[teamNum].size(); j++) {
					Person cur = Team[teamNum].get(j);
					s.add(new Person(cur.r, cur.c, cur.nth));
				}
				Team[teamNum].clear();
				int cnt = 1;
				while (!s.isEmpty()) {
					Person cur = s.pop();
					Team[teamNum].add(new Person(cur.r, cur.c, cnt++));
				}
				break;
			}
		}

	}

	public static void PrintMap(int[][] printMap) {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				System.out.print(printMap[i][j] + " ");
			}
			System.out.println();
		}
	}

	// 격자 밖 예외처리
	public static boolean isIn(int r, int c) {
		return !(r < 0 || c < 0 || r >= N || c >= N);
	}
}