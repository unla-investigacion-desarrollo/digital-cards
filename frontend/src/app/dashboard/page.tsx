"use client";
import React from "react";
import ProfilesTable from "@/components/Table/ProfilesTable";
import useDashboard from "./useDashboard";

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
  console.log(profiles);

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
