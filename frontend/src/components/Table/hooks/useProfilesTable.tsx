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

const useProfilesTable = () => {
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
                  onClick={() =>
                    ProfileService.liveProfile(profileItemTable.profileId)
                      .then((data) => {
                        Swal.fire({
                          icon: "success",
                          title: "live",
                          text: `${data.id}`,
                        });
                      })
                      .catch((error) => {
                        Swal.fire({
                          icon: "error",
                          title: "Error en la petición",
                        });
                      })
                  }
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
              deleteProfile={async () => {
                await ProfileService.deleteProfile(profileItemTable.profileId)
                  .then((data) => {
                    Swal.fire({
                      icon: "success",
                      title: "Eliminacion exitosa",
                      text: `${data}`,
                    });
                  })
                  .catch((error) => {
                    Swal.fire({
                      icon: "error",
                      title: "Error en la petición",
                    });
                  });
              }}
            />
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

export default useProfilesTable;
