package javaSample.thread;

/**
 * static synchronized 함수와 synchronized 함수의 혼용
 * batman()과 superman()은 class를 lock으로 사용하지만 ironman()은 생성된 객체를 기준으로 lock을 잡기 때문에 서로 따로 놀게 된다
 * 따라서 ironman()이 "HERO" 변수를 계속 바꿔치기 하기때문에 전부 동기화가 지켜지지 않음.
 * @author big
 *
 */
public class ThreadExam09 {
	private static String HERO;

	public static void main(String[] agrs) {
		System.out.println("Test start!");

		new Thread(() -> {
			for (int i = 0; i < 1000000; i++) {
				ThreadExam09.batman();
			}
		}).start();

		new Thread(() -> {
			for (int i = 0; i < 1000000; i++) {
				ThreadExam09.superman();
			}
		}).start();

		ThreadExam09 sfunction = new ThreadExam09();

		new Thread(() -> {
			for (int i = 0; i < 1000000; i++) {
				sfunction.ironman();
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

	public synchronized void ironman() {
		HERO = "ironman";

		try {
			long sleep = (long) (Math.random() * 100);
			Thread.sleep(sleep);
			if ("ironman".equals(HERO) == false) {
				System.out.println("synchronization broken - ironman");
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
