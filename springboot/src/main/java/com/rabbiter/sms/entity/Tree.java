package com.rabbiter.sms.entity;

import java.util.Arrays;

/**
 * 树形实体类
 **/
public class Tree {
  private String name;
  private String [] school;
  private String [] profession;

  public Tree() {
  }

  public Tree(String name, String[] school, String[] profession) {
    this.name = name;
    this.school = school;
    this.profession = profession;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String[] getSchool() {
    return school;
  }

  public void setSchool(String[] school) {
    this.school = school;
  }

  public String[] getProfession() {
    return profession;
  }

  public void setProfession(String[] profession) {
    this.profession = profession;
  }

  @Override
  public String toString() {
    return "Tree{" +
            "name='" + name + '\'' +
            ", school=" + Arrays.toString(school) +
            ", profession=" + Arrays.toString(profession) +
            '}';
  }
}
