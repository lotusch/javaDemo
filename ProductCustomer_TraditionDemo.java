
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class ShareData //资源类
{
	private int number = 0;
	private Lock lock = new ReentrantLock();
	private Condition condition = lock.newCondition();
	
	public void increment() throws Exception
	{
		lock.lock();
		try{ 
			//1 判断
			while(number != 0){
				//等待
				condition.await();
			}
			//干活
			number++;
			System.out.println(Thread.currentThread().getName()+"\t"+number);
			//通知唤醒
			condition.signalAll();
		} catch(Exception e){
			e.printStackTrace();
		}finally{
			lock.unlock();
		}
	}

	public void decrement() throws Exception
	{
		lock.lock();
		try{
			//判断
			while(number == 0){
				//等待
				condition.await();
			}
			//干活
			number--;
			System.out.println(Thread.currentThread().getName()+"\t"+number);
			//通知唤醒
			condition.signalAll();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			lock.unlock();
		}
		
	}
}

/**
	题目：一个初始值为零的变量，两个线程对其交替操作，一个加一，一个减一，来5轮。

	线程   操作（方法）  资源类
	
	资源类：判断  干活  通知

	防止虚假唤醒机制（spurious wakeups）
*/


public class ProductCustomerDemo
{
	public static void main(String[] args) 
	{
		ShareData shareData = new ShareData();

		new Thread(()->{
			for(int i=1;i<=5;i++){
				shareData.increment();
			}

		},"AA").start();

		new Thread(()->{
			for(int i=1;i<=5;i++){
				shareData.decrement();
			}
		
		},"BB").start();

		new Thread(()->{
			for(int i=1;i<=5;i++){
				shareData.decrement();
			}
		
		},"CC").start();

		new Thread(()->{
			for(int i=1;i<=5;i++){
				shareData.decrement();
			}
		
		},"DD").start();

	}
}  
