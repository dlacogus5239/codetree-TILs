import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	static int[] col;
	static int N, M;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		col = new int[N + 1];
		for (int i = 0; i < N; i++) {
			col[i] = Integer.parseInt(br.readLine());
		}
		col[N] = -1;

		boolean tmp;
		do {
			tmp = Bomb();
//			System.out.print(tmp + " ");
		} while (tmp);
		int cnt = 0;
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < N; i++) {
			if (col[i] != 0) {
				cnt++;
				sb.append(col[i]).append("\n");
			}

		}
		System.out.println(cnt);
		System.out.println(sb.toString());

	}

	public static boolean Bomb() {
		boolean flag = false;
		int[] next = new int[N + 1];
		next[N] = -1;
		for (int i = 0; i < N; i++) {
			if (col[i] == 0) {
				continue;
			} else if (col[i] != col[i + 1]) {
				int startIdx = i;
				for (int j = i; j >= 0; j--) {
					if (col[j] != col[i]) {
						startIdx = j + 1;
						break;
					}
				}
				if (i - startIdx + 1 >= M) {
					for (int j = startIdx; j <= i; j++) {
						next[i] = 0;
					}
					flag = true;
				} else {
					for (int j = startIdx; j <= i; j++) {
						next[i] = col[i];
					}
				}
			}
		}

//		PrintCol(next);
		Gravity(next);
		return flag;
	}

	public static void Gravity(int[] next) {
		Stack<Integer> s = new Stack<>();

		for (int i = 0; i < N; i++) {
			if (next[i] != 0) {
				s.add(next[i]);
			}
		}

		for (int i = N - 1; i >= 0; i--) {
			if (!s.isEmpty()) {
				next[i] = s.pop();
			} else {
				next[i] = 0;
			}
		}
		copyCol(next);
	}

	public static void copyCol(int[] next) {
		for (int i = 0; i < N; i++) {
			col[i] = next[i];
		}
		col[N] = -1;
	}

	public static void PrintCol(int[] col) {
		for (int i = 0; i < N; i++) {
			System.out.print(col[i] + " ");
		}
		System.out.println();
	}

}