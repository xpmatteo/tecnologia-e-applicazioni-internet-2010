package it.xpug.courses;

import java.util.List;

public interface CourseBase {

	public long count();

	public void create(Course course);

	public void update(Course course);

	public Course getLast();

	public List<Course> findAll();

	public Course findById(Integer id);

	public Course findById(String id);

}
