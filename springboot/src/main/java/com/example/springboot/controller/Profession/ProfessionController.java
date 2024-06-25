package com.example.springboot.controller.Profession;

import com.example.springboot.domain.Profession;
import com.example.springboot.service.Profession.ProfessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 所有专业控制层
 **/
@RestController
@RequestMapping("/api/sms/profession")
public class ProfessionController {

  @Autowired
  private ProfessionService professionService;

  @GetMapping("/getProfessionList")
  private List<Profession> getProfessionList () {
    return professionService.getProfessionList();
  }
}
