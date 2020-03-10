package javaSample.design_patterns.Creational.singleton;

public class StaticBlockSingleton {

	private static StaticBlockSingleton instance;

	private StaticBlockSingleton(){}

	/**
	 * static block �ʱ�ȭ / ���� ó��
	 */
	static{
		try{
			instance = new StaticBlockSingleton();
		}catch(Exception e){
			throw new RuntimeException("Exception occured in creating singleton instance");
		}
	}

	public static StaticBlockSingleton getInstance(){
		return instance;
	}
}
