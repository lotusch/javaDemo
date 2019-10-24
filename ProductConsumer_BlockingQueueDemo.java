
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

class MyResource
{
	private volatile boolean FLAG = true; //默认开启，进行生产+消费,被volatile修饰保证可见性。
	private AtomicInteger atomicInteger = new AtomicInteger();

	BlockingQueue<String> blockingQueue = null;

	public MyResource(BlockingQueue<String> blockingQueue)
	{
		this.blockingQueue = blockingQueue;
		System.out.println(blockingQueue.getClass().getName());
	}

	public void myPorduct() throws Exception
	{
		String data = null;
		boolean retValue;

		while(FLAG)
		{
			data = atomicInteger.incrementAndGet()+""; //相当于i++,但此线程安全。
			retValue = blockingQueue.offer(data,2L,TimeUnit.SECONDS);//插入data，2秒超时。返回boolean值
			if(retValue){
				System.out.println(Thread.currentThread().getName()+"\t 插入队列"+data+"成功");
			}else{
				System.out.println(Thread.currentThread().getName()+"\t 插入队列"+data+"失败");
			}
			TimeUnit.SECONDS.sleep(1);
		}
		System.out.println(Thread.currentThread().getName()+"\t big boss 叫停了，表示FLAG=false,生产动作结束");
	}

	public void myConsumer() throws Exception
	{
		String result = null;

		while(FLAG)
		{
			result = blockingQueue.poll(2L,TimeUnit.SECONDS);
			if(null == result || result.equalsIgnoreCase(""))
			{
				FLAG = false;
				System.out.println(Thread.currentThread().getName()+"\t 超过2秒钟没有取到蛋糕，消费退出");
				System.out.println();
				System.out.println();
				return;
			}
			
		}
		System.out.println(Thread.currentThread().getName()+"\t 消费队列蛋糕"+result+"成功");
	}

	public void Stop(){
		FLAG = false;
	}
}  

/**
	volatile/CAS/atomicInteger/BlockingQueue/线程交互/原子引用

	print 结果：
	java.util.concurrent.ArrayBlockingQueue
	Prod    生产线程启动
	Consumer    消费线程启动
	Prod    插入队列1成功
	Consumer    消费队列蛋糕1成功
	Prod    插入队列2成功
	Consumer    消费队列蛋糕2成功
	Prod    插入队列3成功
	Consumer    消费队列蛋糕3成功
	Prod    插入队列4成功
	Consumer    消费队列蛋糕4成功
	Prod    插入队列5成功
	Consumer    消费队列蛋糕5成功



	5秒钟时间到，big boss main 线程叫停，活动结束
	Prod    big boss 叫停了，表示FLAG=false,生产动作结束
	Consumer    超过2秒钟没有取到蛋糕，消费退出


*/

public class ProductConsumer_BlockingQueueDemo
{
	public static void main(String[] args) 
	{
		MyResource myResource = new MyResource(new ArrayBlockingQueue<>(10));

		new Thread(()->{
			System.out.println(Thread.currentThread().getName()+"\t 生产线程启动");
			try{
				myResource.myPorduct();
			}catch(Exception e){
				e.printStackTrace();
			}
			
		},"Prod").start();

		new Thread(()-{
			System.out.println(Thread.currentThread().getName()+"\t 消费线程启动");
			try{
				myResource.myConsumer();
				System.out.println();
				System.out.println();
			}catch(Exception e){e.printStackTrace();}
			
		},"Consumer").start();

		//暂停一会儿线程
		try{ TimeUnit.SECONDS.sleep(5);} catch(InterruptedException e){ e.printStackTrace();}

		System.out.println();
		System.out.println();
		System.out.println();

		System.out.println("5秒钟时间到，big boss main 线程叫停，活动结束");
		myResource.Stop();
	}
}

