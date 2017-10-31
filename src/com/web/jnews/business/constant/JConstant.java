package com.web.jnews.business.constant;

/**
 * 今资讯项目中使用到的常量
 * @author zhanglin
 * date: 2015-12-01 16:41:24
 */
public class JConstant {
	
	/**
	 * 活动类型
	 * @author zhanglin
	 * date: 2015-12-01 16:41:24
	 */
	public static class ActivityType{
		
		/**
		 * 公告
		 */
		public static final Integer ANNOUCEMENT = 1;
		
		/**
		 * 活动
		 */
		public static final Integer ACTIVITY = 2;
		
		/**
		 * 招商
		 */
		public static final Integer CANVASS = 3;
		
	}
	
	/**
	 * Boolean类型
	 * @author zhanglin
	 * date: 2015-12-01 16:41:24
	 */
	public static class BooleanStatus{
		
		/**
		 * 否
		 */
		public static final Integer FALSE = 0;
		
		/**
		 * 是
		 */
		public static final Integer TRUE = 1;
		
	}
	
	/**
	 * 积分类型对应Id
	 * @author zhanglin
	 * date: 2015-12-01 16:41:24
	 */
	public static class IntegralType{
		
		/**
		 * 注册
		 */
		public static final Long LOGIN = 1L;
		
		/**
		 * 签到
		 */
		public static final Long SIGN = 2L;
		
		/**
		 * 评论
		 */
		public static final Long COMMENT = 3L;
		
		/**
		 * 收藏
		 */
		public static final Long HISTORY = 4L;
		
		/**
		 * 分享
		 */
		public static final Long SHARE = 5L;
		
		/**
		 * 牛评
		 */
		public static final Long COTTAL_COMMENT = 6L;
		
	}
	
	/**
	 * 类型,0、资讯来源 1、其它 2、栏目类型 3、其它b
	 * @author Zzc
	 *
	 */
	public static class DictionaryType{
		/**
		 * 资讯来源
		 */
		public static final Long SOURCE= 0L;
		/**
		 * 其它
		 */
		public static final Long OTHER= 1L;
		/**
		 * 栏目类型
		 */
		public static final Long CHANNELTYPE= 2L;
		/**
		 * 其它2
		 */
		public static final Long OTHER2 = 3L;
		
	}

}
