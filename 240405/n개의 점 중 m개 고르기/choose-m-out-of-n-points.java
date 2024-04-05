import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static int min = Integer.MAX_VALUE;

	static class Node {
		int x, y;

		public Node(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}

		@Override
		public String toString() {
			return "Node [x=" + x + ", y=" + y + "]";
		}

	}

	static boolean[] isChoosen;

	static ArrayList<Node> nodes = new ArrayList<>();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		isChoosen = new boolean[N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());

			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			nodes.add(new Node(x, y));
		}
		Comb(0, 0);
		System.out.println(min);
	}

	public static void Comb(int start, int cnt) {
		if (cnt == M) {
			int tmp = Integer.MAX_VALUE;
//			System.out.println(Arrays.toString(isChoosen));

			for (int i = 0; i < N; i++) {
				if (isChoosen[i]) {
					Node a = nodes.get(i);

					for (int j = i + 1; j < N; j++) {
						if (isChoosen[j]) {
							tmp = -1;
							Node b = nodes.get(j);
							tmp = Math.max(tmp, CalcDistance(a, b));
						}
					}
				}
			}
//			System.out.println(tmp);
			min = Math.min(tmp, min);
			return;
		}

		for (int i = start; i < N; i++) {
			isChoosen[i] = true;
			Comb(i + 1, cnt + 1);
			isChoosen[i] = false;
		}
	}

	public static int CalcDistance(Node start, Node end) {
		int result = 0;
		result = (int) ((int) Math.pow(start.x - end.x, 2) + Math.pow(start.y - end.y, 2));
//		System.out.print(start + " ");
//		System.out.println(end);
//		System.out.println(result);

		return result;
	}

}