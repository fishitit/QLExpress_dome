package com.ql;


import java.util.ArrayList;
import java.util.List;

import com.github.jsonzou.jmockdata.JMockData;
import com.ql.rule.BaseRule;
import com.ql.rule.RuleExp;
import com.ql.source.Source;

/**
 * 
 * @author wangmengguang
 *
 */
public class Case1 {
	public static void main(String[] args) throws Exception {
		
		CreateMoreRule();
	}
	
	private static void CreateMoreRule() throws Exception {
		List<RuleExp>  list =new ArrayList<RuleExp>();
		long ts1=System.currentTimeMillis();
		for(int i=0;i<100000;i++) {
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
		}
		ts2=System.currentTimeMillis();
		System.out.println(ts2-ts1);
	}
	

		
		
	
	
	
	
}
