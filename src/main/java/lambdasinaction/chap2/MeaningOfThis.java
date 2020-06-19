package lambdasinaction.chap2;

public class MeaningOfThis
{
	public final int value = 4;
	public void doIt()
	{
		int value = 6;
		//这是一个匿名内部类
		Runnable r = new Runnable(){
			//内部类的变量必须是final
			//public int value = 5;
			public void run(){
				//int value = 10;
				//this是run函数的第一个参数，this是谁调用的，就是谁
				//所以this.value是实例的成员变量
				System.out.println(value);
			}
		};
		r.run();
	}
	public static void main(String...args)
	{
		MeaningOfThis m = new MeaningOfThis();
		m.doIt(); // ???这个显然是5啊，编译器都告诉我们了
	}
}
