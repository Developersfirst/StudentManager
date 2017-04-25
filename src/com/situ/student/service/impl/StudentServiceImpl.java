package com.situ.student.service.impl;

import java.util.List;

import com.situ.student.dao.IStudentDao;
import com.situ.student.dao.impl.StudentDaoMysqlImpl;
import com.situ.student.entity.Student;
import com.situ.student.service.IStudentService;
import com.situ.student.service.exception.NameRepeatException;

public class StudentServiceImpl implements IStudentService {
	//重点！连接起来两个接口。声明接口，实际new的是其接口的实现类。
	private IStudentDao iStudentDao = new StudentDaoMysqlImpl();

	@Override
	public boolean add(Student student) throws NameRepeatException {
		if (iStudentDao.checkName(student.getName())) {
			throw new NameRepeatException("姓名已经存在");
		} else {
			return iStudentDao.add(student);
		}
	}

	@Override
	public boolean delete(int id) {
		return iStudentDao.delete(id);
	}

	@Override
	public boolean update(Student student) {
		return iStudentDao.update(student);
	}

	@Override
	public Student selectById(int id) {
		return iStudentDao.selectById(id);
	}

	@Override
	public List<Student> selectAll() {
		return iStudentDao.selectAll();
	}

}
