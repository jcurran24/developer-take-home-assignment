package com.cappex.dao;

import java.util.List;
import java.util.LinkedList;

import org.h2.tools.DeleteDbFiles;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;

/**
 * Created by jeremycurran on 2/10/16.
 */
public class CollegeSearchDao {

    private final static String DATABASE_DIRECTORY = "./";
    private Connection theConnection = null;

    public CollegeSearchDao() {
        try {
            theConnection = getConnection();
            initializeDataBase();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    private static Connection getConnection() throws SQLException, ClassNotFoundException {
        DeleteDbFiles.execute(DATABASE_DIRECTORY, "cappex-test-database", true);

        Class.forName("org.h2.Driver");
        Connection connection = DriverManager.getConnection("jdbc:h2:" + DATABASE_DIRECTORY + "cappex-test-database");
        return connection;
    }

    public List<College> queryColleges(String searchNameSubset) throws SQLException, ClassNotFoundException {
        List<College> searchResults = new LinkedList<College>();
        College college = null;

        try (Statement statement = theConnection.createStatement()) {

            ResultSet resultSet = statement.executeQuery("SELECT * FROM colleges WHERE name LIKE '%" + searchNameSubset + "%';");

            while (resultSet.next()) {
                college = new College();
                college.setId(resultSet.getInt("id"));
                college.setName(resultSet.getString("name"));
                college.setCity(resultSet.getString("city"));
                college.setState(resultSet.getString("state"));
                college.setZip(resultSet.getString("zip") );
                college.setPopularityLevel(resultSet.getInt("popularity_level"));
                searchResults.add(college);
            }
        }
        return searchResults;
    }



    private void initializeDataBase() throws Exception {
        try(Statement statement = theConnection.createStatement()) {
            statement.execute("CREATE TABLE colleges(id INT AUTO_INCREMENT PRIMARY KEY, name VARCHAR(255), city VARCHAR(255), state VARCHAR(2), zip CHAR(5), popularity_level INT)");
            statement.execute("INSERT INTO colleges(name, city, state, zip, popularity_level) values('University of Illinois at Chicago', 'Chicago', 'IL', '60607', 4)");
            statement.execute("INSERT INTO colleges(name, city, state, zip, popularity_level) values('Northern Illinois University', 'DeKalb', 'IL', '60115', 2)");
            statement.execute("INSERT INTO colleges(name, city, state, zip, popularity_level) values('Lake Forest College', 'Lake Forest', 'IL', '60045', 3)");
            statement.execute("INSERT INTO colleges(name, city, state, zip, popularity_level) values('Anderson University', 'Anderson', 'IN', '46012', 1)");

            statement.execute("CREATE TABLE majors(id INT AUTO_INCREMENT PRIMARY KEY, name VARCHAR(255))");
            statement.execute("INSERT INTO majors(name) values('Computer Science')");
            statement.execute("INSERT INTO majors(name) values('Business Administration & Management')");
            statement.execute("INSERT INTO majors(name) values('Biology')");
            statement.execute("INSERT INTO majors(name) values('Criminal Justice')");
            statement.execute("INSERT INTO majors(name) values('Accounting')");
            statement.execute("INSERT INTO majors(name) values('History')");
            statement.execute("INSERT INTO majors(name) values('Political Science')");

            statement.execute("CREATE TABLE college_majors(id INT AUTO_INCREMENT PRIMARY KEY, college_id INT, major_id INT)");
            statement.execute("INSERT INTO college_majors(college_id, major_id) values(1, 1)");
            statement.execute("INSERT INTO college_majors(college_id, major_id) values(1, 3)");
            statement.execute("INSERT INTO college_majors(college_id, major_id) values(1, 4)");
            statement.execute("INSERT INTO college_majors(college_id, major_id) values(1, 5)");
            statement.execute("INSERT INTO college_majors(college_id, major_id) values(2, 2)");
            statement.execute("INSERT INTO college_majors(college_id, major_id) values(2, 3)");
            statement.execute("INSERT INTO college_majors(college_id, major_id) values(2, 6)");
            statement.execute("INSERT INTO college_majors(college_id, major_id) values(3, 1)");
            statement.execute("INSERT INTO college_majors(college_id, major_id) values(3, 2)");
            statement.execute("INSERT INTO college_majors(college_id, major_id) values(3, 3)");
            statement.execute("INSERT INTO college_majors(college_id, major_id) values(3, 4)");
            statement.execute("INSERT INTO college_majors(college_id, major_id) values(4, 2)");
            statement.execute("INSERT INTO college_majors(college_id, major_id) values(4, 3)");
            statement.execute("INSERT INTO college_majors(college_id, major_id) values(4, 5)");
            statement.execute("INSERT INTO college_majors(college_id, major_id) values(4, 6)");
            statement.execute("INSERT INTO college_majors(college_id, major_id) values(4, 7)");
        }
    }

}
