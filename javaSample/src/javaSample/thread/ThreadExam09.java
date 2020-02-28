package javaSample.thread;

/**
 * static synchronized �Լ��� synchronized �Լ��� ȥ��
 * batman()�� superman()�� class�� lock���� ��������� ironman()�� ������ ��ü�� �������� lock�� ��� ������ ���� ���� ��� �ȴ�
 * ���� ironman()�� "HERO" ������ ��� �ٲ�ġ�� �ϱ⶧���� ���� ����ȭ�� �������� ����.
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
