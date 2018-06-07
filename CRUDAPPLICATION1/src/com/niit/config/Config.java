package com.niit.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class Config {

	public static Connection getConnection()
	{
		Connection connection = null;
		try {
			/*FileInputStream fis=new FileInputStream("db.properties");
			Properties prop=new Properties();
			prop.load(fis);
			*/
			Class.forName("org.h2.Driver");
			//connection=DriverManager.getConnection(prop.getProperty("url"),prop.getProperty("username"),prop.getProperty("password"));
			connection = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/test","sa","");
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		return connection;
	}
}
