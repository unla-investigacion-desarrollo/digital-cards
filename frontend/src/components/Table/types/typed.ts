interface Columns {
  name: string;
  uid: string;
}

interface ProfileItemTable {
  profileId: string;
  profileName: string;
  userReview: string;
  review: string;
  status: string;
  isLive: boolean;
}

interface ReviewItemTable {
  reviewId: string;
  profileName: string;
  userRequest: string;
  userReviewer: string;
  statusReview: string;
  review: string;
  hasFeeedback: boolean;
}

interface props {
  columns: Columns[];
  profileItems: ProfileItemTable[];
}
