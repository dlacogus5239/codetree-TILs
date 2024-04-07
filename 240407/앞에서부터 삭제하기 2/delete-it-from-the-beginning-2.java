import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		double average = 0;
		int N = Integer.parseInt(br.readLine());

		PriorityQueue<Integer> pq = new PriorityQueue<>();
		int[] arr = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		for (int k = 1; k <= N - 2; k++) {
			for (int i = k; i < N; i++) {
				pq.offer(arr[i]);
			}

//			System.out.println(pq.toString());
			pq.poll();
			int sum = 0;
			while (!pq.isEmpty()) {
				sum += pq.poll();
			}
			average = Math.max(average, sum / (N - k - 1));
		}

		System.out.printf("%.2f", average);

	}

}