
/**
	WeakHashMap 弱引用在缓存中经常使用，GC之后，一律回收。清空缓存，提高性能。

	print 结果：
	{1=HashMap}
	{1=HashMap}
	{1=HashMap} 1    //强引用，gc之后不回收
	======================
	{2=WeakHashMap}
	{2=WeakHashMap}
	{} 0             //weak弱引用，gc之后一律回收。
*/
public class weakHashMapDemo
{
	public static void main(String[] args){
		myHashMap();
		System.out.println("======================");
		myWeakHashMap();
	}

	private static void myHashMap(){
		HashMap<Integer,String> map = new HashMap<>();
		Integer key = new Integer(1);
		String value = "HashMap";

		map.put(key,value);
		System.out.println(map);

		key = null;
		System.out.println(map);

		System.gc();
		System.out.println(map+"\t"+map.size());

	}

	private static void myWeakHashMap(){
		WeakHashMap<Integer,String> map = new WeakHashMap<>();
		Integer key = new Integer(2);
		String value = "WeakHashMap";

		map.put(key,value);
		System.out.println(map);

		key = null;
		System.out.println(map);

		System.gc();
		System.out.println(map+"\t"+map.size());

	}
}