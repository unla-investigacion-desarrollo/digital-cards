import Header from "@/components/Header";
import React from "react";
import CardQR from "@/components/CardQR";

async function getData(idUser: number) {
  const response = await import(
    "../../mocks/infoHomeUser/profe_" + idUser + ".json"
  );
  return response;
}

const index = async () => {
  const data = await getData(1);
  console.log(data);
  return (
    <>
      <Header />
      <div className="flex flex-1 items-center justify-center flex">
        <CardQR
          name={data.name}
          position={data.position}
          qr={data.qr}
          imageProfile={data.imageProfile}
        />
      </div>
    </>
  );
};

export default index;
