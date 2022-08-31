package http_server;

import com.sun.net.httpserver.HttpExchange;
import http_server.http_comands.HttpMethodHandler;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class CommandFactory {

    public HttpMethodHandler getHttpMethodHandler(String methodType, HttpExchange ex, String name, String fileBody) {
        String className = getMethodName(methodType);

        Class[] classes = {HttpExchange.class, String.class, String.class};
        Object[] arguments = {ex, name, fileBody};
        Class clazz;
        Constructor con;
        Object ob = null;
        try {
            clazz = Class.forName(className);
            con = clazz.getConstructor(classes);
            ob = con.newInstance(arguments);
        } catch (ClassNotFoundException | NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return (HttpMethodHandler) ob;

    }

    private String getMethodName(String methodType) {
        String httpMethodType = methodType.toLowerCase();
        httpMethodType = httpMethodType.substring(0, 1).toUpperCase() + httpMethodType.substring(1);
        String className = "http_server.http_comands." + httpMethodType + "MethodHandler";
        return className;
    }
}
