package com.situ.student.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.situ.student.dao.IStudentDao;
import com.situ.student.entity.Student;
import com.situ.student.util.jdbcUtil;

public class StudentDaoMysqlImpl implements IStudentDao{

	@Override
	public boolean add(Student student){
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		connection = jdbcUtil.getConnection();
		boolean result = false;
		String sql = "insert into student(name,age,gender) values(?,?,?);";
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, student.getName());
			preparedStatement.setInt(2, student.getAge());
			preparedStatement.setString(3,student.getGender());
			preparedStatement.executeUpdate();
			result = true;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			jdbcUtil.close(connection, preparedStatement);
		}
		return result;
	}

	@Override
	public boolean delete(int id) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		connection = jdbcUtil.getConnection();
		boolean result = false;
		String sql = "delete from student where id=?;";
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1,id);
			preparedStatement.executeUpdate();
			result = true;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			jdbcUtil.close(connection, preparedStatement);
		}
		return result;
	}

	@Override
	public boolean update(Student student) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		connection = jdbcUtil.getConnection();
		boolean result = false;
		String sql = "update student set name=?,age=?,gender=? where id=?;";
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, student.getName());
			preparedStatement.setInt(2,student.getAge());
			preparedStatement.setString(3,student.getGender());
			preparedStatement.setInt(4, student.getId());
			preparedStatement.executeUpdate();
			result = true;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			jdbcUtil.close(connection, preparedStatement);
		}
		return result;
	}

	@Override
	public Student selectById(int id) {
		Student student = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		connection = jdbcUtil.getConnection();
		String sql = "select * from student where id=?;";
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1,id);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				int studentId = resultSet.getInt("id");
				String name = resultSet.getString("name");
				int age = resultSet.getInt("age");
				String gender = resultSet.getString("gender");
				student = new Student(studentId, name, age, gender);		
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			jdbcUtil.close(connection, preparedStatement, resultSet);
		}
		return student;
	}

	@Override
	public List<Student> selectAll() {
		List<Student> list = new ArrayList<Student>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		connection = jdbcUtil.getConnection();
		String sql = "select * from student;";
		try {
			preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				int studentId = resultSet.getInt("id");
				String name =  resultSet.getString("name");
				int age = resultSet.getInt("age");
				String gender = resultSet.getString("gender");
				list.add(new Student(studentId, name, age, gender));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			jdbcUtil.close(connection, preparedStatement, resultSet);
		}
		return list;
	}

	@Override
	public boolean checkName(String name) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		connection = jdbcUtil.getConnection();
		boolean result = false;
		String sql = "select * from student where name=?;";
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, name);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				result = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

}
