package covidtracker.dao;

import covidtracker.self.assesment.SelfAssesment;

import java.util.List;

public interface SelfAssesmentDao {
    void addSelfAssesment(String userId, SelfAssesment selfAssesment);
    List<SelfAssesment> getSelfAssesments(String userId);
}
