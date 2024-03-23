import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
	static class Student implements Comparable<Student> {
		int num;
		int height;
		int weight;

		public Student(int num, int height, int weight) {
			super();
			this.num = num;
			this.height = height;
			this.weight = weight;
		}

		@Override
		public String toString() {
			return "Student [num=" + num + ", height=" + height + ", weight=" + weight + "]";
		}

		@Override
		public int compareTo(Student o) {
			if (this.height != o.height) {
				return this.height - o.height;
			} else {
				return o.weight - this.weight;
			}

		}

	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		StringTokenizer st;
		ArrayList<Student> arr = new ArrayList<>();

		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			int height = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			arr.add(new Student(i, height, weight));
		}
		Collections.sort(arr);
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < arr.size(); i++) {
			Student cur = arr.get(i);

			sb.append(cur.height).append(" ").append(cur.weight).append(" ").append(cur.num).append("\n");
		}

		System.out.println(sb.toString());

	}

}