import CardQR from "@/components/Card/CardQR";
import ChipMenu from "@/components/ChipMenu";
import { Button } from "@nextui-org/react";
import React from "react";

type chipItem = {
  image: string;
  text: string;
  href: string;
  onlyAdmin: boolean;
};

interface props {
  name: string;
  isQr: boolean;
  position: string;
  qr: string;
  imageProfile: string;
  generateQr: () => void;
  chipsItem: chipItem[];
  isAdmin?: boolean;
}

const DesktopHome = ({
  name,
  isQr,
  position,
  qr,
  imageProfile,
  generateQr,
  chipsItem,
  isAdmin = false,
}: props) => {
  return (
    <div className="hidden lg:flex flex-col w-full h-full items-center justify-center gap-5 ">
      <div className="flex flex-col items-center w-full">
        <h4 className="font-bold text-3xl mb-[3%] text-center">
          Bienvenido {name}
        </h4>
        {isQr ? (
          <CardQR
            name={name}
            position={position}
            qr={qr}
            imageProfile={imageProfile}
          />
        ) : (
          <Button onClick={generateQr}>Generate Qr</Button>
        )}
      </div>
      <div className="flex flex-wrap gap-3 justify-center">
        <ChipMenu
          image="./credential.png"
          text="Visualizar Crendencial"
          href={`${window.location.origin}/digital-card/${localStorage.getItem(
            "userId"
          )}`}
        />
        {chipsItem
          .filter((chip) => isAdmin || !chip.onlyAdmin)
          .map((chip, index) => (
            <ChipMenu
              key={index}
              image={chip.image}
              text={chip.text}
              href={chip.href}
            />
          ))}
      </div>
    </div>
  );
};

export default DesktopHome;
