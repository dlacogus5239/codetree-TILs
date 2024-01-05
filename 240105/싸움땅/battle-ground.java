import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	// 격자 크기, 플레이어 수, 라운드 수
	static int N, M, K;
	static int[][] mapPlayer;
	static PriorityQueue<Integer>[][] mapWeapon;

	static class Player {
		int r, c;
		int direction;
		int weapon;
		// 초기 능력치
		int ability;

		public Player(int r, int c, int direction, int weapon, int ability) {
			this.r = r;
			this.c = c;
			this.direction = direction;
			this.weapon = weapon;
			this.ability = ability;
		}

		@Override
		public String toString() {
			return "Player [r=" + r + ", c=" + c + ", direction=" + direction + ", weapon=" + weapon + ", ability="
					+ ability + "]";
		}

	}

	static Player[] Players;
	static int[] Point;
	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, 1, 0, -1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		// 자료구조 Init START
		mapPlayer = new int[N][N];
		mapWeapon = new PriorityQueue[N][N];

		Players = new Player[M + 1];
		Point = new int[M + 1];
		// 자료구조 Init END

		// map input
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				int tmp = Integer.parseInt(st.nextToken());
				mapWeapon[i][j] = new PriorityQueue<>((o1, o2) -> (o2 - o1));
				if (tmp != 0) {
					mapWeapon[i][j].offer(tmp);
				}

			}
		}

		// Player Input START
		for (int i = 1; i <= M; i++) {
			st = new StringTokenizer(br.readLine());

			int r = Integer.parseInt(st.nextToken()) - 1;
			int c = Integer.parseInt(st.nextToken()) - 1;

			int direction = Integer.parseInt(st.nextToken());
			int ability = Integer.parseInt(st.nextToken());

			// 초기 총은 없다
			Players[i] = new Player(r, c, direction, 0, ability);

			mapPlayer[r][c] = i;
		}
		// Player Input END

		// ROUND START
		for (int r = 0; r < K; r++) {
			// 플레이어 별로 움직이기
			for (int p = 1; p <= M; p++) {
				int nr = Players[p].r + dr[Players[p].direction];
				int nc = Players[p].c + dc[Players[p].direction];

				// 격자 밖이면
				if (!isIn(nr, nc)) {
					// 정 반대 방향
					Players[p].direction = (Players[p].direction + 2) % 4;
					nr = Players[p].r + dr[Players[p].direction];
					nc = Players[p].c + dc[Players[p].direction];
				}
				mapPlayer[Players[p].r][Players[p].c] = 0;
				Players[p].r = nr;
				Players[p].c = nc;

				// Player 있는지 처리
				if (mapPlayer[nr][nc] != 0) {
					// 플레이어가 있을 경우
					// 다이다이
					int vsPlayer = mapPlayer[nr][nc];

					// 누가 이겼는지 결정하기
					int curPlayerAD = Players[p].ability + Players[p].weapon;
					int vsPlayerAD = Players[vsPlayer].ability + Players[vsPlayer].weapon;

					int winPoints = (int) Math.abs(curPlayerAD - vsPlayerAD);

					// FightResult(이긴사람, 진사람, 맞붙은 위치)
					if (curPlayerAD > vsPlayerAD) {
						FightResult(p, vsPlayer, nr, nc);
						Point[p] += winPoints;
					} else if (vsPlayerAD > curPlayerAD) {
						FightResult(vsPlayer, p, nr, nc);
						Point[vsPlayer] += winPoints;
					} else {
						if (Players[p].ability > Players[vsPlayer].ability) {
							FightResult(p, vsPlayer, nr, nc);
							Point[p] += winPoints;
						} else {
							FightResult(vsPlayer, p, nr, nc);
							Point[vsPlayer] += winPoints;
						}
					}
				} else {
					// 없을 경우
					// 무기가 있는지 확인
					if (mapWeapon[nr][nc].size() != 0) {
						// 있을 경우
						// 플레이어가 총이 없을 경우
						if (Players[p].weapon == 0) {
							Players[p].weapon = mapWeapon[nr][nc].poll();
						} else {
							// 있을 경우
							// 공격력이 더 높은거 가져감
							// 우선순위 큐로 구현했으니까
							// 가장 공격력이 높은 총 자동으로 챙김
							mapWeapon[nr][nc].offer(Players[p].weapon);
							Players[p].weapon = mapWeapon[nr][nc].poll();
						}
					}

					// 플레이어 위치 map 갱신
					mapPlayer[Players[p].r][Players[p].c] = 0;
					Players[p].r = nr;
					Players[p].c = nc;
					mapPlayer[nr][nc] = p;

				}
				// 플레이어 있는지 처리 END

			}
//			for (int i = 0; i < N; i++) {
//				for (int j = 0; j < N; j++) {
//					System.out.print(mapPlayer[i][j] + " ");
//				}
//				System.out.println();
//			}
//			System.out.println();

		}
		// ROUND END
		for (int i = 1; i <= M; i++) {
//			System.out.println(Players[i].toString());
			System.out.print(Point[i] + " ");
		}
