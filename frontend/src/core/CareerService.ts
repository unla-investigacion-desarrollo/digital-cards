import axios from "axios";

class CareerService {
  public static async getAll() {
    return axios
      .get(`http://localhost:8080/careers`, {
        headers: {
          Authorization: `Bearer ${localStorage.getItem("token")}`,
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

export default CareerService;
