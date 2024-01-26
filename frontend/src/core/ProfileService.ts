import axios from "axios";

class ProfileService {
  public static async newProfile(profile: any) {
    return axios
      .post(
        `http://localhost:8080/profiles`,
        {
          name: profile.name,
          lastname: profile.name,
          titles: profile.subtitle,
          photo: "",
          current: true,
          courses: "sasa",
          urlLinkedin: profile.linkedlin,
          mail: profile.correo,
          phone: "1150349032",
          moreInfo: profile.aboutMe,
          idCareer: profile.careerId,
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
}

export default ProfileService;
