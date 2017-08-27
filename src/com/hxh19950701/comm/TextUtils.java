package com.hxh19950701.comm;

public class TextUtils {

	private TextUtils() {
		throw new UnsupportedOperationException();
	}

	public static boolean isEmpty(final CharSequence str) {
		return str == null || str.length() == 0;
	}
}
