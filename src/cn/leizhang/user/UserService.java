package cn.leizhang.user;

import cn.leizhang.utils.MailUtils;
import cn.leizhang.utils.UUIDUtils;



public class UserService {
		private UserDao userDao;

		public void setUserDao(UserDao userDao) {
			this.userDao = userDao;
		}

		public User existUser(User user) {
			
			return userDao.existUser(user);
		}

		public void regist(User user) {
			// 保存用户:
			user.setState(0);// 0 未激活  1已经激活.
			String code = UUIDUtils.getUUID()+UUIDUtils.getUUID();//生成激活码
			user.setCode(code);
			userDao.save(user);
			// 发送邮件:
			try {
				MailUtils.sendMail(user.getEmail(), code);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}

		public User findByUserName(String username) {
			
			return userDao.findByUserName(username);
		}
		
}
