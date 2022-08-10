package http_server.http_comands;

import com.sun.net.httpserver.HttpExchange;
import http_server.file_storage.FileKeeper;

import java.io.IOException;
import java.io.OutputStream;

public abstract class HttpMethodHandler {
    public HttpExchange ex;
    public String name;
    public String fileBody;
    public FileKeeper fileKeeper;
    HttpMethodHandler(HttpExchange ex, String name, String fileBody, FileKeeper fileKeeper) {
        this.ex = ex;
        this.name = name;
        this.fileBody = fileBody;
        this.fileKeeper = fileKeeper;
    }

    HttpMethodHandler(HttpExchange ex, String name, FileKeeper fileKeeper) {
        this.ex = ex;
        this.name = name;
        this.fileBody = "";
        this.fileKeeper = fileKeeper;
    }
    public abstract void handleMethod();

    void executeSendResponseHeaders(HttpExchange ex, int code, int length) {
        try {
            ex.sendResponseHeaders(code, length);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    void executeWriteToOutputStream(OutputStream outputStream, String response) {
        try {
            outputStream.write(response.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
