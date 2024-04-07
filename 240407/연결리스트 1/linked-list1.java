import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static class Node {
		String data;
		Node pre;
		Node next;

		public Node(String data) {
			super();
			this.data = data;
			this.pre = null;
			this.next = null;
		}

		@Override
		public String toString() {
			return "Node [data=" + data + ", pre=" + pre + ", next=" + next + "]";
		}

	}

	static final String NODATA = "(Null)";

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Node cur = new Node(br.readLine()); // Node Init
		int N = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());

			int oper = Integer.parseInt(st.nextToken());
			String data;
			Node n;
			switch (oper) {
			case 1:
				data = st.nextToken();
				n = new Node(data);
				insertNodeNext(cur, n);
				break;
			case 2:
				data = st.nextToken();
				n = new Node(data);
				insertNodePre(cur, n);
				break;
			case 3:
				if (cur.pre != null) {
					cur = cur.pre;
				}
				break;
			case 4:
				if (cur.next != null) {
					cur = cur.next;
				}
				break;
			}
			sb.append(cur.pre == null ? NODATA : cur.pre.data).append(" ");
			sb.append(cur.data).append(" ");
			sb.append(cur.next == null ? NODATA : cur.next.data).append("\n");
		}

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

	// target 앞에 u 삽입
	public static void insertNodePre(Node target, Node u) {
		connect(u, target.next);
		connect(target, u);
	}

	// target 뒤에 u 삽입
	public static void insertNodeNext(Node target, Node u) {
		connect(target.pre, u);
		connect(u, target);
	}

}