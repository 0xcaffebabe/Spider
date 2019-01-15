package wang.ismy.spider.enums;

public enum RequestMethods {


    GET("GET"),POST("POST"),DELETE("DELETE"),HEAD("HEAD"),OPTION("OPTION");
    private String method;

    RequestMethods(String method) {
        this.method = method;
    }

    public String getMethod() {
        return method;
    }
}
