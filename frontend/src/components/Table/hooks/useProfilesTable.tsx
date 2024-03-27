"use client";
import React from "react";
import { Chip, Tooltip } from "@nextui-org/react";
import Text from "../components/Text";
import Actions from "../components/Actions";

const statusColorMap = {
  aprobado: "success",
  paused: "danger",
  vacation: "warning",
};

enum ColumsProfilesTable {
  PROFILE_ID = "PROFILE_ID",
  PROFILE_NAME = "PROFILE_NAME",
  USER_REVIEWER = "USER_REVIEWER",
  REVIEW = "REVIEW",
  STATUS = "STATUS",
  IS_LIVE = "IS_LIVE",
  EDIT = "EDIT",
}

const useProfilesTable = () => {
  const renderCell = React.useCallback(
    (profileItemTable: ProfileItemTable, columnKey: string) => {
      const cellValue = profileItemTable[columnKey];

      switch (columnKey) {
        case ColumsProfilesTable.PROFILE_ID:
          return <Text text={profileItemTable.profileId} />;
        case ColumsProfilesTable.PROFILE_NAME:
          return <Text text={profileItemTable.profileName} />;
        case ColumsProfilesTable.USER_REVIEWER:
          return <Text text={profileItemTable.userReview} />;
        case ColumsProfilesTable.REVIEW:
          return <Text text={profileItemTable.review} />;
        case ColumsProfilesTable.STATUS:
          return (
            <Chip
              className="capitalize"
              color={statusColorMap[profileItemTable?.status]}
              size="sm"
              variant="flat"
            >
              {profileItemTable?.status}
            </Chip>
          );
        case ColumsProfilesTable.IS_LIVE:
          return (
            <Chip
              className="capitalize"
              color={profileItemTable.isLive ? "success" : "warning"}
              size="sm"
              variant="flat"
            >
              {profileItemTable.isLive ? "isLive" : "no live"}
            </Chip>
          );
        case ColumsProfilesTable.EDIT:
          return <Actions />;
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

export default useProfilesTable;
