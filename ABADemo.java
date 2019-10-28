
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
	ABA问题解决方法：原子引用(AtomicReference)+新增版本号（引用时间戳）AtomicStampedReference
						
*/

public class ABADemo {
	
	static AtomicReference<Integer> atomicReference = new AtomicReference<>(100);//initialValue:100
	static AtomicStampedReference<Integer> atomicStampedReference = new AtomicStampedReference<>(100,1);//initialRef:100,initialStamp:1

	public static void main(String[] args) 
	{
		System.out.println("=========以下是ABA问题的产生=========");
        new Thread(()->{
			
			atomicReference.compareAndSet(100,101);//expect:100,update:101
			atomicReference.compareAndSet(101,100);//ABA问题
			
		},"t1").start();

		new Thread(()->{
			//暂停1秒t2线程，保证上面t1线程完成一次ABA操作
			try{ TimeUnit.SECONDS.sleep(1); }catch(InterruptedException e){e.printStackTrace();}
			System.out.println(atomicReference.compareAndSet(100,2019)+"\t"+atomicReference.get()); 
		},"t2").start();


		System.out.println("================以下是ABA问题的解决==============");

		new Thread(()->{
			int stamp = atomicStampedReference.getStamp();
			System.out.println(Thread.currentThread().getName()+"\t第1次版本号："+stamp);

			//暂停1秒t3线程，确保t4拿到相同的版本号。
			try{ TimeUnit.SECONDS.sleep(1); }catch(InterruptedException e){e.printStackTrace();}

			atomicStampedReference.compareAndSet(100,101,atomicStampedReference.getStamp(),atomicStampedReference.getStamp()+1);//expectedReference:100,newReference:101,currentStamp,newStamp:currentStamp+1
			System.out.println(Thread.currentThread().getName()+"\t第2次版本号："+atomicStampedReference.getStamp());

			atomicStampedReference.compareAndSet(101,100,atomicStampedReference.getStamp(),atomicStampedReference.getStamp()+1);
			System.out.println(Thread.currentThread().getName()+"\t第3次版本号："+atomicStampedReference.getStamp());
		},"t3").start();
		

		new Thread(()->{
			int stamp = atomicStampedReference.getStamp();
			System.out.println(Thread.currentThread().getName()+"\t第1次版本号："+stamp);
			
			//暂停3秒，保证t3线程完成ABA操作。
			try{ TimeUnit.SECONDS.sleep(3); }catch(InterruptedException e){e.printStackTrace();}
			boolean result = atomicStampedReference.compareAndSet(100,2019,stamp,stamp+1);

			System.out.println(Thread.currentThread().getName()+"\t修改成功否："+result+"\t当前最新实际版本号："+atomicStampedReference.getStamp());
			System.out.println(Thread.currentThread().getName()+"\t当前实际最新值："+atomicStampedReference.getReference());
		},"t4").start();
	}
}

/**
	打印结果：
	=========以下是ABA问题的产生=========
	true     2019
	================以下是ABA问题的解决==============
	t3  第1次版本号：1
	t4  第1次版本号：1
	t3  第2次版本号：2
	t3  第3次版本号：3
	t4  修改成功否：false当前最新实际版本号：3
	t4  当前实际最新值：100
*/
