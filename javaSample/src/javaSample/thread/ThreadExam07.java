package javaSample.thread;

import java.util.ArrayList;
import java.util.List;

/**
 * Singleton ��ü������ ����ȭ
 * ����ȭ�� ������ ��Ƽ������ ȯ�濡�� ������ �ϳ��� ����ϹǷ� ȿ�������� ����.
 * @author big
 *
 */
public class ThreadExam07 {
	public List<Integer> mList = new ArrayList<>();


	private ThreadExam07() {
	}

	private static class Singleton {
		public static final ThreadExam07 instance = new ThreadExam07();
	}

	public static ThreadExam07 getInstance() {
		return Singleton.instance;
	}

	public static void main(String[] agrs) throws InterruptedException {
		System.out.println("Test start!");

		Thread t1 = new Thread(() -> {
			for (int i = 0; i < 10000; i++) {
				ThreadExam07.getInstance().add(i);
			}
		});

		Thread t2 = new Thread(() -> {
			for (int i = 0; i < 10000; i++) {
				ThreadExam07.getInstance().add(i);
			}
		});

		t1.start();
		t2.start();

		t1.join();
		t2.join();

		System.out.println(ThreadExam07.getInstance().mList.size());
		System.out.println("Test end!");
	}

	public synchronized void add(int val) {
		if (mList.contains(val) == false) {
			mList.add(val);
		}
	}

}
