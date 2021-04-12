package com.ql.operator;

import java.util.List;
import static java.util.stream.Collectors.toList;
import com.ql.util.express.Operator;

/**
 * 
 * @author wangmengguang
 *
 */
public class NotExistOperator extends Operator {

	private static final long serialVersionUID = 1L;
	private static final int LENGTH=2;
	
	@Override
	public Object executeInner(Object[] list) throws Exception {
		if(list == null || list.length!=LENGTH) {
			return false;
		}
		List<Object> list1=(List<Object>)list[0];
		List<Object> list2=(List<Object>)list[1];
		List<Object> intersectionList = list1.stream().filter(item -> list2.contains(item)).collect(toList());
		return intersectionList.isEmpty();
	}

}
