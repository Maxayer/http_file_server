package http_server.file_storage;

import java.util.HashMap;
import java.util.Map;

public class TemporalFileKeeper extends FileKeeper {
    private final Map<String, String> fileServer = new HashMap<>();

    @Override
    public String get(String name) {
        return fileServer.get(name);
    }

    @Override
    public boolean add(String name, String file) {
        fileServer.put(name, file);
        return file.equals(fileServer.get(name));
    }

    @Override
    public boolean delete(String name) {
        fileServer.remove(name);
        return fileServer.get(name) == null;
    }

    @Override
    public boolean isFilePresent(String name) {
        return null != fileServer.get(name);
    }
}
