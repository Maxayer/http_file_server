package http_server.http_comands;

import com.sun.net.httpserver.HttpExchange;
import http_server.StatusCode;

import java.io.OutputStream;

public class PostMethodHandler extends HttpMethodHandler {
    public PostMethodHandler(HttpExchange ex, String name, String fileBody) {
        super(ex, name, fileBody);
    }

    @Override
    public void handleMethod() {
        String response = "";
        int code;
        synchronized (fileKeeper) {
            code = fileKeeper.add(name, fileBody) ? StatusCode._201.getCode() : StatusCode._500.getCode();
        }

        executeSendResponseHeaders(ex, code, response.getBytes().length);

        if (code == StatusCode._201.getCode()) {
            OutputStream outputStream = ex.getResponseBody();
            executeWriteToOutputStream(outputStream, response);
        }

    }
}
