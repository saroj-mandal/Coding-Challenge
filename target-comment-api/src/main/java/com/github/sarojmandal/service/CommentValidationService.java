package com.github.sarojmandal.service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Predicate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.sarojmandal.config.MessageSourceConfig;
import com.github.sarojmandal.model.Comment;

/**
 * This is a validation service for the abusive words.
 * 
 * @author Saroj Mandal
 *
 */
@Service
public class CommentValidationService {

	@Autowired
	MessageSourceConfig messageSourceConfig;

	/**
	 * This predicate is to check the provided word is abusive or not?
	 */
	Predicate<String> containsPredicate = (word) -> {
		return messageSourceConfig.getBadWordList().contains(word);
	};

	/**
	 * This function is to collect all the abusive words in a comment.
	 */
	Function<String, Set<String>> wordContainsFunc = (comment) -> {
		Set<String> abusiveWords = new HashSet<>();
		Arrays.stream(comment.split(" ")).forEach(elem -> {
			if (containsPredicate.test(elem)) {
				abusiveWords.add(elem);
			}
		});
		return abusiveWords;
	};

	/**
	 * This method validates the comment and if there is any abusive words then it
	 * returns the list of abusive words.
	 * 
	 * @param comment
	 * @return
	 */
	public Set<String> validateAbusiveWords(Comment comment) {
		return wordContainsFunc.apply(comment.getBody());
	}
}
