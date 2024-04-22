"use client";
import React from "react";
import { Chip, Tooltip } from "@nextui-org/react";
import Text from "../components/Text";
import Actions from "../components/Actions";
import Feedback from "../components/Feedback";
import ReviewService from "@/core/ReviewService";

const statusColorMap = {
  aprobado: "success",
  paused: "danger",
  vacation: "warning",
};

enum ColumsProfilesTable {
  REVIEW_ID = "REVIEW_ID",
  PROFILE_NAME = "PROFILE_NAME",
  USER_REQUEST = "USER_REQUEST",
  USER_REVIEWER = "USER_REVIEWER",
  STATUS_REVIEW = "STATUS_REVIEW",
  REVIEW = "REVIEW",
  HAS_FEEDBACK = "HAS_FEEDBACK",
}

const useReviewTable = () => {
  const addFeebackProfile = (
    reviewId: any,
    reviewerID: any,
    feedback: string
  ) => {
    ReviewService.addFeedBack(reviewId, reviewerID, feedback);
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
        case ColumsProfilesTable.HAS_FEEDBACK:
          return (
            !reviewItemTable.hasFeeedback && (
              <Feedback
                addFeedback={(feedback) =>
                  addFeebackProfile(
                    reviewItemTable.reviewId,
                    localStorage.getItem("userId"),
                    feedback
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
