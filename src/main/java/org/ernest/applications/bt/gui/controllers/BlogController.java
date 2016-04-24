package org.ernest.applications.bt.gui.controllers;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.ernest.applications.bt.gui.services.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class BlogController {

	@Autowired
	BlogService blogService;
	
	@RequestMapping(value="/plataforma-cliclista/ciclismo/sabati-aplicacion-ciclista-blog", method = RequestMethod.GET)
	public String blogSabati(Model model){
		List<PostDto> postInfoDtoList = blogService.getAllPostInformation().stream()
																				.sorted(Comparator.comparing(PostDto::getDate))
																				.limit(70)
																				.collect(Collectors.toList());
		Collections.reverse(postInfoDtoList);
		model.addAttribute("posts", postInfoDtoList);
		return "sabati-aplicacion-ciclista-blog";
	}
	
	@RequestMapping(value="/plataforma-cliclista/ciclismo/sabati-aplicacion-ciclista-post/{postId}", method = RequestMethod.GET)
	public String postSabati(@PathVariable("postId") String postId, Model model){
		
		model.addAttribute("post", blogService.getPost(postId));
		return "sabati-aplicacion-ciclista-post";
	}
}
