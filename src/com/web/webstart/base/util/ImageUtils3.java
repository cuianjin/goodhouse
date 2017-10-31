package com.web.webstart.base.util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;

import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ImageUtils3 {

	  private static Logger log = LoggerFactory.getLogger(ImageUtils2.class);

	    private static String DEFAULT_THUMB_PREVFIX = "thumb_";
	    private static String DEFAULT_CUT_PREVFIX = "cut_";
	    private static Boolean DEFAULT_FORCE = false;


	    /**
	     * <p>Title: cutImage</p>
	     * <p>Description:  根据原图与裁切size截取局部图片</p>
	     * @param srcImg    源图片
	     * @param output    图片输出流
	     * @param rect        需要截取部分的坐标和大小
	     */
	    public static void cutImage(File srcImg, OutputStream output, java.awt.Rectangle rect){
	        if(srcImg.exists()){
	            java.io.FileInputStream fis = null;
	            ImageInputStream iis = null;
	            try {
	                fis = new FileInputStream(srcImg);
	                // ImageIO 支持的图片类型 : [BMP, bmp, jpg, JPG, wbmp, jpeg, png, PNG, JPEG, WBMP, GIF, gif]
	                String types = Arrays.toString(ImageIO.getReaderFormatNames()).replace("]", ",");
	                String suffix = null;
	                // 获取图片后缀
	                if(srcImg.getName().indexOf(".") > -1) {
	                    suffix = srcImg.getName().substring(srcImg.getName().lastIndexOf(".") + 1);
	                }// 类型和图片后缀全部小写，然后判断后缀是否合法
	                if(suffix == null || types.toLowerCase().indexOf(suffix.toLowerCase()+",") < 0){
	                    log.error("Sorry, the image suffix is illegal. the standard image suffix is {}." + types);
	                    return ;
	                }
	                // 将FileInputStream 转换为ImageInputStream
	                iis = ImageIO.createImageInputStream(fis);
	                // 根据图片类型获取该种类型的ImageReader
	                ImageReader reader = ImageIO.getImageReadersBySuffix(suffix).next();
	                reader.setInput(iis,true);
	                ImageReadParam param = reader.getDefaultReadParam();
	                param.setSourceRegion(rect);
	                BufferedImage bi = reader.read(0, param);
	                ImageIO.write(bi, suffix, output);
	            } catch (FileNotFoundException e) {
	                e.printStackTrace();
	            } catch (IOException e) {
	                e.printStackTrace();
	            } finally {
	                try {
	                    if(fis != null) fis.close();
	                    if(iis != null) iis.close();
	                } catch (IOException e) {
	                    e.printStackTrace();
	                }
	            }
	        }else {
	            log.warn("the src image is not exist.");
	        }
	    }


	    public void cutImage(File srcImg, OutputStream output, int x, int y, int width, int height){
	        cutImage(srcImg, output, new java.awt.Rectangle(x, y, width, height));
	    }

	    public static void cutImage(File srcImg, String destImgPath, java.awt.Rectangle rect){
	        File destImg = new File(destImgPath);
	        //if(destImg.exists()){
	            String p = destImg.getPath();
	            try {
//	                if(!destImg.isDirectory()) p = destImg.getParent();
//	                if(!p.endsWith(File.separator)) p = p + File.separator;
	                cutImage(srcImg, new java.io.FileOutputStream(p), rect);
	            } catch (FileNotFoundException e) {
	                log.warn("the dest image is not exist." + e.getMessage());
	            }
	        //}else log.warn("the dest image folder is not exist.");
	    }

//	    public void cutImage(File srcImg, String destImg, int x, int y, int width, int height){
//	        cutImage(srcImg, destImg, new java.awt.Rectangle(x, y, width, height));
//	    }

	    public static void cutImage(String srcImg, String destImg, int x, int y, int width, int height){
	        cutImage(new File(srcImg), destImg, new java.awt.Rectangle(x, y, width, height));
	    }

	    /**
	     * 默认被调用的方法，剪切中间部分
	     * @param srcImg
	     * @param destImg
	     * @param destWidth
	     * @param destHeight
	     */
	    public static void cutImage(String srcImg, String destImg, int destWidth, int destHeight){
	        try{
	            File imgFile = new File(srcImg);
	            File destImgFile = new File(destImg);
	            if(destImgFile.exists()){
	            	return ;
	            }

	            BufferedImage srcBufferedImage = ImageIO.read(imgFile);

	            int imgWidth = destWidth;
	            int imgHeight = destHeight;

	            int srcWidth = srcBufferedImage.getWidth();
	            int srcHeight = srcBufferedImage.getHeight();

	            int x = 0;
	            int y = 0;
	            double destDiff = new Integer(destWidth).doubleValue() / new Integer(destHeight).doubleValue();
	            double srcDiff = new Integer(srcWidth).doubleValue() / new Integer(srcHeight).doubleValue();

	            if(destDiff < srcDiff){// 偏宽,以宽度外框以准
	                if(srcWidth > imgWidth)
	                x = (srcWidth - imgWidth) / 2;
	            }else{
	                if(srcHeight > imgHeight)
	                y = (srcHeight - imgHeight) / 2;
	            }

	            cutImage(new File(srcImg), destImg, new java.awt.Rectangle(x, y, destWidth, destHeight));

	        }catch(Exception e){
	            log.warn("图片异常" + e.getMessage());
	        }
	    }

	    

	}