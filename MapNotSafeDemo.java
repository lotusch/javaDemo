
import java.util.ArrayList;
import java.util.UUID;
import java.util.concurrent.ConcurrentMap;

public class ContainerNotSafeDemo
{
	public static void main(String[] args) 
	{
		Map<String> map = new ConcurrentMap<>();//new HashMap<>(); Collections.synchronizedMap(new HashMap<>()); 
		for(int i=1;i<=30;i++){
			new Thread(()->{
				map.put(Thread.currentThread().getName(),UUID.randomUUID.tostring().substring(0,8));
				System.out.println(map);
			},String.valueOf(i)).start();
		}
		System.out.println("Hello World!");
	}
}
