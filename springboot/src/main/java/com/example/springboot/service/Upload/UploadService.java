package com.example.springboot.service.Upload;

import com.example.springboot.domain.Upload;

import java.util.Map;

/**
 * 上传文件service层
 **/
public interface UploadService {
  /**
   * 上传头像
   */
  void upload(Upload upload);
  /**
  * 获取头像
  */
  String getHeader(Map<String, Object> condition);
}
