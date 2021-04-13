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

import lombok.extern.slf4j.Slf4j;

/**
 * 执行器
 * @author wangmengguang
 *
 */
@Slf4j
public class RuleHandle {
	private static boolean isInitialRunner = false;
	private static ExpressRunner runner;
	
	private static ThreadFactory namedThreadFactory = new ThreadFactoryBuilder().setNameFormat("rule-pool-%d").build();
	private static ExecutorService pool = new ThreadPoolExecutor(20, 60, 1L, TimeUnit.SECONDS, new SynchronousQueue<Runnable>(),namedThreadFactory);
	
	static{
		runner = new ExpressRunner();
		initRunner(runner);
	}
	
	private static void initRunner(ExpressRunner runner) {
		if (isInitialRunner == true) {
			return;
		}
		synchronized (runner) {
			if (isInitialRunner == true) {
				return;
			}
			try {
				runner.addOperator("已在",new IntersectOperator());
				runner.addOperator("不存在",new NotExistOperator());
			} catch (Exception e) {
				log.error("初始化失败表达式",e);
				throw new RuntimeException("初始化失败表达式", e);
			}
		}
		isInitialRunner = true;
	}
	
	/**
	 * 多线程执行,等到回调
	 * @param rule
	 * @return
	 */
	public static Object execute(RuleExp rule) {
		initRunner(runner);
		Future<Object> fs =pool.submit(new Callable<Object>() {
			@Override
			public Object call() throws Exception {
				return executeRule(rule);
			}
		});
		try {
			Object result = fs==null?null:fs.get();
			if(result!=null){
				log.debug("参数："+JSON.toJSONString(rule.getContext()));
				log.debug("表达式："+rule.getExp());
				log.debug("结果："+result);
			}
			return result;
		} catch (Exception e) {
			log.error("执行异常",e);
		}
		return null;
	}
	
	/**
	 * 执行内容
	 * @param rule
	 * @return
	 */
	private static Object executeRule(RuleExp rule) {
		try {
			if(rule.getSource()==null) {
				throw new RuntimeException("资源池为空");
			}
			rule.setRuleContext();
			return runner.execute(rule.getExp(), rule.getContext(), null, true, false);
		} catch (Exception e) {
			throw new RuntimeException("执行异常",e);
		}
	}
	
	
}
