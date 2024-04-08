import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());

		int[] moveCost = new int[N];
		int[] chargeCost = new int[N];

		StringTokenizer st = new StringTokenizer(br.readLine());

		for (int i = 0; i < N - 1; i++) {
			moveCost[i] = Integer.parseInt(st.nextToken());
		}
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			chargeCost[i] = Integer.parseInt(st.nextToken());
		}

		int[] minCost = new int[N];
		int min = Integer.MAX_VALUE;
		for (int i = 0; i < N; i++) {
			min = Math.min(min, chargeCost[i]);
			minCost[i] = min;
		}
//		System.out.println(Arrays.toString(minCost));
//		System.out.println(Arrays.toString(moveCost));
		int answer = 0;
		for (int i = 0; i < N - 1; i++) {
			answer += minCost[i] * moveCost[i];
		}

		System.out.println(answer);
	}

}