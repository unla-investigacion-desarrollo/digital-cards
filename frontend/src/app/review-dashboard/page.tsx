"use client";
import React from "react";
import ProfilesTable from "@/components/Table/ProfilesTable";
import ReviewTable from "@/components/Table/ReviewTable";
import useReviewDasboard from "./useReviewDashboard";

const reviewItems = [
  {
    reviewId: "1",
    profileName: "Tony Reichert",
    userRequest: "Señor miguele",
    userReviewer: "Vranic",
    statusReview: "aprobado",
    review: "rechazado pa ",
    hasFeeedback: true,
  },
  {
    reviewId: "1",
    profileName: "Tony Reichert",
    userRequest: "Señor miguele",
    userReviewer: "Vranic",
    statusReview: "vacation",
    review: "rechazado pa ",
    hasFeeedback: false,
  },
];

const columns = [
  { name: "REVIEW ID", uid: "REVIEW_ID" },
  { name: "PROFILE NAME", uid: "PROFILE_NAME" },
  { name: "USER REQUEST", uid: "USER_REQUEST" },
  { name: "USER REVIEWER", uid: "USER_REVIEWER" },
  { name: "STATUS", uid: "STATUS_REVIEW" },
  { name: "REVIEW", uid: "REVIEW" },
  { name: "HAS FEEDBACk", uid: "HAS_FEEDBACK" },
];

const page = () => {
  const { reviews } = useReviewDasboard();

  return (
    <>
      {reviewItems && (
        <ReviewTable columns={columns} reviewItems={reviews}></ReviewTable>
      )}
    </>
  );
};

export default page;
