package com.example.niu.mlpt.Utils;

/**
 * Created by NIU on 2018/4/23.
 */

public class Api {

   /* 标签	url
    头部左侧头像	/index/ucenter/quality
    铃铛按钮	/index/ucenter/message
    设置按钮	/index/ucenter/set
    会员金卡	/index/ucenter/card
    金额	/index/ucenter/money
    充值	/index/ucenter/recharge
    全部订单	/index/ucenter/order
    待支付	/index/ucenter/order?str=1
    待接单	/index/ucenter/order?str=2
    进行中	/index/ucenter/order?str=3
    我的优惠券	/index/ucenter/mycoupon
    获取口令优惠券	/index/ucenter/couponpass
    邀请有奖	/index/ucenter/invite
    svip	/index/ucenter/svip
    地址管理	/index/ucenter/address
    我收藏屏蔽的跑男	/index/ucenter/manage
    在线客服	/index/ucenter/server*/

    public static final String BASEURL = "http://mlpt.yiyuncloud.com";

    public static final String SLIDE_INVITE="/index/ucenter/invite";
    public static final String SLIDE_MYCOUPON = "/index/ucenter/mycoupon";
    public static final String  SLIDE_GET_COUPON= "/index/ucenter/couponpass";
    public static final String  SLIDE_SVIP= "/index/ucenter/svip";
    public static final String  SLIDE_COMMOM_ADDRESS= "/index/ucenter/address";
    public static final String  SLIDE_COLLECTION= "/index/ucenter/manage";
    public static final String  SLIDE_ONLINE_SERVICE= "/index/ucenter/server";
   /* 全部订单	/index/ucenter/order
    待支付	/index/ucenter/order?str=1
    待接单	/index/ucenter/order?str=2
    进行中	/index/ucenter/order?str=3
*/

    public static final String  SLIDE_ALL_ORDER= "/index/ucenter/order";
    public static final String  SLIDE_WAIT_PAY= "/index/ucenter/order?str=1";
    public static final String  SLIDE_WAIT_SURE= "/index/ucenter/order?str=2";
    public static final String  SLIDE_DOING_ORDER= "/index/ucenter/order?str=3";


    /*头部左侧头像	/index/ucenter/quality
    铃铛按钮	/index/ucenter/message
    设置按钮	/index/ucenter/set
    会员金卡	/index/ucenter/card
    金额	/index/ucenter/money
    充值	/index/ucenter/recharge*/

    public static final String  SLIDE_USER_AVATAT = "/index/ucenter/quality";
    public static final String  SLIDE_MESSAGE= "/index/ucenter/message";
    public static final String  SLIDE_SETTINGS= "/index/ucenter/set";
    public static final String  SLIDE_VIP_CARD= "/index/ucenter/card";
    public static final String  SLIDE_USER_MOMEY= "/index/ucenter/money";
    public static final String  SLIDE_RECHARGE= "/index/ucenter/recharge";











}
