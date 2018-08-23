package cn.wukun.dao;

import cn.wukun.domain.Achievement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AchievementDao extends JpaRepository<Achievement, Integer> {

    Achievement findOneById(int id);
}
