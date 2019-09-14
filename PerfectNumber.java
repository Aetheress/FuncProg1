import java.util.*;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class PerfectNumber {
	
	public interface STATE {
		void classifier();
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
		return new STATE() {
			public void classifier() {
				Integer sumReduce = divisors(n).stream().reduce(0, (a,b) -> a+b);
				Predicate<Integer> deficiency = (sum) -> sum<n;
				Predicate<Integer> perfection = (sum) -> sum==n;
				Predicate<Integer> abundance = (sum) -> sum>n;
				Consumer<Integer> sumAnnounce = (sum) -> System.out.println("Divisor sum of number " + n + " is " + sum);
				sumAnnounce.accept(sumReduce);
				Consumer<Integer> typeAnnounce = (sum) -> {
					if (deficiency.test(sum)) System.out.println("Number " + n + " is deficient.");
					else if (perfection.test(sum)) System.out.println("Number " + n + " is perfect.");
					else if (abundance.test(sum)) System.out.println("Number " + n + " is abundant.");
					else System.out.println("Error in calculating type of given number.");
				};
				typeAnnounce.accept(sumReduce);
			}
		};
	}
	
	public static void detect(int n) {
		STATE type = process(n);
		type.classifier();
	}
	
	public static void main(String[] args) {
		detect(12);
	}
}
