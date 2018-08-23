package cn.wukun.service;

import cn.wukun.domain.Achievement;
import org.springframework.data.domain.Page;

public interface AchievementService {

    //插入数据
    public void insertData();

    //分页查询数据
    Page<Achievement> getPagedAchievement(int pageNo, int pageSize);

    //更新或者新增数据
    public void saveOrUpdate(Achievement achievement);

    //根据id获取数据
    Achievement getAchievementById(int id);
}
