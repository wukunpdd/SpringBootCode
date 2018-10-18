package cn.wukun.hibernate.service.impl;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import cn.wukun.hibernate.domain.UserScore;
import cn.wukun.hibernate.service.UserScoreService;
import cn.wukun.hibernate.dao.UserScoreDao;

@Service
public class UserScoreServiceImpl implements UserScoreService{

    @Autowired
    private UserScoreDao userScoreDao;

    public List<UserScore> getUserScoreList(){
        return userScoreDao.findAll();
    }

    public Page<UserScore> getPageUserScore(int pageNo, int pageSize){
        Pageable pageable = new PageRequest(pageNo, pageSize);
        return userScoreDao.findAll(pageable);
    }

    public UserScore insertUserScore(UserScore userScore){
        return userScoreDao.saveAndFlush(userScore);
    }

    public UserScore updateUserScore(UserScore userScore){
        return userScoreDao.saveAndFlush(userScore);
    }
}