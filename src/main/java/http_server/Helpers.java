package http_server;

public class Helpers {

    public static void sleep(int i) {
        try {
            Thread.sleep(i * 1000L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
