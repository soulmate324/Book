package com.example;

import java.sql.Connection;
import java.sql.DriverManager;

import org.junit.Test;

public class DB_Test {
	@Test
	public void test() throws Exception {
		Class.forName("org.mariadb.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mariadb://127.0.0.1:3306/book", "root", "anne1234");
		System.out.println(con);
	}
}
