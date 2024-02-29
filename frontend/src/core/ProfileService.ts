import axios from "axios";

class ProfileService {
  public static async newProfile(profile: any) {
    return axios
      .post(
        `${process.env.NEXT_PUBLIC_SERVER_URL}/profiles`,
        {
          name: profile.name,
          title: profile.subtitle,
          photo: "",
          current: true,
          courses: profile.subjects,
          institutions: profile.university,
          urlLinkedin: profile.linkedlin,
          mail: profile.correo,
          phone: profile.phone,
          moreInfo: profile.aboutMe,
          idCareer: profile.careerId,
          projects: profile.projects,
        },
        {
          headers: {
            Authorization: `Bearer ${localStorage.getItem("token")}`,
            "Access-Control-Allow-Origin": "*",
            "Content-Type": "application/json",
          },
        }
      )
      .then((response) => {
        return response.data;
      })
      .catch((response) => {
        return response;
      });
  }

  public static async getCurrentProfile(userId: string) {
    return axios
      .get(`${process.env.NEXT_PUBLIC_SERVER_URL}/profiles/live/${userId}`, {
        headers: {
          "Access-Control-Allow-Origin": "*",
          "Content-Type": "application/json",
        },
      })
      .then((response) => {
        return response.data;
      })
      .catch((response) => {
        return response;
      });
  }
}

export default ProfileService;
