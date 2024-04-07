import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		StringTokenizer st = new StringTokenizer(br.readLine());
		PriorityQueue<Integer> pq = new PriorityQueue<>((o1, o2) -> (o2 - o1));

		for (int i = 0; i < N; i++) {
			int tmp = Integer.parseInt(st.nextToken());
			pq.offer(tmp);
		}

		while (pq.size() >= 2) {
			int a = pq.poll();
			int b = pq.poll();

			if (a - b == 0) {
				continue;
			}
			pq.offer(a - b);
		}

		System.out.println(pq.size() == 1 ? pq.poll() : "-1");
	}

}