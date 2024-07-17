import ProfileService from "@/core/ProfileService";
import { useEffect, useState } from "react";
import Swal from "sweetalert2";

interface ProfileItemTable {
  profileId: string;
  profileName: string;
  userReview: string;
  review: string;
  status: string;
  isLive: boolean;
}

const useDashboard = () => {
  const [profiles, setProfiles] = useState<ProfileItemTable[]>([]);

  const request = async () => {
    await ProfileService.getProfiles().then((data) => {
      setProfiles(
        data?.map(({ profileModel, reviewSummary }: any) => ({
          profileId: profileModel?.id,
          profileName: profileModel?.profileName || "Generic",
          userReview: reviewSummary
            ? reviewSummary?.reviewer?.username
            : "sin reviewer",
          review: reviewSummary ? reviewSummary?.feedback : "sin feedback",
          status: profileModel?.status,
          isLive: profileModel?.current,
        }))
      );
    });
  };

  const deleteProfile = async (profileId: string) => {
    await ProfileService.deleteProfile(profileId)
      .then((data) => {
        setProfiles((prevProfiles) =>
          prevProfiles.filter((profile) => profile.profileId !== profileId)
        );
        Swal.fire({
          icon: "success",
          title: "Eliminación exitosa",
          text: `${data}`,
        });
      })
      .catch((error) => {
        Swal.fire({
          icon: "error",
          title: "Error en la petición",
        });
      });
  };

  const enableProfile = async (profileId: string) => {
    await ProfileService.liveProfile(profileId)
      .then((data) => {
        setProfiles((prevProfiles) =>
          prevProfiles.map((profile) =>
            profile.profileId === profileId
              ? { ...profile, isLive: true, status: "APPROVED" }
              : { ...profile, isLive: false }
          )
        );
        Swal.fire({
          icon: "success",
          title: "Perfil habilitado",
          text: `${data.id}`,
        });
      })
      .catch((error) => {
        Swal.fire({
          icon: "error",
          title: "Error en la petición",
        });
      });
  };

  useEffect(() => {
    request();
  }, []);

  return {
    profiles,
    deleteProfile,
    enableProfile,
  };
};

export default useDashboard;
