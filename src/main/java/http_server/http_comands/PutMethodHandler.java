package http_server.http_comands;

import com.sun.net.httpserver.HttpExchange;
import http_server.file_storage.FileKeeper;
import java.io.OutputStream;

public class PutMethodHandler implements HttpMethodHandler{

    @Override
    public void handleMethod(HttpExchange ex, String name, String fileBody, FileKeeper fileKeeper) {
        String response = "";
        int code = fileKeeper.add(name, fileBody) ? 200 : 400;

        executeSendResponseHeaders(ex, code, response.getBytes().length);

        if (code == 200) {
            OutputStream outputStream = ex.getResponseBody();
            executeWriteToOutputStream(outputStream, response);
        }

    }
}
