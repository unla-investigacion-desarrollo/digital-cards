"use client";
import React from "react";
import { Chip } from "@nextui-org/react";
import Text from "../components/Text";
import Feedback from "../components/Feedback";
import { useRouter } from "next/navigation";

import ButtonProfileView from "../components/ButtonProfileView";

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

interface Props {
  addFeebackProfile: (
    reviewId: string,
    reviewerID: string | null,
    feedback: string,
    status: string,
    profileId: string
  ) => void;
}

const useReviewTable = ({ addFeebackProfile }: Props) => {
  const router = useRouter();
  const renderCell = React.useCallback(
    (reviewItemTable: ReviewItemTable, columnKey: string) => {
      const cellValue = reviewItemTable[columnKey];

      switch (columnKey) {
        case ColumsProfilesTable.REVIEW_ID:
          return <Text text={reviewItemTable.reviewId} />;
        case ColumsProfilesTable.PROFILE_NAME:
          return (
            <Text
              text={`${reviewItemTable.profileName} - ${reviewItemTable.profile.id}`}
            />
          );
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
              />
            )
          );
        default:
          return cellValue;
      }
    },
    [addFeebackProfile]
  );
  return {
    renderCell,
  };
};

export default useReviewTable;
