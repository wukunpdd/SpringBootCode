package cn.wukun.service;

import cn.wukun.domain.User;
import cn.wukun.event.MyEvent;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserService {

    @Resource
    private ApplicationContext applicationContext;

    public User getUser(){
        return new User(11, "吴堃", "1234");
    }

    /**
     * 发布事件
     * @return
     */
    public User getUser2() {
        User user = new User(1, "倪升武", "123456");
        // 发布事件
        MyEvent event = new MyEvent(this, user);
        applicationContext.publishEvent(event);
        return user;
    }
}
