
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
			System.out.println(Thread.currentThread().getName()+"\t �Լ����У�"+lockA+"\t ���Ի�ã�"+lockB);
			//��ͣһ����߳�
			try{ TimeUnit.SECONDS.sleep(2); }catch(InterruptedException e){e.printStackTrace();}

			synchronized(lockB){
				System.out.println(Thread.currentThread().getName()+"\t �Լ����У�"+lockB+"\t ���Ի�ã�"+lockA);
			}
		}
	}
}

/**
	������ָ�������������ϵ��߳���ִ�й����У�
	��������Դ����ɵ�һ�ֻ���ȴ�������
	�����������棬�����Ƕ����޷��ƽ���ȥ

	print �����(�߳�˳��cpu���䣬���ܿ���)
	ThreadBBB    �Լ����У�lockB   ���Ի�ã�lockA
	ThreadAAA    �Լ����У�lockA   ���Ի�ã�lockB
	�����п�ס��û�н�����

	��λ������
	linux   ps -ef|grep XXX
	windows �µ�java���г���  Ҳ������ps�Ĳ鿴���̵������Ŀǰ��Ҫ�鿴��ֻ��java
               jps = java ps     jps -l
    ��javaϵͳTerminal��
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