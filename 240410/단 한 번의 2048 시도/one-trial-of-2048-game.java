import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	static int[][] map = new int[4][4];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		for (int i = 0; i < 4; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 4; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		char oper = br.readLine().charAt(0);
		switch (oper) {
		case 'R':
			PushRight();
			break;
		case 'L':
			PushLeft();
			break;
		case 'U':
			PushUp();
			break;
		case 'D':
			PushDown();
			break;
		}
		PrintMap();

	}

	public static void PushRight() {
		int[][] next = new int[4][4];
		for (int r = 0; r < 4; r++) {
			Stack<Integer> s = new Stack<>();
			for (int c = 0; c < 4; c++) {
				if (map[r][c] != 0) {
					s.add(map[r][c]);
				}
			}

			for (int c = 3; c >= 0; c--) {
				if (!s.isEmpty()) {
					next[r][c] = s.pop();
					if (!s.isEmpty()) {
						if (s.peek() == next[r][c]) {
							next[r][c] += s.pop();
						}
					}
				} else {
					next[r][c] = 0;
				}
			}

		}
		CopyMap(next);
	}

	public static void PushLeft() {
		int[][] next = new int[4][4];
		for (int r = 0; r < 4; r++) {
			Stack<Integer> s = new Stack<>();
			for (int c = 3; c >= 0; c--) {
				if (map[r][c] != 0) {
					s.add(map[r][c]);
				}
			}

			for (int c = 0; c < 4; c++) {
				if (!s.isEmpty()) {
					next[r][c] = s.pop();
					if (!s.isEmpty()) {
						if (s.peek() == next[r][c]) {
							next[r][c] += s.pop();
						}
					}
				} else {
					next[r][c] = 0;
				}
			}

		}
		CopyMap(next);
	}

	public static void PushUp() {
		int[][] next = new int[4][4];
		for (int c = 0; c < 4; c++) {
			Stack<Integer> s = new Stack<>();
			for (int r = 3; r >= 0; r--) {
				if (map[r][c] != 0) {
					s.add(map[r][c]);
				}
			}

			for (int r = 0; r < 4; r++) {
				if (!s.isEmpty()) {
					next[r][c] = s.pop();
					if (!s.isEmpty()) {
						if (s.peek() == next[r][c]) {
							next[r][c] += s.pop();
						}
					}
				} else {
					next[r][c] = 0;
				}
			}

		}
		CopyMap(next);
	}

	public static void PushDown() {
		int[][] next = new int[4][4];
		for (int c = 0; c < 4; c++) {
			Stack<Integer> s = new Stack<>();
			for (int r = 0; r < 4; r++) {
				if (map[r][c] != 0) {
					s.add(map[r][c]);
				}
			}

			for (int r = 3; r >= 0; r--) {
				if (!s.isEmpty()) {
					next[r][c] = s.pop();
					if (!s.isEmpty()) {
						if (s.peek() == next[r][c]) {
							next[r][c] += s.pop();
						}
					}
				} else {
					next[r][c] = 0;
				}
			}

		}
		CopyMap(next);
	}

	public static void CopyMap(int[][] next) {
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				map[i][j] = next[i][j];
			}
		}
	}

	public static void PrintMap() {
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
	}

}