"use client";
import React from "react";
import ReviewTable from "@/components/Table/ReviewTable";
import useReviewDashboard from "./useReviewDashboard";

const columns = [
  { name: "REVIEW ID", uid: "REVIEW_ID" },
  { name: "PROFILE NAME", uid: "PROFILE_NAME" },
  { name: "USER REQUEST", uid: "USER_REQUEST" },
  { name: "USER REVIEWER", uid: "USER_REVIEWER" },
  { name: "STATUS", uid: "STATUS_REVIEW" },
  { name: "REVIEW", uid: "REVIEW" },
  { name: "PROFILE VIEW", uid: "PROFILE_VIEW" },
  { name: "HAS FEEDBACK", uid: "HAS_FEEDBACK" },
];

const Page = () => {
  const { reviews, addFeebackProfile } = useReviewDashboard();

  return (
    <>
      {reviews && (
        <ReviewTable
          columns={columns}
          reviewItems={reviews}
          addFeebackProfile={addFeebackProfile}
        />
      )}
    </>
  );
};

export default Page;
