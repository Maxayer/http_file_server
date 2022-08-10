package http_server;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import http_server.file_storage.FileKeeper;
import http_server.http_comands.DeleteMethodHandler;
import http_server.http_comands.GetMethodHandler;
import http_server.http_comands.HttpMethodHandler;
import http_server.http_comands.PutMethodHandler;
import org.apache.commons.io.IOUtils;

import java.io.*;
import java.net.URI;
import java.nio.charset.StandardCharsets;


public class FileHandler implements HttpHandler {
    private final FileKeeper temporalFileKeeper = FileKeeper.getTemporalFileKeeper();
    private String requestMethod;
    private URI requestUri;
    private Headers requestHeaders;
    private InputStream requestBody;
    private String fileBody;

    @Override
    public void handle(HttpExchange ex) {
        setRequestParameters(ex);
        setFileFromRequestBody();
        try {
            if ("put".equalsIgnoreCase(requestMethod)) {
                new PutMethodHandler(ex, requestUri.toString(), fileBody, temporalFileKeeper).handleMethod();
            }

            if ("get".equalsIgnoreCase(requestMethod)) {
                new GetMethodHandler(ex, requestUri.toString(), temporalFileKeeper).handleMethod();
            }
            if ("delete".equalsIgnoreCase(requestMethod)) {
                new DeleteMethodHandler(ex, requestUri.toString(), temporalFileKeeper).handleMethod();
            }
        } finally {
            try {
                ex.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void setRequestParameters(HttpExchange he) {
        requestMethod = he.getRequestMethod();
        requestUri = he.getRequestURI();
        requestHeaders = he.getRequestHeaders();
        requestBody = he.getRequestBody();
    }

    private void setFileFromRequestBody() {
        String fileBody = null;
        try {
            fileBody = IOUtils.toString(requestBody, StandardCharsets.UTF_8);
            if (fileBody != null && !"".equals(fileBody)) {
                fileBody = fileBody.split("\n\r")[1]
                        .replaceAll("-+\\d+-+", "")
                        .trim();
                this.fileBody = fileBody;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
