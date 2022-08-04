package http_server.http_comands;

import com.sun.net.httpserver.HttpExchange;
import http_server.file_storage.FileKeeper;

import java.io.IOException;
import java.io.OutputStream;

public interface HttpMethodHandler {
    void handleMethod(HttpExchange ex, String name, String fileBody, FileKeeper fileKeeper);

    default void executeSendResponseHeaders(HttpExchange ex, int code, int length) {
        try {
            ex.sendResponseHeaders(code, length);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    default void executeWriteToOutputStream(OutputStream outputStream, String response) {
        try {
            outputStream.write(response.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
