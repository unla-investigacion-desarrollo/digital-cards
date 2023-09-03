"use client";
import DigitalCard from "@/components/DigitalCard";
import Header from "@/components/Header";
import { Switch } from "@nextui-org/react";
import React from "react";
import useDigitalCard from "../useDigitalCard";
import DigitalCardFull from "@/components/DigitalCardFull";

const DigitalCardPage = ({ data }: any) => {
  const { state, actions } = useDigitalCard();

  return (
    <>
      <Header />

      <Switch
        className="flex flex-1 items-center justify-center mb-4 ml-[66%]  "
        size="sm"
        isSelected={state.isCompletedMode}
        onChange={actions.changeMode}
      >
        Complete Mode
      </Switch>
      {state.isCompletedMode ? (
        <DigitalCardFull
          name={data.name}
          position={data.position}
          imageProfile={data.imageProfile}
          materias={data.materias}
          linkedin={data.linkedin}
          mail={data.mail}
          universidades={data.universidades}
          proyectosAcademicosAndInvestigaciones={
            data.proyectosAcademicosAndInvestigaciones
          }
        ></DigitalCardFull>
      ) : (
        <DigitalCard
          name={data.name}
          position={data.position}
          imageProfile={data.imageProfile}
          materias={data.materias}
          linkedin={data.linkedin}
          mail={data.mail}
        />
      )}
    </>
  );
};

export default DigitalCardPage;
