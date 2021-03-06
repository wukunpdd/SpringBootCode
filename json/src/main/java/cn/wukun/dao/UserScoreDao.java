package cn.wukun.dao;

import cn.wukun.domian.UserScore;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserScoreDao extends JpaRepository<UserScore, Integer> {

    UserScore findOneByUserId(int userId);
}
