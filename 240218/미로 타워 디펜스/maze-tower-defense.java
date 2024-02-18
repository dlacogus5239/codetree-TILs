import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	// 격자 크기, 라운드 수
	static int N, M;
	// map
	static int[][] map;

	// DIRECTION 우 하 좌 상
	static int[] dr = { 0, 1, 0, -1 };
	static int[] dc = { 1, 0, -1, 0 };

	// score
	static int Score = 0;

	// Center Row, Center Column
	static int C;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		// init INPUT START
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		C = N / 2;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		// init INPUT END

		// ROUND START
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int direction = Integer.parseInt(st.nextToken());
			int power = Integer.parseInt(st.nextToken());

			// Step 1. Player Attack
			for (int p = 1; p <= power; p++) {
				Score += map[C + dr[direction] * p][C + dc[direction] * p];
				map[C + dr[direction] * p][C + dc[direction] * p] = 0;
			}

			// Step 2. Monster Remove
			RemoveMonster();

		}
		// ROUND END
		System.out.println(Score);
	}

	public static void RemoveMonster() {
		// STACK MONSTER START
		Stack<Integer> stack = new Stack<>();
		// 마지막 넣기 전 Queue
		Deque<Integer> dq = new ArrayDeque<>();
		// 좌측상단부터 Snail 형태로 쌓고 꺼내면서 제거하자
		int r = 0;
		int c = 0;
		// 현재 움직여야 하는 횟수
		int totalMove = N - 1;
		// 이 회차에 움직인 횟수
		int curMove = 0;
		// 방향 전환 횟수(2번마다 totalMove --)
		int changeDirection = -1;
		// 현재 방향
		int d = 0;
		if (map[r][c] != 0) {
			stack.add(map[r][c]);
		}
		// Stack 쌓기
		for (int i = 0; i < (N * N) - 1; i++) {
			// 이동
			r += dr[d];
			c += dc[d];
			curMove++;

			if (map[r][c] != 0) {
				stack.add(map[r][c]);
			}

			// 끝까지 움직였으면 방향전환
			if (curMove == totalMove) {
				d = (d + 1) % 4;
				// 방향전환 횟수 + 1
				changeDirection++;
				curMove = 0;
			}
			// 방향 두번 바꿨으면 totalMove--
			if (changeDirection == 2) {
				totalMove--;
				changeDirection = 0;
			}

		}

		// STACK MONSTER END
		map = new int[N][N];
		if (stack.isEmpty()) {
			return;
		}

		// 4개 이상 중복 제거
		int cnt = 1;
		dq.offer(stack.pop());
		while (!stack.isEmpty()) {
			int tmp = stack.pop();
			if (dq.getLast() == tmp) {
				dq.offer(tmp);
				cnt += 1;
			} else {
				if (cnt >= 4) {
					// Step 2-1. Calculate Score
					for (int i = 0; i < cnt; i++) {
						Score += dq.pollLast();
					}
				}
				if (dq.isEmpty()) {
					cnt = 1;
					dq.offer(tmp);
					continue;
				}

				if (dq.getLast() == tmp) {
					cnt = 2;
				} else {
					cnt = 1;
				}
				dq.offer(tmp);
			}
		}

		if (dq.size() == 1) {
			map[C][C - 1] = 1;
			map[C + 1][C - 1] = dq.pollFirst();
		}

		// Step 3. Generate new Monster Array
		Deque<Integer> nDq = new ArrayDeque<Integer>();
		// 숫자 짝지을때
		// 총 개수
		cnt = 1;
		// 해당 숫자
		int pre = dq.pollFirst();
		int cur = dq.pollFirst();

		do {
			if (pre == cur) {
				cnt++;
				cur = dq.pollFirst();
				continue;
			} else {
				nDq.offer(cnt);
				nDq.offer(pre);
				cnt = 1;
				pre = cur;
				if (dq.size() == 1) {
					cur = dq.pollFirst();
					if (cur == pre) {
						nDq.offer(cnt + 1);
						nDq.offer(cur);
					} else {
						nDq.offer(cnt);
						nDq.offer(pre);
						nDq.offer(1);
						nDq.offer(cur);
					}
					break;
				} else if (dq.isEmpty()) {
					break;
				} else {
					cur = dq.pollFirst();
				}
			}

		} while (!dq.isEmpty());

		// Step 4 : MONSTER INPUT START
		// Deque pollFirst --> 중앙에서부터 차례대로 INPUT

		r = C;
		c = C;
		totalMove = 1;
		curMove = 0;
		changeDirection = 0;
		d = 2;
		for (int i = 0; i < N * N - 1; i++) {
			// 먼저 움직임
			r = r + dr[d];
			c = c + dc[d];
			if (nDq.isEmpty()) {
				break;
			}
			map[r][c] = nDq.pollFirst();
			curMove++;
			if (curMove == totalMove) {
				curMove = 0;
				changeDirection++;
				d = (d + 3) % 4;
			}

			if (changeDirection == 2) {
				changeDirection = 0;
				totalMove++;
			}
		}

	}

	// 격자 안인지 판단
	public static boolean isIn(int r, int c) {
		return !(r < 0 || c < 0 || r >= N || c >= N);
	}

}