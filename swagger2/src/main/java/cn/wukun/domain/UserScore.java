package cn.wukun.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "user_score")
@ApiModel("用户积分类")
public class UserScore implements IEntity{

    private static final long serialVersionUID = 1L;

    /**
     * JPA提供的四种标准用法为TABLE,SEQUENCE,IDENTITY,AUTO.
     * TABLE：使用一个特定的数据库表格来保存主键。
     * SEQUENCE：根据底层数据库的序列来生成主键，条件是数据库支持序列。
     * IDENTITY：主键由数据库自动生成（主要是自动增长型）
     * AUTO：主键由程序控制。
     */
    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    @ApiModelProperty("主键Id")
    private Integer userId;

    @Column(name = "user_name")
    @ApiModelProperty("姓名")
    private String userName;

    @Column(name = "user_mobile")
    @ApiModelProperty("手机号")
    private String userMobile;

    @Column(name = "score_value")
    @ApiModelProperty("积分值")
    private Integer scoreValue;

    @Column(name = "create_time")
    @ApiModelProperty("创建时间")
    private Timestamp createTime;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserMobile() {
        return userMobile;
    }

    public void setUserMobile(String userMobile) {
        this.userMobile = userMobile;
    }

    public Integer getScoreValue() {
        return scoreValue;
    }

    public void setScoreValue(Integer scoreValue) {
        this.scoreValue = scoreValue;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }
}
