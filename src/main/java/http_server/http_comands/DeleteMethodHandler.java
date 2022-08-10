package http_server.http_comands;

import com.sun.net.httpserver.HttpExchange;
import http_server.file_storage.FileKeeper;

public class DeleteMethodHandler extends HttpMethodHandler {
    public DeleteMethodHandler(HttpExchange ex, String name, String fileBody, FileKeeper fileKeeper) {
        super(ex, name, fileBody, fileKeeper);
    }

    public DeleteMethodHandler(HttpExchange ex, String name, FileKeeper fileKeeper) {
        super(ex, name, fileKeeper);
    }

    @Override
    public void handleMethod() {
        String response = "";
        if(fileKeeper.delete(name)) {
            executeSendResponseHeaders(ex, 200, response.getBytes().length);
        }
        else {
            executeSendResponseHeaders(ex, 400, response.getBytes().length);
        }
    }
}
