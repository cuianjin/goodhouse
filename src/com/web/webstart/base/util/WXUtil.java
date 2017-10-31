package com.web.webstart.base.util;

import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.UUID;

import com.alibaba.fastjson.JSONObject;
import com.geneqiao.utils.MD5;
import com.geneqiao.utils.PropertiesReadUntils;
import com.geneqiao.utils.StringUtils;
import com.geneqiao.utils.http.HttpClient;

/**
 * @author 吴正亚
 * @Description 微信支付util
 * @date 2016年5月10日 下午6:34:16
 * @version 2.0
 */
public class WXUtil
{

	// 商户号
	public static final String MCH_ID = "";

	// appid
	public static final String APP_ID = "wxa2148f5ad1873ca5";

	private static final String APP_SECRET = "23ded9c1f867c6f8ea815ba95e818d13";

	// apiKey
	public static final String KEY = "";

	// 统一下单url
	public static final String URL_UNIFIED = "https://api.mch.weixin.qq.com/pay/unifiedorder";
	// 退单
	public static final String URL_REFUND = "https://api.mch.weixin.qq.com/secapi/pay/refund";
	// 发送红包
	public static final String URL_SENDREDPACK = "https://api.mch.weixin.qq.com/mmpaymkttransfers/sendredpack";

	public static final String PACKAGE = "Sign=WXPay";

	public static final String URL_TRANSFER = "https://api.mch.weixin.qq.com/mmpaymkttransfers/promotion/transfers";
	
	private static final String NOTIFY_URL = PropertiesReadUntils.getVal("notifyWX");

	private static String token;
	
	private static String ticket;

	private static final String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=%s&secret=%s";

	private static final String openId = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=%s&secret=%s&code=%s&grant_type=authorization_code";
	
	private static final String userInfo = "https://api.weixin.qq.com/cgi-bin/user/info?access_token=%s&openid=%s&lang=zh_CN";
	
	private static final String WEIXIN_TICKET = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=%s&type=jsapi";
	
	private static long startTime;
	
	private static long startTime2;

	public static String getAccessToken()
	{
		if (StringUtils.isEmpty(token) || (System.currentTimeMillis() - startTime) > 7100 * 1000)
		{
			String json = HttpClient.getJSON(String.format(url, APP_ID, APP_SECRET));
			JSONObject jsonObject = JSONObject.parseObject(json);
			if (!jsonObject.containsKey("errcode"))
			{
				token = jsonObject.getString("access_token");
				startTime = System.currentTimeMillis();
			}
		}
		return token;
	}

	public static String getOpenId(String code)
	{
		String json = HttpClient.getJSON(String.format(openId, APP_ID, APP_SECRET, code));
		JSONObject jsonObject = JSONObject.parseObject(json);
		if (!jsonObject.containsKey("errcode")) { return jsonObject.getString("openid"); }
		return null;
	}

	public static String getAccessToken(boolean force)
	{
		if (force)
			token = null;
		return getAccessToken();
	}
	
	public static String getJsTicket()
	{
		if (StringUtils.isEmpty(ticket) || (System.currentTimeMillis() - startTime2) > 7100 * 1000)
		{
			String json = HttpClient.getJSON(String.format(WEIXIN_TICKET, getAccessToken()));
			JSONObject jsonObject = JSONObject.parseObject(json);
			if (jsonObject.containsKey("ticket"))
			{
				ticket = jsonObject.getString("ticket");
				startTime2 = System.currentTimeMillis();
			}
		}
		return ticket;
	}

	public static String getUserInfo(String fromUserName)
	{
		String token2 = getAccessToken();
		String json = HttpClient.getJSON(String.format(userInfo, token2, fromUserName));
		JSONObject jsonObject = JSONObject.parseObject(json);
		if (!jsonObject.containsKey("errcode")) { return json; }
		return null;
	}

	public static void clearToken()
	{
		token = null;
	}

	/**
	 * 接收通知的url
	 * 
	 * @return
	 */
	public static String getWXNotify_url()
	{
		return NOTIFY_URL;
	}

	/**
	 * 生成随机字符串
	 * 
	 * @param length
	 * @return
	 */
	public static String getRandamStr()
	{
		return UUID.randomUUID().toString().replace("-", "");
	}

	public static String getSign(Map<String, Object> map)
	{
		StringBuffer sb = new StringBuffer();
		SortedMap<String, Object> sortmap = new TreeMap<>(map);
		Object obj;
		for (String key : sortmap.keySet())
		{
			obj = sortmap.get(key);
			if (obj == null || "".equals(obj.toString()))
				continue;
			sb.append(key);
			sb.append("=");
			sb.append(obj);
			sb.append("&");
		}
		sb.append("key=");
		sb.append(KEY);
		return MD5.encodeByMD5(sb.toString()).toUpperCase();
	}

	public static String getTimeStamp()
	{
		return String.valueOf(System.currentTimeMillis() / 1000);
	}

	public static String getValue(Map<String, Object> mapreturn, String key)
	{
		if (mapreturn == null)
			return null;
		if (mapreturn.containsKey(key)) { return mapreturn.get(key).toString(); }
		return null;
	}
}

