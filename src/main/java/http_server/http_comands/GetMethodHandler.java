package http_server.http_comands;

import com.sun.net.httpserver.HttpExchange;
import http_server.StatusCode;

import java.io.OutputStream;

public class GetMethodHandler extends HttpMethodHandler {

    public GetMethodHandler(HttpExchange ex, String name, String fileBody) {
        super(ex, name, fileBody);
    }
    public GetMethodHandler(HttpExchange ex, String name) {
        super(ex, name);
    }


    @Override
    public void handleMethod() {
        synchronized (fileKeeper) {
            if(fileKeeper.isFilePresent(name)) {
                String file = fileKeeper.get(name);
                // trigger
                executeSendResponseHeaders(ex, StatusCode._200.getCode(), file.getBytes().length);
                OutputStream outputStream = ex.getResponseBody();
                executeWriteToOutputStream(outputStream, file);
            }
            else {
                executeSendResponseHeaders(ex, StatusCode._404.getCode(), 0);
            }
        }

    }
}
