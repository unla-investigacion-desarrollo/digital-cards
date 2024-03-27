package com.api.unlatestcareer.services.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.unlatestcareer.entities.Career;
import com.api.unlatestcareer.exception.CustomNotFoundException;
import com.api.unlatestcareer.helpers.ViewRouteHelper;
import com.api.unlatestcareer.models.CareerModel;
import com.api.unlatestcareer.repositories.ICareerRepository;
import com.api.unlatestcareer.services.ICareerService;

@Service("careerService")
public class CareerService implements ICareerService {

	private ModelMapper mapper = new ModelMapper();

	@Autowired
	private ICareerRepository careerRepository;

	public CareerModel findById(int id) {
		try {
			Optional<Career> optionalCareer = careerRepository.findById(id);

			if (optionalCareer.isPresent()) {
				Career career = optionalCareer.get();
				CareerModel careerModel = mapper.map(career, CareerModel.class);
				return careerModel;
			} else {
				throw new CustomNotFoundException(ViewRouteHelper.ERROR_NOTFOUND);
			}
		} catch (Exception e) {
			throw new CustomNotFoundException(ViewRouteHelper.ERROR_REQUEST);
		}
	}

	public CareerModel findByName(String name) {
		try {
		Optional<Career> optionalCareer = careerRepository.findByName(name);
		
		if(optionalCareer.isPresent()) {
			Career career = optionalCareer.get();
			CareerModel careerModel = mapper.map(career, CareerModel.class);
			return careerModel;
		} else { 
			throw new CustomNotFoundException(ViewRouteHelper.ERROR_NOTFOUND);
		}
	} catch (Exception e) {
		throw new CustomNotFoundException(ViewRouteHelper.ERROR_REQUEST);
		}
	}
	

	@Override
	public List<CareerModel> getAll() {
		List<Career> careers = careerRepository.findAll();
		return careers.stream().map(career -> mapper.map(career, CareerModel.class)).collect(Collectors.toList());
	}

	@Override
	public boolean deleteById(int id) {
		try {
			Career careerExisting = careerRepository.findById(id).orElse(null);
			boolean deleted = false;
			if (careerExisting != null) {
				careerRepository.deleteById(careerExisting.getId());
				deleted = true;
				return deleted;
			} else {
				throw new CustomNotFoundException(ViewRouteHelper.ERROR_NOTFOUND);
			}
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public CareerModel save(CareerModel career) {
		try {
			Career careerExisting = careerRepository.findById(career.getId()).orElse(null);

			if (careerExisting == null) {
				careerExisting = new Career(career.getName(), career.getLink(),LocalDate.now(), LocalDate.now(), career.isEnabled());
			} else {
				careerExisting = new Career(career);
			}
			careerRepository.save(careerExisting);
			return mapper.map(careerExisting, CareerModel.class);
		} catch (Exception e) {
			throw new CustomNotFoundException(ViewRouteHelper.ERROR_REQUEST);
		}
	}

	@Override
	public CareerModel update(CareerModel career, int careerId) {
		try {
			Career careerExisting = careerRepository.findById(careerId)
					.orElseThrow(() -> (new CustomNotFoundException(ViewRouteHelper.ERROR_NOTFOUND)));
			careerExisting.setName(career.getName());
			careerRepository.save(careerExisting);
			return mapper.map(careerExisting, CareerModel.class);
		} catch (Exception e) {
			return null;
		}
	}
}
