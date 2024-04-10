import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
	static int N;
	static int result = Integer.MAX_VALUE;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		BFS();
		System.out.println(result);
	}

	public static void BFS() {
		Queue<int[]> q = new LinkedList<>();

		q.offer(new int[] { N, 0 });
		while (!q.isEmpty()) {
			int[] cur = q.poll();
			if (cur[0] == 1) {
				result = Math.min(result, cur[1]);
				break;
			}

			if (cur[0] % 2 == 0) {
				q.offer(new int[] { cur[0] / 2, cur[1] + 1 });
			}
			if (cur[0] % 3 == 0) {
				q.offer(new int[] { cur[0] / 3, cur[1] + 1 });
			}
			if (cur[0] - 1 >= 1) {
				q.offer(new int[] { cur[0] - 1, cur[1] + 1 });
			}
			q.offer(new int[] { cur[0] + 1, cur[1] + 1 });
		}
	}

}