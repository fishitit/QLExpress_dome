package com.ql.rule;


import com.alibaba.fastjson.JSON;
import com.ql.RuleHandle;
import com.ql.source.Source;
import com.ql.template.Parameter;

/**
 * 
 * @author wangmengguang
 *
 */
public class BaseRule {
		
	public static void main(String[] args) throws Exception {
		BaseRule test =new BaseRule();
		String json="{\"courseList\":[{\"id\":3669,\"name\":\"1iASO\",\"price\":5735.82,\"purchasedate\":2490816306221},{\"id\":3668,\"name\":\"1iASO\",\"price\":5735.82,\"purchasedate\":2490816306221}],\"user\":{\"age\":31,\"gender\":1,\"value\":5670}}";
		
		RuleExp rule=test.buyCourseNumRange(2,4);
		System.out.println(JSON.toJSONString(rule));
		
		rule.setSource(JSON.parseObject(json, Source.class));
		System.out.println(JSON.toJSONString(RuleHandle.execute(rule)));
	}
	
	
	/**
	 * 年龄大于X
	 * @param x
	 * @return
	 */
	public RuleExp userAgeGeqX(Integer x) {
		String express = "if($0 > x)then{true}else{false}";
		RuleExp rule= new RuleExp(express);
		rule.setCode("userAgeGeqX");
		rule.setRuleName("用户宝宝年龄是否大于"+x);

		Parameter p1 =new Parameter("年龄","Integer",0,"src.user.age","x",x.toString(),"取值范围(0~500)");
		rule.getParameters().add(p1);
		rule.replaceExp();
		return rule;
	}
	
	
	/**
	 * 判断用户购买的课程数量是否在XY范围内
	 * @param min
	 * @param max
	 * @return
	 */
	public RuleExp buyCourseNumRange(Integer x,Integer y) {
		String express = "if($0 >= x && $1 <=y)then{true}else{false}";
		RuleExp rule= new RuleExp(express);
		rule.setCode("buyCourseNumRange");
		rule.setRuleName("判断用户购买的课程数量是否在XY范围内");
		
		Parameter p1 =new Parameter("x值","Integer",0,"src.courseList.size()","x",x.toString(),"取值范围(正整数)");
		Parameter p2 =new Parameter("y值","Integer",1,"src.courseList.size()","y",y.toString(),"取值范围(正整数)");
		rule.getParameters().add(p1);
		rule.getParameters().add(p2);
		rule.replaceExp();
		return rule;
	}
	
	/**
	 * 判断用户购买的课程数量是否大于等于X
	 * @param x
	 * @return
	 */
	public RuleExp buyCourseGteX(Integer x) {
		String express = "if(src.courseList.size() >= x)then{true}else{false}  ";
		RuleExp rule= new RuleExp(express);
		rule.setCode("buyCourseGteX");
		rule.setRuleName("判断用户购买的课程数量是否大于等于X");
		
		Parameter p1 =new Parameter("x值","Integer",0,"src.courseList.size()","x",x.toString(),"取值范围(正整数)");
		rule.getParameters().add(p1);
		rule.replaceExp();
		return rule;
	}
	
	/**
	 * 判断用户购买的课程数量是否小于等于X
	 * @param x
	 * @return
	 */
	public RuleExp buyCourseLeX(Integer x) {
		String express = "if(src.courseList.size()  <=x)then{true}else{false}  ";
		RuleExp rule= new RuleExp(express);
		rule.setCode("buyCourseLeX");
		rule.setRuleName("判断用户购买的课程数量是否小于等于X");

		Parameter p1 =new Parameter("x值","Integer",0,"src.courseList.size()","x",x.toString(),"取值范围(正整数)");
		rule.getParameters().add(p1);
		rule.replaceExp();
		return rule;
	}
	
	/**
	 * 判断用户购买的课程id集合是否已在指定的课程集合
	 * @param min
	 * @param max
	 * @return
	 */
	public RuleExp buyCourseInclude(String ids) {
		String express = "if(list_courseList_id 已在 ids)then{true}else{false}  ";
		RuleExp rule= new RuleExp(express);
		rule.setCode("buyCourseInclude");
		rule.setRuleName("判断用户购买的课程id集合是否已在指定的课程集合");
		
		Parameter p1 =new Parameter("ids","Integer",0,"list_courseList_id","ids",ids.toString(),",","id列表(以,号区分)");
		rule.getParameters().add(p1);
		rule.replaceExp();
		
		return rule;
	}
	
	/**
	 * 判断用户购买的课程id集合是否不存在指定的课程集合
	 * @param min
	 * @param max
	 * @return
	 */
	public RuleExp buyCourseNotInclude(String ids) {
		String express = "if(list_courseList_id 不存在 ids)then{true}else{false}  ";
		RuleExp rule= new RuleExp(express);
		rule.setCode("buyCourseNotInclude");
		rule.setRuleName("判断用户购买的课程id集合是否不存在指定的课程集合");
		
		Parameter p1 =new Parameter("ids","Integer",0,"list_courseList_id","ids",ids.toString(),",","id列表(以,号区分)");
		rule.getParameters().add(p1);
		rule.replaceExp();
		return rule;
	}
}
