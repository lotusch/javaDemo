
/**
	题目：实现一个自旋锁
	自旋锁好处：循环比较获取直到成功为止，没有类似wait的阻塞。

	通过CAS操作完成自旋锁，A线程先进来调用myLock方法，自己持有锁5秒钟，B随后进来后发现
	当前有线程持有锁，不是null,只能通过自旋等待，直到A释放锁后B随后抢到。

	打印结果：
	A come in      1秒后
	B come in      5秒后
	A invoked myUnlock()
	B invoked myUnlock()
*/

import java.util.concurrent.atomic.AtomicReference;

public class SpinLockDemo 
{
	//原子引用线程
    AtomicReference<Thread> atomicReference = new AtomicReference<>();

	public void myLock(){
		Thread thread = Thread.currentThread();
		System.out.println(Thread.currentThread().getName()+"\t come in.");
		while(!atomicReference.compareAndSet(null,thread)){

		}
	}

	public void myUnlock(){
		Thread thread = Thread.currentThread();
		atomicReference.compareAndSet(thread,null);//except,value
		System.out.println(Thread.currentThread().getName()+"\t invoked myUnlock()");
	}

	public static void main(String[] args) 
	{
		SpinLockDemo spinLockDemo = new SpinLockDemo();

		new Thread(()->{
			spinLockDemo.myLock();
			//持有锁5秒钟
			try{ TimeUnit.SECONDS.sleep(5);} catch(InterruptedException e){e.printStackTrace();}
			spinLockDemo.myUnlock();

		},"A").start();
		
		//为了让A线程先进入，等1秒钟。
		try{ TimeUnit.SECONDS.sleep(1); } catch(InterruptedException e){e.printStackTrace();}
		new Thread(()->{
			spinLockDemo.myLock();
			spinLockDemo.myUnlock();
		},"B").start();
	}
}
