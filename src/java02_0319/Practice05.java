package java02_0319;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Practice05 {

	public static void main(String[] args) {

		Map<String, Double> prefCharm = new HashMap<String, Double>();

		/*
		 * 都道府県魅力度ランキング（地域ブランド調査2021）の点数一覧
		 */

		prefCharm.put("Hokkaido", 73.4);
		prefCharm.put("Kyoto", 56.4);
		prefCharm.put("Okinawa", 54.4);
		prefCharm.put("Tokyo", 47.5);
		prefCharm.put("Osaka", 42.0);
		prefCharm.put("Kanagawa", 40.0);
		prefCharm.put("Fukuoka", 37.5);
		prefCharm.put("Nagasaki", 33.9);
		prefCharm.put("Nara", 33.4);
		prefCharm.put("Ishikawa", 32.5);
		prefCharm.put("Nagano", 32.5);
		prefCharm.put("Chiba", 30.4);
		prefCharm.put("Hyogo", 30.0);
		prefCharm.put("Miyagi", 30.0);
		prefCharm.put("Shizuoka", 29.4);
		prefCharm.put("Kagoshima", 27.1);
		prefCharm.put("Miyazaki", 26.6);
		prefCharm.put("Kumamoto", 26.4);
		prefCharm.put("Hiroshima", 26.0);
		prefCharm.put("Aichi", 25.3);
		prefCharm.put("Aomori", 24.6);
		prefCharm.put("Niigata", 24.1);
		prefCharm.put("Mie", 22.4);
		prefCharm.put("Toyama", 22.3);
		prefCharm.put("Yamanashi", 22.2);
		prefCharm.put("Akita", 21.9);
		prefCharm.put("Oita", 21.9);
		prefCharm.put("Kagawa", 21.2);
		prefCharm.put("Kochi", 20.7);
		prefCharm.put("Iwate", 19.9);
		prefCharm.put("Yamagata", 19.8);
		prefCharm.put("Ehime", 19.7);
		prefCharm.put("Wakayama", 19.7);
		prefCharm.put("Fukushima", 19.6);
		prefCharm.put("Okayama", 19.2);
		prefCharm.put("Gifu", 18.2);
		prefCharm.put("Shimane", 17.3);
		prefCharm.put("Shiga", 16.8);
		prefCharm.put("Fukui", 16.6);
		prefCharm.put("Tottori", 16.5);
		prefCharm.put("Tochigi", 16.2);
		prefCharm.put("Yamaguchi", 15.6);
		prefCharm.put("Tokushima", 15.6);
		prefCharm.put("Gunma", 15.3);
		prefCharm.put("Saitama", 14.4);
		prefCharm.put("Saga", 12.8);
		prefCharm.put("Ibaraki", 11.6);

//		valueを降順にソートしてLinkedHashMapに格納
		Stream<Map.Entry<String, Double>> sortedStream = prefCharm.entrySet().stream().sorted(Collections.reverseOrder(Map.Entry.comparingByValue()));
		LinkedHashMap<String, Double> sortedPrefCharm = sortedStream.collect(Collectors.toMap(e -> e.getKey(), e -> e.getValue(), (u, v) -> v, LinkedHashMap::new));
		
//	   ---------------------------------------------------------
		/*
		 * 【やりたいこと】
		 * 	 1. 出身地を入力 
		 * 	 2. 出身地の魅力ポイントを表示 
		 * 	 3. ランキングを表示
		 */

		System.out.println("あなたの出身地の都道府県を入力してください");
		System.out.println("(１文字目が大文字のローマ字で入力)");
		System.out.println("(例: 東京 → Tokyo, 大阪 → Osaka)");
		String homePref = new Scanner(System.in).nextLine();

		if (prefCharm.containsKey(homePref)) {  /* 入力文字が正しい場合 */
			System.out.println(homePref + "の魅力度は" + prefCharm.get(homePref));

		/*【出身地のランキングを求める】
		 *   1.イテレーターとArrayListを準備
		 *   2.魅力度の点数で降順にソートされたsortedPrefCharmを順番にArrayListに格納
		 *   3.出身地が出てきたらそこで格納を止める
		 *   4.ArrayList.sizeを用いてランキング番号を表示
		 *   5.点数が同じで同率の場合は早い方の位を表示するように処理
		 *   　(例: 秋田と大分は同じ21.9点で同率26位として表示)
		 */  
			
//			イテレーターとArrayListを準備
			Iterator<Map.Entry<String, Double>> it = sortedPrefCharm.entrySet().iterator();
			List<String> prefRankingList = new ArrayList<String>();

//			  valueで降順にソートされたsortedPrefCharmを順番にArrayListに格納
			while (it.hasNext()) {
				Map.Entry<String, Double> nextit = it.next();
				if (nextit.getKey().equals(homePref)) {
					prefRankingList.add(nextit.getKey());
					break;     /*出身地が出てきたらそこで格納を止める*/
				} else {
					prefRankingList.add(nextit.getKey());
				}
			}

//			ArrayList.size()を用いてランキング番号を表示
			if (prefRankingList.size() == 1) {
				System.out.println(prefRankingList.size() + "位です");
			} else if ( /*点数が同じで同率の場合は早い方の位を表示するように処理*/
					(double) prefCharm.get(prefRankingList.get(prefRankingList.size() - 1))
					== (double) prefCharm.get(prefRankingList.get(prefRankingList.size() - 2))) {
				System.out.println(prefRankingList.size() - 1 + "位です");
			} else {
				System.out.println(prefRankingList.size() + "位です");
			}

		} else { /*入力文字が正しく無い場合*/
			System.out.println("入力された文字が正しくおありません");
		}

//		------------------------------------------------------------------------------
//		以下、色々と調べた処理のメモ
//		------------------------------------------------------------------------------
		/*
		 * 保管されている全ての値を取得
		 * 
		 */
//		Collection<Double> charmPoint = prefCharm.values();
//		for (double point : charmPoint) {
//			System.out.println(point);
//		}

//		-------------------------------------------------------------------------------
		/*
		 * 保管されている全てのキーを取得
		 * 
		 */
//		Set<String> prefSet = prefCharm.keySet();
//		for (String t : prefSet) {
//			System.out.println(t);
//		}

//		-------------------------------------------------------------------------------
		/*
		 * キーと値をセットで取り出す
		 * 
		 */

//		全てのキーと値を取り出す(拡張for)

//		for (Map.Entry<String, Double> entry : prefCharm.entrySet()) {
//			System.out.println(entry.getKey() + ": " + entry.getValue());
//		}

		
//		全てのキーと値を取り出す(forEachとラムダ式)

//		prefCharm.forEach((key, value) -> System.out.println(key + " " + value));

		
//		全てのキーと値を取り出す(Map.Entryでイテレーター)

//		Iterator<Map.Entry<String, Double>> it = prefCharm.entrySet().iterator();
//		while (it.hasNext()) {
//			Map.Entry<String, Double> nextit = it.next();
//			System.out.println(nextit.getKey() + ": " + nextit.getValue());
//		}

//		-------------------------------------------------------------------------------
		/*
		 * ソート
		 * 
		 */

//		キーをAtoZにソートして一覧表示、ZtoAの場合はArrays.sort(mapKey, Collections.reverseOrder())

//		Object[] mapKey = prefCharm.keySet().toArray();
//		Arrays.sort(mapKey);
//		for (Object pref : mapKey) {
//			System.out.print(pref + ": ");
//			System.out.println(prefCharm.get(pref));
//		}

		
//　Streamを用いてvalueでソート (同じ方法でキーでソートも可能)

//		Stream<Map.Entry<String, Double>> sorted = prefCharm.entrySet().stream().sorted(Map.Entry.comparingByValue());
//		sorted.forEach(System.out::println);

		
//　一行Streamを用いてvalueでソート

//		prefCharm.entrySet().stream().sorted(Map.Entry.comparingByValue()).forEach(System.out::println);

//		-------------------------------------------------------------------------------
		/*
		 * 絞り込み
		 * 
		 */

//		絞り込み(List経由) 配列からforEachはStream経由で

//		List<String> keyPrefList = new ArrayList<String>(prefCharm.keySet());
//		Stream<String> stream = keyPrefList.stream();
//		Stream<String> stream2 = stream.filter(pref -> pref.startsWith("H"));
//		String[] prefArray = stream2.toArray(String[]::new);
//		Arrays.stream(prefArray).forEach(s -> System.out.println(s + "の魅力度は" + prefCharm.get(s)));

		
//		  絞り込み(Map.Entryで)

//		Stream<Map.Entry<String, Double>> stream = prefCharm.entrySet().stream();
//		Stream<Map.Entry<String, Double>> stream2 = stream.filter(entry -> entry.getValue() <= 50.0);
//		Map<String, Double> filteredMap = stream2
//				.collect(Collectors.toMap(e -> e.getKey(), e -> e.getValue(), (u, v) -> v, HashMap::new));
//		filteredMap.forEach((key, value) -> System.out.println(key + " " + value));

	}
}
