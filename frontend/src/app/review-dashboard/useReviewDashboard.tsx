import ReviewService from "@/core/ReviewService";
import { useEffect, useState } from "react";

interface ReviewItemTable {
  reviewId: string;
  ProfileName: string;
  userRequest: string;
  userReviewer: string;
  statusReview: string;
  review: string;
  hasFeeedback: boolean;
  profile: any;
}

const useReviewDasboard = () => {
  const [reviews, setReviews] = useState<ReviewItemTable[]>([]);

  const request = async () => {
    await ReviewService.review().then((data) => {
      setReviews(
        data.map((review: any) => ({
          reviewId: review?.id,
          profileName: "Generic",
          userRequest: review?.requester?.username,
          userReviewer: review?.reviewer?.username,
          statusReview: review?.feedback ? "aprobado" : "vacation",
          review: review?.feedback,
          hasFeeedback: review?.feedback,
          profile: review.profile,
        }))
      );
    });
  };

  useEffect(() => {
    request();
  }, []);

  return {
    reviews,
  };
};

export default useReviewDasboard;
