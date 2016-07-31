package uebung_04;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class HelloClient {
	public static void main(String[] args) throws Exception {
		Registry registry = LocateRegistry.getRegistry(Hello.HOST, Hello.PORT);
		Hello hello = (Hello) registry.lookup(Hello.LOOKUP_NAME);
		System.out.println(hello.getHello());
	}

}
