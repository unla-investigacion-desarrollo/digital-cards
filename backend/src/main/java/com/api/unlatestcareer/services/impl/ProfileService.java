package com.api.unlatestcareer.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.api.unlatestcareer.entities.*;
import com.api.unlatestcareer.exception.CustomNotFoundException;
import com.api.unlatestcareer.helpers.ViewRouteHelper;
import com.api.unlatestcareer.models.CareerModel;
import com.api.unlatestcareer.models.ProfileModel;
import com.api.unlatestcareer.models.TitleModel;
import com.api.unlatestcareer.repositories.ICareerRepository;
import com.api.unlatestcareer.repositories.IProfileRepository;
import com.api.unlatestcareer.repositories.ITitleRepository;
import com.api.unlatestcareer.services.IProfileService;

@Service("profileService")
public class ProfileService implements IProfileService {

	private ModelMapper mapper = new ModelMapper();

	@Autowired
	private IProfileRepository profileRepository;
	
	@Autowired 
	private ITitleRepository titleRepository;
	
	@Autowired
	private ICareerRepository careerRepository;

	@Override
	public ProfileModel findById(int id) {
		try {
			Optional<Profile> optionalProfile = profileRepository.findById(id);

			if (optionalProfile.isPresent()) {
				Profile profile = optionalProfile.get();
				ProfileModel profileModel = mapper.map(profile, ProfileModel.class);
				return profileModel;
			} else {
				throw new CustomNotFoundException(ViewRouteHelper.ERROR_NOTFOUND);
			}
		} catch (Exception e) {
			throw new CustomNotFoundException(ViewRouteHelper.ERROR_REQUEST);
		}
	}

	@Override
	public ProfileModel findByName(String name) {
		ProfileModel profileModel = profileRepository.findByName(name);

		if (profileModel != null) {
			return profileModel;
		}
		return null;
	}

	@Override
	public List<ProfileModel> getAll() {
		List<Profile> profiles = profileRepository.findAll();

		return profiles.stream().map(profile -> mapper.map(profile, ProfileModel.class)).collect(Collectors.toList());
	}

