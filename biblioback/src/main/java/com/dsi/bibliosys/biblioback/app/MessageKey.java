package com.dsi.bibliosys.biblioback.app;

public enum MessageKey {

	// ==============================
	// === Exception
	// ==============================
	NULL_ARGUMENT_EXCEPTION("nullargumentexception"), ILLEGAL_ARGUMENT_EXCEPTION("illegalargumentexception");

	public final String key;

	MessageKey(String key) {
		this.key = key;
	}

}
