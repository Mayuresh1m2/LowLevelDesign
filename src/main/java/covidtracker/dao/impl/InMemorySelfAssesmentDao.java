package covidtracker.dao.impl;

import covidtracker.dao.SelfAssesmentDao;
import covidtracker.self.assesment.SelfAssesment;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class InMemorySelfAssesmentDao implements SelfAssesmentDao {
    Map<String, List<SelfAssesment>> userSelfAssementMap;

    public InMemorySelfAssesmentDao() {
        userSelfAssementMap = new HashMap<>();
    }


    @Override
    public void addSelfAssesment(String userId, SelfAssesment selfAssesment) {
        List<SelfAssesment> assesments = userSelfAssementMap.getOrDefault(userId,
                new LinkedList<>());
        assesments.add(selfAssesment);
        userSelfAssementMap.put(userId, assesments);
    }

    @Override
    public List<SelfAssesment> getSelfAssesments(String userId) {
        return userSelfAssementMap.get(userId).stream().toList();
    }


}
