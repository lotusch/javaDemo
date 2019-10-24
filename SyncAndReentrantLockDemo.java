

/**
	题目：多线程之间按顺序调用，实现A->B->C三个线程启动，要求如下：
	AA打印5次，BB打印10次，CC打印15次
	紧接着
	AA打印5次，BB打印10次，CC打印15次
	......
	来10轮
*/

class ShareData
{
	private int number=1;//A:1 B:2 c:3
	private Lock lock = new ReentrantLock();
	private Condition c1 = new newCondition();
	private Condition c2 = new newCondition();
    private Condition c3 = new newCondition();


	public void print5(){
		lock.lock();
		try{
			//判断
			while(number !=1){
				c1.await();
			}
			//干活
			for(int i=1;i<=5;i++){
				System.out.println(Thread.currentThread().getName()+"\t"+i);
			}
			//通知
			number = 2;
			c2.signal(); //通知唤醒BB线程
		}catch(Exception e){
			e.printStackTrace();
		
		}finally{
			lock.unlock();
		}
	}

	public void print10(){
		try{
			//判断
			while(number!=2){
				c2.await();
			}
			//干活
			for(int i=1;i<=10;i++){
				System.out.println(Thread.currentThread().getName()+"\t"+i);
			}
			
			//通知
			number = 3;
			c3.signal(); //通知唤醒CC
		 }catch(Exception e){
			e.printStackTrace();
		
		}finally{
			lock.unlock();
		}
	}
	
	public void print15(){
		try{
			//判断
			while(number!=3){
				c3.await();
			}
			//干活
			for(int i=1;i<=15;i++){
				System.out.println(Thread.currentThread().getName()+"\t"+i);
			}
			
			//通知
			number = 1;
			c1.signal();//通知唤醒AA
		 }catch(Exception e){
			e.printStackTrace();
		
		}finally{
			lock.unlock();
		}
	}
}

public class SyncAndReentrantLockDemo
{
	public static void main(String[] args) 
	{
		ShareData shareData = new ShareData();

		new Thread(()->{
			for(int i=1;i<=10;i++){
				shareData.print5();
			}
		},"AA").start();

		new Thread(()->{
			for(int i=1;i<=10;i++){
			  shareData.print10();
			}
		},"BB").start();

		new Thread(()->{
			for(int i=1;i<=10;i++){
			  shareData.print15();
			}
		},"CC").start();
	}
}
