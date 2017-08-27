package com.hxh19950701.action;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.struts2.ServletActionContext;

import com.hxh19950701.comm.CustomException;
import com.hxh19950701.comm.CustomException.BaseCustomException;
import com.hxh19950701.comm.CustomException.NoOnlineUserException;
import com.hxh19950701.comm.Pagination;
import com.hxh19950701.pojos.User;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class BaseAction extends ActionSupport {

	private static final long serialVersionUID = 1L;

	private String action = null;
	private String url;
	protected Pagination pagination;
	
	private int code = 0;
	private String msg = null;
	private Object data = null;

	protected Map<String, Object> responseJson = new LinkedHashMap<String, Object>();

	public Map<String, Object> getResponseJson() {
		return responseJson;
	}

	private void setupResponseInfo(int code, String msg, Object data){
		this.code = code;
		this.msg = msg;
		this.data = data;
	}
	
	private void buildResponseJson(){
		responseJson.put("code", code);
		responseJson.put("msg", msg);
		responseJson.put("data", data);
	}
	
	protected User getOnlineUser() throws NoOnlineUserException {
		User user = (User) ActionContext.getContext().getSession().get("user");
		if (user == null) {
			throw new CustomException.NoOnlineUserException();
		}
		else {
			return user;
		}
	}

	public Pagination getPagination() {
		return pagination;
	}

	public void setPagination(Pagination pagination) {
		this.pagination = pagination;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String execute() {
		String ip = ServletActionContext.getRequest().getRemoteAddr();
		try {
			if (action == null) 
				throw new CustomException.NoSuchActionException(action);
			else {
				setupResponseInfo(200, "success", executeMethod(action));
				System.out.println(ip + " " + action + " success");
			}
		}
		catch (InvocationTargetException e) {		//反射时异常
			Throwable targetException = (Throwable) e.getTargetException();
			if (targetException instanceof CustomException.BaseCustomException) {
				CustomException.BaseCustomException customException
					= (BaseCustomException) targetException;
				setupResponseInfo(customException.getErrorCode(),
						customException.getErrorMessage(), null);
			}
			else {		//系统级错误
				targetException.printStackTrace();
				setupResponseInfo(-999, targetException.getClass().getSimpleName(), null);
			}
			System.out.println(ip + " " + action + " failed");
		}
		catch (Exception e) {
			e.printStackTrace();
			setupResponseInfo(-999, e.getClass().getSimpleName(), null);
			System.out.println(ip + " " + action + " failed");
		}
		buildResponseJson();
		return SUCCESS;
	}

	@SuppressWarnings("rawtypes")
	private Object executeMethod(String method) throws Exception {
		Class[] c = null;
		Method m = getClass().getMethod(method, c);
		Object[] o = null;
		Object result = m.invoke(this, o);
		return result;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}
