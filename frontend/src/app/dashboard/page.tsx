"use client";
import React from "react";
import ProfilesTable from "@/components/Table/ProfilesTable";
import UserService from "@/core/UserService";
import useDashboard from "./useDashboard";

const profileItems = [
  {
    profileId: "1",
    profileName: "Tony Reichert",
    userReview: "Vranic",
    review: "empty value",
    status: "aprobado",
    isLive: true,
  },
  {
    profileId: "1",
    profileName: "Tony chein",
    userReview: "Gustavo",
    review: "empty value",
    status: "aprobado",
    isLive: false,
  },
  {
    profileId: "1",
    profileName: "Tony flex",
    userReview: "Laura",
    review: "empty value",
    status: "aprobado",
    isLive: false,
  },
];

const columns = [
  { name: "PROFILE ID", uid: "PROFILE_ID" },
  { name: "PROFILE NAME", uid: "PROFILE_NAME" },
  { name: "USER REVIEWER", uid: "USER_REVIEWER" },
  { name: "REVIEW", uid: "REVIEW" },
  { name: "STATUS", uid: "STATUS" },
  { name: "IS LIVE", uid: "IS_LIVE" },
  { name: "EDIT", uid: "EDIT" },
];

const page = () => {
  const { profiles } = useDashboard();

  return (
    <>
      {profiles && (
        <ProfilesTable
          columns={columns}
          profileItems={profiles}
        ></ProfilesTable>
      )}
    </>
  );
};

export default page;
