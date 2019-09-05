package com.github.sarojmandal.controller;

import java.text.MessageFormat;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.github.sarojmandal.data.CommentRepository;
import com.github.sarojmandal.exception.InvalidRequestException;
import com.github.sarojmandal.model.Comment;
import com.github.sarojmandal.service.CommentValidationService;

/**
 * This is a controller for comment
 * 
 * @author Saroj Mandal
 *
 */
@RestController
public class CommentController {

	@Autowired
	CommentValidationService commentValidationService;

	@Autowired
	CommentRepository commentRepository;

	/**
	 * This method takes care of posting comment
	 * 
	 * @param comment
	 * @param productId
	 * @return
	 */
	@PostMapping(path = "/products/{productId}/comments", consumes = { "application/json",
			"application/xml" }, produces = { "application/json", "application/xml" })
	public ResponseEntity<Comment> postComment(@RequestBody Comment comment, @PathVariable String productId) {
		Set<String> abusiveWords = commentValidationService.validateAbusiveWords(comment);
		HttpStatus status = HttpStatus.OK;
		if (!abusiveWords.isEmpty()) {
			throw new InvalidRequestException(
					MessageFormat.format("Comment contains objectionable words like {0}", abusiveWords.toString()));
		}
		Comment createdComment = commentRepository.addComment(productId, comment);
		return new ResponseEntity<>(createdComment, status);
	}

	/**
	 * This method is for getting comments for a particular product
	 * 
	 * @param productId
	 * @return
	 */
	@GetMapping(path = "/products/{productId}/comments", produces = { "application/json", "application/xml" })
	public ResponseEntity<List<Comment>> getAllComment(@PathVariable String productId) {
		List<Comment> comment = commentRepository.getCommentByProduct(productId);
		HttpStatus status = HttpStatus.OK;
		if (null == comment || comment.isEmpty()) {
			status = HttpStatus.NOT_FOUND;
		}
		return new ResponseEntity<>(comment, status);
	}

}
