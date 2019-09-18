import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class PerfectNumber {
	
	public enum STATE {
		DEFICIENT, PERFECT, ABUNDANT
	}
	
	public static Set<Integer> divisors(int n) {
		Predicate<Integer> divisible = (divisor) -> ((n%divisor)==0);
		Set<Integer> generator = IntStream.range(1, (int)Math.round(Math.sqrt(n)+1)).boxed().collect(Collectors.toSet());
		Set<Integer> divisors = new HashSet<Integer>();
		generator.stream().filter(divisible).forEach(divisors::add);
		Function<Integer, Integer> reverseDiv = (divisor) -> n/divisor;
		divisors.stream().map(reverseDiv).sorted((a,b)->b.compareTo(a)).forEach(divisors::add);
		divisors.remove(n); //implementing sqrt method allows illegal divisors (n itself)
		System.out.println("Divisors of the number " + n + " are:");
		divisors.stream().sorted().forEach(System.out::println);
		return divisors;
	}
	
	public static STATE process(int n) {
				Integer sumReduce = divisors(n).stream().reduce(0, (a,b) -> a+b);
				Predicate<Integer> deficiency = (sum) -> sum<n;
				Predicate<Integer> perfection = (sum) -> sum==n;
				System.out.println("Divisor sum of number " + n + " is " + sumReduce);
				if (deficiency.test(sumReduce)) return STATE.DEFICIENT;
				else if (perfection.test(sumReduce)) return STATE.PERFECT;
				else return STATE.ABUNDANT;
	}
	
	public static void detect(int n) {
		STATE type = process(n);
		System.out.println("Number " + n + " is " + type.toString() + "\n");
	}
	
	public static void main(String[] args) {
		detect(6);
		detect(8);
		detect(20);
		detect(16);
		detect(1);
		//tests for divisors for numbers 1, 6 and 8 are already passed in the middle of their previous tests
	}
}