	@Override
	public boolean deleteById(int id) {
		try {
			Profile profileExisting = profileRepository.findById(id).orElse(null);
			boolean deleted = false;
			if (profileExisting != null) {
				profileRepository.deleteById(profileExisting.getId());
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
	public ProfileModel save(ProfileModel profile) {
		try {
			Profile profileExisting = profileRepository.findById(profile.getId()).orElse(null);

			if (profileExisting == null) {
				profileExisting = new Profile(profile.getPhoto(), profile.isCurrent(), profile.getStatus(),
						profile.getCourses(), profile.getName(), profile.getLastname(), profile.getUrlLinkedin(),
						profile.getMail(), profile.getPhone(), profile.getMoreInfo(), profile.getCreatedAt(),
						profile.getUpdateAt());
			} else {
				profileExisting = new Profile(profile);
			}
			profileRepository.save(profileExisting);
			return mapper.map(profileExisting, ProfileModel.class);
		} catch (Exception e) {
			throw new CustomNotFoundException(ViewRouteHelper.ERROR_REQUEST);
		}
	}

	@Override
	public ProfileModel update(ProfileModel profile, int profileId) {
		try {
			Profile profileExisting = profileRepository.findById(profileId)
					.orElseThrow(() -> (new CustomNotFoundException(ViewRouteHelper.ERROR_NOTFOUND)));
			profileExisting.setName(profile.getName());
			return mapper.map(profileExisting, ProfileModel.class);
		} catch (Exception e) {
			return null;
		}
	}

	 @Override
	    public ProfileModel addTitleToProfile(int profileId, int titleId) {
		 Profile profileExisting = profileRepository.findById(profileId).orElseThrow(() -> (new CustomNotFoundException(ViewRouteHelper.ERROR_NOTFOUND)));
	        Title titleExisting = titleRepository.findById(titleId).orElseThrow(() -> (new CustomNotFoundException(ViewRouteHelper.ERROR_NOTFOUND)));      

	        if (profileExisting != null && titleExisting != null) {
	            // Asocia el título al perfil si aún no está asociado
	            if (!profileExisting.getTitles().contains(titleExisting)) {
	                profileExisting.getTitles().add(titleExisting);
	            }
	            profileRepository.save(profileExisting); // Guarda el perfil actualizado en la base de datos
	        }
	        return mapper.map(profileExisting, ProfileModel.class);
	    }

	@Override
	public ProfileModel removeTitleFromProfile(int profileId, int titleId) {
		Profile profileExisting = profileRepository.findById(profileId).orElseThrow(() -> (new CustomNotFoundException(ViewRouteHelper.ERROR_NOTFOUND)));
        Title titleExisting = titleRepository.findById(titleId).orElseThrow(() -> (new CustomNotFoundException(ViewRouteHelper.ERROR_NOTFOUND)));      


        if (profileExisting != null && titleExisting != null) {
            // Desasocia el título del perfil si está asociado
            if (profileExisting.getTitles().contains(titleExisting)) {
                profileExisting.getTitles().remove(titleExisting);
            }
            profileRepository.save(profileExisting); // Guarda el perfil actualizado en la base de datos
        }
        return mapper.map(profileExisting, ProfileModel.class);
    }

	@Override
	public ProfileModel addCareerToProfile(int profileId, int careerId) {
		 Profile profileExisting = profileRepository.findById(profileId).orElseThrow(() -> (new CustomNotFoundException(ViewRouteHelper.ERROR_NOTFOUND)));
		 Career careerExisting = careerRepository.findById(careerId).orElseThrow(() -> (new CustomNotFoundException(ViewRouteHelper.ERROR_NOTFOUND)));
		 
		 if (profileExisting != null && careerExisting != null) {
			 
			 if(profileExisting.getTitles().contains(careerExisting)) {
				 profileExisting.getTitles().remove(careerExisting);
			 }
			 profileRepository.save(profileExisting);
		 }
		  return mapper.map(profileExisting, ProfileModel.class);
		 }
		 

	@Override
	public ProfileModel removeCareerFromProfile(int profileId, int careerId) {
		 Profile profileExisting = profileRepository.findById(profileId).orElseThrow(() -> (new CustomNotFoundException(ViewRouteHelper.ERROR_NOTFOUND)));
		 Career careerExisting = careerRepository.findById(careerId).orElseThrow(() -> (new CustomNotFoundException(ViewRouteHelper.ERROR_NOTFOUND)));
		 if (profileExisting != null && careerExisting != null) {
	            // Desasocia el título del perfil si está asociado
	            if (profileExisting.getTitles().contains(careerExisting)) {
	                profileExisting.getTitles().remove(careerExisting);
	            }
	            profileRepository.save(profileExisting); // Guarda el perfil actualizado en la base de datos
	        }
	        return mapper.map(profileExisting, ProfileModel.class);
	}

	//Para crear la career y agregarla
	
	public ProfileModel addCareerToProfile(int profileId, CareerModel careerModel) {
	    Profile profileExisting = profileRepository.findById(profileId)
	            .orElseThrow(() -> new CustomNotFoundException(ViewRouteHelper.ERROR_NOTFOUND));

	    // Crea una nueva carrera a partir de CareerModel
	    Career career = new Career();
	    career.setName(careerModel.getName());
	    career.setLink(careerModel.getLink());
	    career.setCreatedAt(careerModel.getCreatedAt());
	    career.setUpdateAt(careerModel.getUpdateAt());
	    career.setEnabled(careerModel.isEnabled());
	    
	 // Asocia la carrera al perfil
	    profileExisting.getCareers().add(career);
	    
	    //Preguntar si hace falta que se guarde en este caso. 
	    careerRepository.save(career);

	    // Guarda el perfil actualizado en la base de datos
	    profileRepository.save(profileExisting);

	    return mapper.map(profileExisting, ProfileModel.class);
	}

	//Para crear el title y agregarlo
	public ProfileModel addTitleToProfile (int profileId, TitleModel titleModel) {
		Profile profileExisting = profileRepository.findById(profileId).orElseThrow(() -> new CustomNotFoundException(ViewRouteHelper.ERROR_NOTFOUND));	
		
		Title title = new Title(titleModel.getName(), titleModel.getCreatedAt(),titleModel.getUpdateAt());
		
		profileExisting.getTitles().add(title);
		
		titleRepository.save(title);
		
		profileRepository.save(profileExisting);
		
		return mapper.map(profileExisting, ProfileModel.class);
	
	}
	
}
