import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {
	static class City {
		String name;
		City pre;
		City next;

		public City(String name) {
			super();
			this.name = name;
			this.pre = null;
			this.next = null;
		}
	}

	static int N;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		int Q = Integer.parseInt(st.nextToken());

		HashMap<Integer, City> map = new HashMap<>();
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			map.put(i, new City(st.nextToken()));
		}
		// 연결
		for (int i = 1; i < N; i++) {
			connect(map.get(i), map.get(i + 1));
		}
		connect(map.get(N), map.get(1));
		City cur = map.get(1);
		StringBuilder sb = new StringBuilder();

		for (int q = 0; q < Q; q++) {
			st = new StringTokenizer(br.readLine());
			int oper = Integer.parseInt(st.nextToken());
			switch (oper) {
			case 1:
				if (cur.next != null) {
					cur = cur.next;
				}
				break;
			case 2:
				if (cur.pre != null) {
					cur = cur.pre;
				}
				break;
			case 3:
				if (cur.next != null) {
					City n = cur.next;
					connect(cur, n.next);
					n.pre = null;
					n.next = null;
				}
				break;
			case 4:
				String tmp = st.nextToken();
				insertCityNext(cur, new City(tmp));
				break;

			}
			if (cur.pre.name.equals(cur.next.name) || cur.pre == null || cur.next == null) {
				sb.append("-1").append("\n");
			} else {
				sb.append(cur.pre.name).append(" ").append(cur.next.name).append("\n");
			}
		}

		System.out.println(sb.toString());
	}

	public static void connect(City s, City e) {
		if (s != null) {
			s.next = e;
		}
		if (e != null) {
			e.pre = s;
		}
	}

	public static void insertCityNext(City cur, City singleton) {
		if (cur.next == null) {
			connect(cur, singleton);
		} else {
			connect(singleton, cur.next);
			connect(cur, singleton);
		}
	}

}