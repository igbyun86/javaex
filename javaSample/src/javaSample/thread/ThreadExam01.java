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
	 * 함수에 synchronized를 걸면 그 함수가 포함된 해당 객체(this)에 lock을 거는 것과 같다.
	 * 아래 출력은 되지 않음
	 * 전역변수는 method area에 저장되고 모든 thread가 공유한다.
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
