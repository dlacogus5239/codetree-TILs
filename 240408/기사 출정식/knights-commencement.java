import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {
	static class Knight {
		int num;
		Knight pre;
		Knight next;

		public Knight(int num) {
			super();
			this.num = num;
			this.pre = null;
			this.next = null;
		}

	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		HashMap<Integer, Knight> map = new HashMap<>();
		st = new StringTokenizer(br.readLine());
		int[] arr = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		for (int i = 1; i <= N; i++) {
			map.put(arr[i], new Knight(arr[i]));
		}
		for (int i = 1; i < N; i++) {
			connect(map.get(arr[i]), map.get(arr[i + 1]));
		}
		connect(map.get(arr[N]), map.get(arr[1]));
		// 원형 연결
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < M; i++) {
			int call = Integer.parseInt(br.readLine());
			sb.append(map.get(call).next.num).append(" ").append(map.get(call).pre.num).append("\n");
			connect(map.get(call).pre, map.get(call).next);

			map.get(call).pre = map.get(call).next = null;
		}

		System.out.println(sb.toString());

	}

	public static void connect(Knight s, Knight e) {
		if (s != null) {
			s.next = e;
		}
		if (e != null) {
			e.pre = s;
		}
	}

}