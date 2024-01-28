import GetInfoHome from "@/core/GetInfoHome";
import { useEffect, useState } from "react";
import QRCode from "qrcode";

type userInfo = {
  name: string;
  position: string;
  qr: string;
  imageProfile: string;
  idPage: string;
};

const useHome = () => {
  const [isLoading, setIsLoading] = useState(true);
  const [isQr, setIsQr] = useState(false);
  const [userInfo, setUserInfo] = useState<userInfo>({
    name: "",
    position: "",
    qr: "",
    imageProfile: "",
    idPage: "",
  });

  const getInfoHome = () => {
    setIsLoading(true);
    let idUser = localStorage.getItem("username");

    GetInfoHome.getData(idUser || "")
      .then((response) => {
        setUserInfo(response);
        setIsLoading(false);
        setIsQr(false);
      })
      .catch(() => {
        setIsLoading(false);
        setIsQr(false);
      });
  };

  const generateQr = () => {
    QRCode.toDataURL(
      `https://digital-cards-five.vercel.app/digital-card/${localStorage.getItem(
        "userId"
      )}`
    )
      .then((image) => {
        setUserInfo((prevState) => ({
          ...prevState,
          qr: image,
        }));
        setIsQr(true);
      })
      .catch((err) => {
        console.error(err);
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
    actions: {
      generateQr,
    },
  };
};

export default useHome;
