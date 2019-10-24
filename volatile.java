import java.util.concurrent.TimeUnit;

class MyData
{
	volatile int number = 0;  //加不加volatile修饰，影响着线程间的可见性。

	public void addto60(){
		this.number = 60;
	}

	/*验证原子性*/
	public void addPlusPlus(){
		number++;
	}

	/*保证原子性办法*/
	AtomicInteger atomicInteger = new AtomicInteger();
	public void addMyAtomic(){
		atomicInteger.getAndIncrement();
	}
}

/*
  1 验证volatile的可见性
    1.1 假如 int number = 0; number变量前不加volatile关键字修饰，没有可见性（即通知变量值已修改）。

	volatile是增强主内存和各线程间的可见性。

  2 验证volatile不保证原子性的案例演示
  	2.1 什么是原子性

	2.2 volatile 不保证原子性

	2.3 用public synchronized void addPlusPlus(){} 可以解决原子性问题，但不用，因为使用大炮打蚊子。

	2.4 volatile 为什么不能保证原子性

	2.5 解决办法 用juc下的AtomicInteger

*/

public class VolatileDemo 
{

	public static void main(String[] args){
		MyData myData = new MyData();

		for(int i=1;i<20;i++){

			new Thread(()->{
				for(int j=1;j<=1000;j++){
					myData.addPlusPlus();
					myData.addMyAtomic();
				}
			},String.valueOf(i)).start();
		}

		//需要等待上面20个线程全部计算完成后，再用main线程取得最终的结果值看是多少。
		while(Thread.activeCount()>2){
			Thread.yield();
		}

		System.out.println(Thread.currentThread().getName()+"\t type int finally number value:"+myData.number);//<20000 的任何值
		System.out.println(Thread.currentThread().getName()+"\t type AtomicInteger finally number value:"+myData.atomicInteger);//20000
	}


    //volatile可以保证可见性，及时通知其它线程，主物理内存的值已经被修改。
	public static void seeOkByVolatile(){

		MyData mydata = new MyData();

		new Thread(()->{
			
			System.out.println(Thread.currentThread().getName()+"\t come in");

			//暂停一会儿线程
			try{TimeUnit.SECONDS.sleep(3);} catch(Exception e){e.printStackTrace();}

			System.out.println(Thread.currentThread().getName()+"\t update number value :"+mydata.number);
		},"AA").start();

		//第2个线程就是我们的main线程
		while(mydata.number == 0){
			
		}
		
		System.out.println(Thread.currentThread().getName()+"\t mission is over,main get number value :"+mydata.number);
	}
}