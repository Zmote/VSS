package uebung_01;

import java.net.InetAddress;
import java.net.InterfaceAddress;
import java.net.MalformedURLException;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;

public class Aufgabe1_InetAddress {
	
	public static void main(String[] args) throws UnknownHostException, SocketException, MalformedURLException {
//		a)
		outputLocalInetAddressInformation();
		outputInterfaceInformation();
		Enumeration<NetworkInterface> nets = NetworkInterface.getNetworkInterfaces();
		for(NetworkInterface net : Collections.list(nets)){
			outputInterfaceAddresses(net.getName());
		}
//		b)
//		getIPofURL("http://sidv0012.hsr.ch");
//		c)
//		getNameFromIP("152.96.36.121");
//		d)
//		getAllIPforHost("www.google.com");
	}
	
	static void getAllIPforHost(String host) throws UnknownHostException {
		InetAddress[] ips = InetAddress.getAllByName(host);
		System.out.println(host + ":");
		for(InetAddress ip : ips){
			System.out.println(ip.getHostAddress());
		}
		
	}

	static void getNameFromIP(String ipString) throws UnknownHostException{
		InetAddress ip = InetAddress.getByName(ipString);
		System.out.println(ip + ": " + ip.getHostName());
	}
	
	static void getIPofURL(String url) throws UnknownHostException, MalformedURLException{
		InetAddress address = InetAddress.getByName(new URL(url).getHost());
		System.out.println(url + ": " + address.getHostAddress());
	}
	
	static void outputLocalInetAddressInformation() throws UnknownHostException{
		InetAddress localAdress = InetAddress.getLocalHost();
		System.out.println("Local adres: " + localAdress);
		System.out.println("Canonical Host Name: " + localAdress.getCanonicalHostName());
		System.out.println(localAdress.getHostAddress());
		System.out.println(localAdress.getHostName());
	}
	
	static void outputInterfaceInformation() throws SocketException{
		Enumeration<NetworkInterface> nets = NetworkInterface.getNetworkInterfaces();
		for(NetworkInterface net : Collections.list(nets)){
			displayInterfaceInformation(net);
		}
	}
	
	static void displayInterfaceInformation(NetworkInterface netint) throws SocketException {
        System.out.printf("Display name: %s\n", netint.getDisplayName());
        System.out.printf("Name: %s\n", netint.getName());
        Enumeration<InetAddress> inetAddresses = netint.getInetAddresses();
        for (InetAddress inetAddress : Collections.list(inetAddresses)) {
            System.out.printf("InetAddress: %s\n", inetAddress);
        }
        System.out.printf("\n");
     }
	
	static void outputInterfaceAddresses(String network) throws SocketException{
		NetworkInterface net = NetworkInterface.getByName(network);
		List<InterfaceAddress> listIA = net.getInterfaceAddresses();
		for(InterfaceAddress iAdres : listIA){
			System.out.println(iAdres.getAddress().getHostName() + ": " + iAdres.getAddress().getHostAddress());
		}
	}

}
