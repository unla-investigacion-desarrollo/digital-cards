import axios from "axios";

class UserService {
  public static async loginRequest(username: string, password: string) {
    return axios
      .post(
        `${process.env.NEXT_PUBLIC_SERVER_URL}/usuario/login`,
        {
          username: username,
          password: password,
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
        localStorage.setItem("username", response.data.username);
        localStorage.setItem("token", response.data.token);
        localStorage.setItem("userId", response.data.userId);
        return response.data;
      });
  }

  public static async createNewUserRequest(username: string, password: string) {
    return axios
      .post(
        `${process.env.NEXT_PUBLIC_SERVER_URL}/usuario`,
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
        return response.data;
      });
  }

  public static async changePasswordRequest(password: string) {
    return axios
      .put(
        `${process.env.NEXT_PUBLIC_SERVER_URL}/usuario/change-password`,
        {
          password: password,
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
      });
  }

  public static async userRequest() {
    return axios
      .get(`${process.env.NEXT_PUBLIC_SERVER_URL}/usuario/1`, {
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
