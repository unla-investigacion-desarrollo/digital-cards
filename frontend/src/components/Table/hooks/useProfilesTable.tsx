"use client";
import React from "react";
import { Button, Chip, Tooltip } from "@nextui-org/react";
import Text from "../components/Text";
import Actions from "../components/Actions";
import { useRouter } from "next/navigation";
import ProfileService from "@/core/ProfileService";
import Swal from "sweetalert2";

const statusColorMap = {
  APPROVED: "success",
  REJECTED: "danger",
  PENDING: "warning",
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

interface Props {
  deleteProfile: (profileId: string) => void;
  enableProfile: (profileId: string) => void;
}

const useProfilesTable = ({ deleteProfile, enableProfile }: Props) => {
  const router = useRouter();
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
            <>
              {profileItemTable.isLive ? (
                <Chip
                  className="capitalize"
                  color={profileItemTable.isLive ? "success" : "default"}
                  size="sm"
                  variant="flat"
                >
                  isLive
                </Chip>
              ) : (
                <Chip
                  className="capitalize"
                  variant="flat"
                  size="sm"
                  isDisabled={profileItemTable?.status !== "APPROVED"}
                  onClick={() => enableProfile(profileItemTable.profileId)}
                >
                  Habilitar
                </Chip>
              )}
            </>
          );
        case ColumsProfilesTable.EDIT:
          return (
            <Actions
              editProfile={() =>
                router.push(`form-profile/${profileItemTable.profileId}/`)
              }
              deleteProfile={() => deleteProfile(profileItemTable.profileId)}
            />
          );
        default:
          return cellValue;
      }
    },
    [deleteProfile, enableProfile]
  );
  return {
    renderCell,
  };
};

export default useProfilesTable;
