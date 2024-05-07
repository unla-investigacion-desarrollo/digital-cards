"use client";
import React from "react";
import { Chip, Tooltip } from "@nextui-org/react";
import Text from "../components/Text";
import Feedback from "../components/Feedback";
import ReviewService from "@/core/ReviewService";
import Swal from "sweetalert2";
import ButtonProfileView from "../components/ButtonProfileView";
import ProfileService from "@/core/ProfileService";

const statusColorMap = {
  APPROVED: "success",
  REJECTED: "danger",
  PENDING: "warning",
};

enum ColumsProfilesTable {
  REVIEW_ID = "REVIEW_ID",
  PROFILE_NAME = "PROFILE_NAME",
  USER_REQUEST = "USER_REQUEST",
  USER_REVIEWER = "USER_REVIEWER",
  STATUS_REVIEW = "STATUS_REVIEW",
  REVIEW = "REVIEW",
  PROFILE_VIEW = "PROFILE_VIEW",
  HAS_FEEDBACK = "HAS_FEEDBACK",
}

const useReviewTable = () => {
  const addFeebackProfile = async (
    reviewId: any,
    reviewerID: any,
    feedback: string,
    status: string,
    profileId: any
  ) => {
    await ReviewService.addFeedBack(reviewId, reviewerID, feedback)
      .then(async () => {
        await ProfileService.updateStatusProfile(status, profileId);
        Swal.fire({
          icon: "success",
          title: "Feeedback guardo con exito",
          text: "Auto close alert!",
          timer: 2000,
        });
      })
      .catch(() =>
        Swal.fire({
          icon: "error",
          title: "Feeedback no guardo con exito",
          text: "Auto close alert!",
          timer: 2000,
        })
      );
  };

  const renderCell = React.useCallback(
    (reviewItemTable: ReviewItemTable, columnKey: string) => {
      const cellValue = reviewItemTable[columnKey];

      switch (columnKey) {
        case ColumsProfilesTable.REVIEW_ID:
          return <Text text={reviewItemTable.reviewId} />;
        case ColumsProfilesTable.PROFILE_NAME:
          return <Text text={reviewItemTable.profileName} />;
        case ColumsProfilesTable.USER_REQUEST:
          return <Text text={reviewItemTable.userRequest} />;
        case ColumsProfilesTable.USER_REVIEWER:
          return <Text text={reviewItemTable.userReviewer} />;
        case ColumsProfilesTable.REVIEW:
          return <Text text={reviewItemTable.review} />;
        case ColumsProfilesTable.STATUS_REVIEW:
          return (
            <Chip
              className="capitalize"
              color={statusColorMap[reviewItemTable?.statusReview]}
              size="sm"
              variant="flat"
            >
              {reviewItemTable?.statusReview}
            </Chip>
          );
        case ColumsProfilesTable.PROFILE_VIEW:
          return <ButtonProfileView profileInfo={reviewItemTable.profile} />;
        case ColumsProfilesTable.HAS_FEEDBACK:
          return (
            !reviewItemTable.hasFeeedback && (
              <Feedback
                addFeedback={(feedback, status) =>
                  addFeebackProfile(
                    reviewItemTable.reviewId,
                    localStorage.getItem("userId"),
                    feedback,
                    status,
                    reviewItemTable.profile.id
                  )
                }
              ></Feedback>
            )
          );
        default:
          return cellValue;
      }
    },
    []
  );
  return {
    renderCell,
  };
};

export default useReviewTable;
