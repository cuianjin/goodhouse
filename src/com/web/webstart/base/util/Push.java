package com.web.webstart.base.util;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.jpush.api.JPushClient;
import cn.jpush.api.common.resp.APIConnectionException;
import cn.jpush.api.common.resp.APIRequestException;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Options;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.Notification;

/**
 * @Autor: zhangl
 * @times: 2015-4-29下午03:50:33
 * 类的说明：消息推送
 **/
public class Push {
	
	protected static final Logger LOG = LoggerFactory.getLogger(Push.class);

	private static final String appKey ="3d2b4333c2bd4a8c10e41255";
	private static final String masterSecret = "3751f2b8eb686653150d5e86";
	
	public static String TITLE = null;//标题
    public static String ALERT = null;//别名
    public static String MSG_CONTENT = null;//对应 Portal 推送通知界面上的"通知内容”字段。
    public static Map<String,String> EXTRAS = null;//对应 Portal 推送消息界面上的"可选设置”里的附加字段。
    
    @SuppressWarnings("static-access")
	public Push(String title,String alert,String msg_content,Map<String,String> extras){
    	this.TITLE = title;
    	this.ALERT = alert;
    	this.MSG_CONTENT = msg_content;
    	this.EXTRAS = extras;
    }

    
	/**
	 * 推送消息
	 * @param payload
	 */
	public static void sendPush(PushPayload payload) {
		
        JPushClient jpushClient = new JPushClient(masterSecret, appKey);
        
        try {
            PushResult result = jpushClient.sendPush(payload);
            LOG.info("Got result - " + result);
        } catch (APIConnectionException e) {
            LOG.error("Connection error. Should retry later. ", e);
        } catch (APIRequestException e) {
            LOG.error("Error response from JPush server. Should review and fix it. ", e);
            LOG.info("HTTP Status: " + e.getStatus());
            LOG.info("Error Code: " + e.getErrorCode());
            LOG.info("Error Message: " + e.getErrorMessage());
            LOG.info("Msg ID: " + e.getMsgId());
        }
	}
	
	/**
	 * author:changlu
	 * 构建平台（所有ios）广播推送
	 * @return
	 */
	public static PushPayload buildPushObject_all_ios_alert() {
		
		return PushPayload.newBuilder()
				.setPlatform(Platform.android_ios())
				 .setAudience(Audience.all())
				.setNotification(Notification.ios(ALERT, EXTRAS))
				.setOptions(Options.newBuilder()
						.setApnsProduction(true)
						.build())
						.build();
	    		
	}

	public static PushPayload buildPushObject_all_alias_alert() {
		         return PushPayload.newBuilder()
		                 .setPlatform(Platform.all())//设置接受的平台
		                 .setAudience(Audience.all())//Audience设置为all，说明采用广播方式推送，所有用户都可以接收到
		                 .setNotification(Notification.alert(ALERT))
		                 .build();
		     }


	/**
	 * author:changlu
	 * 构建平台（所有 安卓）广播推送
	 * @return
	 */
	public static PushPayload buildPushObject_all_android_alert() {
		
		return PushPayload.newBuilder()
				.setPlatform(Platform.android_ios())
				 .setAudience(Audience.all())
				.setNotification(Notification.android(ALERT, TITLE,EXTRAS))
				.setOptions(Options.newBuilder()
						.setApnsProduction(true)
						.build())
						.build();
	    		
	}
	/**
	 * 构建平台（所有）
	 * @return
	 */
	public static PushPayload buildPushObject_all_all_alert() {
	    return PushPayload.alertAll(ALERT);
	}
    
	/**
	 * 构建平台（所有）别名推送
	 * @param tags
	 * @return
	 */
	public static PushPayload buildPushObject_all_tag_alert(List<String> tags) {
        Notification.android(ALERT, TITLE, EXTRAS);
		return PushPayload.newBuilder()
                .setPlatform(Platform.all())
                .setAudience(Audience.alias(tags))
                .setNotification(Notification.ios(tags, EXTRAS))
                .setOptions(Options.newBuilder()
                        .setApnsProduction(true)
                        .build())
                .build();
    }
	
	/**
	 * 构建平台（android）别名推送
	 * @param alias
	 * @return
	 */
	public static PushPayload buildPushObject_android_tag_alert(List<String> alias) {
		return PushPayload.newBuilder()
                .setPlatform(Platform.android_ios())
                .setAudience(Audience.alias(alias))
                .setNotification(Notification.android(ALERT, TITLE,EXTRAS))
                .setOptions(Options.newBuilder()
                        .setApnsProduction(true)
                        .build())
                .build();
	}
	/**
	 * 构建平台（ios）别名推送
	 * @param alias
	 * @return
	 */
	public static PushPayload buildPushObject_ios_tag_alert(List<String> alias) {
		return PushPayload.newBuilder()
				.setPlatform(Platform.android_ios())
				.setAudience(Audience.alias(alias))
				.setNotification(Notification.ios(ALERT, EXTRAS))
				.setOptions(Options.newBuilder()
						.setApnsProduction(false)
						.build())
						.build();
	}

	public static void main(String[] agrs){
		String title = "aa";
		String content = "bb";
		String alert = "bb";
		Push p = new Push(title, content, alert, null);
		PushPayload ppla = buildPushObject_all_alias_alert();
		p.sendPush(ppla);
	}

//	public static void testSendPush(String appKey ,String masterSecret) {
//
//
//
//		          jpushClient = new JPushClient(masterSecret, appKey, 3);
//
//		         // HttpProxy proxy = new HttpProxy("localhost", 3128);
//		         // Can use this https proxy: https://github.com/Exa-Networks/exaproxy
//
//
//		         // For push, all you need do is to build PushPayload object.
//		         //PushPayload payload = buildPushObject_all_all_alert();
//		          //生成推送的内容，这里我们先测试全部推送
//		         PushPayload payload=buildPushObject_all_alias_alert();
//
//
//		         try {
//			             System.out.println(payload.toString());
//			             PushResult result = jpushClient.sendPush(payload);
//			             System.out.println(result+"................................");
//
//			             LOG.info("Got result - " + result);
//
//			         } catch (APIConnectionException e) {
//			             LOG.error("Connection error. Should retry later. ", e);
//
//			         } catch (APIRequestException e) {
//			             LOG.error("Error response from JPush server. Should review and fix it. ", e);
//			             LOG.info("HTTP Status: " + e.getStatus());
//			             LOG.info("Error Code: " + e.getErrorCode());
//			             LOG.info("Error Message: " + e.getErrorMessage());
//			             LOG.info("Msg ID: " + e.getMsgId());
//			         }
//		     }


}
