package it.xpug.courses.persistence;

import it.xpug.courses.Course;

import java.util.List;

public interface CourseBase {

	long count();

	void create(Course course);

	void update(Course course);

	Course getLast();

	List<Course> findAll();

	Course findById(Integer id);

	Course findById(String id);

	void deleteAll();

}
