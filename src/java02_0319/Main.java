package java02_0319;

import java.time.LocalDateTime;
import java.time.format.TextStyle;
import java.util.Arrays;
import java.util.Locale;
import java.util.stream.IntStream;

public class Main {

	public static void main(String[] args) {

		System.out.println("----1, String型変数を用いてHello, Worldの表示----");
		practice01();

		System.out.println("----2, for文を用いて1～10まで足した結果の表示----");
		practice02(); /* for文を用いて */
		practice02_oneLine(); /* IntStreamを用いて1行で */

		System.out.println("----3, 3年後の今日は何曜日？----");
		practice03();

		System.out.println("----4, 引数が0なら偽、引数が1なら真と表示----");
		practice04(0);
		practice04(1);
		practice04(2);

	}

	public static void practice01() {
		String greeting = "Hello, World!";
		System.out.println(greeting);
	}

	public static void practice02() {
		int total = 0;
		for (int i = 0; i < 10; i++) {
			total += i + 1;
		}
		System.out.println("1から10の合計は" + total);
	}

	public static void practice02_oneLine() {
		System.out.println("1から10の合計は" + IntStream.rangeClosed(1, 10).sum());
	}

	public static void practice03() {
		LocalDateTime future = LocalDateTime.now().plusYears(3);
		System.out.print("三年後の今日は");
		System.out.print(future.getDayOfWeek().getDisplayName(TextStyle.SHORT, Locale.JAPANESE));
		System.out.println("曜日です");
	}

	public static void practice04(int i) {
		if (i == 0 || i == 1) {
			System.out.println(practice04_01(i));
		} else {
			System.out.println("0か1を入力してください");
		}
	}

	public static boolean practice04_01(int i) {
		if (i == 0) {
			return false;
		}
		return true;
	}

}
