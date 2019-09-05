package com.github.sarojmandal.data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.github.sarojmandal.model.Comment;

@Component
public class CommentRepository {
	private Map<String, List<Comment>> comments = new HashMap<>();

	/**
	 * This method returns all comments irrespective of product.
	 * 
	 * @return
	 */
	public Map<String, List<Comment>> getAllComment() {
		return comments;
	}

	/**
	 * This method adds comment to a product
	 * 
	 * @param productId
	 * @param comment
	 * @return
	 */
	public Comment addComment(String productId, Comment comment) {
		comment.setCreatedDate(LocalDateTime.now());
		comment.setProductId(productId);
		if (null != comments.get(productId)) {
			comments.get(productId).add(comment);
		} else {
			List<Comment> productComments = new ArrayList<>();
			productComments.add(comment);
			comments.put(productId, productComments);
		}
		return comment;
	}

	/**
	 * This method returns all the comments for a particular product.
	 * 
	 * @param productId
	 * @return
	 */
	public List<Comment> getCommentByProduct(String productId) {
		return comments.get(productId);
	}
}
