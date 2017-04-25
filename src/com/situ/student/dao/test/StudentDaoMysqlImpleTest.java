package com.situ.student.dao.test;

import java.util.List;

import org.junit.Test;

import com.situ.student.dao.IStudentDao;
import com.situ.student.dao.impl.StudentDaoMysqlImpl;
import com.situ.student.entity.Student;

public class StudentDaoMysqlImpleTest {

	@Test
	public void testadd() {
		Student student = new Student("小明", 25, "女");
		IStudentDao iStudentDao = new StudentDaoMysqlImpl();
		boolean result = iStudentDao.add(student);
		if (result) {
			System.out.println("添加成功");
		} else {
			System.out.println("添加失败");
		}
	}

	@Test
	public void testdelete() {
		IStudentDao iStudentDao = new StudentDaoMysqlImpl();
		boolean result = iStudentDao.delete(5);
		if (result) {
			System.out.println("删除成功");
		} else {
			System.out.println("删除失败");
		}
	}

	@Test
	public void testupdate() {
		Student student = new Student(4, "佳佳", 18, "女");
		IStudentDao iStudentDao = new StudentDaoMysqlImpl();
		boolean result = iStudentDao.update(student);
		if (result) {
			System.out.println("修改成功");
		} else {
			System.out.println("修改失败");
		}
	}

	@Test
	public void testselectById() {
		Student student = null;
		IStudentDao iStudentDao = new StudentDaoMysqlImpl();
		student = iStudentDao.selectById(4);
		if (student != null) {
			System.out.println(student);		
		} else {
			System.out.println("查询失败");
		}
	}
	
	@Test
	public void testseleceAll() {
		List<Student> list = null;
		IStudentDao iStudentDao = new StudentDaoMysqlImpl();
		list = iStudentDao.selectAll();
		if (list != null) {
			for (Student student : list) {
				System.out.println(student);
			}
		} else {
			System.out.println("查询失败");
		}
	}
}
