package com.situ.student.view;

import java.util.List;
import java.util.Scanner;

import com.situ.student.entity.Student;
import com.situ.student.service.IStudentService;
import com.situ.student.service.exception.NameRepeatException;
import com.situ.student.service.impl.StudentServiceImpl;

public class StudentView {

	private Scanner scanner = null;
	private IStudentService iStudentService = null;

	public static void main(String[] args) {
		StudentView studentView = new StudentView();
		studentView.init();
		studentView.showView();
	}

	private void init() {
		scanner = new Scanner(System.in);
		iStudentService = new StudentServiceImpl();
	}

	private void showView() {
		while (true) {
			System.out.println("-------学生信息管理系统-------");
			System.out.println("0-退出");
			System.out.println("1-添加学生信息");
			System.out.println("2-删除学生信息");
			System.out.println("3-修改学生信息");
			System.out.println("4-查询具体学生信息");
			System.out.println("5-展示全部学生信息");
			System.out.println("请选择：");
			int num = scanner.nextInt();
			if (num == 0) {
				System.out.println("退出！");
				break;
			}
			switch (num) {
			case 1:
				addStudent();
				break;
			case 2:
				deleteStudent();
				break;
			case 3:
				updateStudent();
				break;
			case 4:
				selectStudent();
				break;
			case 5:
				showStudentInfos();
				break;
			default:
				break;
			}
		}

	}

	/**
	 * 展示所有学生信息
	 */
	private void showStudentInfos() {
		System.out.println("----所有学生信息----");
		List<Student> list = iStudentService.selectAll();
		for (Student student : list) {
			System.out.println(student);
		}
	}

	/**
	 * 查询具体学生信息
	 */
	private void selectStudent() {
		System.out.println("请输入要查询的id");
		int id = scanner.nextInt();
		Student student = iStudentService.selectById(id);
		if (student != null) {
			System.out.println(student);
		} else {
			System.out.println("查无此人");
		}

	}

	/**
	 * 修改学生信息
	 */
	private void updateStudent() {
		showStudentInfos();
		System.out.println("请输入要修改的学生的id");
		int id = scanner.nextInt();
		Student student = iStudentService.selectById(id);
		System.out.println("修改姓名-1，年龄-2，性别-3");
		int num = scanner.nextInt();
		switch (num) {
		case 1:
			System.out.println("请输入更改后的姓名：");
			String name = scanner.next();
			student.setName(name);
			break;
		case 2:
			System.out.println("请输入更改后的年龄：");
			int age = scanner.nextInt();
			student.setAge(age);
			break;
		case 3:
			System.out.println("请输入更改后的性别");
			String gender = scanner.next();
			student.setGender(gender);
			break;
		default:
			break;
		}
		//注意此处修改完学生信息后，要让其执行到数据库以覆盖原有数据库中的学生信息，否则无法完成更改。
		boolean result = iStudentService.update(student);
		if (result) {
			System.out.println("修改成功");
		} else {
			System.out.println("修改失败");
		}
	}

	/**
	 * 删除学生信息
	 */
	private void deleteStudent() {
		showStudentInfos();
		System.out.println("请输入要删除学生的id");
		int id = scanner.nextInt();
		boolean result = false;
		result = iStudentService.delete(id);
		if (result) {
			System.out.println("删除成功");
		} else {
			System.out.println("删除失败");
		}
	}

	/**
	 * 添加学生信息
	 */
	private void addStudent() {
		System.out.println("请输入学生姓名：");
		String name = scanner.next();
		System.out.println("请输入学生年龄：");
		int age = scanner.nextInt();
		System.out.println("请输入学生性别：");
		String gender = scanner.next();
		Student student = new Student(name, age, gender);
		boolean result = false;
		try {
			result = iStudentService.add(student);
			result = true;
		} catch (NameRepeatException e) {
			System.out.println(e.getMessage());
		}
		if (result) {
			System.out.println("添加成功");
		} else {
			System.out.println("添加失败");
		}
	}
}
