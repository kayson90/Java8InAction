package lambdasinaction.chap2;

import java.util.*;

public class FilteringApples{

	public static void main(String ... args){

		List<Apple> inventory = Arrays.asList(new Apple(80,"green"), new Apple(155, "green"), new Apple(120, "red"));

		// [Apple{color='green', weight=80}, Apple{color='green', weight=155}]
		List<Apple> greenApples = filterApplesByColor(inventory, "green");
		System.out.println(greenApples);

		// [Apple{color='red', weight=120}]
		List<Apple> redApples = filterApplesByColor(inventory, "red");
		System.out.println(redApples);

		// [Apple{color='green', weight=80}, Apple{color='green', weight=155}]
		List<Apple> greenApples2 = filter(inventory, new AppleColorPredicate());
		System.out.println(greenApples2);

		// [Apple{color='green', weight=155}]
		List<Apple> heavyApples = filter(inventory, new AppleWeightPredicate());
		System.out.println(heavyApples);

		// []
		List<Apple> redAndHeavyApples = filter(inventory, new AppleRedAndHeavyPredicate());
		System.out.println(redAndHeavyApples);

		// [Apple{color='red', weight=120}]
		List<Apple> redApples2 = filter(inventory, new ApplePredicate() {
			public boolean test(Apple a){
				return a.getColor().equals("red");
			}
		});
		System.out.println(redApples2);

	}

	/**
	 * 比如说产品提了一个需求，说要把绿色苹果筛选出来,这个代码初级程序员都会写。
	 * @param inventory
	 * @return
	 */
	public static List<Apple> filterGreenApples(List<Apple> inventory){
		List<Apple> result = new ArrayList<>();
		for(Apple apple: inventory){
			if("green".equals(apple.getColor())){
				result.add(apple);
			}
		}
		return result;
	}

	/**
	 * 需求变了，绿色不好，其他颜色也需要筛选，那没问题，很容易下想到将参数变化
	 * @param inventory
	 * @param color
	 * @return
	 */
	public static List<Apple> filterApplesByColor(List<Apple> inventory, String color){
		List<Apple> result = new ArrayList<>();
		for(Apple apple: inventory){
			if(apple.getColor().equals(color)){
				result.add(apple);
			}
		}
		return result;
	}

	/**
	 * 不光是颜色了，重量也要变化，这个时候程序猿就不爽了，感觉产品你特么在搞我…
	 * @param inventory
	 * @param weight
	 * @return
	 */
	public static List<Apple> filterApplesByWeight(List<Apple> inventory, int weight){
		List<Apple> result = new ArrayList<>();
		for(Apple apple: inventory){
			if(apple.getWeight() > weight){
				result.add(apple);
			}
		}
		return result;
	}

	/**
	 * 然后程序猿就想了一个办法防止被搞，我不管你怎么筛选，那么我使用策略模式这样的设计模式
	 * @param inventory
	 * @param p
	 * @return
	 */
	public static List<Apple> filter(List<Apple> inventory, ApplePredicate p){
		List<Apple> result = new ArrayList<>();
		for(Apple apple : inventory){
			if(p.test(apple)){
				result.add(apple);
			}
		}
		return result;
	}

	/**
	 * 静态内部类
	 */
	public static class Apple {
		private int weight = 0;
		private String color = "";

		public Apple(int weight, String color){
			this.weight = weight;
			this.color = color;
		}

		public Integer getWeight() {
			return weight;
		}

		public void setWeight(Integer weight) {
			this.weight = weight;
		}

		public String getColor() {
			return color;
		}

		public void setColor(String color) {
			this.color = color;
		}

		public String toString() {
			return "Apple{" +
					"color='" + color + '\'' +
					", weight=" + weight +
					'}';
		}
	}

	/**
	 * 策略模式是一个行为模式，就是将行为写成一个模板，不同的行为具体去实现这个接口，在context里去选择这些行为
	 * 所以下回产品再让你筛选，那你新写一个接口实现就好了，其余的代码也不用变
	 * 那如果产品经理又搞事情，那咋办？打他啊！
	 * 实际上，我们新写一个策略就好了
	 * Predicate实际上就是一个谓词，某个主语做什么事情，就是描述主语的行为。
	 */
	interface ApplePredicate{
		public boolean test(Apple a);
	}

	static class AppleWeightPredicate implements ApplePredicate{
		public boolean test(Apple apple){
			return apple.getWeight() > 150;
		}
	}
	static class AppleColorPredicate implements ApplePredicate{
		public boolean test(Apple apple){
			return "green".equals(apple.getColor());
		}
	}

	static class AppleRedAndHeavyPredicate implements ApplePredicate{
		public boolean test(Apple apple){
			return "red".equals(apple.getColor())
					&& apple.getWeight() > 150;
		}
	}

	/**
	 *	匿名内部类能好一点，省掉了类型的定义，但是方法实现定义还是要写
	 */

	/**
	 * 但是实际上在写策略模式的时候，定义接口，实现接口也其实没有必要，我们需要的就是那个不同的行为！就是谓语。
	 */
}
