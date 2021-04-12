package com.ql.rule;

import java.util.List;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.ql.operator.IntersectOperator;
import com.ql.operator.NotExistOperator;
import com.ql.source.Source;
import com.ql.template.Parameter;
import com.ql.util.express.ExpressRunner;

/**
 * 
 * @author wangmengguang
 *
 */
public class RuleUtils {
	
	
	public static RuleExp importRule(String ruleText) {
		try {
			RuleExp rule= JSON.parseObject(ruleText,new TypeReference<RuleExp>(){}); 
			JSONArray parametersObj= JSON.parseObject(ruleText).getJSONArray("parameters");
			rule.setParameters(JSONObject.parseArray(parametersObj.toJSONString(), Parameter.class));
			return rule;
		} catch (Exception e) {
			e.printStackTrace();
		}
	    return null;
	}
	
	public static String exportRule(RuleExp rule) {
		try {
			return JSON.toJSONString(rule);
		} catch (Exception e) {
			e.printStackTrace();
		}
	    return null;
	}
	
	
	/**
	 * 验证公式
	 * @param mocktext
	 * @param value
	 * @return
	 * @throws Exception
	 */
	public static Object verifyRule (RuleExp rule,String mocktext,Object value) throws Exception {
		ExpressRunner runner = new ExpressRunner();
		runner.addOperator("已在",new IntersectOperator());
		runner.addOperator("不存在",new NotExistOperator());
		
		Source source=JSON.parseObject(mocktext, Source.class); 
		rule.setSource(source);
		rule.setRuleContext();
		System.out.println("参数："+JSON.toJSONString(rule.getContext()));
		System.out.println("表达式："+rule.getExp());
		Object result = runner.execute(rule.getExp(), rule.getContext(), null, true, false);
		System.out.println("结果： " + JSON.toJSONString(result) );
		return result;
	}
}
