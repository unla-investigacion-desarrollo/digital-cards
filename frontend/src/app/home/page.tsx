import Header from "@/components/Header";
import React from "react";
import CardQR from "@/components/CardQR";

const index = () => {
  return (
    <>
      <Header />
      <div className="flex flex-1 items-center justify-center flex">
        <CardQR
          name={"Arnold"}
          position={"Software Engineer"}
          qr={"/qrPageUnla.png"}
          imageProfile={"/profe1.png"}
        />
      </div>
    </>
  );
};

export default index;
