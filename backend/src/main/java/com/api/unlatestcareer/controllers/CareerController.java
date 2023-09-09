package com.api.unlatestcareer.controllers;

import com.api.unlatestcareer.exception.CustomNotFoundException;
import com.api.unlatestcareer.helpers.ViewRouteHelper;
import java.util.List;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.unlatestcareer.models.CareerModel;
import com.api.unlatestcareer.services.impl.CareerService;

@RestController
@RequestMapping(path = "/careers")
public class CareerController {

	private CareerService careerService;

	public CareerController(CareerService careerService) {
		this.careerService = careerService;
	}

	@PostMapping("")
	public ResponseEntity<?> createCareer(@RequestBody CareerModel model) {
		try {
			CareerModel savedCareer = careerService.save(model);
			if (savedCareer != null) {
				return ResponseEntity.status(HttpStatus.OK).body(savedCareer);
			} else {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ViewRouteHelper.ERROR_CREATE);
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ViewRouteHelper.ERROR_REQUEST);
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> updateCareer(@PathVariable int id, @RequestBody CareerModel model) {
		try {
			CareerModel updatedCareer = careerService.update(model, id);
			if (updatedCareer != null) {
				return ResponseEntity.status(HttpStatus.OK).body(updatedCareer);
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
			return ResponseEntity.status(HttpStatus.OK).body(careerService.findById(id));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ViewRouteHelper.ERROR_NOTFOUND);
		}
	}

	@GetMapping("")
	public ResponseEntity<?> getAll() {
		try {
			List<CareerModel> careers = careerService.getAll();
			return ResponseEntity.ok(careers);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ViewRouteHelper.ERROR_SERVER);
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteCareer(@PathVariable int id) {
		try {
			boolean deleted = careerService.deleteById(id);
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