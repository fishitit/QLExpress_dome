package com.ql.source;

import java.io.Serializable;

/**
 * 
 * @author wangmengguang
 *
 */
public class User implements Serializable {
	
	private static final long serialVersionUID = 5207884030074129113L;
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
