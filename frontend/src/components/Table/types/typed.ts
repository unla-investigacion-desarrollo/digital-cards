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

interface props {
  columns: Columns[];
  profileItems: ProfileItemTable[];
}
