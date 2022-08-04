package http_server.http_comands;

import com.sun.net.httpserver.HttpExchange;
import http_server.file_storage.FileKeeper;

public class DeleteMethodHandler implements HttpMethodHandler {
    @Override
    public void handleMethod(HttpExchange ex, String name, String fileBody, FileKeeper fileKeeper) {
        String response = "";
        if(fileKeeper.delete(name)) {
            executeSendResponseHeaders(ex, 200, response.getBytes().length);
        }
        else {
            executeSendResponseHeaders(ex, 400, response.getBytes().length);
        }
    }
}
