package http_server;

import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

public class MainClass extends Thread {
    public static void main (String[] args) throws IOException {
        InetSocketAddress inetSocketAddress = new InetSocketAddress(8082);
        HttpServer jsonServer = HttpServer.create(inetSocketAddress, 0);
        jsonServer.createContext("/", new FileHandler());
        jsonServer.setExecutor(Executors.newCachedThreadPool());

        //jsonServer.start();
    }
}
