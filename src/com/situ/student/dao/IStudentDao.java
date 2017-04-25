package com.situ.student.dao;

import java.util.List;

import com.situ.student.entity.Student;

public interface IStudentDao {
	
	public abstract boolean add(Student student);
	
	public abstract boolean delete(int id);
	
	public abstract boolean update(Student student);

	public abstract Student selectById(int id);
	
	public abstract List<Student> selectAll();
	
	public abstract boolean checkName(String name);
}
