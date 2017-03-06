package com.demo.spring_web.service.impl;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.demo.spring_web.model.FileDto;
import com.demo.spring_web.service.IFileService;
import com.demo.spring_web.util.StringUtils;

@Service
public class FileService implements IFileService {
	private String basePath = "D:\\";
	private String url = "/upload/";

	@Override
	public FileDto uploadFile(MultipartFile file) throws Exception {
		// TODO Auto-generated method stub
		if(!file.isEmpty()){
			Date date = new Date();
			SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
			// 服务器上的用户相对路径，格式为userimage/日期/文件.
			String userPath = "userimage" + File.separator + formatter.format(date) + File.separator;
			// 上传的文件名
			String filename = file.getOriginalFilename();
			// 获取扩展名
			String type = file.getContentType();
			String suffix = type.substring(type.lastIndexOf("/") + 1);
			// 存到文件服务器上的文件名
			String fileNameOnServer = StringUtils.getRandomUUIDStr() + "." + suffix;
			// 存到文件服务器上的文件路径和文件名
			String fileUrl = userPath + fileNameOnServer;
			fileUrl=fileUrl.replace("\\", "/");
			// 存到文件服务器中
			File targetFile = new File(basePath + fileUrl);
			if(!targetFile.getParentFile().exists()){
				if(!targetFile.getParentFile().mkdirs()){
					throw new Exception("创建目标文件所在目录失败");
				}
			}
			file.transferTo(targetFile);
			// 返回FileDto对象
			FileDto dto = new FileDto();
			dto.setFileName(filename);
			dto.setFileFullUrl(url+fileUrl);
			dto.setFileUrl(fileUrl);
			dto.setFileType(suffix);
			return dto;
		}else {
			throw new Exception("文件参数有误");
		}
	}

	@Override
	public boolean removeFile(String fullFileUrl) {
		// TODO Auto-generated method stub
		String fileUrl = fullFileUrl.substring(url.length());
		File file = new File(basePath + fileUrl); 
		// 路径为文件且不为空则进行删除  
	    if (file.isFile() && file.exists()) {  
	        file.delete(); 
	        return true;
	    }  
		return false;
	}

}
