package com.training.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.training.exceptions.DuplicateException;
import com.training.exceptions.NewsExceptionHandler;
import com.training.exceptions.NotFoundException;
import com.training.models.NewsDTO;
import com.training.models.ResponseData;
import com.training.services_impl.NewsServiceImpl;
import com.training.services_impl.ResponseDataServiceImpl;

@CrossOrigin(origins="*", allowedHeaders="*")
@RestController
@RequestMapping("/data")
public class NewsController {

	@Autowired
	NewsServiceImpl newsService;

	@Autowired
	ResponseDataServiceImpl responseService;

	@Autowired
	NewsExceptionHandler newsException;

	@GetMapping(value = "")
	public ResponseEntity<ResponseData> getNews() {
		return responseService.responseSuccess(newsService.getNews());
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<ResponseData> getNewsById(@PathVariable("id") long id) {
		try {
			NewsDTO news = newsService.getNewsById(id);
			if (news == null)
				throw new NotFoundException();
			return responseService.responseSuccess(news);
		} catch (NotFoundException ne) {
			return newsException.notFoundException(ne);
		}
	}

	@PostMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseData> addNews(@RequestBody NewsDTO newsDTO) {
		try {
			char status = newsService.addNews(newsDTO);
			if (status == 'd')
				throw new DuplicateException();
			else if (status == 'n')
				throw new NotFoundException();
			return responseService.responseSuccess(newsDTO);
		} catch (DuplicateException de) {
			return newsException.duplicateException(de);
		} catch (NotFoundException ne) {
			return newsException.notFoundException(ne);
		}
	}

	@PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseData> updateNews(@PathVariable("id") long id, @RequestBody NewsDTO newsDTO) {
		try {
			if (!newsService.updateNews(id, newsDTO))
				throw new NotFoundException();
			return responseService.responseSuccess(newsDTO);
		} catch (NotFoundException ne) {
			return newsException.notFoundException(ne);
		}

	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<ResponseData> deleteNews(@PathVariable("id") long id) {
		try {
			if(!newsService.deleteNews(id))
				throw new NotFoundException();
			return responseService.responseSuccess(null);
		} catch (NotFoundException ne) {
			return newsException.notFoundException(ne);
		}
	}
}
