package cn.wukun.dao;

import cn.wukun.domain.UserScore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface UserScoreDao extends JpaRepository<UserScore, Integer>,JpaSpecificationExecutor<UserScore> {

}
