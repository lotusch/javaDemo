
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;

/**
	集合类不安全的问题
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
		//单一main线程情况下，ArrayList不会出现问题，但在多线程情况下，list.add()时会出现
		//java.util.ConcurrentModificationException 异常

		/**
			1 故障现象
				java.util.ConcurrentModificationException


			2 导致原因
				并发争抢修改导致，参考花名册签名情况，
				一个人正在写入，另一个人来争抢写入导致并发修改异常。

			3 解决方案
				3.1 new Vector<>(); 1.0version 已有，底层加了synchronized.
				3.2 Collections.synchronizedList(new ArrayList<>());
			 ***3.3 new CopyOnWriteArrayList<>();

			4 优化建议（同样的错误不犯第2次）

		*/
	}
}

/**笔记
	写时复制
	CopyOnWrite容器即写时复制的容器。往一个容器添加元素时，不直接往当前容器object[]添加，
	而是现将当前容器Object[]进行Copy，复制一个新的容器Object[] newElements，然后向新的容器
	Object[] newElements添加元素，添加完成后，再将原容器的引用指向新的容器setArray（newElements）；。
	这样做的好处是可以对CopyOnWrite容器进行并发的读，而不需要加锁，因为当前容器不会添加任何元素。
	所以CopyOnWrite容器也是一种读写分离的思想，读和写不同的容器

	public boolean add(E e){
		final ReentrantLock lock = this.lock;
		lock.lock();//锁住
		
		//干活
		try{
			Object[] elements = getArray();//获取当前数组对象
			int len = elements.length;//当前数组长度
			Object[] newElements = Arrays.copyOf(elements,len+1);//copy数组及扩容
			newElements[len] = e;//将传入的元素添加到新数组最后面（to the end of the list）
			SetArray(newElements);//将原有容器的引用指向新的数组容器
			return true;//返回
		}finally{
			lock.unlock();//解锁
		}
	}

*/
