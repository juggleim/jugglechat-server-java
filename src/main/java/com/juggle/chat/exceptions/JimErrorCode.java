package com.juggle.chat.exceptions;

public class JimErrorCode {
    public static final int SUCCESS = 0;

    public static final int ErrorCode_AppDefault = 17000;
    public static final int ErrorCode_APP_APPKEY_REQUIRED = 17001;
	public static final int ErrorCode_APP_NOT_EXISTED = 17002;
	public static final int ErrorCode_APP_REQ_BODY_ILLEGAL = 17003;
	public static final int ErrorCode_APP_INTERNAL_TIMEOUT = 17004;
	public static final int ErrorCode_APP_NOT_LOGIN = 17005;
	public static final int ErrorCode_APP_CONTINUE = 17006;
	public static final int ErrorCode_APP_QRCODE_EXPIRED = 17007;
	public static final int ErrorCode_APP_SMS_SEND_FAILED = 17008;
	public static final int ErrorCode_APP_SMS_CODE_EXPIRED = 17009;

	//friends
	public static final int ErrorCode_APP_FRIEND_DEFAULT = 17100;
	public static final int ErrorCode_APP_FRIEND_APPLY_DECLINE = 17101;
	public static final int ErrorCode_APP_FRIEND_APPLY_REPEATED = 17102;
	public static final int ErrorCode_APP_FRIEND_CONFIRM_EXPIRED = 17103;

	//group
	public static final int ErrorCode_APP_GROUP_DEFAULT = 17200;
	public static final int ErrorCode_APP_GROUP_MEMBEREXISTED = 17201;

	//assistant
	public static final int ErrorCode_APP_ASSISTANT_PROMPT_DBERROR = 17300;

	//file
	public static final int ErrorCode_APP_FILE_NOOSS = 17401;
	public static final int ErrorCode_APP_FILE_SIGNERR = 17402;
}
