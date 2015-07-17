package com.lzx.framework.socket.netty3;


import java.io.IOException;
import java.sql.Timestamp;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.lzx.framework.server.IServer;

public class TestMain {
	
	public static short myid=0;
	private static ApplicationContext context = new ClassPathXmlApplicationContext("server.xml");

	public static void main(String[] args) throws IOException {
		// System.out.println("getLocalHost" + InetAddress.getLocalHost());
		// System.out.println("System.setProperty java.rmi.server.hostname as:"+"sa.rmi.com");
		// System.setProperty("java.rmi.server.hostname", "db.rmi.com");
		// System.out.println("RMISocketFactory.setSocketFactory port as:9796");
		// RMISocketFactory.setSocketFactory(new MyRMISocket(9796));

		IServer server = (IServer) context.getBean("server");
		if (args.length == 0 || args[0].equals("start")) {
			startServer(server);
		}
		if (args.length != 0 && args[0].equals("stop")) {
			stopServer(server);
		}

	}

	public static void startServer(IServer server) {
		String serverName = server.getName();
		printServerStart(serverName);
		server.setApplicationContext(context);
		printSystemEcho("initBeforeStart");
		server.initBeforeStart();
		printSystemEcho("starting");
		try {
			server.start();
			printSystemEcho("initAfterStarted");
			server.initAfterStarted();
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(0);
		}
	}

	public static void stopServer(IServer server)  {
		try {
			server.setApplicationContext(context);
			printSystemEcho("beforeStop");
			server.beforeStop();
			printSystemEcho("stop");
			server.stop();
			printSystemEcho("afterStop");
			server.afterStop();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void printSystemEcho(String msg) {
		System.out.println("++++++++---" + msg + " ......");
	}

	public static void printServerStart(String serverName) {
		String successMsg = "Start Server \"" + serverName + "\" at " + new Timestamp(System.currentTimeMillis());
		System.out.println("　　                                                                   \\\\  - -  //");
		System.out.println("                                 (  @ @  )");
		System.out.println("┏━━━━━━━━━━━━━━━━━oOOo-(_)-oOOo━━━━━━━━━━┓");
		System.out.println("      " + successMsg + "");

		System.out.println("      Designed by linZhiXian,fly_go@hotmail.com");
		System.out.println(" ━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
	}
}
