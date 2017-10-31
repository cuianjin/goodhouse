package com.web.webstart.base.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Autor: zhangl
 * @Times: 2015-8-19下午02:10:39
 * 类的说明：
 **/
public class PushMessgeUtil{
	
	@SuppressWarnings("static-access")
	public static void pushMessage(String content , List<String> emps, Map<String,String> extras){
		List<String> emps1 = new ArrayList<String>();
		for(String emp : emps){
			emps1.add(emp);
		}
		//推送
		Push push = new Push("您有一条新的提醒" , content , "您有一条新的提醒", extras);
		//推向android
		push.sendPush(push.buildPushObject_android_tag_alert(emps1));
		//推向ios
		push.sendPush(push.buildPushObject_ios_tag_alert(emps1));
	}
	@SuppressWarnings("static-access")
	public static void pushMessage(String content ,  Map<String,String> extras){
		
		//推送
		Push push = new Push("您有一条新的提醒" , content , "您有一条新的提醒", extras);
		//推向所有平台
		//push.sendPush(push.buildPushObject_all_all_alert());
		//ios
		push.sendPush(push.buildPushObject_all_ios_alert());
		//安卓
		push.sendPush(push.buildPushObject_all_android_alert());
		
		
	}
	
	public static void main(String[] args) {
		
	}
	
}
