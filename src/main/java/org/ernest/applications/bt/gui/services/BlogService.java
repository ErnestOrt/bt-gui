package org.ernest.applications.bt.gui.services;

import java.util.List;

import org.ernest.applications.bt.gui.controllers.PostDto;

public interface BlogService {

	List<PostDto> getAllPostInformation();

	PostDto getPost(String postId);

}
