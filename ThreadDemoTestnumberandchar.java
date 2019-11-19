

/*题目：写两个线程，一个线程打印1~52，另一个线程打印字母A~Z。
打印顺序为12A34B56C...5152Z,要求用线程间的通信*/


class operateData{
	private boolean flag = true; //标志位；线程的执行顺序是不一定的，由CUP控制，所以人为控制一下。
	final Lock lock = new ReentrantLock();
	final Condition c1 = lock.newCondition();
	final Condition c2 = lock.newCondition();
	
	
	private void numbers(){
		lock.lock();
		try{
			//判断
			while(!flag)
				c1.await();
			//干活
			for(int i=1;i<=52;i++){
				System.out.println(i);
				if(i%2==0){
					flag = false;
					c2.signal();
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			lock.unlock();
		}

	}

	private void letters(){
		lock.lock()
		try{
			while(flag)
				c2.await();
			//干活
			for(int i=1;i<=26;i++){
				System.out.println((char)(i+64));
				flag = true;
				c1.signal();
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			lock.unlock();
		}
	}
//	private int flag = true;
//
//	private synchronized void numbers(){
//		
//		//判断
//		while(!flag){
//			this.wait();
//		}
//		//干活
//		for(int i=1;i<=52;i++){ 
//			System.out.println(i);
//			if(i%2==0){
//		        //通知唤醒
//				flag = false;
//			    this.notifyAll();
//			}
//		}
//	}
//
//	private synchronized void letters(){
//		
//		//判断
//		while(flag){
//			this.wait();
//		}
//		//干活
//		for(int i=1;i<=26;i++){
//			System.out.println((char)(i+64));
//			//通知唤醒
//			flag = true;
//			this.notifyAll();
//		}
//		
//	}

}



public class ThreadTest{
	public static void main(String[] args){

		operateData od = new operateData();
		
		new Thread(()->{od.numbers();},"A").start();
		new Thread(()->{od.letters();},"B").start();
	}
}