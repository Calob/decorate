package com.dec.classes;

import java.security.MessageDigest;
import org.apache.commons.lang3.RandomStringUtils;
/**
 * createSalt()用于随机产生12位字符串作为加密MD5时的salt
 * CreateMD5(String password, String salt)将密码加�?
 * CreateMD5(String password,String email, String salt)三个参数加密作为confirmation用于重置密码的验�?
 */
public class SetPassSalt {
/**
 * 
 * @return  12位随机字符串作为salt
 * @throws Exception
 */
	public String CreateSalt() throws Exception {

		String salt = RandomStringUtils.random(12, true, true);
		return salt;
	}
/**
 * 
 * @param password 用户密码
 * @param salt 12位随机字符串
 * @return 密码加密后的MD5字符�?
 * @throws Exception
 */
	public String CreateMD5(String password, String salt) throws Exception {
		MessageDigest m = MessageDigest.getInstance("MD5");
		m.update(salt.getBytes("UTF-8"));
		m.update(password.getBytes("UTF-8"));
		byte[] s = m.digest();
		String result = "";
		for (int i = 0; i < s.length; i++) {
			result += Integer.toHexString((0x000000ff & s[i] | 0xffffff00))
					.substring(6);
		}

		return result;
	}
	/**
	 * 
	 * @param password 用户密码
	 * @param email  用户邮箱
	 * @param salt 12位随机字符串
	 * @return  用户�?邮箱+salt加密产生的MD5字符�?
	 * @throws Exception
	 */
	public String CreateMD5(String username,String email, String salt) throws Exception {
		MessageDigest m = MessageDigest.getInstance("MD5");
		m.update(salt.getBytes("UTF-8"));
		m.update(username.getBytes("UTF-8"));
		m.update(email.getBytes("UTF-8"));
		byte[] s = m.digest();
		String result = "";
		for (int i = 0; i < s.length; i++) {
			result += Integer.toHexString((0x000000ff & s[i] | 0xffffff00))
					.substring(6);
		}

		return result;
	}


}
