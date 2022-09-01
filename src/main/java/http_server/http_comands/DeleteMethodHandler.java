package http_server.http_comands;

import com.sun.net.httpserver.HttpExchange;
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
                executeSendResponseHeaders(ex, 200, response.getBytes().length);
            }
            else {
                executeSendResponseHeaders(ex, 400, response.getBytes().length);
            }
        }

    }
}
