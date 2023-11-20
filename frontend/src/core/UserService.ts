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
        return response.data;
      })
      .catch((response) => {
        return response;
      });
  }
}

export default UserService;
