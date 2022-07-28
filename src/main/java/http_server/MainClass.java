package http_server;

import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

public class MainClass extends Thread {
    public static void main (String[] args) throws IOException {
        HttpServer jsonServer = HttpServer.create(new InetSocketAddress(8082), 0);
        jsonServer.createContext("/", new FileHandler());
        jsonServer.setExecutor(Executors.newCachedThreadPool());

        jsonServer.start();
    }
}
