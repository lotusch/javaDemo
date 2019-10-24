
import java.util.concurrent.Semaphore;
import java.util.TimeUnit;

/** Semaphore 信号灯或信号量，争车位

	print 结果：
	3  抢到车位
	1  抢到车位
	2  抢到车位   ***停了4秒
	2  停车4秒后离开车位
	3  停车4秒后离开车位
	4  抢到车位
	1  停车4秒后离开车位
	6  抢到车位
	5  抢到车位  ***停了4秒
	5  停车4秒后离开车位
	4  停车4秒后离开车位
	6  停车4秒后离开车位

*/

public class SemaphoreDemo{

	public static void main(String[] args) 
	{
		Semaphore semaphore = new Semaphore(3);//permit:3 模拟三个停车位，三个不同的共享资源

		for(int i=0;i<=6;i++){ //六辆车抢车位
			try{
					new Thread(()->{
						semaphore.acquire();
						System.out.println(Thread.currentThread().getName()+"\t 抢到停车位");
						try{TimeUnit.SECONDS.sleep(4);} catch(InterruptedException e){e.printStackTrace();}
						System.out.println(Thread.currentThread().getName()+"\t 停车4秒后离开车位");

					},String.valueOf(i)).start();
			
			}catch(Exception e){
				e.printStackTrace();
			
			}finally{
				semaphore.release();
			}
			
		}

	}
}
