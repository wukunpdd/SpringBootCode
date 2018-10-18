package cn.wukun.hibernate.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

import cn.wukun.hibernate.domain.UserScore;

public interface UserScoreDao extends JpaRepository<UserScore,Integer>{

    List<UserScore> findByUserName(String userName);

    UserScore findOneByUserName(String userName);

    List<UserScore> findByCreateTime(java.sql.Timestamp createTime);

    UserScore findOneByCreateTime(java.sql.Timestamp createTime);

    List<UserScore> findByUserMobile(String userMobile);

    UserScore findOneByUserMobile(String userMobile);

    List<UserScore> findByScoreValue(Integer scoreValue);

    UserScore findOneByScoreValue(Integer scoreValue);

}