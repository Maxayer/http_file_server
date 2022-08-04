package http_server.file_storage;

public abstract class FileKeeper {
    private static FileKeeper fileKeeper;

    public static FileKeeper getTemporalFileKeeper() {
        if(fileKeeper == null) {
            fileKeeper = new TemporalFileKeeper();
        }
        return fileKeeper;
    }
    public abstract String get(String name);
    public abstract boolean add (String name, String file);
    public abstract boolean delete (String name);

    public abstract boolean isFilePresent(String name);
}
