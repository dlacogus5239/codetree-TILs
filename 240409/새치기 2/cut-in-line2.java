import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {
	static class Node {
		String name;
		Node pre, next;

		public Node(String name) {
			super();
			this.name = name;
			this.pre = null;
			this.next = null;
		}

		@Override
		public String toString() {
			return "Node [name=" + name + "]";
		}

	}

	static int N, M, Q;
	static HashMap<Integer, Node> nodes, heads, tails;
	static HashMap<String, Integer> dict;
	static int[] line;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		Q = Integer.parseInt(st.nextToken());
		nodes = new HashMap<>();
		heads = new HashMap<>();
		tails = new HashMap<>();
		dict = new HashMap<>();

		line = new int[N + 1];

		st = new StringTokenizer(br.readLine());

		for (int i = 1; i <= N; i++) {
			String tmp = st.nextToken();
			dict.put(tmp, i);
			nodes.put(i, new Node(tmp));
		}
		for (int i = 1; i < N; i++) {
			connect(nodes.get(i), nodes.get(i + 1));
		}
		int cnt = 1;
		int lineCnt = (int) N / M; // 한 줄에 서야하는 사람 수
		for (int i = 1; i <= N; i++) {
			Node cur = nodes.get(i);
			line[i] = cnt;
			if (i % lineCnt == 1) {
				cur.pre = null;
				heads.put(cnt, cur);
			} else if (i % lineCnt == 0) {
				cnt++;
				cur.next = null;
				tails.put(cnt, cur);
			}
		} // 줄 나눔

//		System.out.println(Arrays.toString(line));
		for (int q = 0; q < Q; q++) {
			st = new StringTokenizer(br.readLine());

			int oper = Integer.parseInt(st.nextToken());
			String a, b, c;
			switch (oper) {
			case 1:
				a = st.nextToken();
				b = st.nextToken();
				insertNodePre(nodes.get(dict.get(a)), nodes.get(dict.get(b)));
				break;
			case 2:
				a = st.nextToken();
				disconnect(nodes.get(dict.get(a)));
				line[dict.get(a)] = -1;
				break;
			case 3:
				a = st.nextToken();
				b = st.nextToken();
				c = st.nextToken();
				insertNodesPre(nodes.get(dict.get(a)), nodes.get(dict.get(b)), nodes.get(dict.get(c)));
				break;
			}
		}
//		System.out.println(Arrays.toString(line));
//		System.out.println(heads.toString());
//		System.out.println(dict.toString());
		PrintLine();

	}

	public static void connect(Node s, Node e) {
		if (s != null) {
			s.next = e;
		}
		if (e != null) {
			e.pre = s;
		}
	}

	// n 이 target앞으로
	public static void insertNodePre(Node n, Node target) {
		int lineNum = line[dict.get(target.name)]; // 넣을 라인
		int lineNumN = line[dict.get(n.name)]; // 뺄 라인
		// n이 tail일 경우
		if (n.next == null) {
			tails.put(lineNumN, n.pre);
			n.pre.next = null;
		} else {
			connect(n.pre, n.next);
		}

		// target이 head일 경우
		if (target.pre == null) {
			heads.put(lineNum, n);
			connect(n, target);

		} else {
			// 아닐 경우
			connect(target.pre, n);
			connect(n, target);
		}

		refreshLine(lineNum);
		refreshLine(lineNumN);

	}

	// s-e 까지 target앞으로
	public static void insertNodesPre(Node s, Node e, Node target) {
		int lineNum = line[dict.get(target.name)];
		int lineNumN = line[dict.get(s.name)];
		// e 이 tail일 경우
		if (e.next == null) {
			tails.put(lineNumN, s.pre);
			s.pre.next = null;
		} else {
			connect(s.pre, e.next);
		}

		// s가 head일 경우
		if (s.pre == null) {
			heads.put(lineNumN, s.pre);
			s.pre.pre = null;
		}

		if (target.pre == null) {
			heads.put(lineNum, s);
			connect(e, target);
		} else {
			connect(target.pre, s);
			connect(e, target);
		}
	}

	public static void refreshLine(int lineNum) {
		Node cur = heads.get(lineNum);

		while (cur != null) {
			line[dict.get(cur.name)] = lineNum;
			cur = cur.next;
		}

	}

	public static void PrintLine() {
		StringBuilder sb = new StringBuilder();

		for (int i = 1; i <= M; i++) {
			Node cur = heads.get(i);
			if (cur == null) {
				sb.append("-1").append("\n");
				continue;
			}
			while (cur.next != null) {
				sb.append(cur.name + " ");
				cur = cur.next;
			}
			sb.append(cur.name).append("\n");
		}

		System.out.println(sb.toString());
	}

	public static void disconnect(Node n) {
		connect(n.pre, n.next);
		n.pre = null;
		n.next = null;
	}
}