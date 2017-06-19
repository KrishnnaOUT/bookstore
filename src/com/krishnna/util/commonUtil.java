package com.krishnna.util;

/**
 * 自定义的方法类
 * @author Ningkui
 * createCode -->生成32位的随机验证码
 */
public class commonUtil {
	
	
	/**
	 *  生成32位随机验证码
	 */
	public static String getCode(){

		String str = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
		
		StringBuilder code = new StringBuilder();
		
		for(int i= 0 ; i<32 ; i++){
			int index = (int) (Math.random()*str.length());
			code.append(str.charAt(index));
		}
		
		String code1 = code.toString();
		return code1;
	}

}
