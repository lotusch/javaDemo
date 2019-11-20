
/**
	数组都是引用类型。
*/
class ArraysDemo{
	public static void main(String[] args){
		
		int[][] arr = new int[4][3];
		System.out.println(arr[0]);//[I@15db9742 地址值
		System.out.println(arr[0][0]);//0

		System.out.println(arr);//[[I@6d06d69c 地址值 [[二维数组，I int型 @在 6d06d69c

		System.out.println("****************");
		float[][] arr1 = new float[4][3];
		System.out.println(arr1[0]);//地址值
		System.out.println(arr1[0][0]);//0.0

		System.out.println("*****************");
		String[][] arr2 = new String[3][5];
		System.out.println(arr2[0]);//地址值
		System.out.println(arr2[0][0]);//null

		System.out.println("*****************");
		double[][] arr3 = new double[4][]; //内层数组元素没有初始化，所以外层元素arr[0] arr[1]等没有保存内层元素地址
		System.out.println(arr3[1]);//null
		System.out.println(arr3[1][0]);//空指针异常；arr3[1]保存地址是null,null再指向空为空指针异常。


	}
}
