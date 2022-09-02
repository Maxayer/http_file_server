package http_server;

public enum StatusCode {
    _200(200, "OK"),
    _201(201, "Created"),
    _202(202,"Accepted"),
    _204(204, "No Content"),
    _400(400, "Bad Request"),
    _403(403, "Forbidden"),
    _404(404, "Not Found"),
    _500(500, "Internal Server Error");

    private int code;
    private String description;

    public int getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    StatusCode(int code, String description) {
        this.code = code;
        this.description = description;
    }

}
