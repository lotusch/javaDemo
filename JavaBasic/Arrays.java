
/**
	���鶼���������͡�
*/
class ArraysDemo{
	public static void main(String[] args){
		
		int[][] arr = new int[4][3];
		System.out.println(arr[0]);//[I@15db9742 ��ֵַ
		System.out.println(arr[0][0]);//0

		System.out.println(arr);//[[I@6d06d69c ��ֵַ [[��ά���飬I int�� @�� 6d06d69c

		System.out.println("****************");
		float[][] arr1 = new float[4][3];
		System.out.println(arr1[0]);//��ֵַ
		System.out.println(arr1[0][0]);//0.0

		System.out.println("*****************");
		String[][] arr2 = new String[3][5];
		System.out.println(arr2[0]);//��ֵַ
		System.out.println(arr2[0][0]);//null

		System.out.println("*****************");
		double[][] arr3 = new double[4][]; //�ڲ�����Ԫ��û�г�ʼ�����������Ԫ��arr[0] arr[1]��û�б����ڲ�Ԫ�ص�ַ
		System.out.println(arr3[1]);//null
		System.out.println(arr3[1][0]);//��ָ���쳣��arr3[1]�����ַ��null,null��ָ���Ϊ��ָ���쳣��


	}
}
