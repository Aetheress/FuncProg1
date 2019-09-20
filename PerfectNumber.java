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
				Predicate<Integer> abundance = (sum) -> sum>n;
				List<Boolean> stateList = new ArrayList<>();
				stateList.add(deficiency.test(sumReduce));
				stateList.add(perfection.test(sumReduce));
				stateList.add(abundance.test(sumReduce));
				Map<List<Boolean>, STATE> stateMap = new HashMap();
				List<Boolean> states1 = new ArrayList<>();
				List<Boolean> states2 = new ArrayList<>();
				List<Boolean> states3 = new ArrayList<>();
				states1.add(true);
				states1.add(false);
				states1.add(false);
				states2.add(false);
				states2.add(true);
				states2.add(false);
				states3.add(false);
				states3.add(false);
				states3.add(true);
				stateMap.put(states1, STATE.DEFICIENT);
				stateMap.put(states2, STATE.PERFECT);
				stateMap.put(states3, STATE.ABUNDANT);
				STATE type = stateMap.get(stateList);
				System.out.println("Divisor sum of number " + n + " is " + sumReduce);
				return type;
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
