package com.ql.rule;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.StringUtils;

import com.ql.source.Source;
import com.ql.template.Parameter;
import com.ql.util.express.DefaultContext;

/**
 * 规则定义
 * 
 * @author wangmengguang
 *
 */
public class RuleExp implements Serializable {

	private static final long serialVersionUID = -1217969839277001264L;

	/**
	 * 执行规则的数据源
	 */
	private DefaultContext<String, Object> context = new DefaultContext<String, Object>();

	/**
	 * 自定义入参条件
	 */
	private List<Parameter> parameters = new ArrayList<Parameter>();

	/**
	 * 执行QL的公式
	 */
	private String exp;

	/**
	 * 规则名称
	 */
	private String ruleName;

	/**
	 * 规则的唯一code
	 */
	private String code;

	/**
	 * 源数据资源池，用于替换表达式和生成模拟数据，资源池的数据最后会落地 context
	 */
	private Source source;

	public RuleExp(String exp) {
		this.exp = exp;
	}

	/**
	 * 组装资源 系统规则： list_ 开头表示枚举该对象的字段返回 以逗号区分的字符串 size_ 开头表示统计枚举对象的总长度
	 */
	public void setRuleContext() {
		// 资源池全部加入到上下文
		getContext().put("src", getSource());

		// 自定义入参加入到上下文(如果不是组件定义有可能是空数组)
		for (Parameter parm : getParameters()) {
			// 集合处理
			if (StringUtils.isNotBlank(parm.getValues())
					&& StringUtils.equalsIgnoreCase(parm.getSourceCode(), "list_")) {
				String[] combo = parm.getSourceCode().split("_");
				if (combo.length == 3 && StringUtils.containsIgnoreCase(combo[1], "courseList")) {
					getContext().put(parm.getSourceCode(),
							Arrays.asList(getSource().getCourseFieldListToString(combo[2]).split(",")));
				}
			}
		}

		// 检查上下文
		Set set = getContext().keySet();
		for (Iterator iter = set.iterator(); iter.hasNext();) {
			String key = (String) iter.next();
			if (StringUtils.isBlank(key)) {
				continue;
			}
			if (StringUtils.equalsIgnoreCase(key, "list_")) {
				String[] combo = key.split("_");
				if (combo.length == 3 && StringUtils.containsIgnoreCase(combo[1], "courseList")) {
					getContext().put(key, Arrays.asList(getSource().getCourseFieldListToString(combo[2]).split(",")));
				}
			}
		}
	}

	/**
	 * 替换变量
	 */
	public void replaceExp() {
		int i = 0;
		for (Parameter parm : parameters) {
			// 替换$Num 占位符（用于替换资源的字段）
			if (i == parm.getIndex() && StringUtils.isNotBlank(parm.getSourceCode())) {
				this.exp = this.exp.replace("$" + i, parm.getSourceCode());
			}

			// 替换 条件数值（单个）
			if (i == parm.getIndex() && StringUtils.isNotBlank(parm.getValue())) {
				this.context.put(parm.getRuleMapCode(), parm.getValue());
			}

			// 替换 条件数值（字符列表需要二次转化）
			if (i == parm.getIndex() && StringUtils.isNotBlank(parm.getValues())) {
				this.context.put(parm.getRuleMapCode(), Arrays.asList(parm.getValues().split(parm.getGrepCode())));
			}
			i++;
		}
	}

	public DefaultContext<String, Object> getContext() {
		return context;
	}

	public void setContext(DefaultContext<String, Object> context) {
		this.context = context;
	}

	public List<Parameter> getParameters() {
		return parameters;
	}

	public void setParameters(List<Parameter> parameters) {
		this.parameters = parameters;
	}

	public String getExp() {
		return exp;
	}

	public void setExp(String exp) {
		this.exp = exp;
	}

	public String getRuleName() {
		return ruleName;
	}

	public void setRuleName(String ruleName) {
		this.ruleName = ruleName;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Source getSource() {
		return source;
	}

	public void setSource(Source source) {
		this.source = source;
	}
}
