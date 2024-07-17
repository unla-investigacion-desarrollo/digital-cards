import ReviewService from "@/core/ReviewService";
import ProfileService from "@/core/ProfileService";
import { useEffect, useState } from "react";
import Swal from "sweetalert2";

interface ReviewItemTable {
  reviewId: string;
  profileName: string;
  userRequest: string;
  userReviewer: string;
  statusReview: string;
  hasFeeedback: boolean;
  profile: any; // Ajusta el tipo según tus necesidades
}

const useReviewDashboard = () => {
  const [reviews, setReviews] = useState<ReviewItemTable[]>([]);

  const request = async () => {
    await ReviewService.review().then((data) => {
      setReviews(
        data.map((review: any) => ({
          reviewId: review?.id,
          profileName: review?.profile?.profileName || "Generic",
          userRequest: review?.requester?.username,
          userReviewer: review?.reviewer?.username,
          statusReview: review?.profile?.status,
          review: review?.feedback,
          hasFeeedback: !!review?.feedback,
          profile: review.profile,
        }))
      );
    });
  };

  const addFeebackProfile = async (
    reviewId: string,
    reviewerID: string | null,
    feedback: string,
    status: string,
    profileId: string
  ) => {
    await ReviewService.addFeedBack(reviewId, reviewerID, feedback)
      .then(async () => {
        await ProfileService.updateStatusProfile(status, profileId);
        setReviews((prevReviews) =>
          prevReviews.map((review) =>
            review.reviewId === reviewId
              ? { ...review, hasFeeedback: true, statusReview: status }
              : review
          )
        );
        Swal.fire({
          icon: "success",
          title: "Feedback guardado con éxito",
          text: "Auto close alert!",
          timer: 2000,
        });
      })
      .catch(() =>
        Swal.fire({
          icon: "error",
          title: "Feedback no guardado con éxito",
          text: "Auto close alert!",
          timer: 2000,
        })
      );
  };

  useEffect(() => {
    request();
  }, []);

  return {
    reviews,
    addFeebackProfile,
  };
};

export default useReviewDashboard;
