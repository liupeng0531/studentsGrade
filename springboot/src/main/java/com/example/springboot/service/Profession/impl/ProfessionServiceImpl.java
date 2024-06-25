package com.example.springboot.service.Profession.impl;

import com.example.springboot.mapper.Profession.ProfessionMapper;
import com.example.springboot.domain.Profession;
import com.example.springboot.service.Profession.ProfessionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 专业信息业务层
 **/
@Service
public class ProfessionServiceImpl implements ProfessionService {
  @Resource
  private ProfessionMapper professionMapper;

  @Override
  public List<Profession> getProfessionList() {
    return professionMapper.getProfessionList();
  }
}
