import Header from "@/components/Header";
import React from "react";

async function getData(id: number) {
  const response = await import("../../../mocks/profe_" + id + ".json");
  return response;
}

const index = async ({ params }: { params: { id: number } }) => {
  const data = await getData(params.id);

  return (
    <>
      <Header />

      <div className="text-white">{params.id}</div>
      <div className="text-white">{data.id}</div>
    </>
  );
};

export default index;
