package org.ernest.applications.bt.gui.services.impl;

import java.text.SimpleDateFormat;

import org.ernest.applications.bt.db.manager.notices.ct.CreateNoticeInput;
import org.ernest.applications.bt.db.manager.notices.ct.entries.Notice;
import org.ernest.applications.bt.gui.entities.NoticeDto;
import org.ernest.applications.bt.gui.services.NoticeDataService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class NoticeDataServiceImpl implements NoticeDataService{

	@Value("${service.notices.port}")
	String noticesPort;
	
	@Override
	public String create(String content, String title) {
		
		CreateNoticeInput input = new CreateNoticeInput();
		input.setTitle(title);
		input.setContent(content);
		return new RestTemplate().postForObject("http://localhost:"+noticesPort+"/create", input, String.class);
	}
	
	@Override
	public NoticeDto getNotice(String noticeId) {
		NoticeDto noticeDto = new NoticeDto();
		
		Notice notice = new RestTemplate().getForObject("http://localhost:"+noticesPort+"/retrieve/"+noticeId, Notice.class);
		noticeDto.setId(notice.get_id());
		noticeDto.setTitle(notice.getTitle());
		noticeDto.setContent(notice.getContent());
		noticeDto.setDay(new SimpleDateFormat("d").format(notice.getDate()));
		noticeDto.setMonth(new SimpleDateFormat("MMM").format(notice.getDate()));
		
		return noticeDto;
	}
	
	@Override
	public void delete(String noticeId) {
		new RestTemplate().getForObject("http://localhost:"+noticesPort+"/delete/"+noticeId, String.class);
	}
}
