/**
	���Է�װ�Զ�������
*/
class ArrayUtilTest
{
	public static void main(String[] args){

		ArrayUtil util = new ArrayUtil();
		int[] arr = new int[]{32,68,74,32,5,3,2,654,-98,0,-53,5};
		int max = util.getMax(arr);
		System.out.println("���ֵΪ��"+max);

		System.out.println("����ǰ��");
		util.print(arr);
		System.out.println();
		
		util.sort(arr);
		System.out.println("�����");
		util.print(arr);

		System.out.println("���ң�5");
		int index = util.getIndex(arr,5);
		if(index>=0){
			System.out.println("�ҵ��ˣ�������ַΪ��" +index);
		}else{
			System.out.println("δ�ҵ�");
		}

	}
}