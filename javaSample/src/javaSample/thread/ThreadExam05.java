package javaSample.thread;

import java.util.HashMap;
import java.util.Map;

/**
 * map push/get 각각 메서드 블록 동기화 처리
 * @author big
 *
 */
public class ThreadExam05 {
	private Map<String, String> mMap1 = new HashMap<>();
	private Map<String, String> mMap2 = new HashMap<>();

	public static void main(String[] agrs) {
		ThreadExam05 syncblock2 = new ThreadExam05();
		System.out.println("Test start!");

		new Thread(() -> {
			for (int i = 0; i < 10000; i++) {
				syncblock2.put1("A", "B");
				syncblock2.get2("C");
			}
		}).start();

		new Thread(() -> {
			for (int i = 0; i < 10000; i++) {
				syncblock2.put2("C", "D");
				syncblock2.get1("A");
			}
		}).start();

		System.out.println("Test end!");
	}

	public void put1(String key, String value) {
		synchronized (this) {
			mMap1.put(key, value);
		}
	}

	public String get1(String key) {
		synchronized (this) {
			return mMap1.get(key);
		}
	}

	public void put2(String key, String value) {
		synchronized (this) {
			mMap2.put(key, value);
		}
	}

	public String get2(String key) {
		synchronized (this) {
			return mMap2.get(key);
		}
	}

}
