import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int[][] oper = new int[K][2];
		HashSet<Integer>[] seat = new HashSet[N + 1];
		for (int i = 1; i <= N; i++) {
			seat[i] = new HashSet<>();
		}

		int[] person = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			person[i] = i;
			seat[i].add(i);
		}
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());

			oper[i][0] = Integer.parseInt(st.nextToken());
			oper[i][1] = Integer.parseInt(st.nextToken());

		}

		for (int i = 0; i < K * 3; i++) {
			int[] cur = oper[i % K];

			int tmp = person[cur[1]];
			person[cur[1]] = person[cur[0]];
			person[cur[0]] = tmp;

//			System.out.println(Arrays.toString(person));
			seat[person[cur[0]]].add(cur[0]);
			seat[person[cur[1]]].add(cur[1]);
		}
		StringBuilder sb = new StringBuilder();
		for (int i = 1; i <= N; i++) {
			sb.append(seat[i].size()).append("\n");
		}

		System.out.println(sb.toString());

	}

}