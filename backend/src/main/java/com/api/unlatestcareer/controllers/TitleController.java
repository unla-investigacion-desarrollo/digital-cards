package com.api.unlatestcareer.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.unlatestcareer.exception.CustomNotFoundException;
import com.api.unlatestcareer.helpers.ViewRouteHelper;
import com.api.unlatestcareer.models.TitleModel;
import com.api.unlatestcareer.services.impl.TitleService;

@RestController
@RequestMapping(path = "/titles")
public class TitleController {

	private TitleService titleService;

	public TitleController(TitleService titleService) {
		this.titleService = titleService;
	}

	private boolean hasRole(String role) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		return authentication != null && authentication.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals(role));
	}
	
	@PostMapping("")
	public ResponseEntity<?> createTitle(@RequestBody TitleModel model) {
		try {
			TitleModel savedTitle = titleService.save(model);
			if (savedTitle != null) {
				return ResponseEntity.status(HttpStatus.OK).body(savedTitle);
			} else {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ViewRouteHelper.ERROR_CREATE);
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ViewRouteHelper.ERROR_REQUEST);
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> updateTitle(@PathVariable int id, @RequestBody TitleModel model) {
		try {
			TitleModel updatedTitle = titleService.update(model, id);
			if (updatedTitle != null) {
				return ResponseEntity.status(HttpStatus.OK).body(updatedTitle);
			} else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ViewRouteHelper.ERROR_NOTFOUND);
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ViewRouteHelper.ERROR_SERVER);
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> findById(@PathVariable int id) {
		try {
			if (hasRole(ViewRouteHelper.ADMIN_ROLE)) {
				return ResponseEntity.status(HttpStatus.OK).body(titleService.findById(id));
			} else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ViewRouteHelper.ERROR_NOTFOUND);
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ViewRouteHelper.ERROR_NOTFOUND);
		}
	}

	@GetMapping("")
	public ResponseEntity<?> getAll() {
		try {
			if (hasRole(ViewRouteHelper.ADMIN_ROLE)) {
				List<TitleModel> titles = titleService.getAll();
				return ResponseEntity.ok(titles);
			} else {
				return ResponseEntity.status(HttpStatus.FORBIDDEN).body(ViewRouteHelper.ACCESS_DENIED);
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ViewRouteHelper.ERROR_SERVER);
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteTitle(@PathVariable int id) {
		try {
			boolean deleted = titleService.deleteById(id);
			if (deleted) {
				return ResponseEntity.status(HttpStatus.OK).body(ViewRouteHelper.SUCCESS_DELETE);
			} else {
				throw new CustomNotFoundException(ViewRouteHelper.ERROR_NOTFOUND);
			}
		} catch (CustomNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ViewRouteHelper.ERROR_SERVER);
		}
	}
}
