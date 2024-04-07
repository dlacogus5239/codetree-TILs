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
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		for (int q = 0; q < Q; q++) {
			st = new StringTokenizer(br.readLine());
			int oper = Integer.parseInt(st.nextToken());
			int i, j;
			switch (oper) {
			case 1:
				i = Integer.parseInt(st.nextToken());
				disconnect(map.get(i));
				break;
			case 2:
				i = Integer.parseInt(st.nextToken());
				j = Integer.parseInt(st.nextToken());
				insertNodePre(map.get(i), map.get(j));
				break;
			case 3:
				i = Integer.parseInt(st.nextToken());
				j = Integer.parseInt(st.nextToken());
				insertNodeNext(map.get(i), map.get(j));
				break;
			case 4:
				i = Integer.parseInt(st.nextToken());
				sb.append(map.get(i).pre == null ? 0 : map.get(i).pre.num).append(" ")
						.append(map.get(i).next == null ? 0 : map.get(i).next.num).append("\n");
				break;

			}
		}

		for (int i = 1; i <= N; i++) {
			sb.append(map.get(i).next == null ? 0 : map.get(i).next.num).append(" ");
		}

		System.out.println(sb.toString());
	}

	public static void disconnect(Node s) {
		connect(s.pre, s.next);
		s.pre = null;
		s.next = null;
	}

	public static void connect(Node s, Node e) {
		if (s != null) {
			s.next = e;
		}
		if (e != null) {
			e.pre = s;
		}
	}

	public static void insertNodePre(Node target, Node u) {
		connect(target.pre, u);
		connect(u, target);
	}

	public static void insertNodeNext(Node target, Node u) {
		connect(u, target.next);
		connect(target, u);
	}

}