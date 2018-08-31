package cn.wukun.web;

import cn.wukun.dao.UserScoreDao;
import cn.wukun.domain.JsonResult;
import cn.wukun.domain.UserScore;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.Date;

@RestController
@RequestMapping("/userScore")
@Api("用户积分接口")
public class UserScoreController {

    @Autowired
    private UserScoreDao userScoreDao;

    @RequestMapping(value = "/getUserScore", method = RequestMethod.POST)
    @ApiOperation("根据用户Id获取用户积分信息")
    public JsonResult<UserScore> getUserScore(@RequestParam("userId") @ApiParam("用户Id") Integer userId){
        UserScore userScore = userScoreDao.findOneByUserId(userId);
        return new JsonResult<>(userScore);
    }

    @RequestMapping(value = "/insertUserSore", method = RequestMethod.POST)
    @ApiOperation("插入数据")
    public JsonResult<Void> insertUserScore(@RequestBody @ApiParam("待插入数据") UserScore userScore){
        userScore.setCreateTime(new Timestamp(new Date().getTime()));
        userScoreDao.save(userScore);

        return new JsonResult<>();
    }
}
