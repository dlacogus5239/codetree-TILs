import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {
	static class Student {
		int num;
		Student pre;
		Student next;

		public Student(int num) {
			super();
			this.num = num;
			this.pre = null;
			this.next = null;
		}

	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int Q = Integer.parseInt(br.readLine());

		HashMap<Integer, Student> row = new HashMap<>();
		int cnt = 1;
		// 일단 1번 학생 줄 세움
		row.put(1, new Student(1));
		StringBuilder sb = new StringBuilder();

		for (int q = 0; q < Q; q++) {
			st = new StringTokenizer(br.readLine());

			int oper = Integer.parseInt(st.nextToken());
			int a, b;
			int init;
			switch (oper) {
			case 1:
				a = Integer.parseInt(st.nextToken());
				b = Integer.parseInt(st.nextToken());

				init = cnt + 1;
				cnt += b;
				for (int i = 0; i < b; i++) {
					row.put(init + i, new Student(init + i));

					if (i != 0) {
						connect(row.get(init + i - 1), row.get(init + i));
					}
				}
				insertNodesNext(row.get(init), row.get(init + b - 1), row.get(a));

				break;
			case 2:
				a = Integer.parseInt(st.nextToken());
				b = Integer.parseInt(st.nextToken());
				init = cnt + 1;
				cnt += b;
				for (int i = 0; i < b; i++) {
					row.put(init + i, new Student(init + i));
					if (i != 0) {
						connect(row.get(init + i - 1), row.get(init + i));
					}
				}
				insertNodesPre(row.get(init), row.get(init + b - 1), row.get(a));

				break;
			case 3:
				a = Integer.parseInt(st.nextToken());
				if (row.get(a).pre == null || row.get(a).next == null) {
					sb.append("-1").append("\n");
				} else {
					sb.append(row.get(a).pre.num).append(" ").append(row.get(a).next.num).append("\n");
				}
				break;
			}
		}

		System.out.println(sb.toString());

	}

	public static void connect(Student s, Student e) {
		if (s != null) {
			s.next = e;
		}
		if (e != null) {
			e.pre = s;
		}
	}

	// s - e 까지 학생들 v 뒤에 연결
	public static void insertNodesNext(Student s, Student e, Student v) {
		Student nextV = v.next;
		connect(v, s);
		connect(e, nextV);
	}

	// s - e 까지 학생들 v 앞에 연결
	public static void insertNodesPre(Student s, Student e, Student v) {
		connect(v.pre, s);
		connect(e, v);
	}
}