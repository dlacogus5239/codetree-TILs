import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());
		PriorityQueue<Integer> pq = new PriorityQueue<>((o1, o2) -> (o2 - o1));
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < N; i++) {
			int tmp = Integer.parseInt(br.readLine());

			if (tmp == 0) {
				if (pq.isEmpty()) {
					sb.append("0").append("\n");
				} else {
					sb.append(pq.poll()).append("\n");
				}
			} else {
				pq.offer(tmp);
			}
		}

		System.out.println(sb.toString());
	}

}