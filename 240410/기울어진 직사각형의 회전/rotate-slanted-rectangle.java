import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
	static int[] dr = { -1, -1, 1, 1 };
	static int[] dc = { 1, -1, -1, 1 };

	static int dir;
	static int[][] map;
	static int[] m = new int[4];

	static int N;
	static int r, c;

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
		} // input END
		st = new StringTokenizer(br.readLine());
		r = Integer.parseInt(st.nextToken()) - 1;
		c = Integer.parseInt(st.nextToken()) - 1;

		for (int i = 0; i < 4; i++) {
			m[i] = Integer.parseInt(st.nextToken());
		}
		dir = Integer.parseInt(st.nextToken());
		Rotate(dir);
		PrintMap();

	}

	public static void Rotate(int dir) {
		if (dir == 0) { // 반시계방향
			antiClockwise();
		} else { // 시계방향
			Clockwise();
		}
	}

	public static void antiClockwise() {
		int[] cur = { r, c };
		Deque<Integer> dq = new ArrayDeque<>();// 회전하면서 정보 담을 덱. 마지막에 앞에꺼 빼거나 해서 동작시키기
		for (int d = 0; d < 4; d++) {
			for (int i = 0; i < m[d]; i++) {
				cur[0] += dr[d];
				cur[1] += dc[d];
				dq.offerLast(map[cur[0]][cur[1]]);
			}
		}
		dq.offerFirst(dq.pollLast());
		cur[0] = r;
		cur[1] = c;
		for (int d = 0; d < 4; d++) {
			for (int i = 0; i < m[d]; i++) {
				cur[0] += dr[d];
				cur[1] += dc[d];
				map[cur[0]][cur[1]] = dq.pollFirst();
			}
		}
//		System.out.println(dq.toString());
	}

	public static void Clockwise() {
		int[] cur = { r, c };
		Deque<Integer> dq = new ArrayDeque<>();
		for (int d = 0; d < 4; d++) {
			int curD = d ^ 1;
			for (int i = 0; i < m[curD]; i++) {
				cur[0] += dr[curD];
				cur[1] += dc[curD];
				dq.offerLast(map[cur[0]][cur[1]]);
			}
		}
		dq.offerFirst(dq.pollLast());
		cur[0] = r;
		cur[1] = c;
		for (int d = 0; d < 4; d++) {
			int curD = d ^ 1;
			for (int i = 0; i < m[curD]; i++) {
				cur[0] += dr[curD];
				cur[1] += dc[curD];
				map[cur[0]][cur[1]] = dq.pollFirst();
			}
		}

//		System.out.println(dq.toString());
	}
	public static void PrintMap() {
		StringBuilder sb = new StringBuilder();
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				sb.append(map[i][j] + " ");
			}
			sb.append("\n");
		}
		
		System.out.println(sb.toString());
	}
	
	public static boolean isIn(int r, int c) {
		return !(r < 0 || c < 0 || r >= N || c >= N);
	}
}