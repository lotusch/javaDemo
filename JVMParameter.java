

public class HelloGC
{
	public static void main(String[] args){
		long totalMemory = Runtime.getRuntime().totalMemory();//return Java 虚拟机中的内存总量。
		long maxMemory = Runtime.getRuntime().maxMemory(); //return Java 虚拟机试图使用的最大内存量。

		System.out.println("TOTAL_MEMORY(-Xms) = "+totalMemory+"(字节)、"+(totalMemory/(double)1024/1024)+"MB");
		Systme.out.println("MAX_MEMORY(-Xmx) = "+maxMemory+"(字节)、"+(maxMemory/(double)1024/1024)+"MB")
	}
}