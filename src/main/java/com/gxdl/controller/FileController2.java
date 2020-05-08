package com.gxdl.controller;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.gxdl.common.AppFileUtils;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FileUtil;

@RestController
@RequestMapping("uploadFile")
public class FileController2 {
/**
 * 文件上传
 */
	@RequestMapping("uploadFile")
	public Map<String, Object> uploadFile(MultipartFile mf) {
				//1,得到文件名
		String oldName = mf.getOriginalFilename();
				//2,根据文件名生成新的文件名
		String newName = AppFileUtils.createNewFileName(oldName);
				//3,得到当前日期的字符串
		String dirName = DateUtil.format(new Date(), "yyyy-MM-dd");
				//4,构造文件夹
		File dirFile = new File(AppFileUtils.UPLOAD_PATH, dirName);
				//5,判断当前文件夹是否存在
		if (!dirFile.exists()) {
			dirFile.mkdirs();
		}
				//6,构造文件对象
		File file = new File(dirFile,newName);
				//7,把mf里面的图片信息写入file
		try {
			mf.transferTo(file);
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("path", dirName+"/"+newName);
		return map;
				
	}
	
	
	/**
	 * 图片下载
	 */
	@RequestMapping("showImageByPath")
	public ResponseEntity<Object> showImageByPath(String path){
		
		return AppFileUtils.createResponseEntity(path);
		
	}
	
	/**
	 * 文件下载
	 */
	@RequestMapping("downloadFile")
	public ResponseEntity<Object> downloadFile(String path){
		//1.构造文件对象
				File file = new File("F:/download",path);
				if (file.exists()) {
					//将下载的文件，封装byte[]
					byte[] bytes=null;
					try {
						bytes =FileUtil.readBytes(file);
					} catch (Exception e) {
						e.printStackTrace();
					}

					//创建封装响应头信息的对象
					HttpHeaders header=new HttpHeaders();
					//封装响应内容类型(APPLICATION_OCTET_STREAM 响应的内容不限定)
					header.setContentType(MediaType.APPLICATION_OCTET_STREAM);
					//设置下载的文件的名称
//					header.setContentDispositionFormData("attachment", "123.jpg");
					//创建ResponseEntity对象
					ResponseEntity<Object> entity=
							new ResponseEntity<Object>(bytes, header, HttpStatus.CREATED);
					return entity;
				}
				
				return null;
	}
}
