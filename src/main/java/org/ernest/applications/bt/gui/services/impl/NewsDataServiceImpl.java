package org.ernest.applications.bt.gui.services.impl;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.ernest.applications.bt.gui.entities.ArticleDto;
import org.ernest.applications.bt.gui.services.NewsDataService;
import org.ernest.applications.bt.manager.news.ct.Article;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class NewsDataServiceImpl implements NewsDataService {

	@Value("${service.news.port}")
	String newsPort;
	
	@Override
	public List<ArticleDto> get() {
		return Arrays.asList(new RestTemplate().getForObject("http://localhost:" + newsPort + "/getnews", Article[].class))
			  .stream()
			  .map(article -> {
				  ArticleDto articleDto = new ArticleDto();
				  articleDto.setTitle(article.getTitle());
				  articleDto.setDescription(article.getDescription());
				  articleDto.setUrl(article.getUrl());
				  articleDto.setDay(new SimpleDateFormat("d").format(article.getDate()));
				  articleDto.setMonth(new SimpleDateFormat("MMM").format(article.getDate()));
				  return articleDto;
			  })
			  .collect(Collectors.toList());
	}
}