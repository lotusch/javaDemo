
/**
	int[] arr = new int[]{12,3,3,34,56,77,432};
	������ÿ��λ���ϵ�ֵȥ������λ�õ�Ԫ�أ��õ��Ľ����Ϊ��λ���ϵ���ֵ��
	�����µ����顣

*/
class TransferValue
{
	public static void main(String[] args){
		int[] arr = new int[]{12,3,3,34,56,77,432};
		//1 ���ű���
		for(int i=arr.length-1;i>=0;i--){
			arr[i] = arr[i]/arr[0];
		}

		//2 ʵ��һ������
		int temp = arr[0]; //����һ�������Ŀ���
		for(int i=0;i<arr.length;i++){
			arr[i] = arr[i]/temp;
		}
	}
}