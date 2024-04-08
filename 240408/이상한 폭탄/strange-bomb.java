import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int[] bomb = new int[N];

		for (int i = 0; i < N; i++) {
			bomb[i] = Integer.parseInt(br.readLine());
		}
		int[] R = new int[N];
		HashMap<Integer, Integer> lastIndex = new HashMap<>();

		for (int i = N - 1; i >= 0; i--) {
			if (!lastIndex.containsKey(bomb[i])) {
				R[i] = -1;
			} else {
				R[i] = lastIndex.get(bomb[i]);
			}

			lastIndex.put(bomb[i], i);
		}
		int answer = 0;
		for (int i = 0; i < N; i++) {
			if (R[i] != -1 && Math.abs(R[i] - i) <= K) {
				answer = Math.max(bomb[i], answer);
			}
		}

		System.out.println(answer);
	}

}