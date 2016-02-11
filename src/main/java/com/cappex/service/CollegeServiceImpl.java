package com.cappex.service;

import com.cappex.dao.College;
import com.cappex.dao.CollegeSearchDao;

import java.util.List;


/**
 * Created by jeremycurran on 2/10/16.
 */
public class CollegeServiceImpl implements CollegeService {
    private CollegeSearchDao collegeSearchDao = new CollegeSearchDao();

    @Override
    public List<College> searchCollege(String name) throws Exception {
        return collegeSearchDao.queryColleges(name);
    }
}
