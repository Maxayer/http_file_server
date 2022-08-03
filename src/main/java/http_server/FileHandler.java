package http_server;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import http_server.file_storage.FileKeeper;
import org.apache.commons.io.IOUtils;

import java.io.*;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;


public class FileHandler implements HttpHandler {
    private final FileKeeper temporalFileKeeper = FileKeeper.getTemporalFileKeeper();
    private String requestMethod;
    private URI requestUri;
    private Headers requestHeaders;
    private InputStream requestBody;
    private String fileBody;

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        setRequestParameters(exchange);
        setFileFromRequestBody();

        if ("put".equalsIgnoreCase(requestMethod)) {
            saveFile();
            String response = "";
            try {
                exchange.sendResponseHeaders(200, response.getBytes().length);
                OutputStream outputStream = exchange.getResponseBody();
                outputStream.write(response.getBytes());
            } catch (Exception e) {
                e.printStackTrace();
            }
            exchange.close();
        }

        if ("get".equalsIgnoreCase(requestMethod)) {
            String file = getSavedFile();
            OutputStream outputStream = null;
            try {
                exchange.sendResponseHeaders(200, file.getBytes().length);
                outputStream = exchange.getResponseBody();
                outputStream.write(file.getBytes());
            }
            catch (Exception e) {
                e.printStackTrace();
            }
            finally {
                if (outputStream != null) {
                    outputStream.close();
                }
                exchange.close();
            }
        }
        Helpers.sleep(5);
    }

    private void saveFile() {
        temporalFileKeeper.add(requestUri.toString(), fileBody);
    }

    private String getSavedFile() {
        return temporalFileKeeper.get(requestUri.toString());
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
