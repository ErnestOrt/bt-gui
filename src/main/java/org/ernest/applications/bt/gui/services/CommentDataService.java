package org.ernest.applications.bt.gui.services;

import java.util.List;

import org.ernest.applications.bt.gui.entities.CommentDto;
import org.ernest.applications.bt.gui.entities.UserDto;

public interface CommentDataService {

	String create(String userId, String content);

	CommentDto getComment(String commentId, List<UserDto> users);

}
