
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;

/**
	�����಻��ȫ������
	ArrayList
*/

public class ContainerNotSafeDemo{

	public static void main(String[] args) 
	{
		List<String> list = new CopyOnWriteArrayList<>();//Collections.synchronizedList(new ArrayList<>()); 

		for(int i=1;i<=30;i++){
			new Thread(()->{
				list.add(UUID.randomUUID().toString().substring(0,8));
				System.out.println(list);
				
			},String.valueOf(i))->start();
		}
		//��һmain�߳�����£�ArrayList����������⣬���ڶ��߳�����£�list.add()ʱ�����
		//java.util.ConcurrentModificationException �쳣

		/**
			1 ��������
				java.util.ConcurrentModificationException


			2 ����ԭ��
				���������޸ĵ��£��ο�������ǩ�������
				һ��������д�룬��һ����������д�뵼�²����޸��쳣��

			3 �������
				3.1 new Vector<>(); 1.0version ���У��ײ����synchronized.
				3.2 Collections.synchronizedList(new ArrayList<>());
			 ***3.3 new CopyOnWriteArrayList<>();

			4 �Ż����飨ͬ���Ĵ��󲻷���2�Σ�

		*/
	}
}

/**�ʼ�
	дʱ����
	CopyOnWrite������дʱ���Ƶ���������һ���������Ԫ��ʱ����ֱ������ǰ����object[]��ӣ�
	�����ֽ���ǰ����Object[]����Copy������һ���µ�����Object[] newElements��Ȼ�����µ�����
	Object[] newElements���Ԫ�أ������ɺ��ٽ�ԭ����������ָ���µ�����setArray��newElements������
	�������ĺô��ǿ��Զ�CopyOnWrite�������в����Ķ���������Ҫ��������Ϊ��ǰ������������κ�Ԫ�ء�
	����CopyOnWrite����Ҳ��һ�ֶ�д�����˼�룬����д��ͬ������

	public boolean add(E e){
		final ReentrantLock lock = this.lock;
		lock.lock();//��ס
		
		//�ɻ�
		try{
			Object[] elements = getArray();//��ȡ��ǰ�������
			int len = elements.length;//��ǰ���鳤��
			Object[] newElements = Arrays.copyOf(elements,len+1);//copy���鼰����
			newElements[len] = e;//�������Ԫ����ӵ�����������棨to the end of the list��
			SetArray(newElements);//��ԭ������������ָ���µ���������
			return true;//����
		}finally{
			lock.unlock();//����
		}
	}

*/
