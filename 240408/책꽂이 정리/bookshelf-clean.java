import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {
	static int N, K;
	static int Q;

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

	}

	static HashMap<Integer, Node> books, heads, tails;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		books = new HashMap<>();
		heads = new HashMap<>();
		tails = new HashMap<>();
		// 1번 책꽃이에 1-N개의 책 Init
		for (int i = 1; i <= N; i++) {
			books.put(i, new Node(i));
		}
		tails.put(1, books.get(N));
		heads.put(1, books.get(1));
		for (int i = 1; i < N; i++) {
			connect(books.get(i), books.get(i + 1));
		}
		Q = Integer.parseInt(br.readLine());

		for (int q = 0; q < Q; q++) {
			st = new StringTokenizer(br.readLine());

			int oper = Integer.parseInt(st.nextToken());
			int i = Integer.parseInt(st.nextToken());
			int j = Integer.parseInt(st.nextToken());
			Node n;
			switch (oper) {
			case 1:
				n = pop_front(i);

				if (n != null) {
					push_back(j, n);
				}
				break;
			case 2:
				n = pop_back(i);
				if (n != null) {
					push_front(j, n);
				}
				break;
			case 3:
				move_all_front(i, j);
				break;
			case 4:
				move_all_back(i, j);
				break;
			}
		}
		StringBuilder sb = new StringBuilder();
		for (int i = 1; i <= K; i++) {
			int cnt = 0;

			Node cur = heads.get(i);
			while (null != cur) {
				cnt++;
				cur = cur.next;
			}
			sb.append(cnt).append(" ");

			cur = heads.get(i);
			while (cur != null) {
				sb.append(cur.num).append(" ");
				cur = cur.next;
			}

			sb.append("\n");
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

	// i번 연결 리스트의 head를 삭제한 후 반환
	public static Node pop_front(int i) {
		Node ret = heads.get(i);
		if (ret != null) {
			heads.put(i, ret.next);
			ret.next = null;

			if (heads.get(i) != null) {
				heads.get(i).pre = null;
			} else {
				tails.put(i, null);
			}
		}

		return ret;
	}

	// i번 연결 리스트의 tail을 삭제한 후 반환
	public static Node pop_back(int i) {
		Node ret = tails.get(i);
		if (ret != null) {
			tails.put(i, ret.pre);

			ret.pre = null;

			if (tails.get(i) != null) {
				tails.get(i).next = null;

			} else {
				heads.put(i, null);
			}
		}

		return ret;
	}

	public static void push_front(int i, Node singleton) {
		if (heads.get(i) == null) {
			heads.put(i, singleton);
			tails.put(i, singleton);
		} else {
			connect(singleton, heads.get(i));
			heads.put(i, singleton);
		}
	}

	public static void push_back(int i, Node singleton) {
		if (tails.get(i) == null) {
			heads.put(i, singleton);
			tails.put(i, singleton);
		} else {
			connect(tails.get(i), singleton);
			tails.put(i, singleton);
		}
	}

	public static void move_all_front(int i, int j) {
		if (i == j || heads.get(i) == null) {
			return;
		}

		if (heads.get(j) == null) {
			// j번 연결 리스트가 비어있다면, head와 tail은 i번의 것과 동일
			heads.put(j, heads.get(i));
			tails.put(j, tails.get(i));
		} else {
			// j번 연결 리스트가 비어있지 않다면
			// i번의 tail과 j번의 head를 연결하고
			// j번의 head는 i번의 head가 됨
			connect(tails.get(i), heads.get(j));
			heads.put(j, heads.get(i));
		}
		// 이제, i번 연결 리스트는 비어있음
		heads.put(i, null);
		tails.put(i, null);
	}

	public static void move_all_back(int i, int j) {
		if (i == j || heads.get(i) == null) {
			return;
		}

		if (heads.get(j) == null) {
			// j번 연결 리스트가 비어있다면, head와 tail은 i와 동일
			heads.put(j, heads.get(i));
			tails.put(j, tails.get(i));
		} else {
			// j번 연결 리스트가 비어있지 않다면
			// j번의 tail과 i번의 head를 연결하고
			// j번의 tail는 i번의 tail이 됨
			connect(tails.get(j), heads.get(i));
			tails.put(j, tails.get(i));
		}
		// 이제, i번 연결 리스트는 비어있음
		heads.put(i, null);
		tails.put(i, null);
	}

}