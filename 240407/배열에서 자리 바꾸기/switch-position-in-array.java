import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {
	static class Node {
		int num;
		Node pre;
		Node next;

		public Node(int num) {
			super();
			this.num = num;
			this.pre = null;
			this.next = null;
		}

		@Override
		public String toString() {
			return "Node [num=" + num + ", pre=" + pre + ", next=" + next + "]";
		}

	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());
		int Q = Integer.parseInt(br.readLine());
		HashMap<Integer, Node> map = new HashMap<>();

		for (int i = 1; i <= N; i++) {
			map.put(i, new Node(i));
		}
		map.get(1).pre = null;
		map.get(1).next = map.get(1);
		for (int i = 1; i < N; i++) {
			connect(map.get(i), map.get(i + 1));
		}
		map.get(N).next = null;

		StringTokenizer st;
		for (int q = 0; q < Q; q++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());

			swapNodes(map.get(a), map.get(b), map.get(c), map.get(d));

		}

		Node cur = map.get(1);
		// 가장 처음 노드 찾아줌
		while (cur.pre != null) {
			cur = cur.pre;
		}

		StringBuilder sb = new StringBuilder();

		while (cur.next != null) {
			sb.append(cur.num).append(" ");
			cur = cur.next;
		}
		sb.append(cur.num);
		System.out.println(sb.toString());

	}

	public static void connect(Node s, Node e) {
		if (s != null) {
			s.next = e;
		}
		if (e != null) {
			e.pre = s;
		}
	}

	public static void swapNodes(Node a, Node b, Node c, Node d) {
		// 각 노드의 이전 노드와 다음 노드의 연결 상태 저장
		Node after_preA = c.pre;
		Node after_nextB = d.next;

		Node after_preC = a.pre;
		Node after_nextD = b.next;

		// b, c가 인접한 경우 예외 처리
		if (b.next == c) {
			after_preA = d;
			after_nextD = a;
		}
		// d, a가 인접한 경우 예외처리
		if (d.next == a) {
			after_nextB = c;
			after_preC = b;
		}

		connect(after_preA, a);
		connect(b, after_nextB);

		connect(after_preC, c);
		connect(d, after_nextD);

	}

}