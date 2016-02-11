package com.cappex;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.eclipse.jetty.annotations.AnnotationConfiguration;
import org.eclipse.jetty.plus.webapp.EnvConfiguration;
import org.eclipse.jetty.plus.webapp.PlusConfiguration;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.*;
import org.h2.tools.DeleteDbFiles;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * This class launches the web application in an embedded Jetty container.
 * This is the entry point to your application. The Java command that is used for
 * launching should fire this main method.
 */
public class Main {

    private final static String DATABASE_DIRECTORY = "./";

    /**
     * Starts Jetty application server & initializes the database.
     *
     * NOTE: DO NOT MODIFY THIS METHOD
     */
    public static void main(String[] args) throws Exception {
        DeleteDbFiles.execute(DATABASE_DIRECTORY, "cappex-test-database", true);

        Class.forName("org.h2.Driver");

		try (Connection connection = DriverManager.getConnection("jdbc:h2:" + DATABASE_DIRECTORY + "cappex-test-database");
			 Statement statement = connection.createStatement()) {


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

			ResultSet resultSet = statement.executeQuery("SELECT id, name, city, state, zip, popularity_level FROM colleges");
			System.out.println("Printing out contents of the colleges table:");
			while (resultSet.next()) {
				System.out.println("id: " + resultSet.getInt("id"));
				System.out.println("name: " + resultSet.getString("name"));
				System.out.println("city: " + resultSet.getString("city"));
				System.out.println("state: " + resultSet.getString("state"));
				System.out.println("zip: " + resultSet.getString("zip"));
				System.out.println("popularity_level: " + resultSet.getInt("popularity_level") + "\n");
			}

			resultSet = statement.executeQuery("SELECT id, name FROM majors");
			System.out.println("\nPrinting out contents of the majors table:");
			while (resultSet.next()) {
				System.out.println("id: " + resultSet.getInt("id"));
				System.out.println("name: " + resultSet.getString("name") + "\n");
			}

			resultSet = statement.executeQuery("SELECT id, college_id, major_id FROM college_majors");
			System.out.println("\nPrinting out contents of the college_majors table:");
			while (resultSet.next()) {
				System.out.println("id: " + resultSet.getInt("id"));
				System.out.println("college_id: " + resultSet.getString("college_id"));
				System.out.println("major_id: " + resultSet.getString("major_id") + "\n");
			}
		} catch(Exception e) {
			e.printStackTrace();
		}

        String webPort = System.getenv("PORT");
        if (StringUtils.isBlank(webPort)) {
            webPort = "8080";
        }
        Server server = new Server(NumberUtils.toInt(webPort));
        String warDir = "target/developer-take-home-assignment-1.0-SNAPSHOT";

        WebAppContext context = new WebAppContext();
        context.setInitParameter("org.eclipse.jetty.servlet.Default.dirAllowed", "false");
        context.setResourceBase(warDir);
        context.setDescriptor(warDir + "WEB-INF/web.xml");
        context.setConfigurations(new Configuration[]{
                new AnnotationConfiguration(),
                new WebXmlConfiguration(),
                new WebInfConfiguration(),
                new TagLibConfiguration(),
                new PlusConfiguration(),
                new MetaInfConfiguration(),
                new FragmentConfiguration(),
                new EnvConfiguration()});

        // Specify the context path that you want this webapp to show up as
        context.setContextPath("/");
        context.setParentLoaderPriority(true);
        server.setHandler(context);
        server.start();
        server.join();
    }
}