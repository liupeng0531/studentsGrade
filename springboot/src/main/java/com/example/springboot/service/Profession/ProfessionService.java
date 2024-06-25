package com.example.springboot.service.Profession;

import com.example.springboot.domain.Profession;

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
