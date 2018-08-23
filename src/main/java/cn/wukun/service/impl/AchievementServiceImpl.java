package cn.wukun.service.impl;

import cn.wukun.business.AchievementManager;
import cn.wukun.dao.AchievementDao;
import cn.wukun.domain.Achievement;
import cn.wukun.service.AchievementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AchievementServiceImpl implements AchievementService {

    @Autowired
    private AchievementDao achievementDao;
    @Autowired
    private AchievementManager achievementManager;

    @Transactional
    public void insertData() {
        Achievement achievement = achievementManager.fillAchievement(1,"Tom","Math",85,0);
        achievementDao.save(achievement);

        achievement = achievementManager.fillAchievement(2,"Jerry","Chinese",105,1);
        achievementDao.save(achievement);

        achievement = achievementManager.fillAchievement(3,"Tom","Chinese",85,0);
        achievementDao.save(achievement);

        achievement = achievementManager.fillAchievement(4,"Jerry","Chinese",105,1);
        achievementDao.save(achievement);

        achievement = achievementManager.fillAchievement(5,"Tom","Chinese",85,0);
        achievementDao.save(achievement);

        achievement = achievementManager.fillAchievement(6,"Jerry","Chinese",105,1);
        achievementDao.save(achievement);

        achievement = achievementManager.fillAchievement(5,"Tom","Chinese",85,0);
        achievementDao.save(achievement);

        achievement = achievementManager.fillAchievement(8,"Jerry","Chinese",105,1);
        achievementDao.save(achievement);

        achievement = achievementManager.fillAchievement(5,"Tom","Chinese",85,0);
        achievementDao.save(achievement);

        achievement = achievementManager.fillAchievement(10,"Jerry","Chinese",105,1);
        achievementDao.save(achievement);
    }

    @Transactional
    public Page<Achievement> getPagedAchievement(int pageNo, int pageSize) {
        Sort sort = new Sort(Sort.Direction.ASC, "number");
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);

        return achievementDao.findAll(pageable);
    }

    @Transactional
    public void saveOrUpdate(Achievement achievement){
        achievementDao.save(achievement);
    }

    public Achievement getAchievementById(int id){
        return achievementDao.findOneById(id);
    }
}
