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

type chipItem = {
  image: string;
  text: string;
  href: string;
  onlyAdmin: boolean;
};

const chipsItem: chipItem[] = [
  {
    image: "./historial.png",
    text: "Dashboard de review",
    href: "review-dashboard",
    onlyAdmin: true,
  },
  {
    image: "./historial.png",
    text: "Dashboard de profiles",
    href: "dashboard",
    onlyAdmin: false,
  },
  {
    image: "./form.png",
    text: "Formulario cambio de credential",
    href: "/form-profile",
    onlyAdmin: false,
  },
  {
    image: "./changePassword.png",
    text: "Cambiar Password",
    href: "/change-password",
    onlyAdmin: false,
  },
  {
    image: "./newUser.png",
    text: "Crear nuevo Usuario",
    href: "/new-user",
    onlyAdmin: true,
  },
];

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
    setUserInfo(
      (prevState) => ({ ...prevState, name: idUser?.split("@")[0] } as userInfo)
    );

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
      `${window.location.origin}/digital-card/${localStorage.getItem("userId")}`
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
    chipsItem,
  };
};

export default useHome;
