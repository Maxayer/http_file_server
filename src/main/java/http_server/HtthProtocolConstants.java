package http_server;

public class HtthProtocolConstants {

    public static final String CRLF = "\n\r";
    public static final String html = "<html><head><title>Sun Java HTTP Server</title></head><body><h1>This page was served using Sun Java HTTP Server</h1></body></html>";
    public static final String json = "{\"menu\": {\n" +
            "  \"id\": \"file\",\n" +
            "  \"value\": \"File\",\n" +
            "  \"popup\": {\n" +
            "    \"menuitem\": [\n" +
            "      {\"value\": \"New\", \"onclick\": \"CreateNewDoc()\"},\n" +
            "      {\"value\": \"Open\", \"onclick\": \"OpenDoc()\"},\n" +
            "      {\"value\": \"Close\", \"onclick\": \"CloseDoc()\"}\n" +
            "    ]\n" +
            "  }\n" +
            "}}";
    public static final String response = "HTTP/1.1 200 OK" + CRLF +
            "Content-Length: " + html.getBytes().length + CRLF +
            CRLF +
            html +
            CRLF + CRLF;
}
