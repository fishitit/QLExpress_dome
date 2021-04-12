package com.ql;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import com.alibaba.fastjson.JSON;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.ql.operator.IntersectOperator;
import com.ql.operator.NotExistOperator;
import com.ql.rule.RuleExp;
import com.ql.util.express.ExpressRunner;

/**
 * 执行器
 * @author wangmengguang
 *
 */
public class RuleHandle {
	private static ExpressRunner runner = new ExpressRunner();
	private static ThreadFactory namedThreadFactory = new ThreadFactoryBuilder().setNameFormat("rule-pool-%d").build();
	private static ExecutorService pool = new ThreadPoolExecutor(20, 60, 1L, TimeUnit.SECONDS, new SynchronousQueue<Runnable>(),namedThreadFactory);
	static{
		try {
			runner.addOperator("已在",new IntersectOperator());
			runner.addOperator("不存在",new NotExistOperator());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static Boolean execute(RuleExp rule) {
		Future<Boolean> fs =pool.submit(new Callable<Boolean>() {
			@Override
			public Boolean call() throws Exception {
				return executeRule(rule);
			}
		});
		
		try {
			Boolean result = fs==null?null:fs.get();
			if(result==null){
				System.out.println(String.format("hys-nullponiter-test:fs=%s,result=%s", fs,result));
			}
			if(result) {
				System.out.println("参数："+JSON.toJSONString(rule.getContext()));
				System.out.println("表达式："+rule.getExp());
				System.out.println("结果："+result);
			}
			return result;
		} catch (Exception e) {
			e.fillInStackTrace();
		}
		return null;
	}
	
	private static Boolean executeRule(RuleExp rule) {
		try {
			if(rule.getSource()==null) {
				return false;
			}
			rule.setRuleContext();
			Object result = runner.execute(rule.getExp(), rule.getContext(), null, true, false);
			if(result !=null && result instanceof Boolean) {
				return (Boolean)result;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	
}
