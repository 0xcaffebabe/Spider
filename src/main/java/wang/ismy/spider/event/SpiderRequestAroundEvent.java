package wang.ismy.spider.event;

/*
* 蜘蛛请求环绕事件，跟AOP中的环绕通知一样
*/
public interface SpiderRequestAroundEvent {

    void around();

}
