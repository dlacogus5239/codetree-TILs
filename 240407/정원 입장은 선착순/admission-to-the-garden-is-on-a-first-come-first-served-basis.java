import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static int N;

	static class Person {
		int num; // 사람 번호
		int time; // 정원에 있는 시간
		int arrive; // 도착 시간

		public Person(int num, int time, int arrive) {
			super();
			this.num = num;
			this.time = time;
			this.arrive = arrive;
		}

		@Override
		public String toString() {
			return "Person [num=" + num + ", time=" + time + ", arrive=" + arrive + "]";
		}

	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		PriorityQueue<Person> pq = new PriorityQueue<>((o1, o2) -> o1.arrive - o2.arrive);
		StringTokenizer st;
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			int arrive = Integer.parseInt(st.nextToken());
			int time = Integer.parseInt(st.nextToken());
			pq.offer(new Person(i, time, arrive));
		}

		// 첫번째 사람은 무조건 처음 들어감
		Person cur = pq.poll();
		int nextTime = 0;
		nextTime = cur.arrive + cur.time;
		PriorityQueue<Integer> result = new PriorityQueue<>((o1, o2) -> (o2 - o1));
		PriorityQueue<Person> waiting = new PriorityQueue<>((o1, o2) -> (o1.num - o2.num));
		result.offer(0);
		// 처음 사람 처리완료

		while (!pq.isEmpty()) {
			cur = pq.peek();
			// 도착시간이 지금 정원에 있는 사람이 나오기 전이면 waiting에 추가
			if (nextTime > cur.arrive) {
				waiting.offer(pq.poll());
				continue;
			} else { // 다 도착처리 이후
				// 기다리는 사람 처리
				if (!waiting.isEmpty()) {
					Person enter = waiting.poll();
					result.offer(nextTime - enter.arrive);
					nextTime += enter.time;
				} else {
					result.offer(0);
					nextTime = cur.arrive + cur.time;
					pq.poll();
				}
			}
		}

		if (!waiting.isEmpty()) {
			Person enter = waiting.poll();
			result.offer(nextTime - enter.arrive);
			nextTime += enter.time;
		}

		System.out.println(result.poll());

	}

}