package cn.wukun.business;

import cn.wukun.domain.UserScore;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.Date;

@Component
public class UserScoreManager {

    public UserScore fillUserScore(String userName, String userMobile, Integer scoreValue){
        UserScore userScore = new UserScore();

        userScore.setCreateTime(new Timestamp(new Date().getTime()));
        userScore.setScoreValue(scoreValue);
        userScore.setUserMobile(userMobile);
        userScore.setUserName(userName);

        return userScore;
    }
}
