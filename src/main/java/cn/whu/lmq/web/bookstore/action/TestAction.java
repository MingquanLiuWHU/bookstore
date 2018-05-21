package cn.whu.lmq.web.bookstore.action;

import com.opensymphony.xwork2.ActionSupport;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.util.HashMap;
import java.util.Map;

@Controller("testAction")
@Scope("prototype")
public class TestAction extends ActionSupport {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private Map<Integer,Integer>  map = new HashMap<>();

    public Map<Integer, Integer> getMap() {
        return map;
    }

    public void setMap(Map<Integer, Integer> map) {
        this.map = map;
    }

    public String test() {
        System.out.println("进入测试函数");
        map.forEach((k,v)->{
            System.out.print("Key:" + k +",");
            System.out.println("Value:"+v);
        });
        return SUCCESS;
    }
}
