package javaSample.thread;

/**
 * 서로 다른 객체이기 때문에 로그를 찍지 않는다.
 * @author big
 *
 */
public class ThreadExam02 {
	private String mMessage;

	public static void main(String[] agrs) {
		ThreadExam02 temp1 = new ThreadExam02();
		ThreadExam02 temp2 = new ThreadExam02();

		System.out.println("Test start!");

		new Thread(() -> {
			for (int i = 0; i < 100; i++) {
				temp1.callMe("Thread1_" + i);
			}
		}).start();

		new Thread(() -> {
			for (int i = 0; i < 100; i++) {
				temp2.callMe("Thread2_" + i);
			}
		}).start();

		System.out.println("Test end!");
	}

	/**
	 *
	 * @param whoCallMe
	 */
	public void callMe(String whoCallMe) {
		mMessage = whoCallMe;

		try {
			long sleep = (long) (Math.random() * 100);
			Thread.sleep(sleep);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.out.println(whoCallMe);
		if (!mMessage.equals(whoCallMe)) {
			System.out.println(whoCallMe + " | " + mMessage);
		}
	}

}
