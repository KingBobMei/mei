package com.mei.pdf.util;

import com.itextpdf.text.Font;
import com.itextpdf.text.pdf.BaseFont;
import lombok.extern.slf4j.Slf4j;

/**
 * @Author meiyouqing
 * @Date 2021-02-08
 * @Description
 **/
@Slf4j
public class PdfFontUtil {

	public static Font getDefaultFont(Integer fontSize){
		try {
			BaseFont baseFont = BaseFont.createFont();
			return  new Font(baseFont, fontSize);
		}catch (Exception e){
			log.error("create default font error: {}", e);
			return null;
		}
	}

	public static Font getAsianFont(Integer fontSize){
		try {
			BaseFont font = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
			return new Font(font, fontSize);
		}catch (Exception e){
			log.error("create asian font error: {}", e);
			return null;
		}
	}

	public static Font getSimheiFont(Integer fontSize){
		try {
			BaseFont baseFont = BaseFont.createFont("template/simhei.ttf", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
			return new Font(baseFont, fontSize);
		}catch (Exception e){
			log.error("create simhei font error: {}", e);
			return null;
		}
	}

	public static Font getMsyhFont(Integer fontSize){
		try {
			BaseFont baseFont = BaseFont.createFont("template/msyh.ttf", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
			return new Font(baseFont, fontSize);
		}catch (Exception e){
			log.error("create msyh font error: {}",e);
			return null;
		}
	}


}
