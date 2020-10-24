package design_pattern.creational.singleton;

public class Test {
	public static void main(String[] args) {
		EagerInitializedSingleton eagerSingleton = EagerInitializedSingleton.getInstance();
		StaticBlockSingleton staticSingleton = StaticBlockSingleton.getInstance();
		LazyInitializedSingleton lazySingleton = LazyInitializedSingleton.getInstance();
		ThreadSafeSingleton threadSingleton = ThreadSafeSingleton.getInstance();
		BillPughSingleton billSingleton = BillPughSingleton.getInstance();
		
		System.out.println("This instance belong to: " + eagerSingleton.getClass().getSimpleName());
		System.out.println("This instance belong to: " + staticSingleton.getClass().getSimpleName());
		System.out.println("This instance belong to: " + lazySingleton.getClass().getSimpleName());
		System.out.println("This instance belong to: " + threadSingleton.getClass().getSimpleName());
		System.out.println("This instance belong to: " + billSingleton.getClass().getSimpleName());
		
		Integer.valueOf("1");
		
		EagerInitializedSingleton ei1 = EagerInitializedSingleton.getInstance();
		EagerInitializedSingleton ei2 = EagerInitializedSingleton.getInstance();
		System.out.println(ei1 == ei2);
	}
}
