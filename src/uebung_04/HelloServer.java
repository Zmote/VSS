package uebung_04;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class HelloServer implements Hello {

	@Override
	public String getHello() throws RemoteException {
		return "Hello RMI";
	}
	
	public static void main(String[] args) throws Exception {
		HelloServer hello = new HelloServer();
		Hello stub = (Hello) UnicastRemoteObject.exportObject(hello,PORT);
		Registry registry = LocateRegistry.createRegistry(PORT);
		registry.rebind(LOOKUP_NAME, stub);
	}

}
