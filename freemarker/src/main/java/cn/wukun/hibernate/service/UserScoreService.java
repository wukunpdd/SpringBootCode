package cn.wukun.hibernate.service;

import java.util.List;
import org.springframework.data.domain.Page;

import cn.wukun.hibernate.domain.UserScore;

public interface UserScoreService{
    List<UserScore> getUserScoreList();

    Page<UserScore> getPageUserScore(int pageNo, int pageSize);

    UserScore insertUserScore(UserScore userScore);

    UserScore updateUserScore(UserScore userScore);
}