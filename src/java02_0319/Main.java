package java02_0319;

import java.time.LocalDateTime;
import java.time.format.TextStyle;
import java.util.Locale;

public class Main {

	public static void main(String[] args) {
		System.out.println("task01-----------------------");
		task01();
		System.out.println("task02-----------------------");
		task02();
		System.out.println("task03-----------------------");
		task03();
		System.out.println("task04-----------------------");
		task04(0);
		task04(1);
		task04(2);

	}

	public static void task01() {
		String greeting = "Hello, World!";
		System.out.println(greeting);
	}

	public static void task02() {
		int total = 0;
		for (int i = 0; i < 10; i++) {
			total += i + 1;
		}
		System.out.println("1から10の合計は" + total);
	}

	public static void task03() {
		LocalDateTime future = LocalDateTime.now().plusYears(3);
		System.out.print("三年後の今日は");
		System.out.print(future.getDayOfWeek().getDisplayName(TextStyle.SHORT, Locale.JAPANESE));
		System.out.println("曜日です");
//		見やすさ優先で分解した方が良いのか?
	}

	public static void task04(int i) {
		if (i == 0 || i == 1) {
			System.out.println(task04Boolean(i));
		} else {
			System.out.println("0か1を入力してください");
		}
	}

	public static boolean task04Boolean(int i) {
		if (i == 0) {
			return false;
		}
		return true;
	}

}
