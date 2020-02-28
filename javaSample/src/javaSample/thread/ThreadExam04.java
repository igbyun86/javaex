package javaSample.thread;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author big
 *
 */
public class ThreadExam04 {
	public List<Integer> mList = new ArrayList<>();

	public static void main(String[] agrs) throws InterruptedException {
		ThreadExam04 syncblock1 = new ThreadExam04();
		System.out.println("Test start!");

		Thread t1 = new Thread(() -> {
			for (int i = 0; i < 100; i++) {
				syncblock1.add(i);
			}
		});

		Thread t2 = new Thread(() -> {
			for (int i = 0; i < 100; i++) {
				syncblock1.add(i);
			}
		});

		t1.start();
		t2.start();

		t1.join();
		t2.join();

		System.out.println(syncblock1.mList.size());
		System.out.println("Test end!");
	}


	public void add(int val) {

		/**
		 * ��ϸ� ����ȭ ó��
		 * this�� �޼ҵ尡 ȣ��� ��ü�� �ǹ�
		 */
		synchronized (this) {
			if (mList.contains(val) == false) {
				mList.add(val);
			}
		}
	}

}
