package com.krishnna.util;

/**
 * �Զ���ķ�����
 * @author Ningkui
 * createCode -->����32λ�������֤��
 */
public class commonUtil {
	
	
	/**
	 *  ����32λ�����֤��
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
