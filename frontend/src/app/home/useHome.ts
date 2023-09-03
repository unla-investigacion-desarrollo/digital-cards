import GetInfoHome from "@/core/GetInfoHome";
import { useEffect, useState } from "react";

type userInfo = {
  name: string;
  position: string;
  qr: string;
  imageProfile: string;
};

const useHome = () => {
  const [isLoading, setIsLoading] = useState(true);
  const [isQr, setIsQr] = useState(false);
  const [userInfo, setUserInfo] = useState<userInfo>({
    name: "",
    position: "",
    qr: "",
    imageProfile: "",
  });

  const getInfoHome = () => {
    setIsLoading(true);
    let idUser = localStorage.getItem("username");
    console.log(idUser);

    GetInfoHome.getData(idUser || "")
      .then((response) => {
        setUserInfo(response);
        setIsLoading(false);
        setIsQr(true);
      })
      .catch(() => {
        setIsLoading(false);
        setIsQr(false);
      });
  };

  useEffect(() => {
    getInfoHome();
  }, []);

  return {
    state: {
      isLoading,
      userInfo,
      isQr,
    },
  };
};

export default useHome;
