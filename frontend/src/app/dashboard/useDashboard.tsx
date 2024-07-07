import ProfileService from "@/core/ProfileService";
import UserService from "@/core/UserService";
import { useEffect, useState } from "react";

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
        data.map(({ profileModel, reviewSummary }: any) => ({
          profileId: profileModel.id,
          profileName: profileModel.profileName || "Generic",
          userReview: reviewSummary
            ? reviewSummary?.reviewer?.username
            : "sin reviwer",
          review: reviewSummary ? reviewSummary?.feedback : "sin feedback",
          status: profileModel.status,
          isLive: profileModel.current,
        }))
      );
    });
  };

  useEffect(() => {
    request();
  }, []);

  return {
    profiles,
  };
};

export default useDashboard;