//		System.out.println();

//		for (int i = 0; i < N; i++) {
//			for (int j = 0; j < N; j++) {
//				System.out.print(mapWeapon[i][j].toString() + " ");
//			}
//			System.out.println();
//		}

	}

	// 플레이어 진사람, 이긴사람 처리
	public static void FightResult(int winner, int loser, int r, int c) {
		// 진사람
		// 총 내려놓기
		if (Players[loser].weapon != 0) {
			mapWeapon[r][c].offer(Players[loser].weapon);
			Players[loser].weapon = 0;
		}

		// 이동
		// 빈칸 찾을때까지 이동
		int Lnr = 0;
		int Lnc = 0;
		// 이동 무조건 하기 때문에 그 전에 Map 비워두기
		mapPlayer[Players[loser].r][Players[loser].c] = 0;
		while (true) {

			Lnr = Players[loser].r + dr[Players[loser].direction];
			Lnc = Players[loser].c + dc[Players[loser].direction];

			// 격자 밖일 경우
			if (!isIn(Lnr, Lnc)) {
				// 오른쪽 90도 회전
				Players[loser].direction = (Players[loser].direction + 1) % 4;
			} else if (mapPlayer[Lnr][Lnc] == 0) {
				// 빈칸 발견시
				// 이동
				mapPlayer[Lnr][Lnc] = loser;
				Players[loser].r = Lnr;
				Players[loser].c = Lnc;
				// 총 획득
				if (Players[loser].weapon == 0) {
					if (mapWeapon[Lnr][Lnc].size() != 0) {
						Players[loser].weapon = mapWeapon[Lnr][Lnc].poll();
					}
				} else {
					mapWeapon[Lnr][Lnc].offer(Players[loser].weapon);
					Players[loser].weapon = mapWeapon[Lnr][Lnc].poll();
				}

				break;
			}
			// 플레이어가 있을 경우
			else {
				// 오른쪽 90도 회전
				Players[loser].direction = (Players[loser].direction + 1) % 4;
			}
		}

		// 이긴사람
		// 포인트 획득은 위에서 처리
		// 총 획득
		if (Players[winner].weapon == 0) {
			if(mapWeapon[r][c].size() != 0) {
				
				Players[winner].weapon = mapWeapon[r][c].poll();
			}
		} else {
			mapWeapon[r][c].offer(Players[winner].weapon);
			Players[winner].weapon = mapWeapon[r][c].poll();
		}
		// 이긴 사람이 해당 칸에 머무르기
		mapPlayer[Players[winner].r][Players[winner].c] = 0;
		mapPlayer[r][c] = winner;
		Players[winner].r = r;
		Players[winner].c = c;

	}

	// 격자 밖인지 검사
	public static boolean isIn(int r, int c) {
		return !(r < 0 || c < 0 || r >= N || c >= N);
	}

}