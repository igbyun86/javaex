package javaSample.design_patterns.Creational.singleton;

/**
 * 5.
 * @author big
 *
 */
public class BillPughSingleton {
	private BillPughSingleton(){}

	private static class SingletonHelper{
		private static final BillPughSingleton INSTANCE = new BillPughSingleton();
	}

	/**
	 * getInstance 메서드를 호출할 때 인스턴스 생성하기 때문에 동기화 지원
	 * @return
	 */
	public static BillPughSingleton getInstance(){
		return SingletonHelper.INSTANCE;
	}

}
