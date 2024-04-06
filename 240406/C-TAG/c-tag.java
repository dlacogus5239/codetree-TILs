import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main{
	static char[][] A, B;
	static int N, M;
	static boolean[] isChoosen;
	static int result = 0;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		A = new char[N][M];
		B = new char[N][M];
		for (int i = 0; i < N; i++) {
			A[i] = br.readLine().toCharArray();
		}
		for (int i = 0; i < N; i++) {
			B[i] = br.readLine().toCharArray();
		}

		isChoosen = new boolean[M];
		Permutation(0, 0);
		System.out.println(result);
	}

	public static void Permutation(int start, int cnt) {
		if (cnt == 3) {
//			System.out.println(Arrays.toString(isChoosen));
			if (Validate()) {
				result++;
			}
			return;
		}

		for (int i = start; i < M; i++) {
			isChoosen[i] = true;
			Permutation(i + 1, cnt + 1);
			isChoosen[i] = false;
		}
	}

	public static boolean Validate() {
		HashSet<String> hs = new HashSet<>();

		for (int i = 0; i < N; i++) {
			String tmp = "";
			for (int j = 0; j < M; j++) {
				if (isChoosen[j]) {
					tmp += A[i][j];
				}
			}
			hs.add(tmp);
		}

		for (int i = 0; i < N; i++) {
			String tmp = "";
			for (int j = 0; j < M; j++) {
				if (isChoosen[j]) {
					tmp += B[i][j];
				}
			}

			if (hs.contains(tmp)) {
				return false;
			}
		}

		return true;
	}

}