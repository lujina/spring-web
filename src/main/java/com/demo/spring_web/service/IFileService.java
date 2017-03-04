package com.demo.spring_web.service;

import org.springframework.web.multipart.MultipartFile;

import com.demo.spring_web.model.FileDto;

public interface IFileService {
	FileDto uploadFile(MultipartFile file) throws Exception;
}
