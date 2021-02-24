package com.mei.pdf.contorller;

import io.micrometer.core.instrument.util.StringUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author meiyouqing
 * @Date 2021-02-24
 * @Description
 **/
@Controller
@RequestMapping("/Resources")
public class RescourseController {

	/**
	 * http://stackoverflow.com/questions/19721820/pattern-matching-to-get-only-class-names-from-css-file
	 */
	private final Pattern _regex = Pattern.compile("\\.(.*)\\s?\\{", Pattern.MULTILINE);
	private String _icons;
	/**
	 * Logger for this class
	 */
	private final org.slf4j.Logger _logger = org.slf4j.LoggerFactory.getLogger(this.getClass());

	@RequestMapping(value = "IconData", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String iconData(HttpServletRequest request, HttpServletResponse response) throws IOException {
		if (this._icons == null) {

			// region 第一种方式：springboot项目获取resources路径（相对路径），只能开发用，部署路径不知道什么
			//            //https://blog.csdn.net/qq_29669265/article/details/89678077?depth_1-utm_source=distribute.pc_relevant.none-task&utm_source=distribute.pc_relevant.none-task
			//            String physicalUserDir = System.getProperty("user.dir");//项目的物理路径
			//            File virtualPathFile = new File("src/main/resources/static/Content/jquery.easyUI/1.5.1/themes/icon.css");//相对路径，只能开发用，部署路径不知道什么
			//            String physicalPath = virtualPathFile.getCanonicalPath();//物理路径
			//            if (!IOHelper.isExistFilePath(physicalPath)) {
			//                return "[]";
			//            }
			//            String iconsText = null;
			//            try {
			//                iconsText = IOHelper.readAllText(physicalPath);
			//            } catch (IOException e) {
			//                this._logger.error("ResourcesController.iconData()", e);
			//            }
			// endregion

			// region SpringBoot读取Resource下文件的几种方式（通过类加载器读取文件流，类加载器可以读取jar包中的编译后的class文件，当然也是可以读取jar包中的文件流了）
			//https://www.jianshu.com/p/7d7e5e4e8ae3
			ClassPathResource classPathResource = new ClassPathResource("static/Content/jquery.easyUI/1.5.1/themes/icon.css");
			if (classPathResource == null) {
				return "[]";
			}
			String iconsText = null;
			try (InputStream inputStream = classPathResource.getInputStream()) {
				iconsText = IOHelper.readAllText(inputStream);
			} catch (IOException e) {
				this._logger.error("ResourcesController.iconData()", e);
				return "[]";
			}
			// endregion

			StringBuilder sb = new StringBuilder("[{\"text\":\"无\",\"value\":\"\"}");
			if (StringUtils.isNotBlank(iconsText)) {
				Matcher matcher = this._regex.matcher(iconsText);
				String value = null;
				while (matcher.find()) {
					value = matcher.group(1).trim();
					sb.append(String.format(",{\"text\":\"%s\",\"value\":\"%s\"}", value, value));
				}
			}
			sb.append("]");
			this._icons = sb.toString();
			sb = null;
		}
		return this._icons;
	}

}
