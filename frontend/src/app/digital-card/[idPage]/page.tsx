"use client";
import Header from "@/components/Header";
import React from "react";
import { Switch } from "@nextui-org/react";
import DigitalCard from "@/components/DigitalCard";

async function getData(idPage: string) {
  const response = await import("../../../mocks/profe_" + idPage + ".json");
  return response;
}

const index = async ({ params }: { params: { idPage: string } }) => {
  const data = await getData(params.idPage);

  return (
    <>
      <Header />
      <Switch className="flex flex-1 items-center justify-center " size="sm">
        Mode Complete
      </Switch>
      <DigitalCard
        name={data.name}
        position={data.position}
        imageProfile={data.imageProfile}
        materias={data.materias}
        linkedin={data.linkedin}
        mail={data.mail}
      ></DigitalCard>
    </>
  );
};

export default index;
