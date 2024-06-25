package com.rabbiter.sms.service.User;

import com.rabbiter.sms.entity.User;
import com.rabbiter.sms.utils.PagingResult;
import org.apache.ibatis.session.RowBounds;

import java.util.List;
import java.util.Map;

/**
 * 管理员Service层
 **/
public interface AdminService {
  /**
   * 新增学生账号
   */
  void add(User user);

  /**
   * description: 删除学生账号
   *
   * @param ids
   * @return void
   * @author rabbiter
   * @date 2019/8/29 14:55
   */
  void delete(List<Integer> ids);

  /**
   * 修改学生账号
   */
  void update(User user);

  /**
   * 获取学生账号信息列表
   */
  PagingResult<User> getAdminList(RowBounds rowBounds, Map<String, Object> condition);
}
