package java03_0326;

public class MergeSort {
	
	/*【mergeSortメソッド】
	 * 配列の要素数を真ん中で2つのグループに分割していく処理を繰り返す
	 * 最終的にleftとrightの差が1になるまで分割
	 * 過程で分割されたものも含めてmergeメソッドへと渡される。
	 * 最下層から順にmergeメソッドが行われていく
	 * 
	 * (例) 配列の要素数が9の場合
	 * 0,1,2,3,4,5,6,7,8,9　 （最上層）
	 * 0,1,2,3,4  /  5,6,7,8,9    　 
	 * 0,1,2  /  3,4  //  5,6,7  /  8,9　   
	 * 0,1 / 2  //  3,4   ///   5,6 / 7  // 8,9　（最下層）
	 *　
	 */
	public static void mergeSort(int[] intArray, int left, int right) {
			if (left < right) {
				int mid = (left + right) / 2;

				mergeSort(intArray, left, mid);
				mergeSort(intArray, mid + 1, right);
				merge(intArray, left, mid, right);
			}
		}
		

	/*【mergeメソッド】
	 * mergeSortで分割された要素数のグループを用いて最下層から昇順に並び替えていく
	 * 
	 * ①
	 * (0,1)グループが渡されると配列n1Array[ intArray[0] ]と配列n2Array[ intArray[1] ]を作る
	 * 先頭同士を比較してintArrayに格納してintArray[0], intArray[1] が昇順に整列
	 * 
	 * ②
	 * (0,1,2)グループ渡されると配列n1Array[ intArray[0], intArray[1] ]と配列n2Array[ intArray[2] ]を作る
	 * 先頭同士を比較してintArrayに格納し、intArray[0], intArray[1], intArray[2] が昇順に整列
	 *
	 * ③〜　(最上層まで繰り返し)
	 */
		public static void merge(int[] intArray, int left, int mid, int right) {
			int leftLength = mid - left + 1; // left ~ midの長さ
			int rightLength = right - mid; // mid＋1 ~ rightの長さ

			int[] leftArray = new int[leftLength]; // intArray[left ~ mid] を格納する配列の宣言
			int[] rightArray = new int[rightLength]; // intArray[mid+1 ~ right] を格納する配列の宣言

			for (int i = 0; i < leftLength; i++) { // intArray[left ~ mid] をleftArrayに格納
				leftArray[i] = intArray[left + i];
			}
			for (int i = 0; i < rightLength; i++) { // intArray[mid+1 ~ right] をrightArrayに格納
				rightArray[i] = intArray[mid + 1 + i];
			}

			int leftCount = 0; 
			int rightCount = 0;
			int sortPoint = left; // intArrayへの格納場所を示す。leftからrightまで1ずつ加算

			while (leftCount < leftLength && rightCount < rightLength) { // leftArrayとrightArrayの先頭を比較して小さい方をintArrayに格納
				if (leftArray[leftCount] <= rightArray[rightCount]) {
					intArray[sortPoint] = leftArray[leftCount];
					leftCount++;
				} else {
					intArray[sortPoint] = rightArray[rightCount];
					rightCount++;
				}
				sortPoint++;
			}

			while (leftCount < leftLength) { // rightArrayの格納が先に終わった場合
				intArray[sortPoint] = leftArray[leftCount];
				leftCount++;
				sortPoint++;
			}

			while (rightCount < rightLength) { // leftArrayの格納が先に終わった場合
				intArray[sortPoint] = rightArray[rightCount];
				sortPoint++;
				rightCount++;
			}
			
//			確認のため処理を一つずつ出力
//			最後は昇順にマージソートを終えたintArrayを全て出力
			System.out.println(left + "~" + right + "番目の整列");
			for (int num = left; num <= right; num++) {
				System.out.print(intArray[num] + " ");
			}
			System.out.println("\n");
		}		


}
