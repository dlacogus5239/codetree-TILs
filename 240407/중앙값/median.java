import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		int N = 0;
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine());

			PriorityQueue<Integer> pq = new PriorityQueue<>();
			Queue<Integer> tmp = new LinkedList<>();
			for (int i = 1; i <= N; i++) {
				int cur = Integer.parseInt(st.nextToken());
				pq.offer(cur);

				if (i % 2 == 1) {
					int len = pq.size();

					for (int j = 0; j < len / 2; j++) {
						tmp.offer(pq.poll());
					}
					int num = pq.poll();
					sb.append(num).append(" ");
					pq.offer(num);
//					System.out.println(tmp.toString());

					while (!tmp.isEmpty()) {
						pq.offer(tmp.poll());
					}
				}

			}
			sb.append("\n");
		}

		System.out.println(sb.toString());
	}

}