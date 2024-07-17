import { axiosInstance } from "../utils/axios";

class ReviewService {
  public static async reviewRequest(profileId: any) {
    return axiosInstance.get(`/review`).then((response) => {
      return response.data.filter(
        (review: any) => review.profile.id == profileId
      );
    });
  }

  public static async newReview(userRequesterId: any, profileId: any) {
    return axiosInstance
      .post(`/review`, {
        userRequesterId: userRequesterId,
        profileId: profileId,
      })
      .then((response) => {
        return response.data;
      });
  }

  public static async review() {
    return axiosInstance.get(`/review`).then((response) => {
      return response.data;
    });
  }

  public static async addFeedBack(
    feedbackId: number,
    userReviewerId: any,
    feedback: string
  ) {
    return axiosInstance
      .patch(`/review/${feedbackId}/addFeedback`, {
        feedback: feedback,
        userReviewerId: userReviewerId,
      })
      .then((response) => {
        return response.data;
      });
  }
}

export default ReviewService;
