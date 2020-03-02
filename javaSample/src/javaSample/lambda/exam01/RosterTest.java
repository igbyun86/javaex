package javaSample.lambda.exam01;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class RosterTest {
	interface CheckPerson {
		boolean test(Person p);
	}

	// Approach 1: �ϳ��� ���ǰ� ��ġ�ϴ� ��� �˻�
	public static void printPersonsOlderThan(List<Person> roster, int age) {
		for (Person p : roster) {
			if (p.getAge() >= age) {
				p.printPerson();
			}
		}
	}

	// Approach 2: ���� ������ �����ϴ� �˻�
	public static void printPersonsWithinAgeRange(List<Person> roster, int low, int high) {
		for (Person p : roster) {
			if (low <= p.getAge() && p.getAge() < high) {
				p.printPerson();
			}
		}
	}

	// Approach 3: local class�� �˻� ���� ����
	// Approach 4: �͸� Ŭ�������� �˻� ���� ����
	// Approach 5: Lambda ǥ�������� �˻� ���� ����
	public static void printPersons(List<Person> roster, CheckPerson tester) {
		for (Person p : roster) {
			if (tester.test(p)) {
				p.printPerson();
			}
		}
	}

	// Approach 6: Lambda�� �Բ� ǥ�� �Լ� �������̽� ���
	public static void printPersonsWithPredicate(List<Person> roster, Predicate<Person> tester) {
		for (Person p : roster) {
			if (tester.test(p)) {
				p.printPerson();
			}
		}
	}

	// Approach 7: ���� �� ��� ��� Lambda ���
	public static void processPersons(List<Person> roster, Predicate<Person> tester, Consumer<Person> block) {
			for (Person p : roster) {
				// java.util.function.Predicate.test
				// �־��� ���ǿ� ���� true/false ��ȯ
				if (tester.test(p)) {
					// java.util.function.Consumer.accept
					// �־��� �Ű������� ���� �۾� ����
					block.accept(p);
				}
			}
	}

	// Approach 7, �ι�° ����

	public static void processPersonsWithFunction(List<Person> roster,
		Predicate<Person> tester,
		Function<Person, String> function,
		Consumer<String> block) {

		for (Person p : roster) {
			if (tester.test(p)) {
				// java.util.function.Function.apply
				// �Լ��� argument ����
				String data = function.apply(p);
				block.accept(data);
			}
		}
	}

	// Approach 8: ���׸� ���

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
		 * Approach 1: �ϳ��� ���ǰ� ��ġ�ϴ� ��� �˻�
		 * �ۼ��� �����ϳ� Person Ŭ������ ��������� ����ǰų� ������ �߰�/����Ǵ� ��� ���ۼ��ϰų� �߰��ؾ���.
		 */
		System.out.println("Persons older than 20:");
		printPersonsOlderThan(roster, 20);
		System.out.println();


		/**
		 * Approach 2: ���� ������ �����ϴ� �˻�
		 * printPersonsOlderThan�� ���� ��������� ����ǰų� ������ �߰�/����Ǵ� ��� ���ۼ��ϰų� �߰��ؾ���.
		 */
		System.out.println("Persons between the ages of 14 and 30:");
		printPersonsWithinAgeRange(roster, 14, 30);
		System.out.println();


		/**
		 * Approach 3: local class�� �˻� ���� ����
		 * �������̽��� ��� �޾� class�� method�� �����Ͽ� �˻� ���� ����
		 * �� �������̽��� localŬ���� �ڵ带 �߰��ؾ��ϴ� ������ ����.
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
		 * Approach 4: �͸� Ŭ�������� �˻� ���� ����
		 * �� class�� �����ʿ䰡 ���� �ڵ���� �پ���.
		 * ������ �������̽��ȿ� �ϳ��� �޼��带 �ۼ��Ѵ� �͵� �ڵ���� ŭ.
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
		 * Approach 5: Lambda ǥ�������� �˻� ���� ����
		 * �ϳ��� �߻�޼��常 �����ϴ� �������̽�
		 * �޼��� �̸� ��������
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
		 * Approach 6: Lambda�� �Բ� ǥ�� �Լ� �������̽� ���
		 * �������̽��� �޼���� �ϳ��� �Ű� ������ ����Ͽ� boolean���� �����ϹǷ�
		 * �Լ��� �������̽��� Predicate<T>�� boolean test(T t) �� ����Ͽ� �����Ѵ�.
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
		 * Approach 7: ���� �� ��� ��� Lambda ���
		 * ������ �����ϴ� Consumer<T> void accept �޼��� �߰�
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
		 * Approach 7, �ι�° ����
		 * Function<T,R> �������̽� �߰� apply(T t) return R
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
		 * Approach 8: ���׸� ���
		 * ���׸��� ����Ͽ� ��� Ÿ�� ����
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
		 * Approach 9: Stream�� Lambda ���
		 * Stream<E> stream() => ��ü�� ��Ҹ� ���
		 * Stream<T> filter(Predicate<? super T> predicate) => ��ü�� ��ġ�ϴ� ��ü�� ��ġ�ϴ� Predicate��ü ���͸�
		 * <R> Stream<R> map(Function<? super T,? extends R> mapper) => Function���� ������ ��ü�� ��ȯ
		 * void forEach(Consumer<? super T> action) => Consumer��ü�� ������ ������ ����
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
