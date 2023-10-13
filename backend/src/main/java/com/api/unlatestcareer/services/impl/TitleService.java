package com.api.unlatestcareer.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.unlatestcareer.entities.Title;
import com.api.unlatestcareer.exception.CustomNotFoundException;
import com.api.unlatestcareer.helpers.ViewRouteHelper;
import com.api.unlatestcareer.models.TitleModel;
import com.api.unlatestcareer.repositories.ITitleRepository;
import com.api.unlatestcareer.services.ITitleService;

@Service("titleService")
public class TitleService implements ITitleService {

	private ModelMapper mapper = new ModelMapper();

	@Autowired
	private ITitleRepository titleRepository;

	@Override
	public TitleModel findById(int id) {

		try {
			Optional<Title> optionalTitle = titleRepository.findById(id);

			if (optionalTitle.isPresent()) {
				Title title = optionalTitle.get();
				TitleModel titleModel = mapper.map(title, TitleModel.class);
				return titleModel;
			} else {
				throw new CustomNotFoundException(ViewRouteHelper.ERROR_NOTFOUND);
			}
		} catch (Exception e) {
			throw new CustomNotFoundException(ViewRouteHelper.ERROR_REQUEST);
		}
	}

	@Override
	public TitleModel findByName(String name) {
		TitleModel titleModel = titleRepository.findByName(name);
		if (titleModel != null) {
			return titleModel;
		}
		return null;
	}

	@Override
	public List<TitleModel> getAll() {
		List<Title> titles = titleRepository.findAll();
		return titles.stream().map(title -> mapper.map(title, TitleModel.class)).collect(Collectors.toList());
	}

	@Override
	public boolean deleteById(int id) {
		try {
			Title titleExisting = titleRepository.findById(id).orElse(null);
			boolean deleted = false;
			if (titleExisting != null) {
				titleRepository.deleteById(titleExisting.getId());
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
	public TitleModel save(TitleModel title) {
		try {
			Title titleExisting = titleRepository.findById(title.getId()).orElse(null);

			if (titleExisting == null) {
				titleExisting = new Title(title.getName(), title.getCreatedAt(), title.getUpdatedAt());

			} else {
				titleExisting = new Title(title);
			}
			titleRepository.save(titleExisting);
			return mapper.map(titleExisting, TitleModel.class);
		} catch (Exception e) {
			throw new CustomNotFoundException(ViewRouteHelper.ERROR_REQUEST);
		}
	}

	@Override
	public TitleModel update(TitleModel title, int titleId) {
		try {
			Title titleExisting = titleRepository.findById(titleId)
					.orElseThrow(() -> (new CustomNotFoundException(ViewRouteHelper.ERROR_NOTFOUND)));
			titleExisting.setName(title.getName());
			titleRepository.save(titleExisting);
			return mapper.map(titleExisting, TitleModel.class);
		} catch (Exception e) {
			return null;
		}
	}

}
