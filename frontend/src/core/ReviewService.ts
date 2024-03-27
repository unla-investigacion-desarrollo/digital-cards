import axios from "axios";

class ReviewService {
  public static async reviewRequest(profileId: any) {
    return axios
      .get(`${process.env.NEXT_PUBLIC_SERVER_URL}/review`, {
        headers: {
          Authorization: `Bearer ${localStorage.getItem("token")}`,
          "Access-Control-Allow-Origin": "*",
          "Content-Type": "application/json",
        },
      })
      .then((response) => {
        return response.data.filter(
          (review: any) => review.profile.id == profileId
        );
      });
  }

  public static async review() {
    return axios
      .get(`${process.env.NEXT_PUBLIC_SERVER_URL}/review`, {
        headers: {
          Authorization: `Bearer ${localStorage.getItem("token")}`,
          "Access-Control-Allow-Origin": "*",
          "Content-Type": "application/json",
        },
      })
      .then((response) => {
        return response.data;
      });
  }
}

export default ReviewService;
