package com.ql.rule;


import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.lang.StringUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.ql.operator.IntersectOperator;
import com.ql.operator.NotExistOperator;
import com.ql.source.Course;
import com.ql.source.Source;
import com.ql.template.Parameter;
import com.ql.util.express.ExpressRunner;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author wangmengguang
 *
 */
@Slf4j
public class RuleUtils {
	
	public static RuleExp importRule(String ruleText) {
		try {
			if(StringUtils.isBlank(ruleText)) {
				return null;
			}
			RuleExp rule= JSON.parseObject(ruleText,new TypeReference<RuleExp>(){}); 
			JSONArray parametersObj= JSON.parseObject(ruleText).getJSONArray("parameters");
			rule.setParameters(JSONObject.parseArray(parametersObj.toJSONString(), Parameter.class));
			return rule;
		} catch (Exception e) {
			log.error("转换类型出错",e);
		}
	    return null;
	}
	
	public static Source importSource(String sourceText) {
		try {
			if(StringUtils.isBlank(sourceText)) {
				return null;
			}
			Source rource= JSON.parseObject(sourceText,new TypeReference<Source>(){}); 
			JSONArray courseListObj= JSON.parseObject(sourceText).getJSONArray("courseList");
			rource.setCourseList(JSONObject.parseArray(courseListObj.toJSONString(), Course.class));
			return rource;
		} catch (Exception e) {
			log.error("转换类型出错",e);
		}
	    return null;
	}
	
	public static String exportRule(RuleExp rule) {
		try {
			if(rule == null) {
				return null;
			}
			return JSON.toJSONString(rule);
		} catch (Exception e) {
			log.error("导出json出错",e);
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
	public static Map<String,Object> verifyRule (RuleExp rule,String mocktext) throws Exception {
		Map<String,Object> resultMap =new ConcurrentHashMap<String, Object>();
		ExpressRunner runner = new ExpressRunner();
		runner.addOperator("已在",new IntersectOperator());
		runner.addOperator("不存在",new NotExistOperator());

		Source source=importSource(mocktext); 
		rule.setSource(source);
		rule.setRuleContext();
		
		Object result = runner.execute(rule.getExp(), rule.getContext(), null, true, false);
		resultMap.put("prams", JSON.toJSONString(rule.getContext()));
		resultMap.put("exp", rule.getExp());
		resultMap.put("data", JSON.toJSONString(result));
		return resultMap;
	}
}
