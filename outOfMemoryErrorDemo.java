
import java.util.Random;

public class JavaHeapSpaceDemo
{
	public static void main(String[] args){
		String str = "atTest";

		while(true){
			str += str+ new Random().nextInt(11111111)+ new Random().nextInt(22222222);
			str.intern();
			//return str;
		}
		// 在VM options 中 -Xms10m -Xmx10m
		//Exception in thread "main" java.lang.OutOfMemoryError:Java heap space
		//+= 在java中相当于new了很多新对象
		//或直接new一个大对象 byte[] bt = new byte[50*1024*1024];//50MB
	}
}