package javaSample.thread;

public class ThreadExam01 {
	private String mMessage;

	public static void main(String[] agrs) {
		ThreadExam01 temp = new ThreadExam01();

		System.out.println("Test start!");

		new Thread(() -> {
			for (int i = 0; i < 100; i++) {
				temp.callMe("Thread1_" + i);
			}
		}).start();

		new Thread(() -> {
			for (int i = 0; i < 100; i++) {
				temp.callMe("Thread2_" + i);
			}
		}).start();

		System.out.println("Test end!");
	}

	/**
	 * �Լ��� synchronized�� �ɸ� �� �Լ��� ���Ե� �ش� ��ü(this)�� lock�� �Ŵ� �Ͱ� ����.
	 * �Ʒ� ����� ���� ����
	 * ���������� method area�� ����ǰ� ��� thread�� �����Ѵ�.
	 * @param whoCallMe
	 */
	public synchronized void callMe(String whoCallMe) {
		mMessage = whoCallMe;

		try {
			long sleep = (long) (Math.random() * 100);
			Thread.sleep(sleep);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		if (!mMessage.equals(whoCallMe)) {
			System.out.println(whoCallMe + " | " + mMessage);
		}
	}

}
