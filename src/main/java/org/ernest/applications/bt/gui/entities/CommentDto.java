package org.ernest.applications.bt.gui.entities;

import java.util.Date;

public class CommentDto {

	private Date creationDate;
	private String ownerId;
	private String ownerName;
	private int ownerIcon;
	private String content;
	
	public Date getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	public String getOwnerId() {
		return ownerId;
	}
	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
	}
	public String getOwnerName() {
		return ownerName;
	}
	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}
	public int getOwnerIcon() {
		return ownerIcon;
	}
	public void setOwnerIcon(int ownerIcon) {
		this.ownerIcon = ownerIcon;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
}