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
		// 최소 N - 1부터 시작
		int k = N - 1;
		int sum = 0;
		for (int i = N - 2; i < N; i++) {
			pq.offer(arr[i]);
			sum += arr[i];
		} // DONE

		while (k-- > 1) {
//			System.out.println(k);
//			System.out.println(pq.toString());
			int tmp = pq.poll();
			sum -= tmp;

			average = Math.max(average, sum / (N - k - 1));
			pq.offer(tmp);
			pq.offer(arr[k - 1]);
			sum += tmp + arr[k - 1];
		}

		System.out.printf("%.2f", average);

	}

}