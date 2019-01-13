package wang.ismy.spider;

public class WebNotFoundProcessChain implements ResponseProcessChain {
    @Override
    public void process(Response response) {
        if (response.getHttpCode() == 404){
            System.out.println("该网页无法找到");
        }
    }
}
