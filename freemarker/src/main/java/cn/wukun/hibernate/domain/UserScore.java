package cn.wukun.hibernate.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
  
@Entity
@Table(name="user_score")
public class UserScore implements IEntity<Integer>{
	private static final long serialVersionUID = 1L;
	 
  	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "user_id",length = 11)
    private Integer userId;

    @Column(name = "user_name",length = 255)
    private String userName;

    @Column(name = "create_time",length = 19)
    private java.sql.Timestamp createTime;

    @Column(name = "user_mobile",length = 255)
    private String userMobile;

    @Column(name = "score_value",length = 11)
    private Integer scoreValue;

    public Integer getUserId(){  
      return userId;  
    }
      
    public void setUserId(Integer userId){  
      this.userId = userId;  
    }

    public String getUserName(){  
      return userName;  
    }
      
    public void setUserName(String userName){  
      this.userName = userName;  
    }

    public java.sql.Timestamp getCreateTime(){  
      return createTime;  
    }
      
    public void setCreateTime(java.sql.Timestamp createTime){  
      this.createTime = createTime;  
    }

    public String getUserMobile(){  
      return userMobile;  
    }
      
    public void setUserMobile(String userMobile){  
      this.userMobile = userMobile;  
    }

    public Integer getScoreValue(){  
      return scoreValue;  
    }
      
    public void setScoreValue(Integer scoreValue){  
      this.scoreValue = scoreValue;  
    }

}