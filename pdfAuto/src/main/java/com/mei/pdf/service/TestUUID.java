package com.mei.pdf.service;

import java.util.UUID;

/**
 * @Author meiyouqing
 * @Date 2021-02-19
 * @Description
 **/
public class TestUUID {
	public static void main(String[] args) {
		StringBuffer url = new StringBuffer("http://passport1.litb-test.com/user.User/changePassword");
		StringBuffer uri = new StringBuffer("/user.User/changePassword");
		String tempContextUrl = url.delete(url.length() - uri.length(), url.length()).append("/").toString();
		System.out.println(tempContextUrl);

		String email = "123@qq.com";
		String[] emailList = email.split("@");
		System.out.println(emailList[0]);
		System.out.println(emailList[1]);



	}
}
