package com.cappex.service;

import java.util.List;

import com.cappex.dao.College;

/**
 * Created by jeremycurran on 2/10/16.
 */
public interface CollegeService {

    public List<College> searchCollege(String name) throws Exception;
}
