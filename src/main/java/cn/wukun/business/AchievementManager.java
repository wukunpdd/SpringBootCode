package cn.wukun.business;

import cn.wukun.domain.Achievement;
import org.springframework.stereotype.Component;

@Component
public class AchievementManager {

    public Achievement fillAchievement(int number, String name, String subject, int grade, int isQualified){
        Achievement achievement = new Achievement();

        achievement.setNumber(number);
        achievement.setGrade(grade);
        achievement.setIsQualified(isQualified);
        achievement.setSubject(subject);
        achievement.setName(name);

        return achievement;
    }
}
