"use client";
import {
  Card,
  CardHeader,
  CardBody,
  CardFooter,
  Divider,
  Link,
  Image,
  Chip,
} from "@nextui-org/react";
import Header from "@/components/Header";
import React from "react";
import CardQR from "@/components/CardQR";
import useHome from "./useHome";
import { Button } from "@nextui-org/react";
import OptionList from "@/components/OptionList";

const index = () => {
  const { state, actions } = useHome();
  return (
    <>
      <Header />
      {state.isLoading ? (
        <h1> is Loading ..</h1>
      ) : (
        <div className="flex flex-row sw-[1200px] h-[75vh]  ">
          <div className="flex flex-col w-1/2 mt-5 items-center justify-center">
            <h4 className="font-bold text-3xl mb-20">
              Bienvenido {state?.userInfo?.name}
            </h4>
            {state.isQr ? (
              <CardQR
                name={state.userInfo.name}
                position={state.userInfo.position}
                qr={state.userInfo.qr}
                imageProfile={state.userInfo.imageProfile}
              />
            ) : (
              <Button onClick={actions.generateQr}> Generate Qr</Button>
            )}
          </div>
          <div className="flex flex-col w-1/2 mt-5 items-center ">
            <Card className="max-w-[600px]">
              <h2 className="font-bold text-2xl mt-3 text-center">
                Ultimo feedback de peticion
              </h2>
              <CardHeader className="flex gap-3">
                <Image
                  alt="nextui logo"
                  height={50}
                  radius="sm"
                  src="/profe1.png"
                  width={50}
                />
                <div className="flex flex-col">
                  <p className="text-md">Alejandra</p>
                  <p className="text-small text-default-500">reviewer</p>
                </div>
              </CardHeader>
              <Divider />
              <CardBody>
                <p>
                  "Tu presentación es concisa y clara, pero podrías agregar un
                  enlace a tu perfil de LinkedIn para mayor credibilidad.
                  Además, ampliar la sección 'Acerca de Mí' y proporcionar
                  ejemplos específicos de proyectos de investigación sería
                  beneficioso para destacar tu experiencia."
                </p>
              </CardBody>
              <Divider />
              <CardFooter>
                <Chip color="danger">Peticion rechazada</Chip>
              </CardFooter>
            </Card>
            <Divider className="mt-6 mb-6" />
            <OptionList></OptionList>
          </div>
        </div>
      )}
    </>
  );
};

export default index;
