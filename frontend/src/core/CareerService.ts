import { axiosInstance } from "../utils/axios";

class CareerService {
  public static async getAll() {
    return axiosInstance
      .get(`/careers`)
      .then((response) => {
        return response.data;
      })
      .catch((response) => {
        return response;
      });
  }
}

export default CareerService;
