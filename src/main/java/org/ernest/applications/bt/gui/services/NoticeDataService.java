package org.ernest.applications.bt.gui.services;

import org.ernest.applications.bt.gui.entities.NoticeDto;

public interface NoticeDataService {

	String create(String content, String title);

	NoticeDto getNotice(String noticeId);

	void delete(String noticeId);

}
