package javaSample.design_patterns.Creational.singleton;

public class ThreadSafeSingleton {

	private static ThreadSafeSingleton instance;

	private ThreadSafeSingleton(){}

	/**
	 * ����ȭ�� ���������� ������ ���ϵ�
	 * @return
	 */
	public static synchronized ThreadSafeSingleton getInstance(){
		if(instance == null){
			instance = new ThreadSafeSingleton();
		}
		return instance;
	}

	/*
	public static ThreadSafeSingleton getInstanceUsingDoubleLocking(){
		if(instance == null){
			synchronized (ThreadSafeSingleton.class) {
				if(instance == null){
					instance = new ThreadSafeSingleton();
				}
			}
		}
		return instance;
	}
	*/
}
