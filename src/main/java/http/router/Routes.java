package http.router;

import http.method.httpMethod;

import java.util.*;

public class Routes {

    public static final Map<String, ArrayList<httpMethod>> VALID_PATHS = paths();

    private static Map<String, ArrayList<httpMethod>> paths() {
        Map<String, ArrayList<httpMethod>> paths = new HashMap<>();
        paths.put("/", new ArrayList<>(Arrays.asList(httpMethod.GET)));
        paths.put("/put-target", new ArrayList<>(Arrays.asList(httpMethod.PUT)));
        paths.put("/form", new ArrayList<>(Arrays.asList(httpMethod.POST)));
        paths.put("/redirect", new ArrayList<>(Arrays.asList(httpMethod.GET)));
        paths.put("/coffee", new ArrayList<>(Arrays.asList(httpMethod.GET)));
        paths.put("/tea", new ArrayList<>(Arrays.asList(httpMethod.GET)));
        paths.put("/method_options", new ArrayList<>(Arrays.asList(httpMethod.GET, httpMethod.HEAD, httpMethod.POST, httpMethod.OPTIONS, httpMethod.PUT)));
        paths.put("/method_options2", new ArrayList<>(Arrays.asList(httpMethod.GET, httpMethod.OPTIONS, httpMethod.HEAD)));
        return paths;
    }
}
