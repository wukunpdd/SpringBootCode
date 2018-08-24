package cn.wukun.service;

import cn.wukun.domain.UserScore;

public interface UserScoreService {
    UserScore getUserScore(String userName);

    void insertUserScore();
}
