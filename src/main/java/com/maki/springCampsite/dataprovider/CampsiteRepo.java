package com.maki.springCampsite.dataprovider;

import com.maki.springCampsite.domain.Campsite;
import com.maki.springCampsite.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CampsiteRepo extends MongoRepository<Campsite, String> {
}
