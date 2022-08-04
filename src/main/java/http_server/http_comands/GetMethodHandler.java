package http_server.http_comands;

import com.sun.net.httpserver.HttpExchange;
import http_server.file_storage.FileKeeper;

import java.io.OutputStream;

public class GetMethodHandler implements HttpMethodHandler {

    @Override
    public void handleMethod(HttpExchange ex, String name, String fileBody, FileKeeper fileKeeper) {

        if(fileKeeper.isFilePresent(name)) {
            String file = fileKeeper.get(name);
            executeSendResponseHeaders(ex, 200, file.getBytes().length);
            OutputStream outputStream = ex.getResponseBody();
            executeWriteToOutputStream(outputStream, file);
        }
        else {
            executeSendResponseHeaders(ex, 404, 0);
        }
    }
}
