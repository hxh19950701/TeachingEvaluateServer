package com.hxh19950701.service;

import java.util.List;

import com.hxh19950701.comm.CheckUtils;
import com.hxh19950701.comm.CustomException;
import com.hxh19950701.comm.MD5Util;
import com.hxh19950701.dao.CodeDao;
import com.hxh19950701.pojos.Code;

public class CodeService {

	private CodeDao codeDao;

	public void setCodeDao(CodeDao codeDao) {
		this.codeDao = codeDao;
	}

	public Code create() {
		String codeValue = MD5Util.encipher(System.currentTimeMillis() + "")
				.substring(0, 8);
		Code code = new Code(codeValue);
		codeDao.save(code);
		return code;
	}

	public void checkEnable(String code) throws CustomException.BaseCustomException {
		CheckUtils.checkCode(code);
		List<Code> codes = codeDao.findCode(code);
		if (codes == null || codes.size() == 0) {
			throw new CustomException.NoSuchCodeException(code);
		}
		if (!codes.get(0).isEnable()) {
			throw new CustomException.CodeExpiredException(code);
		}
	}
}