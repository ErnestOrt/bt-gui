package org.ernest.applications.bt.gui.services.impl;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.ernest.applications.bt.db.manager.blog.ct.entities.PostContent;
import org.ernest.applications.bt.db.manager.blog.ct.entities.PostInformation;
import org.ernest.applications.bt.gui.controllers.PostDto;
import org.ernest.applications.bt.gui.services.BlogService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class BlogServiceImpl implements BlogService{

	@Value("${service.blog.port}")
	String blogPort;
	
	@Override
	public List<PostDto> getAllPostInformation() {
		List<PostInformation> postInformationList = Arrays.asList(new RestTemplate().getForObject("http://localhost:"+blogPort+"/retrieveall", PostInformation[].class));
		
		return postInformationList.stream().map(info -> {
														PostDto postInfoDto = new PostDto();
														postInfoDto.setId(info.get_id());
														postInfoDto.setTitle(info.getTitle());
														postInfoDto.setDescription(info.getTitle());
														postInfoDto.setDate(info.getCreationDate());
														return postInfoDto;})
											.collect(Collectors.toList());
	}

	@Override
	public PostDto getPost(String postId) {
		
		PostInformation postInformation = new RestTemplate().getForObject("http://localhost:"+blogPort+"/retrieveinfo/"+postId, PostInformation.class);
		PostContent postContent = new RestTemplate().getForObject("http://localhost:"+blogPort+"/retrievecontent/"+postId, PostContent.class);
		
		PostDto postDto = new PostDto();
		postDto.setTitle(postInformation.getTitle());
		postDto.setDescription(postInformation.getDescription());
		postDto.setDate(postInformation.getCreationDate());
		postDto.setContent(postContent.getContent());

		return postDto;
	}

}
