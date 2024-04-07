import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		PriorityQueue<Long> pq = new PriorityQueue<>();
		StringTokenizer st = new StringTokenizer(br.readLine());
		long[] arr = new long[N];
		for (int i = 0; i < N; i++) {
			arr[i] = Long.parseLong(st.nextToken());
		}
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < N; i++) {
			pq.offer(arr[i]);
//			System.out.println(pq.toString());
			if (pq.size() < 3) { // 3개가 안되기 떄문에 -1출력
				sb.append("-1").append(" ");
			} else {
				long sum = 1L;
				Stack<Long> s = new Stack<>();
				for (int j = 0; j < 3; j++) {
					long tmp = pq.poll();
					s.add(tmp);
					sum *= tmp;
				}
				while (!s.isEmpty()) {
					pq.offer(s.pop());
				}

				sb.append(sum).append(" ");

			}
			sb.append("\n");
		}

		System.out.println(sb.toString());
	}

}