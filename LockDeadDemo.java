
class HoldLockThread implements Runnable
{
	private String lockA;
	private String lockB;

	public HoldLockThread(String lockA, String lockB){
		this.lockA = lockA;
		this.lockB = lockB;
	}

	@override
	public void run(){
		synchronized(lockA){
			System.out.println(Thread.currentThread().getName()+"\t 自己持有："+lockA+"\t 尝试获得："+lockB);
			//暂停一会儿线程
			try{ TimeUnit.SECONDS.sleep(2); }catch(InterruptedException e){e.printStackTrace();}

			synchronized(lockB){
				System.out.println(Thread.currentThread().getName()+"\t 自己持有："+lockB+"\t 尝试获得："+lockA);
			}
		}
	}
}

/**
	死锁是指两个或两个以上的线程在执行过程中，
	因争夺资源而造成的一种互相等待的现象，
	若无外力干涉，那它们都将无法推进下去

	print 结果：(线程顺序cpu分配，不受控制)
	ThreadBBB    自己持有：lockB   尝试获得：lockA
	ThreadAAA    自己持有：lockA   尝试获得：lockB
	（运行卡住，没有结束）

	定位死锁：
	linux   ps -ef|grep XXX
	windows 下的java运行程序  也有类似ps的查看进程的命令，但目前需要查看的只是java
               jps = java ps     jps -l
    在java系统Terminal中
	D:\devSoft\JetBrains\IdeaProjects\demo01>jps -l  <Enter>
	...
	...
	9636 com.atguigu.Interview.... .DeadLockDemo
	15320
	15416 sun.tools.jps.Jps

	D:\devSoft\JetBrains\IdeaProjects\demo01>jstack 9636  <Enter>
	...
	...
	Java Stack information for the thread listed above:
	==================================================
	"ThreadBBB":
		- waiting to lock <0x000000076b9b29f8> (a java.lang.String)
		- locked <0x000000076b9b2a30> (a java.lang.String)
		at java.lang.Thread.run(Thread.java:745)
	"ThreadAAA":
		-wait to lock <0x000000076b9b2a30> (a java.lang.String)
		-locked <0x000000076b9b29f8> (a java.lang.String)
		at java.lang.Thread.run(Thread.java:745)

    Found 1 deadlock.
*/

public class DeadLockDemo
{
	public static void main(String[] args){
	
		String lockA = "lockA";
		String lockB = "lockB";

		new Thread(new HoldLockThread(lockA,lockB),"ThreadAAA").start();
		new Thread(new HoldLockThread(lockB,lockA),"ThreadBBB").start();
	}
}