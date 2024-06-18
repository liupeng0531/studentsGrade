package com.rabbiter.sms.service.Profession;

import com.rabbiter.sms.domain.Profession;

import java.util.List;

/**
 * 专业信息Service层
 **/
public interface ProfessionService {
  /**
   * 获取专业
   */
  List<Profession> getProfessionList();
}
