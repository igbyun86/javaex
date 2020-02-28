package javaSample.thread;

/**
 * synchronized 함수는 자신이 포함된 객체에 lock을 건다
 * synchronized로 인하여 객체에 포함된 다른 모든 synchronized의 접근 까지 lock이 걸린다.
 * 로그는 출력되지 않는다.
 * @author big
 *
 */
public class ThreadExam03 {
	private String mHero;

	public static void main(String[] agrs) {
		ThreadExam03 temp = new ThreadExam03();

		System.out.println("Test start!");

		new Thread(() -> {
			for (int i = 0; i < 100; i++) {
				temp.batman();
			}
		}).start();

		new Thread(() -> {
			for (int i = 0; i < 100; i++) {
				temp.superman();
			}
		}).start();

		System.out.println("Test end!");
	}

	public synchronized void batman() {
		mHero = "batman";

		try {

			long sleep = (long) (Math.random() * 100);
			Thread.sleep(sleep);

			if ("batman".equals(mHero) == false) {
				System.out.println("synchronization broken");
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public synchronized void superman() {
		mHero = "superman";

		try {

			long sleep = (long) (Math.random() * 100);
			Thread.sleep(sleep);

			if ("superman".equals(mHero) == false) {
				System.out.println("synchronization broken");
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
