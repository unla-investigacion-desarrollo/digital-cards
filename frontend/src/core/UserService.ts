import axios from "axios";

class UserService {
  public static async loginRequest(username: string, password: string) {
    return axios
      .post(
        `http://localhost:8080/usuario/login`,
        {
          username: username,
          password: password,
        },
        {
          headers: {
            "Access-Control-Allow-Origin": "*",
            "Content-Type": "application/json",
          },
        }
      )
      .then((response) => {
        localStorage.setItem("username", response.data.username);
        localStorage.setItem("token", response.data.token);
        localStorage.setItem("userId", response.data.userId);
        return response.data;
      });
  }

  public static async userRequest() {
    return axios
      .get(`http://localhost:8080/usuario/1`, {
        headers: {
          Authorization: `Bearer ${localStorage.getItem("token")}`,
          "Access-Control-Allow-Origin": "*",
          "Content-Type": "application/json",
        },
      })
      .then((response) => {
        return response;
      })
      .catch((response) => {
        return response;
      });
  }
}

export default UserService;
