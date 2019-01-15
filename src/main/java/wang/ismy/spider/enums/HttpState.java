package wang.ismy.spider.enums;

public enum HttpState {
    OK_200(200,"OK"),NOT_FOUND_404(404,"Not Found"),MOVED_TEMP(302,"Moved Temp");

    public int getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    private int code;

    private String desc;


    HttpState(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
