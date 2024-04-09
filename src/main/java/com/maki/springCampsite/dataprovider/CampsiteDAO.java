package com.maki.springCampsite.dataprovider;

import com.maki.springCampsite.domain.Campsite;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Repository
public class CampsiteDAO {
    private final MongoTemplate mongoTemplate;

    public CampsiteDAO(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    public List<Campsite> findByCriteria(Map<String, String> paramMap) {
        Query query = new Query();
        Set<String> keySet = paramMap.keySet();
        for (String key : keySet) {
            Criteria criteria = Criteria.where(key).regex(".*" + paramMap.get(key) + ".*", "i");
            query.addCriteria(criteria);
        }
        return mongoTemplate.find(query, Campsite.class);
    }
}
