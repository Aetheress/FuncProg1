import java.util.*;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class PerfectNumber {
	
	public enum STATE {
		DEFICIENT, PERFECT, ABUNDANT
	}
	
	public static Set<Integer> divisors(int n) {
		Predicate<Integer> divisible = (divisor) -> ((n%divisor)==0);
		Set<Integer> generator = IntStream.range(1, (n/2)+1).boxed().collect(Collectors.toSet());
		Set<Integer> divisors = new HashSet<Integer>();
		generator.stream().filter(divisible).forEach(divisors::add);
		Consumer<Integer> roleCall = (divisor) -> System.out.println("Divisors of the number " + divisor + " are:");
		roleCall.accept(n);
		divisors.stream().sorted().forEach(System.out::println);
		return divisors;
	}
	
	public static STATE process(int n) {
				Integer sumReduce = divisors(n).stream().reduce(0, (a,b) -> a+b);
				Predicate<Integer> deficiency = (sum) -> sum<n;
				Predicate<Integer> perfection = (sum) -> sum==n;
				Consumer<Integer> sumAnnounce = (sum) -> System.out.println("Divisor sum of number " + n + " is " + sum);
				sumAnnounce.accept(sumReduce);
				if (deficiency.test(sumReduce)) return STATE.DEFICIENT;
				else if (perfection.test(sumReduce)) return STATE.PERFECT;
				else return STATE.ABUNDANT;
	}
	
	public static void detect(int n) {
		STATE type = process(n);
		Consumer<STATE> stateAnnounce = (state) -> System.out.println("Number " + n + " is " + state.toString());
		stateAnnounce.accept(type);
		System.out.println();
	}
	
	public static void main(String[] args) {
		detect(6);
		detect(8);
		detect(20);
		//do not have the functionality to test 16 with sqrt yet
		detect(1);
		//tests for divisors for numbers 1, 6 and 8 are already passed in the middle of their previous tests
	}
}
