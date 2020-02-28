package javaSample.thread;

/**
 * static 함수의 synchronization
 * static 함수의 경우 해당 class에 lock을 걸기 때문에 로그는 출력하지 않는다.
 * @author big
 *
 */
public class ThreadExam08 {
	private static String HERO;

	public static void main(String[] agrs) {
		System.out.println("Test start!");
		new Thread(() -> {
			for (int i = 0; i < 10000; i++) {
				ThreadExam08.batman();
			}
		}).start();
		new Thread(() -> {
			for (int i = 0; i < 10000; i++) {
				ThreadExam08.superman();
			}
		}).start();

		System.out.println("Test end!");
	}

	public static synchronized void batman() {
		HERO = "batman";

		try {
			long sleep = (long) (Math.random() * 100);
			Thread.sleep(sleep);
			if ("batman".equals(HERO) == false) {
				System.out.println("synchronization broken - batman");
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static synchronized void superman() {
		HERO = "superman";

		try {
			long sleep = (long) (Math.random() * 100);
			Thread.sleep(sleep);
			if ("superman".equals(HERO) == false) {
				System.out.println("synchronization broken - superman");
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
