package com.edao.codes.patterns;


import java.util.ArrayList;
import java.util.List;

/**
 * @author liushuai
 *
 */
public class FilterChainTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
//		String str = "这是一个敏感词<a>民主<a>";
//		HTMLFilter htmlFilter = new HTMLFilter();
//		SensitiveFilter sensitiveFilter = new SensitiveFilter();
//		FilterChain chain = new FilterChain();
//		chain.addFilter(htmlFilter);
//		chain.addFilter(sensitiveFilter);
//		StringProcessor processor = new StringProcessor();
//		processor.setChain(chain);
//		String newStr = processor.process(str);
//		System.out.println(newStr);
		
		String ruleName = "SSSS_OWNER_RULE";
		RuleNameFilter ruleNameFilter = new RuleNameFilter();
		RuleClassFilterChain chain = new RuleClassFilterChain();
		chain.addFilter(ruleNameFilter);
		RuleClassProcessor processor = new RuleClassProcessor();
		processor.setChain(chain);
		ResponseObj response = processor.process(ruleName);
		System.out.println(response.getRuleClass());
	}

}
class ResponseObj {
	String ruleClass;
	String errMsg;
	String ruleInfo;
	public String getRuleClass() {
		return ruleClass;
	}
	public void setRuleClass(String ruleClass) {
		this.ruleClass = ruleClass;
	}
	public String getErrMsg() {
		return errMsg;
	}
	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}
	public String getRuleInfo() {
		return ruleInfo;
	}
	public void setRuleInfo(String ruleInfo) {
		this.ruleInfo = ruleInfo;
	}
	
}
interface RuleClassFilter {
	ResponseObj doFilter(String str);
}

class RuleNameFilter implements RuleClassFilter {
	
	private String getStatus(String ruleName) {
		if (ruleName.endsWith("_STATUS_RULE")) {
			return "STATUS_RULE";  // 状态规则 
		} else if (ruleName.endsWith("_OWNER_RULE")) {
			return "OWNER_RULE";   // Owner规则 
		} else if (ruleName.endsWith("_SENSITIVE_RULE")) {
			return "SENSITIVE_RULE";  // Sensitive规则 
		} else if (ruleName.endsWith("_ACTION_RULE")) {
			return "ACTION_RULE";  // action规则
		} else if (ruleName.startsWith("PASS_") 
				&& ruleName.contains("_ACCESS_")) {
			return "ACCESS_RULE_ASSET"; // 资产通过访问规则
		} else if (ruleName.contains("_PASS_RULE")) {
			return "ACCESS_RULE_DB";    // 数据库访问通过规则
		} else if (ruleName.startsWith("REFUSE_") 
				&& ruleName.contains("_ACCESS_")) {
			return "REFUSE_ACCESS_RULE_ASSET"; // 资产拒绝访问规则
		} else if (ruleName.contains("_REFUSE_RULE")) {
			return "REFUSE_ACCESS_RULE_DB";  // 数据库访问拒绝规则
		} else if ("NOREGISTER".equals(ruleName)) {
			return "NOREGISTER";
		} else if (ruleName.endsWith("_SIMURULE")) {
			return "SIMURULE";
		} else if (ruleName.contains("_SQL_RULE")) {
			return "CAN_NOT_EXTRACT";
		} else { // 授权规则
			return "PRIV_RULE";
		}
	}

	public ResponseObj doFilter(String ruleName) {
		String ruleClass = getStatus(ruleName);
		ResponseObj obj = new ResponseObj();
		obj.setRuleClass(ruleClass);
		return obj;
	}
	
	
	
}

class RuleClassFilterChain implements RuleClassFilter{
	
	List<RuleClassFilter> filters = new ArrayList<RuleClassFilter>();
	
	public void addFilter(RuleClassFilter filter) {
		if (filter != null) {
			filters.add(filter);
		}
	}

	public ResponseObj doFilter(String str) {
		ResponseObj response = null;
		for (RuleClassFilter filter : filters) {
			response = filter.doFilter(str);
		}
		return response;
	}
	
}

class RuleClassProcessor {
	RuleClassFilterChain chain;

	public RuleClassFilterChain getChain() {
		return chain;
	}

	public void setChain(RuleClassFilterChain chain) {
		this.chain = chain;
	}
	
	public ResponseObj process(String ruleName) {
		return chain.doFilter(ruleName);
	}
}

interface Filter {
	String doFilter(String str);
}

class HTMLFilter implements Filter {
	public String doFilter(String str) {
		if (str == null) return "";
		
		str = str.replace("<", "[");
		str = str.replace(">", "]");
		
		return str;
	}
}

class SensitiveFilter implements Filter {
	public String doFilter(String str) {
		if (str == null) return "";
		
		str = str.replace("民主", "minzhu");
		
		return str;
	}
}

class FilterChain implements Filter {
	private List<Filter> chain = new ArrayList<Filter>();
	
	public void addFilter(Filter filter) {
		chain.add(filter);
	}
	
	public String doFilter(String str) {
		if (str == null) return "";
		for(Filter filter : chain) {
			str = filter.doFilter(str);
		}
		return str;
	}
	
}

class StringProcessor {
	FilterChain chain;
	public FilterChain getChain() {
		return chain;
	}
	public void setChain(FilterChain chain) {
		this.chain = chain;
	}
	
	public String process(String str) {
		return chain.doFilter(str);
	}
}
