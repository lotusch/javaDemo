

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;

public class ContainerNotSafeDemo 
{
	public static void main(String[] args) 
	{
		Set<String> set = new CopyOnWriteArraySet<>(); //Collections.synchronizedSet(new HashSet<>()); 
		for(int i=1;i<=30;i++){
			new Thread(()->{
				set.add(UUID.randomUUID().tostring().substring(0,8));
				System.out.println(set);
			},String.valueOf(i)).start();
		}
		
		//System.out.println("Hello World!");
	}
}
