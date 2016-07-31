package uebung_04;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Hello extends Remote {
	public static final int PORT =5001;
	public static final String HOST = "localhost";
	public static final String LOOKUP_NAME ="Hello";
	public String getHello() throws RemoteException;
}
