import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int[] dr = { -1, -1, 1, 1 };
	static int[] dc = { 1, -1, -1, 1 };
	static int N;
	static int[][] map;
	static int r, c;
	static int dir; // clockwise or anticlockwise

	// 1, 2, 3, 4방향으로 m[0]..m[3]만큼
	static int[] m = new int[4];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());

		map = new int[N][N];

		StringTokenizer st;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		st = new StringTokenizer(br.readLine());
		r = Integer.parseInt(st.nextToken()) - 1;
		c = Integer.parseInt(st.nextToken()) - 1;
		for (int i = 0; i < 4; i++) {
			m[i] = Integer.parseInt(st.nextToken());
		}
		dir = Integer.parseInt(st.nextToken());

		Rotate(dir);
		printMap();
	}

	public static void Rotate(int dir) {
		if (dir == 0) {
			AntiClockwise();
		} else {
			Clockwise();
		}
	}

	public static void Clockwise() {
		Deque<Integer> q = new ArrayDeque<>();
		int[] cur = { r, c };
		q.offer(map[r][c]);
		for (int d = 0; d < 4; d++) {
			int curD = d ^ 1;
			for (int i = 0; i < m[curD]; i++) {
				cur[0] += dr[curD];
				cur[1] += dc[curD];
				q.offer(map[cur[0]][cur[1]]);
			}
		}
		System.out.println(q.toString());
		int tmp = q.pollLast();
		cur[0] = r;
		cur[1] = c;
		q.offerFirst(tmp);
		map[r][c] = q.pollFirst();
		for (int d = 0; d < 4; d++) {
			int curD = d ^ 1;
			for (int i = 0; i < m[curD]; i++) {
				cur[0] += dr[curD];
				cur[1] += dc[curD];
				map[cur[0]][cur[1]] = q.pollFirst();
			}
		}
	}

	public static void AntiClockwise() {
		Deque<Integer> q = new ArrayDeque<>();
		int[] cur = { r, c };
		q.offer(map[r][c]);
		for (int d = 0; d < 3; d++) {
			for (int i = 0; i < m[d]; i++) {
				cur[0] += dr[d];
				cur[1] += dc[d];
				q.offer(map[cur[0]][cur[1]]);
			}
		}
		int tmp = q.pollLast();
		cur[0] = r;
		cur[1] = c;
		q.offerFirst(tmp);
		map[r][c] = q.pollFirst();
		for (int d = 0; d < 3; d++) {
			for (int i = 0; i < m[d]; i++) {
				cur[0] += dr[d];
				cur[1] += dc[d];
				map[cur[0]][cur[1]] = q.pollFirst();
			}
		}
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