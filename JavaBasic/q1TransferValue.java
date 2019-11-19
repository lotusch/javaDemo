
/**
	int[] arr = new int[]{12,3,3,34,56,77,432};
	让数组每个位置上的值去除以首位置的元素，得到的结果作为该位置上的新值，
	遍历新的数组。

*/
class TransferValue
{
	public static void main(String[] args){
		int[] arr = new int[]{12,3,3,34,56,77,432};
		//1 倒着遍历
		for(int i=arr.length-1;i>=0;i--){
			arr[i] = arr[i]/arr[0];
		}

		//2 实例一个变量
		int temp = arr[0]; //多了一个变量的开销
		for(int i=0;i<arr.length;i++){
			arr[i] = arr[i]/temp;
		}
	}
}