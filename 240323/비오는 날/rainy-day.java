import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;



public class Main {
	static class Info {
		int date;
		String day;

		public Info(int date, String day) {
			super();
			this.date = date;
			this.day = day;
		}

		@Override
		public String toString() {
			return "Info [date=" + date + ", day=" + day + "]";
		}

	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());

		ArrayList<Info> weather = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			String[] cur = br.readLine().split(" ");

			if (cur[2].equals("Rain")) {
				int date = Integer.parseInt(cur[0].replaceAll("-", ""));
				String day = cur[1];
				weather.add(new Info(date, day));
			} else {
				continue;
			}
		}

		Collections.sort(weather, (o1, o2) -> (o1.date - o2.date));
		String Date = Integer.toString(weather.get(0).date);
		String resultDate = Date.substring(0, 4) + "-" + Date.substring(4, 6) + "-" + Date.substring(6, Date.length());
		String resultDay = weather.get(0).day;

		System.out.println(resultDate + " " + resultDay + " Rain");
	}

}