package cn.wukun.service;

import cn.wukun.domain.User;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    public User getUser(){
        return new User(11, "吴堃", "1234");
    }
}
