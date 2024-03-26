import ReviewService from "@/core/ReviewService";
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
            userReview: profile?.reviews
              ? profile?.reviews[0]?.userReviewerId
              : "sin reviwer",
            review: profile?.reviews
              ? profile?.reviews[0]?.feedback
              : "sin feedback",
            status: profile.status,
            isLive: profile.current,
          }))
        );

        // data.profiles.forEach(async (profile) => {
        //   await ReviewService.reviewRequest(profile.id).then((data) => {
        //     console.log(data);
        //   });
        // });
      }
    );

    const profilesComplete = [];
  };

  useEffect(() => {
    request();
  }, []);

  return {
    profiles,
  };
};

export default useDashboard;
