
/**
	自定义数组的工具类
*/
class ArrayUtil
{
	//求数组的最大值
	public int getMax(int[] arr){
		int maxValue = arr[0];
		for(int i=1;i<arr.length;i++){
			if(maxValue < arr[i]){
				maxValue = arr[i];
			}
		}
		return maxValue;
	}

	//求数组的最小值
	public int getMin(int[] arr){
		int minValue = arr[0];
		for(int i=1;i<=arr.length;i++){
			if(minValue > arr[i]){
				minValue = arr[i];
			}
		}
		return minValue;
	}

	//求数组总和
	public int getSum(int[] arr){
		int sum = 0;
		for(int i=0;i<arr.length;i++){
			sum +=arr[i];
		}
		return sum;
	}

	//求数组的平均值
	public int getAvg(int[] arr){

		return getSum(arr) / arr.length; //方法中调同类中的方法
	}

	//反转数组
	public void reverse(int[] arr){
		//方法一
		for(int i=0;i<arr.length / 2 ;i++){
			int temp = arr[i];
			arr[i] = arr[arr.length-i-1];
			arr[arr.length-i-1] = temp;
		}

		//方法二
		for(int i=0,j=arr.length-1;i<j;i++,j--){
			String temp = arr[i];
			arr[i] = arr[j];
			arr[j] = temp;
		}
		
	}

	//复制数组
	public int[] copy(int[] arr){
		int[] arr1 = new int[arr.length];
		for(int i = 0;i<arr1.length;i++){
			arr1[i] = arr[i];
		}
		return arr1;
	}

	//数组排序
	public void sort(int[] arr){
		//冒泡排序
		for(int i = 0;i<arr.length-1;i++){
			for(int j = 0;j<arr.length-1-i;j++){
				if(arr[j]>arr[j+1]){
					int temp = arr[j];
					arr[j] = arr[j+1];
					arr[j+1] = temp;
				}
			}
		}
	}

	//遍历数组
	public void print(int[] arr){
		for(int i=0;i<arr.length;i++){
			System.out.print(arr[i]+"\t");
		}
	}

	//查找指定元素 线性查找
	public int getIndex(int[] arr,int dest){
		//线性查找
		for(int i=0;i<arr.length;i++){
			if(dest == arr[i]){ //其它类型的使用dest.equals(arr[i])，int是值类型，所以用==也可以。
				return i;
			}
		}
		return -1; //没找到，返回一个负数。
	}

	//二分法查找
	//前提：所要查找的数组必须有序
	public int getIndex(int[] arr,int dest,1){ //重载getIndex,1 为常量形参
		//int[] arr = new int[]{-188,-36,-24,2,35,36,59,66,79,89,102,210,333,356};
		int head = 0;//首索引
		int end = arr.length-1;//末索引
		boolean ifFlag = true;
		while(head <= end){
			int middle = (head + end)/2;
			if(dest == arr[middle]){
				System.out.println("找到了，索引位置是"+middle);
				isFlag == false;
				break;
			}else if(arr[middle] > dest){
				end = middle-1;
			}else{
				head = middle+1;
			}
		}
		if(isFlag){
			System.out.println("很遗憾，目标值不在此数组中。");
		}
		
	}
}