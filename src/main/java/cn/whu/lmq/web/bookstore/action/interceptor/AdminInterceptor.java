package cn.whu.lmq.web.bookstore.action.interceptor;

import cn.whu.lmq.web.bookstore.bean.Admin;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component("adminInterceptor")
@Scope("prototype")
public class AdminInterceptor extends AbstractInterceptor {
    /**
     * 拦截管理员未登录
     *
     * @param invocation 被拦截的action动作
     */
    @Override
    public String intercept(ActionInvocation invocation) throws Exception{
        Object admin = ActionContext.getContext().getSession().get("admin");
        if(!(admin instanceof Admin)){
            return "login";
        }
        return invocation.invoke();
    }
}
