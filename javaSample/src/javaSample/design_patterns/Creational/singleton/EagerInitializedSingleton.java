package javaSample.design_patterns.Creational.singleton;

/**
 * 1.
 * @author big
 *
 */
public class EagerInitializedSingleton {

	private static final EagerInitializedSingleton instance = new EagerInitializedSingleton();

	//private constructor to avoid client applications to use constructor
	private EagerInitializedSingleton(){}

	/**
	 * 초기에 인스턴스 생성
	 * 작성하기 가장 쉬운 방법이지만 메서드를 호출하지 않아도 인스턴스화됨.
	 * 예외처리도 안되어있음
	 * @return
	 */
	public static EagerInitializedSingleton getInstance(){
		return instance;
	}
}
