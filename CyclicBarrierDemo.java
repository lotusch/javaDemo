

import java.util.concurrent.CyclicBarrier;

/**
	CyclicBarrier 可循环屏障，集齐七颗龙珠，召唤神龙。

	print 结果：
	1  收集到第：1龙珠
	5  收集到第：5龙珠
	3  收集到第：3龙珠
	2  收集到第：2龙珠
	4  收集到第：4龙珠
	7  收集到第：7龙珠
	6  收集到第：6龙珠
	main  ****召唤神龙

*/

public class CyclicBarrierDemo
{
	public static void main(String[] args) 
	{
		//CyclicBarrier(int parties, Runnable barrierAction)
		//最后实现的是Runnable barrierAction
		CyclicBarrier cyclicBarrier = new CyclicBarrier(7,()->{System.out.println(Thread.currentThread().getName()+"\t ****召唤神龙");});
		for(int i=1;i<=7;i++){ //7个龙珠

			final int tempInt = i;
		
			new Thread(()->{

				System.out.println(Thread.currentThread().getName()+"\t 收集到第："+tempInt+"龙珠");
				try{
					cyclicBarrier.await();//先到先等
					
				}catch(InterruptedException e){
					e.printStackTrace();
					
				}catch(BrokenBarrierException e){
					e.printStackTrace();
				}
		
			},String.valueOf(i)).start();
		}

		//System.out.println("Hello World!");
	}
}
