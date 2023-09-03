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
      <div className="flex items-center justify-center ">
        <div className="flex flex-col sw-[1200px] h-[75vh] mt-5 items-center justify-center">
          <h4 className="font-bold text-3xl mb-20"> Bienvenido {data.name}</h4>
          <CardQR
            name={data.name}
            position={data.position}
            qr={data.qr}
            imageProfile={data.imageProfile}
          />
        </div>
      </div>
    </>
  );
};

export default index;
