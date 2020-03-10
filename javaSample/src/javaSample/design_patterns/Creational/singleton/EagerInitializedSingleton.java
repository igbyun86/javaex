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
	 * �ʱ⿡ �ν��Ͻ� ����
	 * �ۼ��ϱ� ���� ���� ��������� �޼��带 ȣ������ �ʾƵ� �ν��Ͻ�ȭ��.
	 * ����ó���� �ȵǾ�����
	 * @return
	 */
	public static EagerInitializedSingleton getInstance(){
		return instance;
	}
}
