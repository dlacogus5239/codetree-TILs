import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static int[] arr;
	static int[] input;
	static int answer = Integer.MIN_VALUE;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		input = new int[N];
		arr = new int[M];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			input[i] = Integer.parseInt(st.nextToken());
		}
		comb(0, 0);
		System.out.println(answer);
	}

	public static void comb(int start, int cnt) {
		if (cnt == M) {
			int tmp = arr[0];
			for (int i = 1; i < M; i++) {
				tmp ^= arr[i];
			}
			answer = Math.max(answer, tmp);
			return;
		}

		for (int i = start; i < N; i++) {
			arr[cnt] = input[i];
			comb(i + 1, cnt + 1);
		}
	}

}