package javaSample.thread;

/**
 * synchronized �Լ��� �ڽ��� ���Ե� ��ü�� lock�� �Ǵ�
 * synchronized�� ���Ͽ� ��ü�� ���Ե� �ٸ� ��� synchronized�� ���� ���� lock�� �ɸ���.
 * �α״� ��µ��� �ʴ´�.
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
