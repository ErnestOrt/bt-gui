package org.ernest.applications.bt.gui.services.impl;

import java.util.Date;
import java.util.List;

import org.ernest.applications.bt.db.manager.comments.ct.CreateCommentInput;
import org.ernest.applications.bt.db.manager.comments.ct.entities.Comment;
import org.ernest.applications.bt.gui.entities.CommentDto;
import org.ernest.applications.bt.gui.entities.UserDto;
import org.ernest.applications.bt.gui.services.CommentDataService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class CommentDataServiceImpl implements CommentDataService {

	@Value("${service.comments.port}")
	String commentsPort;
	
	@Override
	public String create(String userId, String content) {
		CreateCommentInput createComment = new CreateCommentInput(); 
		createComment.setContent(content);
		createComment.setCreationDate(new Date());
		createComment.setUserIdOwner(userId);
		
		return new RestTemplate().postForObject("http://localhost:" + commentsPort + "/create", createComment, String.class);
	}

	@Override
	public CommentDto getComment(String commentId, List<UserDto> users) {
		Comment comment = new RestTemplate().getForObject("http://localhost:" + commentsPort + "/retrieve/"+commentId, Comment.class);
		
		CommentDto commentDto = new CommentDto();
		commentDto.setOwnerId(comment.getUserIdOwner());
		UserDto userDto = users.stream().filter(user -> user.getId().equals(comment.getUserIdOwner())).findAny().get();
		commentDto.setOwnerName(userDto.getName());
		commentDto.setOwnerIcon(userDto.getAvatarIcon());
		commentDto.setContent(comment.getContent());
		commentDto.setCreationDate(comment.getCreationDate());
		
		return commentDto;
	}
}