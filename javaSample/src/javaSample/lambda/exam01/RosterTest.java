package javaSample.lambda.exam01;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class RosterTest {
	interface CheckPerson {
		boolean test(Person p);
	}

	// Approach 1: 하나의 조건과 일치하는 대상 검색
	public static void printPersonsOlderThan(List<Person> roster, int age) {
		for (Person p : roster) {
			if (p.getAge() >= age) {
				p.printPerson();
			}
		}
	}

	// Approach 2: 여러 조건을 만족하는 검색
	public static void printPersonsWithinAgeRange(List<Person> roster, int low, int high) {
		for (Person p : roster) {
			if (low <= p.getAge() && p.getAge() < high) {
				p.printPerson();
			}
		}
	}

	// Approach 3: local class로 검색 기준 정의
	// Approach 4: 익명 클래스에서 검색 기준 정의
	// Approach 5: Lambda 표현식으로 검색 기준 정의
	public static void printPersons(List<Person> roster, CheckPerson tester) {
		for (Person p : roster) {
			if (tester.test(p)) {
				p.printPerson();
			}
		}
	}

	// Approach 6: Lambda와 함께 표준 함수 인터페이스 사용
	public static void printPersonsWithPredicate(List<Person> roster, Predicate<Person> tester) {
		for (Person p : roster) {
			if (tester.test(p)) {
				p.printPerson();
			}
		}
	}

	// Approach 7: 조건 및 출력 모두 Lambda 사용
	public static void processPersons(List<Person> roster, Predicate<Person> tester, Consumer<Person> block) {
			for (Person p : roster) {
				// java.util.function.Predicate.test
				// 주어진 조건에 대해 true/false 반환
				if (tester.test(p)) {
					// java.util.function.Consumer.accept
					// 주어진 매개변수에 대해 작업 수행
					block.accept(p);
				}
			}
	}

	// Approach 7, 두번째 예제

	public static void processPersonsWithFunction(List<Person> roster,
		Predicate<Person> tester,
		Function<Person, String> function,
		Consumer<String> block) {

		for (Person p : roster) {
			if (tester.test(p)) {
				// java.util.function.Function.apply
				// 함수에 argument 적용
				String data = function.apply(p);
				block.accept(data);
			}
		}
	}

	// Approach 8: 제네릭 사용

	public static <X, Y> void processElements(
		Iterable<X> source,
		Predicate<X> tester,
		Function<X, Y> mapper,
		Consumer<Y> block) {
			for (X p : source) {
				if (tester.test(p)) {
					Y data = mapper.apply(p);
					block.accept(data);
				}
			}
	}

	public static void main(String... args) {

		/**
		 * Generate sample data
		 */
		List<Person> roster = Person.createRoster();

		for (Person p : roster) {
			p.printPerson();
		}
		System.out.println();


		/**
		 * Approach 1: 하나의 조건과 일치하는 대상 검색
		 * 작성은 간단하나 Person 클래스의 멤버변수가 변경되거나 조건이 추가/변경되는 경우 재작성하거나 추가해야함.
		 */
		System.out.println("Persons older than 20:");
		printPersonsOlderThan(roster, 20);
		System.out.println();


		/**
		 * Approach 2: 여러 조건을 만족하는 검색
		 * printPersonsOlderThan과 같이 멤버변수가 변경되거나 조건이 추가/변경되는 경우 재작성하거나 추가해야함.
		 */
		System.out.println("Persons between the ages of 14 and 30:");
		printPersonsWithinAgeRange(roster, 14, 30);
		System.out.println();


		/**
		 * Approach 3: local class로 검색 기준 정의
		 * 인터페이스를 상속 받아 class와 method를 정의하여 검색 기준 정의
		 * 새 인터페이스와 local클래스 코드를 추가해야하는 문제가 있음.
		 */
		System.out.println("Persons who are eligible for Selective Service:");
		class CheckPersonEligibleForSelectiveService implements CheckPerson {
			public boolean test(Person p) {
					return p.getGender() == Person.Sex.MALE
						&& p.getAge() >= 18
						&& p.getAge() <= 25;
				}
		}

		printPersons(roster, new CheckPersonEligibleForSelectiveService());
		System.out.println();


		/**
		 * Approach 4: 익명 클래스에서 검색 기준 정의
		 * 새 class를 만들필요가 없어 코드양이 줄어든다.
		 * 하지만 인터페이스안에 하나의 메서드를 작성한는 것도 코드양이 큼.
		 */
		System.out.println("Persons who are eligible for Selective Service " +
				"(anonymous class):");

		printPersons(
			roster,
			new CheckPerson() {
				@Override
				public boolean test(Person p) {
					return p.getGender() == Person.Sex.MALE
						&& p.getAge() >= 18
						&& p.getAge() <= 25;
				}
			}
		);
		System.out.println();


		/**
		 * Approach 5: Lambda 표현식으로 검색 기준 정의
		 * 하나의 추상메서드만 포함하는 인터페이스
		 * 메서드 이름 생략가능
		 */
		System.out.println("Persons who are eligible for Selective Service " +
			"(lambda expression):");

		printPersons(
			roster,
			(Person p) -> p.getGender() == Person.Sex.MALE
				&& p.getAge() >= 18
				&& p.getAge() <= 25
		);
		System.out.println();


		/**
		 * Approach 6: Lambda와 함께 표준 함수 인터페이스 사용
		 * 인터페이스의 메서드는 하나의 매개 변수를 사용하여 boolean값을 리턴하므로
		 * 함수형 인터페이스인 Predicate<T>의 boolean test(T t) 를 사용하여 정의한다.
		 */
		System.out.println("Persons who are eligible for Selective Service " +
			"(with Predicate parameter):");

		printPersonsWithPredicate(
			roster,
			p -> p.getGender() == Person.Sex.MALE
				&& p.getAge() >= 18
				&& p.getAge() <= 25
		);

		System.out.println();

		/**
		 * Approach 7: 조건 및 출력 모두 Lambda 사용
		 * 동작을 지정하는 Consumer<T> void accept 메서드 추가
		 */
		System.out.println("Persons who are eligible for Selective Service " +
			"(with Predicate and Consumer parameters):");
		processPersons(
			roster,
			p -> p.getGender() == Person.Sex.MALE
				&& p.getAge() >= 18
				&& p.getAge() <= 25,
			p -> p.printPerson()
		);
		System.out.println();


		/**
		 * Approach 7, 두번째 예제
		 * Function<T,R> 인터페이스 추가 apply(T t) return R
		 */
		System.out.println("Persons who are eligible for Selective Service " +
			"(with Predicate, Function, and Consumer parameters):");

		processPersonsWithFunction(
			roster,
			p -> p.getGender() == Person.Sex.MALE
				&& p.getAge() >= 18
				&& p.getAge() <= 25,
			p -> p.getEmailAddress(),
			email -> System.out.println(email)
		);
		System.out.println();

		/**
		 * Approach 8: 제네릭 사용
		 * 제네릭을 사용하여 모든 타입 적용
		 */
		System.out.println("Persons who are eligible for Selective Service " +
			"(generic version):");

		processElements(
			roster,
			p -> p.getGender() == Person.Sex.MALE
				&& p.getAge() >= 18
				&& p.getAge() <= 25,
			p -> p.getEmailAddress(),
			email -> System.out.println(email)
		);

		System.out.println();

		/**
		 * Approach 9: Stream과 Lambda 사용
		 * Stream<E> stream() => 객체의 요소를 출력
		 * Stream<T> filter(Predicate<? super T> predicate) => 객체와 일치하는 객체와 일치하는 Predicate객체 필터링
		 * <R> Stream<R> map(Function<? super T,? extends R> mapper) => Function에서 지정한 객체로 반환
		 * void forEach(Consumer<? super T> action) => Consumer객체가 지정한 동작을 수행
		 */
		System.out.println("Persons who are eligible for Selective Service " +
			"(with bulk data operations):");

		roster
			.stream()
			.filter(
				p -> p.getGender() == Person.Sex.MALE
					&& p.getAge() >= 18
					&& p.getAge() <= 25)
			.map(p -> p.getEmailAddress())
			.forEach(email -> System.out.println(email));
	}
}
