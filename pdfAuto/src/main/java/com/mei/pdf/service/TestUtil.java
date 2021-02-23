package com.mei.pdf.service;

import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Attribute;
import org.jsoup.nodes.Attributes;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.util.CollectionUtils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * @Author meiyouqing
 * @Date 2021-02-09
 * @Description
 **/
@Slf4j
public class TestUtil {

	public static void main(String[] args) {

		String hong = "22525955.html";
		boolean flag = true;
		while (flag){
			TestUtil testUtil = new TestUtil();
			String htmlNo = testUtil.read(hong);
			hong = !htmlNo.equals(hong) ? htmlNo : hong;
			Scanner input = new Scanner(System.in);
			System.out.println("请输入>>>>>>>>");
			String next = input.nextLine();
			if(next.length() == 0){
				flag = true;
			}else{
				flag = false;
			}
		}

	}

	public String read(String htmlNo){
		try {
			String hong = "http://www.yunxs.com/honglouchun/" + htmlNo;
			URL url = new URL(hong);
			HttpURLConnection conn = (HttpURLConnection)url.openConnection();
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setRequestMethod("GET");
			conn.connect();
			InputStream is = conn.getInputStream();
			InputStreamReader isr = new InputStreamReader(is,"utf-8");
			BufferedReader br = new BufferedReader(isr);
			StringBuffer buffer=new StringBuffer();
			String line=null;
			while((line=br.readLine())!=null){
				buffer.append(line);
			}

			Document bodyDocument = Jsoup.parseBodyFragment(buffer.toString());
			Elements bodyDocumentAllElements = bodyDocument.getAllElements();
			for(Element element : bodyDocumentAllElements){
				if(element.nodeName().equals("div") && element.siblingIndex() == 6){

					String[] text = element.ownText().split(" ");
					for (String a : text){
						System.out.println(a);
					}

				}
				Attributes attributes = element.attributes();
				List<Attribute> attributeList = attributes.asList();

				if(!CollectionUtils.isEmpty(attributeList)){
					List<Attribute> keyright = attributeList.stream().filter(t -> t.getKey().equals("id")&&t.getValue().equals("keyright")).collect(Collectors.toList());
					if(!CollectionUtils.isEmpty(keyright)){
						List<Attribute> href = attributeList.stream().filter(t -> t.getKey().equals("href")).collect(Collectors.toList());
						htmlNo = href.get(0).getValue();
					}
				}
			}
			System.out.println("**********   " + htmlNo);
			Document document = Jsoup.parse("http://www.yunxs.com/honglouchun/21089876.html");
			//HttpClient httpClient = new HttpClient();
			return htmlNo;
		}catch (Exception e){
			log.info("error is {}", e);
		}
		return null;
	}
}
