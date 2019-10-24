
/**
	多个线程同时读一个资源类没有任何问题，为了满足并发量，读取共享资源应该可以同时进行。
	但
	如果有一个线程想去写共享资源类，就不应再有其它线程可以对该资源进行读或写
	小总结：
			读-读 能共存
			读-写 不能共存
			写-写 不能共存

			写操作：原子+独占，整个过程必须是一个完整的统一体，中间不许被分割和打断。

	print:before 不加锁  写入不能保证原子性，被各种打断加塞儿

	2 正在写入：2
	1 正在写入：1
	5 正在写入：5
	1 正在读取：...
	3 正在写入：3
	5 正在读取：...
	2 正在读取：...
	3 正在读取：...
	4 正在读取：...
	4 正在写入：4
	1 写入完成：
	1 读取完成：null
	5 写入完成：
	3 写入完成：
	2 写入完成:
	4 读取完成：4
	3 读取完成：3
	2 读取完成：null
	5 读取完成：null
	4 写入完成：

	print :after 加锁 ReentrantReadWriteLock()

	3 正在写入：3
	3 写入完成：
	1 正在写入：1
	1 写入完成：
	2 正在写入：2
	2 写入完成：
	4 正在写入：4
	4 写入完成：
	5 正在写入：5
	5 写入完成：
	1 正在读取：...
	2 正在读取：...
	3 正在读取：...
	5 正在读取：...
	1 正在读取：...
	1 读取完成：1
	4 读取完成：4
	5 读取完成：5
	2 读取完成：2
	3 读取完成：3

*/

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;

class MyCache
{
	private volatile Map<String,Object> map = new HashMap<>();//用volatile修饰，保证可见性
	private ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock();  //读写锁

	public void put(String key,Object value){
		rwLock.writeLock().lock();
		try{
			System.out.println(Thread.currentThread().getName+"\t 正在写入："+key);
			//暂停一会儿线程
			try{TimeUnit.MILLISECONDS.sleep(300);} catch(InterruptedException e){e.printStackTrace();}
			map.put(key,value);
			System.out.println(Thread.currentThread().getName()+"\t 写入完成：");
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			rwLock.writeLock().unlock();
		}
	}

	public void get(String key){
		rwLock.readLock().lock();
		try{
			System.out.println(Thread.currentThread().getName+"\t 正在读取：...");
			//暂停一会儿线程
			try{TimeUnit.MILLISECONDS.sleep(300);} catch(InterruptedException e){e.printStackTrace();}
			Object result = map.get(key);
			System.out.println(Thread.currentThread().getName()+"\t 读取完成："+result);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			rwLock.readLock().unlock();
		}
		
	}
}
/**
	资源类：高内聚 低耦合
	线程操作资源类
*/

public class ReadWriteLockDemo 
{
	MyCache myCache = new MyCache();
	
	public static void main(String[] args) 
	{
		//5个线程写
		for(int i=1;i<=5;i++){
			final int tempInt = i; 
			new Thread(()->{
				myCache.put(tempInt+"",tempInt+"")//tempInt+""转义String类型	
			},String.valueOf(i)).start();
		}
		
		//5个线程读
		for(int i=1;i<=5;i++){
			final int tempInt = i;
			new Thread(()->{
				myCache.get(tempInt+"");
			},String.valueOf(i))->start();
		}
	}
}
