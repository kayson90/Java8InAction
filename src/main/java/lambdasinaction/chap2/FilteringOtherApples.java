package lambdasinaction.chap2;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lvwenkai
 * @date 2020-06-04
 */
public class FilteringOtherApples
{
	interface Predicate<T>{
		boolean test(T t);
	}

	/**
	 * 定义泛型方法，前面要写上泛型参数列表
	 * @param list
	 * @param p
	 * @param <T>
	 * @return
	 */
	static <T> List<T> filter(List<T> list,Predicate<T> p){
		List<T> result = new ArrayList<>();
		for (T e:list){
			if (p.test(e)){
				result.add(e);
			}
		}
		return result;
	}

	public static void main(String[] args)
	{

	}
}
