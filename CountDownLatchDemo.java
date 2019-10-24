

/**
	CountDownLatch  倒数发射，减到零，然后干活。
	***public class CountDownLatch extends Object

	实例：教室关门
	print 结果：
	1  上完自习，离开教室
	4  上完自习，离开教室
	3  上完自习，离开教室
	2  上完自习，离开教室
	6  上完自习，离开教室
	5  上完自习，离开教室
	main  ****班长最后锁门走人
	
*/
public class CountDownLatchDemo
{
	final int tempInt = 6;
	public static void main(String[] args) 
	{
		CountDownLatch countDownLatch = new CountDownLatch(tempInt);

		for(int i=1;i<=tempInt;i++){
		
			new Thread(()->{
				System.out.println(Thread.currentThread().getName()+"\t上完自习，离开教室");
				countDownLatch.countDown();//minus to zero
			},String.valueOf(i)).start();
		}
		
		countDownLatch.await();//等待，阻塞下面语句的线程。让上面循环中的线程全部执行完毕。
		System.out.println(Thread.currentThread().getName()+"\t ****班长最后锁门走人");
	}
}
