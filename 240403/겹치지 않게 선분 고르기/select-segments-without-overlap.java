import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static boolean[] isChoosen;

	static class Line {
		int start, end;

		public Line(int start, int end) {
			super();
			this.start = start;
			this.end = end;
		}

		@Override
		public String toString() {
			return "Line [start=" + start + ", end=" + end + "]";
		}

	}

	static ArrayList<Line> lines = new ArrayList<>();
	static int result = 0;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		isChoosen = new boolean[N];
		StringTokenizer st;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			lines.add(new Line(start, end));
		}
		chooseLine(0);
		System.out.println(result);

	}

	public static void chooseLine(int cnt) {
		if (cnt == N) {
			if (validate()) {
				int countLine = 0;
				for (int i = 0; i < isChoosen.length; i++) {
					if (isChoosen[i]) {
						countLine++;
					}
				}
				result = Math.max(result, countLine);
			}
			return;
		}

		isChoosen[cnt] = true;
		chooseLine(cnt + 1);
		isChoosen[cnt] = false;
		chooseLine(cnt + 1);
	}

	public static boolean validate() {
		int[] matrix = new int[1001];
		for (int i = 0; i < N; i++) {
			if (isChoosen[i]) {
				Line cur = lines.get(i);
				for (int j = cur.start; j <= cur.end; j++) {
					if (matrix[j] == 1) {
						return false;
					}
					matrix[j] = 1;
				}
			}
		}

		return true;
	}

}