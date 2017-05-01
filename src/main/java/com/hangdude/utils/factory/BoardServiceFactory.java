package com.hangdude.utils.factory;

import com.hangdude.service.BoardService;
import com.hangdude.service.impl.HangdudeBoardService;

/**
 * A singleton class that returns an instance of a {@link BoardService}
 * 
 * @author ahamouda
 *
 */
public final class BoardServiceFactory implements Cloneable {

	private static final HangdudeBoardService BOARD_SERVICE;

	static {
		BOARD_SERVICE = new HangdudeBoardService();
	}

	private BoardServiceFactory() {
	}

	public static HangdudeBoardService getInstance() {
		return BOARD_SERVICE;
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		throw new CloneNotSupportedException();
	}

}
