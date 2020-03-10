package javaSample.design_patterns.Creational.singleton;

public class LazyInitializedSingleton {

	private static LazyInitializedSingleton instance;

	private LazyInitializedSingleton(){}

	/**
	 * ���� �������� ��� ������ �߻��� �� ����.
	 * @return
	 */
	public static LazyInitializedSingleton getInstance(){
		if(instance == null){
			instance = new LazyInitializedSingleton();
		}
		return instance;
	}
}
