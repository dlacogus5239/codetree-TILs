import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {
	static int N;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		int[][] nums = new int[N][N];
		StringTokenizer st;
		for (int i = 0; i < 4; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				nums[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		HashMap<Integer, Integer> freq1 = new HashMap<>(); // A, B
		HashMap<Integer, Integer> freq2 = new HashMap<>(); // C, D

		// A수열(0), B수열(1) 조합해서 합 저장 --> freq1
		// C수열(2), D수열(3) 조합해서 합 저장 --> freq2
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				int tmp1 = nums[0][i] + nums[1][j];
				int tmp2 = nums[2][i] + nums[3][j];

				freq1.put(tmp1, freq1.getOrDefault(tmp1, 0) + 1);
				freq2.put(tmp2, freq2.getOrDefault(tmp2, 0) + 1);
			}
		}
		int answer = 0;
		for (int i : freq1.keySet()) {
			int diff = 0 - i;
			if (freq2.containsKey(diff)) {
				answer += freq2.get(diff);
			}
		}
		System.out.println(answer);
	}

}