package com.ql.source;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * 资源池
 * @author wangmengguang
 *
 */
public class Source implements Serializable {
    private List<Course> courseList =new ArrayList<Course>();
    
    private User user=new User();
    
    public String getCourseFieldListToString(String fieldName) {
    	String str=new String();
    	String methodName="get"+fieldName.substring(0,1).toUpperCase()+fieldName.substring(1);
    	for(Course c:courseList) {
    		try {
    			//获取方法
    			Method method = c.getClass().getMethod(methodName);
    			str=method.invoke(c)+",";
			} catch (Exception e) {
				e.printStackTrace();
			}
    	}
    	return str;
    }
    
    
	public List<Course> getCourseList() {
		return courseList;
	}


	public void setCourseList(List<Course> courseList) {
		this.courseList = courseList;
	}


	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
}
