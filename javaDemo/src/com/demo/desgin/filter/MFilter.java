package com.demo.desgin.filter;

import java.util.ArrayList;
import java.util.List;

public class MFilter {

    public static void main(String[] args) {
        String str = "大家好😊， <欢迎>, www.baidu.com. 123";
        Msg msg = new Msg();
        msg.setMsg(str);

        MFilterChain filterChain = new MFilterChain();
        filterChain.addFilter(new HTMLFilter()).addFilter(new FaceFilter());

        MFilterChain filterChain1 = new MFilterChain();
        filterChain1.addFilter(new HTTPSFilter()).addFilter(new NumberFilter());

        filterChain.addFilter(filterChain1);
        filterChain.doFilter(msg);
        System.out.println(" 过滤后的字符串："+ msg);

        //=============================

        Request request = new Request();
        request.str = "request";
        Response response = new Response();
        response.str = "response";

        FilterChain chain = new FilterChain();
        chain.addFilter(new HTMLFilter2()).addFilter(new FaceFilter2());
        chain.doFilter(request, response, chain);
    }
}


interface IFilter {
    void doFilter(Request request, Response response, FilterChain filterChain);
}
class Request {
    public String str;
}
class Response {
    public String str;
}
class FilterChain implements IFilter{
    int index = 0;
    List<IFilter> filterList = new ArrayList<>();
    public FilterChain addFilter(IFilter t) {
        if(filterList.contains(t)){
            return this;
        }
        filterList.add(t);
        return this;
    }
    public void deleteFilter(IFilter t){
        if(filterList == null || filterList.isEmpty()){
            return;
        }

        for (int i = 0; i < filterList.size(); i++) {
            if(filterList.get(i) == t){
                filterList.remove(i);
            }
        }
    }
    @Override
    public void doFilter(Request request, Response response, FilterChain filterChain){
        if(index>=filterList.size()) return;
        IFilter filter = filterList.get(index);
        index++;
        filter.doFilter(request, response, filterChain);
    }

}

class HTMLFilter2 implements IFilter{

    @Override
    public void doFilter(Request request, Response response, FilterChain filterChain) {
        System.out.println(" request---HTMLFilter2");
        filterChain.doFilter(request, response, filterChain);
        System.out.println(" response---HTMLFilter2");
    }
}

class FaceFilter2 implements IFilter {

    @Override
    public void doFilter(Request request, Response response, FilterChain filterChain) {
        System.out.println(" request---FaceFilter2");
        filterChain.doFilter(request, response, filterChain);
        System.out.println(" response---FaceFilter2");
    }
}

//================
class Msg {
    String msg;

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    @Override
    public String toString() {
        return "Msg{" +
                "msg='" + msg + '\'' +
                '}';
    }
}

interface IMFilter {
    void doFilter(Msg msg);
}

class MFilterChain implements IMFilter{
    List<IMFilter> filterList = new ArrayList<>();
    public MFilterChain addFilter(IMFilter t) {
        if(filterList.contains(t)){
            return this;
        }
        filterList.add(t);
        return this;
    }
    public void deleteFilter(IMFilter t){
        if(filterList == null || filterList.isEmpty()){
            return;
        }

        for (int i = 0; i < filterList.size(); i++) {
            if(filterList.get(i) == t){
                filterList.remove(i);
            }
        }
    }
    @Override
    public void doFilter(Msg msg){
        for (IMFilter filter: filterList) {
            filter.doFilter(msg);
        }
    }

}

//过滤HTML 字符
class HTMLFilter implements IMFilter{

    @Override
    public void doFilter(Msg msg) {
        String str = msg.getMsg();
        str = str.replace("<","[").replace(">","]");
        msg.setMsg(str);
    }
}

class HTTPSFilter implements IMFilter {

    @Override
    public void doFilter(Msg msg) {
        String str = msg.getMsg();
        str = str.replace("www.baidu.com","https://www.baidu.com");
        msg.setMsg(str);
    }
}

class FaceFilter implements IMFilter {

    @Override
    public void doFilter(Msg msg) {
        String str = msg.getMsg();
        str = str.replace("😊", ":)");
        msg.setMsg(str);
    }
}

class NumberFilter implements IMFilter {

    @Override
    public void doFilter(Msg msg) {
        String str = msg.getMsg();
        str = str.replace("123","一，二，三");
        msg.setMsg(str);
    }
}
