package com.mei.pdf.service;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * @Author meiyouqing
 * @Date 2021-02-22
 * @Description
 **/
public class TestCurl {



	public static String execCurl(String[] cmds){
		ProcessBuilder process = new ProcessBuilder(cmds);
		Process p;
		try {
			p = process.start();

			return null;

		} catch (Exception e) {
			System.out.println("error");
		}
		return null;
	}
	public static void main(String[] args) {

		String[] cmds = {"curl", "-o", "6599302.zip", "http://lxwms.elitb.com/api/lxwms/task/PrintLabel/printTaskPlan",
				"-H", "Connection: keep-alive",
				"-H", "User-Agent: Mozilla/5.0 (Macintosh; Intel Mac OS X 10_13_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/88.0.4324.150 Safari/537.36",
				"-H", "Content-Type: text/plain;charset=UTF-8",
				"-H", "Accept: */*",
				"-H", "Origin: http://lxwms.elitb.com",
				"-H", "Referer: http://lxwms.elitb.com/print-label.html",
				"-H", "Accept-Language: zh-CN,zh;q=0.9,en-US;q=0.8,en;q=0.7",

				"-H", "tracestate: 80560@nr=0-1-80560-1073304133-fdd98c4b082026de----1613985518620",
				"-H", "traceparent: 00-2ecd819c2837d502d2937c32d98fc200-fdd98c4b082026de-01",
				"-H", "newrelic: eyJ2IjpbMCwxXSwiZCI6eyJ0eSI6IkJyb3dzZXIiLCJhYyI6IjgwNTYwIiwiYXAiOiIxMDczMzA0MTMzIiwiaWQiOiJmZGQ5OGM0YjA4MjAyNmRlIiwidHIiOiIyZWNkODE5YzI4MzdkNTAyZDI5MzdjMzJkOThmYzIwMCIsInRpIjoxNjEzOTg1NTE4NjIwfX0=",
				"-H", "Cookie: APP=lxwms; token=790D6A92B9BC4A5DBF9C5CEABDEA0B9F; uid=8561; JSESSIONID=5591A8A44669806A4196F6E84D2E3274",
				"--data-raw", "{\"id\":}",
				"--compressed", "",
				"--insecure", ""};
		Map<Long, String> map = new HashMap<>();
		map.put(6706571l,	"31842491");
		map.put(7549724l,	"31928312");
		map.put(7607405l,	"31939303");
		map.put(7647079l,	"31939303");
		map.put(1263585l,	"40118616");
		map.put(1324049l,	"40149385");
		map.put(5304051l,	"40149385");
		map.put(5285642l,	"40237396");
		map.put(5704501l,	"40553094");
		map.put(6390392l,	"40958953");
		map.put(6441802l,	"41184761");
		map.put(6581294l,	"41184761");
		map.put(6105009l,	"41214467");
		map.put(6266141l,	"41241269");
		map.put(6388184l,	"41241269");
		map.put(6193283l,	"41309583");
		map.put(6701704l,	"41309583");
		map.put(6554539l,	"41313157");
		map.put(6675559l,	"41449355");
		map.put(6852194l,	"41449355");
		map.put(6529448l,	"41487944");
		map.put(6438704l,	"41572122");
		map.put(6704309l,	"41572122");
		map.put(6824750l,	"41798698");
		map.put(6893109l,	"41811319");
		map.put(6932388l,	"41877190");
		map.put(7126613l,	"42022098");
		map.put(7091809l,	"42190708");
		map.put(7292010l,	"42261178");
		map.put(7451159l,	"42405989");
		map.put(7527833l,	"42566667");
		map.put(7641752l,	"42714146");
		map.put(7687674l,	"42714146");
		map.put(7719811l,	"42714206");
		map.put(7629644l,	"42749415");
		map.put(7686443l,	"42749669");
		map.put(7672402l,	"42800771");
		map.put(7780309l,	"42908056");
		map.put(7914632l,	"42984724");
		map.put(7994532l,	"42984724");
		map.put(7849762l,	"42987771");
		map.put(8064312l,	"43097926");
		map.put(8027924l,	"43190754");
		map.put(8042456l,	"43197443");
		map.put(8151656l,	"43198038");
		map.put(8097534l,	"43252253");
		map.put(8115081l,	"43271113");
		map.put(8185393l,	"43336599");
		map.put(8274184l,	"43452360");
		map.put(8353710l,	"43559191");
		Set<Long> keySet = map.keySet();
		Iterator<Long> iterator = keySet.iterator();
		while(iterator.hasNext()){
			Long key = iterator.next();
			cmds[2] = String.valueOf(map.get(key)) + ".zip";
			cmds[27] = cmds[27].substring(0,6) + String.valueOf(key) + "}";
			System.out.println(cmds[2] + cmds[27]);
			execCurl(cmds);
		}


	}
}
