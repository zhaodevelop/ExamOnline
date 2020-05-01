package com.ruanyuan.common.utils;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FilenameUtils;
import org.springframework.web.multipart.MultipartFile;
/**
 * 上传图片工具类
 * @author
 *
 */
public class ImageUtils {
	public static String upload(HttpServletRequest request, MultipartFile pictureFile) throws IOException {
		String imgPath = null;// 装配后的图片地址
		// 上传图片
		if (pictureFile != null && !pictureFile.isEmpty()) {
			// 使用UUID给图片重命名，并去掉四个“-”
			String name = UUID.randomUUID().toString().replaceAll("-", "");
			// 获取文件的扩展名
			String ext = FilenameUtils.getExtension(pictureFile.getOriginalFilename());
			// 设置文件名
			String url=null;
			if ("jpg".equals(ext)) {
				url = request.getSession().getServletContext().getRealPath("/upload");
			} else if ("png".equals(ext)) {
				url = request.getSession().getServletContext().getRealPath("/upload");
			} else if ("jpeg".equals(ext)) {
				url = request.getSession().getServletContext().getRealPath("/upload");
			} else if ("gif".equals(ext)) {
				url = request.getSession().getServletContext().getRealPath("/upload");
			}else if ("JPG".equals(ext)) {
				url = request.getSession().getServletContext().getRealPath("/upload");
			}else if ("PNG".equals(ext)) {
				url = request.getSession().getServletContext().getRealPath("/upload");
			} else if ("JPEG".equals(ext)) {
				url = request.getSession().getServletContext().getRealPath("/upload");
			} else if ("GIF".equals(ext)) {
				url = request.getSession().getServletContext().getRealPath("/upload");
			}else {
				return null;
			}
			// 检验文件夹是否存在
			isFolderExists(url);
			// 以绝对路径保存重名命后的图片
			pictureFile.transferTo(new File(url + "/" + name + "." + ext));
			// 装配图片地址
			imgPath = "upload/" + name + "." + ext;
		}
		return imgPath;
	}
	//验证文件夹是否存在
	public static boolean isFolderExists(String strFolder){
        File file = new File(strFolder);
     
        if (!file.exists())
        {
           if (file.mkdir())
           {
               return true;
           }
           else{
               return false;
           }
        }
        System.out.println("-----------------文件上传路径："+strFolder);
        return true;
    }

}
