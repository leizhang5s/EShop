package cn.leizhang.utils;

import java.util.UUID;

/**
 * 生成激活码
 * @author 传智.郭嘉
 *
 */
public class UUIDUtils {
	public static String getUUID(){
		return UUID.randomUUID().toString().replace("-", "");
	}
}
