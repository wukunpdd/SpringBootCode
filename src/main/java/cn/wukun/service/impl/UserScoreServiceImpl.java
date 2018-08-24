package cn.wukun.service.impl;

import cn.wukun.business.UserScoreManager;
import cn.wukun.dao.UserScoreDao;
import cn.wukun.domain.UserScore;
import cn.wukun.dto.SearchCondition;
import cn.wukun.service.UserScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserScoreServiceImpl implements UserScoreService {

    @Autowired
    private UserScoreDao userScoreDao;
    @Autowired
    private UserScoreManager userScoreManager;

    public UserScore getUserScore(String userName){
        List<SearchCondition> searchConditionList = new ArrayList<>();
        searchConditionList.add(new SearchCondition("userName", userName));

        Specification<UserScore> specification = getWhereClause(searchConditionList);
        UserScore userScore = userScoreDao.findOne(specification).get();

        return userScore;
    }

    @Transactional
    public void insertUserScore(){
        UserScore userScore = userScoreManager.fillUserScore("wukun", "18368028156", 85);
        userScoreDao.save(userScore);

        userScore = userScoreManager.fillUserScore("fen", "13755919043", 99);
        userScoreDao.save(userScore);
    }

    private Specification<UserScore> getWhereClause(List<SearchCondition> searchConditionList){
        return new Specification<UserScore>() {
            @Override
            public Predicate toPredicate(Root<UserScore> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicateList = new ArrayList<>();

                for(SearchCondition searchCondition:searchConditionList) {
                    if (searchCondition.getType().equals("userName")) {
                        predicateList.add(criteriaBuilder.equal(root.get("userName"), searchCondition.getValue()));
                    }

                    if(searchCondition.getType().equals("userMobile")){
                        predicateList.add(criteriaBuilder.like(root.get("userMobile"), searchCondition.getValue()));
                    }

                    if(searchCondition.getType().equals("scoreValue")){
                        predicateList.add(criteriaBuilder.ge(root.get("scoreValue"), Integer.parseInt(searchCondition.getValue())));
                    }
                }

                Predicate[] pre = new Predicate[predicateList.size()];
                return criteriaQuery.where(predicateList.toArray(pre)).getRestriction();
            }
        };
    }
}
