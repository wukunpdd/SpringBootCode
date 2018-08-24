package cn.wukun.web;

import cn.wukun.domain.UserScore;
import cn.wukun.service.UserScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/userScore")
public class UserScoreController {

    @Autowired
    private UserScoreService userScoreService;

    @RequestMapping("/getUserScore")
    public UserScore getUserScore(@RequestParam String userName) {
        return userScoreService.getUserScore(userName);
    }

    @RequestMapping("/insertUserScore")
    public void insertUserScore(){
        userScoreService.insertUserScore();
    }

}
