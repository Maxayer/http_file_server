package http_server;

import http_server.file_storage.FileKeeper;

public class Config {
    private static Config INSTANCE;
    private FileKeeper fileKeeper;
    private CommandFactory commandFactory;

    private Config() {
        initializeProperties();
    }


    public static Config getINSTANCE() {
        if(INSTANCE == null) {
            INSTANCE = new Config();
        }
        return INSTANCE;
    }

    private void initializeProperties() {
        fileKeeper = FileKeeper.getTemporalFileKeeper();
        commandFactory = new CommandFactory();
    }

    public FileKeeper getFileKeeper() {
        return fileKeeper;
    }

    public CommandFactory getCommandFactory() {
        return commandFactory;
    }
}
