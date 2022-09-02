package http_server.http_comands;

import com.sun.net.httpserver.HttpExchange;
import http_server.StatusCode;
import http_server.file_storage.FileKeeper;

public class DeleteMethodHandler extends HttpMethodHandler {
    public DeleteMethodHandler(HttpExchange ex, String name, String fileBody) {
        super(ex, name, fileBody);
    }

    public DeleteMethodHandler(HttpExchange ex, String name) {
        super(ex, name);
    }

    @Override
    public void handleMethod() {
        String response = "";
        synchronized (fileKeeper) {
            if(fileKeeper.delete(name)) {
                executeSendResponseHeaders(ex, StatusCode._204.getCode(), response.getBytes().length);
            }
            else {
                executeSendResponseHeaders(ex, StatusCode._500.getCode(), response.getBytes().length);
            }
        }

    }
}
