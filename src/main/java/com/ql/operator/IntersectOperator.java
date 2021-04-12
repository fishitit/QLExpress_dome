package com.ql.operator;

import static java.util.stream.Collectors.toList;

import java.util.List;

import org.apache.commons.collections4.CollectionUtils;

import com.ql.util.express.Operator;

/**
 * 
 * @author wangmengguang
 *
 */
public class IntersectOperator extends Operator {

	private static final long serialVersionUID = 1L;
	private static final int LENGTH=2;
	
	@Override
	public Object executeInner(Object[] list) throws Exception {
		if(list == null || list.length!=LENGTH) {
			return false;
		}
		List<Object> list1=(List<Object>)list[0];
		List<Object> list2=(List<Object>)list[1];
		if(CollectionUtils.isEmpty(list1)||CollectionUtils.isEmpty(list2)) {
			return false;
		}
		
		
		List<Object> intersectionList = list1.stream().filter(item -> list2.contains(item)).collect(toList());
		return !intersectionList.isEmpty();
	}

}
