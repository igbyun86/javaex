package javaSample.design_patterns.Creational.singleton;

public class LazyInitializedSingleton {

	private static LazyInitializedSingleton instance;

	private LazyInitializedSingleton(){}

	/**
	 * 다중 쓰레드인 경우 문제가 발생할 수 있음.
	 * @return
	 */
	public static LazyInitializedSingleton getInstance(){
		if(instance == null){
			instance = new LazyInitializedSingleton();
		}
		return instance;
	}
}
