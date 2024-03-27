import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	static class Node {
		int r, c;

		public Node(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}

		@Override
		public String toString() {
			return "Node [r=" + r + ", c=" + c + "]";
		}

	}

	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	// 격자 크기, 사과 개수, 뱀의 방향 변환 횟수
	static int N, M, K;

	static ArrayList<Node> snake = new ArrayList<>();
	static int[][] appleMap;
	static int[][] snakeMap;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		appleMap = new int[N][N];
		snakeMap = new int[N][N];
		// snake Init
		snake.add(new Node(0, 0));
		snakeMap[0][0] = 1;
		// apple init
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken()) - 1;
			int c = Integer.parseInt(st.nextToken()) - 1;

			appleMap[r][c] = 1;

		}
		int result = 0;
		// move Start
		MOVE: for (int time = 1; time <= K; time++) {
			st = new StringTokenizer(br.readLine());

			int d = parseDirection(st.nextToken().charAt(0));
			int move = Integer.parseInt(st.nextToken());

			// this turn Move
			for (int m = 1; m <= move; m++) {
				// head Move
				Node cur = new Node(snake.get(0).r, snake.get(0).c);
				int nr = cur.r + dr[d];
				int nc = cur.c + dc[d];
				result++;
				// 격자 밖이거나
				if (!isIn(nr, nc)) {
					break MOVE;
				}
				// 몸통에 부딪힐 경우 BREAK
				if (snakeMap[nr][nc] == 1) {
					break MOVE;
				}
				boolean isApple = false;
				// 사과가 있을 경우
				if (appleMap[nr][nc] == 1) {
					isApple = true;
					appleMap[nr][nc] = 0;
				}

				snake.get(0).r = nr;
				snake.get(0).c = nc;
				Node pre = new Node(cur.r, cur.c);
				for (int i = 1; i < snake.size(); i++) {
					cur = new Node(snake.get(i).r, snake.get(i).c);

					snake.get(i).r = pre.r;
					snake.get(i).c = pre.c;

					pre.r = cur.r;
					pre.c = cur.c;
				}
				if (isApple) {
					snake.add(new Node(pre.r, pre.c));
				}
				// copy snake
				snakeMap = new int[N][N];

				for (int i = 0; i < snake.size(); i++) {
					Node tmp = snake.get(i);

					snakeMap[tmp.r][tmp.c] = 1;
				}

//				PrintMap(snakeMap);

			}
		}
		System.out.println(result);
	}

	public static void PrintMap(int[][] map) {
		System.out.println("=========");
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
	}

	public static int parseDirection(char dir) {
		int result = -1;
		switch (dir) {
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

		default:
			System.out.println("ERROR CASE!");
			break;
		}

		return result;

	}

	public static boolean isIn(int r, int c) {
		return !(r < 0 || c < 0 || r >= N || c >= N);
	}

}