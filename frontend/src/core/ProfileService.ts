import { axiosInstance } from "../utils/axios";

class ProfileService {
  public static async newProfile(profile: any) {
    return axiosInstance
      .post(`/profiles`, {
        profileName: profile.profileName,
        name: profile.name,
        title: profile.subtitle,
        photo: profile.image,
        current: true,
        courses: profile.subjects,
        institutions: profile.university,
        urlLinkedin: profile.linkedlin,
        mail: profile.correo,
        phone: profile.phone,
        moreInfo: profile.aboutMe,
        idCareer: profile.careerId,
        projects: profile.projects,
      })
      .then((response) => {
        return response.data;
      })
      .catch((response) => {
        return response;
      });
  }

  public static async editProfile(profile: any, profileId: string) {
    return axiosInstance
      .put(`/profiles/${profileId}`, {
        profileName: profile.profileName,
        name: profile.name,
        title: profile.subtitle,
        photo: profile.image,
        current: true,
        courses: profile.subjects,
        institutions: profile.university,
        urlLinkedin: profile.linkedlin,
        mail: profile.correo,
        phone: profile.phone,
        moreInfo: profile.aboutMe,
        idCareer: profile.careerId,
        projects: profile.projects,
      })
      .then((response) => {
        return response.data;
      })
      .catch((response) => {
        return response;
      });
  }

  public static async deleteProfile(profileId: string) {
    return axiosInstance
      .delete(`/profiles/${profileId}`)
      .then((response) => {
        return response.data;
      })
      .catch((response) => {
        return response;
      });
  }

  public static async liveProfile(profileId: string) {
    return axiosInstance
      .put(`/profiles/set-active/${profileId}`, {})
      .then((response) => {
        return response.data;
      })
      .catch((response) => {
        return response;
      });
  }

  public static async updateStatusProfile(status: any, profileId: any) {
    return axiosInstance
      .put(`/profiles/change-status/${profileId}`, {
        status,
      })
      .then((response) => {
        return response.data;
      })
      .catch((response) => {
        return response;
      });
  }

  public static async getCurrentProfile(userId: string) {
    return axiosInstance
      .get(`/profiles/live/${userId}`)
      .then((response) => {
        return response.data;
      })
      .catch((response) => {
        return response;
      });
  }

  public static async getProfiles() {
    return axiosInstance
      .get(`/profiles/summary`)
      .then((response) => {
        return response.data;
      })
      .catch((response) => {
        return response;
      });
  }
}

export default ProfileService;
