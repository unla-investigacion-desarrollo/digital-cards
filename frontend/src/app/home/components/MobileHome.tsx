import CardQR from "@/components/Card/CardQR";
import { Button } from "@nextui-org/react";
import React from "react";

interface props {
  name: string;
  isQr: boolean;
  position: string;
  qr: string;
  imageProfile: string;
  generateQr: () => void;
}

const MobileHome = ({
  name,
  isQr,
  position,
  qr,
  imageProfile,
  generateQr,
}: props) => {
  return (
    <div className="flex flex-col items-center justify-center w-full lg:hidden mt-5">
      <h4 className="font-bold text-3xl mb-[10%]">Bienvenido {name}</h4>
      {isQr ? (
        <div className="flex flex-col items-center">
          <CardQR
            name={name}
            position={position}
            qr={qr}
            imageProfile={imageProfile}
          />
        </div>
      ) : (
        <Button onClick={generateQr}>Generate Qr</Button>
      )}
    </div>
  );
};

export default MobileHome;
