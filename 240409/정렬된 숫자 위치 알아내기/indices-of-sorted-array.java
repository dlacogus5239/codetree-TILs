import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {
	static class Number {
		int idx;
		int val;

		public Number(int idx, int val) {
			super();
			this.idx = idx;
			this.val = val;
		}

		@Override
		public String toString() {
			return "Number [idx=" + idx + ", val=" + val + "]";
		}

	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		ArrayList<Number> num = new ArrayList<>();
		int[] result = new int[N];
		for (int i = 0; i < N; i++) {
			num.add(new Number(i, Integer.parseInt(st.nextToken())));
		}
		Collections.sort(num, new Comparator<Number>() {
			@Override
			public int compare(Number o1, Number o2) {
				if (o1.val != o2.val) {
					return o1.val - o2.val;
				}
				return o1.idx - o2.idx;

			}

		});
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < N; i++) {
			Number cur = num.get(i);
			result[cur.idx] = i + 1;
		}
		for (int i = 0; i < result.length; i++) {
			sb.append(result[i]).append(" ");
		}
		System.out.println(sb.toString());

	}

}