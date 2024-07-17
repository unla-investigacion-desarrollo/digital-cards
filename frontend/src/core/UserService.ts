import { axiosInstance } from "../utils/axios";

class UserService {
  public static async loginRequest(username: string, password: string) {
    return axiosInstance
      .post(`/usuario/login`, {
        username: username,
        password: password,
      })
      .then((response) => {
        localStorage.setItem("username", response.data.username);
        localStorage.setItem("token", response.data.token);
        localStorage.setItem("userId", response.data.userId);
        return response.data;
      });
  }

  public static async createNewUserRequest(username: string, password: string) {
    return axiosInstance
      .post(`/usuario`, {
        username: username,
        password: password,
      })
      .then((response) => {
        return response.data;
      });
  }

  public static async changePasswordRequest(password: string) {
    return axiosInstance
      .put(`/usuario/change-password`, {
        password: password,
      })
      .then((response) => {
        return response.data;
      });
  }

  public static async userRequest(id: string) {
    return axiosInstance
      .get(`/usuario/${id}`)
      .then((response) => {
        return response.data;
      })
      .catch((response) => {
        return response.data;
      });
  }
}

export default UserService;
