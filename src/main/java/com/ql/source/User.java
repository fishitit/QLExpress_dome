package com.ql.source;

/**
 * 
 * @author wangmengguang
 *
 */
public class User {
	
	public static int GENDER_BOY=1;
	public static int GENDER_GRIL=0;
	
		private Integer gender;
		private Integer age;
		
		public Integer getGender() {
			return gender;
		}
		public void setGender(Integer gender) {
			this.gender = gender;
		}
		public Integer getAge() {
			return age;
		}
		public void setAge(Integer age) {
			this.age = age;
		}
		
}
