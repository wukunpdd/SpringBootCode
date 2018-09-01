package cn.wukun.dao;

import cn.wukun.domain.UserScore;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface UserScoreMapper {
    UserScore getUserScoreByUserName(String userName);

    @Select("select * from user_score where user_id = #{userId}")
    UserScore getUserScoreByUserId(@Param("userId") Integer userId);
}
