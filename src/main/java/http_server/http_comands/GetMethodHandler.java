package http_server.http_comands;

import com.sun.net.httpserver.HttpExchange;

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
