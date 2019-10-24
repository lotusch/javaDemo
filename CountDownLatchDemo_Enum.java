import lombok.Getter;


/**
	简单当做数据库使用，不存在链接数据库关闭数据库问题，连接池满问题。
*/
public enum CountryEnum{
	(1,"齐"),(2,"楚"),(1,"燕"),(1,"赵"),(1,"魏"),(1,"韩");

	@Getter private Integer retCode; //@Getter 相当于set,get方法
	@Getter private String retMessage;

	CountryEnum(Integer retCode, String retMessage){ //构造方法
		this.retCode = retCode;
		this.retMessage = retMessage;
	}

	public static CountryEnum forEach_CountryEnum(int index){
		CountryEnum[] myArray = CountryEnum.values();
		for(CountryEnum element : myArray){

			if(index == element.getRetCode()){
				return element;
			}
		}
		return null;
	}
}

/**
	CountDownLatch  倒数发射，减到零，然后干活。
	
	
*/
public class CountDownLatchDemo
{
	final int tempInt = 6;
	public static void main(String[] args) 
	{
		CountDownLatch countDownLatch = new CountDownLatch(tempInt);

		for(int i=1;i<=tempInt;i++){
		
			new Thread(()->{
				System.out.println(Thread.currentThread().getName()+"\t国，被灭");
				countDownLatch.countDown();//minus to zero
			},CountryEnum.forEach_CountryEnum(i).getRetMessage()).start();//String.valueOf(i)
		}
		
		countDownLatch.await();//等待，阻塞下面语句的线程。让上面循环中的线程全部执行完毕。
		System.out.println(Thread.currentThread().getName()+"\t ****秦帝国，一统华夏");
	}
}
