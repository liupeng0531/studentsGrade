package com.example.springboot.mapper.Course;

import com.github.pagehelper.PageRowBounds;
import com.example.springboot.entity.Course;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 课程信息mapper层
 **/
@Mapper
public interface CourseMapper {
  /**
   * 新增课程信息
   */
  void addCourse(Course course);

  /**
   * 删除课程信息
   */
  void delete(@Param("ids") List<Long> ids);

  /**
   * 修改课程信息
   */
  void update(Course course);
  /**
  * 获取课程信息
  */
  List<Course> getCourseList(PageRowBounds rowBounds, @Param("condition") Map<String, Object> condition);
  /**
   * 查看课程最大id
   */
  String checkCodeCount(@Param("condition") Map<String, Object> condition);
  /**
  * 根据专业、学期获取课程列表
  */
  List<Course> getCourseByMap(@Param("condition") Map<String, Object> condition);

  /**
   * 根据课程id获取课程信息
   */
  Course getCourseById(@Param("courseId") String courseId);
}
