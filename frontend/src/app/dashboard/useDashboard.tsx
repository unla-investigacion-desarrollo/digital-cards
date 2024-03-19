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
    await UserService.userRequest(localStorage.getItem("userId") as any).then(
      (data) => {
        setProfiles(
          data.profiles.map((profile: any) => ({
            profileId: profile.id,
            profileName: "Generic",
            userReview: profile?.reviews[0]?.userReviewerId || "sin reviwer",
            review: profile?.reviews[0]?.feedback || "sin feedback",
            status: profile.status,
            isLive: profile.current,
          }))
        );
        console.log(data.profiles);
      }
    );
  };

  useEffect(() => {
    request();
  }, []);

  return {
    profiles,
  };
};

export default useDashboard;
