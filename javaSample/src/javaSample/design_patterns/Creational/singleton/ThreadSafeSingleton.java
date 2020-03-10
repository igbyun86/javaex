package javaSample.design_patterns.Creational.singleton;

public class ThreadSafeSingleton {

	private static ThreadSafeSingleton instance;

	private ThreadSafeSingleton(){}

	/**
	 * 동기화를 지원하지만 성능이 저하됨
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
