package com.ql;


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
		test3();
	}
	
	private static void test3() throws Exception {
		long ts1=System.currentTimeMillis();
		
		for(int i=0;i<100000;i++) {
			BaseRule test =new BaseRule();
			RuleExp rule=test.buyCourseNotInclude("1,2,234,213,12,4,12,31,245,12,5,125,12,3,12,312,54,125,12,4,12,4,124,1,24,12,4,124,1,12,51,5123,6,7,512,34,12,43,63,246");
			rule.setSource(JMockData.mock(Source.class));
			System.out.println(RuleHandle.execute(rule));
		}
		long ts2=System.currentTimeMillis();
		System.out.println(ts2-ts1);
	}
	

		
		
	
	
	
	
}
