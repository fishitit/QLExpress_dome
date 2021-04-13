package com.ql;


import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.github.jsonzou.jmockdata.JMockData;
import com.ql.rule.BaseRule;
import com.ql.rule.RuleExp;
import com.ql.rule.RuleUtils;
import com.ql.source.Source;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author wangmengguang
 *
 */
@Slf4j
public class Case1 {
	public static void main(String[] args) throws Exception {
		//CreateMoreRule();
		importRole();
		log.info("info");
	}
	
	
	private static void importRole() {
		try {
			String text="{\"code\":\"buyCourseLeX\",\"exp\":\"if(src.courseList.size()  <= x)then{true}else{false}  \",\"parameters\":[{\"index\":0,\"name\":\"x值\",\"range\":\"取值范围(正整数)\",\"ruleMapCode\":\"x\",\"sourceCode\":\"src.courseList.size()\",\"type\":\"Integer\",\"value\":\"9\"}],\"ruleName\":\"判断用户购买的课程数量是否小于等于X\"}";
			RuleExp rule =RuleUtils.importRule(text);
			rule.setSource(JMockData.mock(Source.class));
			rule.replaceExp();
			System.out.println(RuleHandle.execute(rule));
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void CreateMoreRule() throws Exception {
		List<RuleExp>  list =new ArrayList<RuleExp>();
		long ts1=System.currentTimeMillis();
		for(int i=0;i<10;i++) {
			BaseRule test =new BaseRule();
			RuleExp rule=test.buyCourseLeX(i);
			list.add(rule);
		}
		long ts2=System.currentTimeMillis();
		System.out.println(ts2-ts1);
		ts1=System.currentTimeMillis();
		for(RuleExp rule:list) {
			rule.setSource(JMockData.mock(Source.class));
			RuleHandle.execute(rule);
			System.out.println(JSON.toJSONString(rule));
		}
		ts2=System.currentTimeMillis();
		System.out.println(ts2-ts1);
	}
	

		
		
	
	
	
	
}
