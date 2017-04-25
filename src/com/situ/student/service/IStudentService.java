package com.situ.student.service;

import java.util.List;

import com.situ.student.entity.Student;
import com.situ.student.service.exception.NameRepeatException;

public interface IStudentService{
	
	public abstract boolean add(Student student) throws NameRepeatException;
	
	public abstract boolean delete(int id);
	
	public abstract boolean update(Student student);
	
	public abstract Student selectById(int id);
	
	public abstract List<Student> selectAll();

}
