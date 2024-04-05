import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int[] arr;
	static boolean[] isChoosen;
	static int N;
	static int min = Integer.MAX_VALUE;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		arr = new int[N * 2];
		isChoosen = new boolean[N * 2];

		StringTokenizer st = new StringTokenizer(br.readLine());

		for (int i = 0; i < N * 2; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		Arrays.fill(isChoosen, false);
		Comb(0, 0);
		System.out.println(min);
	}

	public static void Comb(int start, int cnt) {
		if (cnt == N) {
			int a = 0;
			int b = 0;

			for (int i = 0; i < N * 2; i++) {
				if (isChoosen[i]) {
					a += arr[i];
				} else {
					b += arr[i];
				}
			}

			min = Math.min(min, Math.abs(a - b));
			return;
		}

		for (int i = start; i < N * 2; i++) {
			isChoosen[i] = true;
			Comb(i + 1, cnt + 1);
			isChoosen[i] = false;
		}
	}

}