package com.rabbiter.sms.service.User;

import com.rabbiter.sms.dto.User;
import com.rabbiter.sms.utils.PagingResult;
import org.apache.ibatis.session.RowBounds;

import java.util.List;
import java.util.Map;

/**
 * 教师用户接口
 **/
public interface TeacherService {
  /**
   * 新增教师账号
   */
  void addTeacher(User user);

  /**
   * 删除教师账号
   */
  void delete(List<Integer> ids);

  /**
   * 修改教师账号
   */
  void update(User user);

  /**
   * 获取教师账号信息列表
   */
  PagingResult<User> getTeacherList(RowBounds rowBounds, Map<String, Object> condition);
}
