package lambdasinaction.chap5;

import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * Created by raoul-gabrielurma on 14/01/2014.
 */
public class Laziness
{

	public static void main(String[] args)
	{
		/**
		 * 串行流水线模式，2个满足条件以后流水线就截断了
		 * 流就是说只有一次循环，不是流就是很多个循环组合起来的
		 */
		List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8);
		List<Integer> twoEvenSquares =
				numbers.stream()
						.filter(n -> {
							System.out.println("filtering " + n);
							return n % 2 == 0;
						})
						.map(n -> {
							System.out.println("mapping " + n);
							return n * n;
						})
						.limit(2)
						.collect(toList());

	}

}
