import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static int[] col;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		col = new int[N];
		for (int i = 0; i < N; i++) {
			col[i] = Integer.parseInt(br.readLine());
		}
		Bomb();
		int cnt = 0;
		StringBuilder sb = new StringBuilder();
		for (int i = N - 1; i > 0; i--) {
			if (col[i] != 0) {
				cnt++;
				sb.append(col[i]).append("\n");
			}
		}
		System.out.println(cnt);
		System.out.println(sb.toString());
	}

	public static void Bomb() {
		boolean didExplode;
		Stack<Integer> s;
		do {
			s = new Stack<>();
			didExplode = false;
			for (int i = 0; i < N; i++) {
				if (col[i] == 0) {
					continue;
				}

				int endIdx = getEndIdx(i, col[i]);
				if (endIdx - i + 1 >= M) {
					for (int j = i; j <= endIdx; j++) {
						col[j] = 0;
					}
					didExplode = true;
				}
			}
			for (int i = 0; i < N; i++) {
				if (col[i] != 0) {
					s.add(col[i]);
				}
			}
			int[] tmp = new int[N];
			for (int i = N - 1; i >= 0; i--) {
				if (!s.isEmpty()) {
					tmp[i] = s.pop();
				} else {
					tmp[i] = 0;
				}
			}

			for (int i = 0; i < N; i++) {
				col[i] = tmp[i];
			}

		} while (didExplode);
	}

	public static int getEndIdx(int startIdx, int curNum) {
		int endIdx = startIdx + 1;
		while (endIdx < N) {
			if (col[endIdx] == curNum) {
				endIdx++;
			} else {
				break;
			}
		}
		return endIdx - 1;
	}

}