/*
 * 先日解いていた試験勉強の問題にてマージソートのアルゴリズムが
 * 取り上げられていたので、第3回課題と合わせてjavaで書けるか試してみました。
 * 
 *【やりたいこと】
 *    ・要素数も中身もランダムな数字の配列を作ってマージソートを行う 
 *    ・その過程にオリジナル例外も入れてみる
 *    ・デバックツールを用いてエラーを解決する
 *    ・デバックツールを用いて再帰処理を一行ずつ理解する
 *    ・ファイルの生成&書き込み、IOExceptionの処理をする
 */

package java03_0326;

import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) {

//		10から20までのランダムな長さの配列に乱数(0~99)を格納する
		int[] intArray = makeArray();

//		マージソート前の配列を出力する
		Arrays.stream(intArray).forEach(i -> System.out.print(i + " "));
		System.out.print("\n");

//		配列の中に重複した数字があるかチェック
//		重複があればメソッドから例外がthrowされてここでcatch
		try {
			duplicateCheck(intArray);
		} catch (DuplicateInArrayException e) {
			System.out.println(e.getMessage());
		}finally {
			System.out.println("マージソートを行います\n");
		}

//		マージソートを行い、ソートの過程と結果を出力する
		MergeSort.mergeSort(intArray, 0, intArray.length - 1);
		System.out.println("マージソートが完了しました\n");
		
//		intArrayの結果を出力したtxtファイルをデスクトップに生成
//		(2回目以降は追記される)
		System.out.println("ファイルを生成します");
		makeFile(intArray);
	}
	
	
//	10から20までのランダムな長さの配列に乱数(0~99)を格納するメソッド
	public static int[] makeArray() {
		int indexNum = new Random().nextInt(11) + 10;
		int[] intArray = new int[indexNum];

		for (int i = 0; i < indexNum; i++) {
			intArray[i] = new Random().nextInt(100);
		}
		return intArray;
	}
	

//	配列内の数字に重複があった場合、オリジナル例外(DuplicateArrayException)をthrowするメソッド
	public static void duplicateCheck(int[] intArray) throws DuplicateInArrayException {
		int duplicate = 0;
		for (int i = 0; i < intArray.length; i++) { // もし重複があればduplicateに1を加算
			for (int j = i + 1; j < intArray.length; j++) {
				if (intArray[i] == intArray[j]) {
					duplicate++;
				}
			}
		}
		if (duplicate > 0) {
			throw new DuplicateInArrayException("配列内の数字が重複しています");
		}
	}
	
//	intArrayの結果を出力したtxtファイルをデスクトップに生成
	public static void makeFile(int[] intArray) {
		
//		配列を文字列に変換
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < intArray.length; i++) {
			sb.append(intArray[i]).append(" ");
		}
		String intArrayString = sb.toString() + "\n";
		
//		Desktopまでパスを取得してtxtファイルの生成
		File desktopPath = new File(System.getProperty("user.home"), "Desktop");
		File file = new File(desktopPath, "sortedRandomInt.txt");
		
//		ファイルに書き込み
		try (FileWriter filewriter = new FileWriter(file, true);){
			filewriter.write(intArrayString);
			System.out.println("テキストファイルの作成に成功しました");
		}catch(IOException e){
			System.out.println("テキストファイルの生成に失敗しました");
			e.printStackTrace();
		}		
	}

}
