
/**
	�Զ�������Ĺ�����
*/
class ArrayUtil
{
	//����������ֵ
	public int getMax(int[] arr){
		int maxValue = arr[0];
		for(int i=1;i<arr.length;i++){
			if(maxValue < arr[i]){
				maxValue = arr[i];
			}
		}
		return maxValue;
	}

	//���������Сֵ
	public int getMin(int[] arr){
		int minValue = arr[0];
		for(int i=1;i<=arr.length;i++){
			if(minValue > arr[i]){
				minValue = arr[i];
			}
		}
		return minValue;
	}

	//�������ܺ�
	public int getSum(int[] arr){
		int sum = 0;
		for(int i=0;i<arr.length;i++){
			sum +=arr[i];
		}
		return sum;
	}

	//�������ƽ��ֵ
	public int getAvg(int[] arr){

		return getSum(arr) / arr.length; //�����е�ͬ���еķ���
	}

	//��ת����
	public void reverse(int[] arr){
		//����һ
		for(int i=0;i<arr.length / 2 ;i++){
			int temp = arr[i];
			arr[i] = arr[arr.length-i-1];
			arr[arr.length-i-1] = temp;
		}

		//������
		for(int i=0,j=arr.length-1;i<j;i++,j--){
			String temp = arr[i];
			arr[i] = arr[j];
			arr[j] = temp;
		}
		
	}

	//��������
	public int[] copy(int[] arr){
		int[] arr1 = new int[arr.length];
		for(int i = 0;i<arr1.length;i++){
			arr1[i] = arr[i];
		}
		return arr1;
	}

	//��������
	public void sort(int[] arr){
		//ð������
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

	//��������
	public void print(int[] arr){
		for(int i=0;i<arr.length;i++){
			System.out.print(arr[i]+"\t");
		}
	}

	//����ָ��Ԫ�� ���Բ���
	public int getIndex(int[] arr,int dest){
		//���Բ���
		for(int i=0;i<arr.length;i++){
			if(dest == arr[i]){ //�������͵�ʹ��dest.equals(arr[i])��int��ֵ���ͣ�������==Ҳ���ԡ�
				return i;
			}
		}
		return -1; //û�ҵ�������һ��������
	}

	//���ַ�����
	//ǰ�᣺��Ҫ���ҵ������������
	public int getIndex(int[] arr,int dest,1){ //����getIndex,1 Ϊ�����β�
		//int[] arr = new int[]{-188,-36,-24,2,35,36,59,66,79,89,102,210,333,356};
		int head = 0;//������
		int end = arr.length-1;//ĩ����
		boolean ifFlag = true;
		while(head <= end){
			int middle = (head + end)/2;
			if(dest == arr[middle]){
				System.out.println("�ҵ��ˣ�����λ����"+middle);
				isFlag == false;
				break;
			}else if(arr[middle] > dest){
				end = middle-1;
			}else{
				head = middle+1;
			}
		}
		if(isFlag){
			System.out.println("���ź���Ŀ��ֵ���ڴ������С�");
		}
		
	}
}