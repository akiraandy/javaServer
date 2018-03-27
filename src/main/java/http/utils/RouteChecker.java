package http.utils;

import http.IO.file.FileIO;
import http.method.httpMethod;

import static http.router.Routes.VALID_PATHS;

public class RouteChecker {

    public static boolean validRoute(String path) {
        return VALID_PATHS.containsKey(path);
    }
    public static boolean writePermitted(String path) {
        return VALID_PATHS.get(path).contains(httpMethod.POST);
    }
    public static boolean updatePermitted(String path) {
        return VALID_PATHS.get(path).contains(httpMethod.PUT);
    }
    public static boolean deletePermitted(String path) {
        return VALID_PATHS.get(path).contains(httpMethod.DELETE);
    }
    public static boolean validAction(String path, httpMethod method) {
        try {
            return VALID_PATHS.get(path).contains(method);
        } catch(Exception e) {
            return false;
        }
    }
}