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
	 * getInstance �޼��带 ȣ���� �� �ν��Ͻ� �����ϱ� ������ ����ȭ ����
	 * @return
	 */
	public static BillPughSingleton getInstance(){
		return SingletonHelper.INSTANCE;
	}

}
