package cn.wukun.web;

import cn.wukun.domain.Achievement;
import cn.wukun.service.AchievementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

/**
 * 增改查测试
 * 三种参数的获取方式
 */
@RestController  
@RequestMapping("/achievement") 
public class AchievementController {

	@Autowired
	private AchievementService achievementService;
	
	@RequestMapping("/insert")
	public String insertData(){
		achievementService.insertData();
		return "插入数据完成";
	}

	/**
	 * @RequestParam 获取参数
	 */
	@RequestMapping("/data")
	public Page<Achievement> data(@RequestParam("pageNo") Integer pageNo, @RequestParam("pageSize") Integer pageSize){
		return achievementService.getPagedAchievement(pageNo, pageSize);
	}

	/**
	 * @RequestBody 获取对象参数
	 */
	@RequestMapping("/saveAchievement")
	public String saveAchievement(@RequestBody Achievement achievement){
		System.out.println("achievement:"+achievement);
		achievementService.saveOrUpdate(achievement);
		return "success";
	}

	/**
	 * @PathVariable 获取参数
	 */
	@RequestMapping("/test/{name}/{password}")
	public String testPathVariable(@PathVariable String name, @PathVariable String password){
		return "name:"+name+",password:"+password;
	}

	@RequestMapping("/getAchievementById")
	public Achievement getAchievementById(@RequestParam("id") Integer id){
		return achievementService.getAchievementById(id);
	}
}
