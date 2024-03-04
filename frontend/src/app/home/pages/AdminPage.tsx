"use client";
import Header from "@/components/Header";
import React from "react";
import CardQR from "@/components/CardQR";
import { Button } from "@nextui-org/react";
import useHome from "../useHome";
import ChipMenu from "@/components/ChipMenu";
import CardReview from "@/components/CardReview";

const AdminPage = () => {
  const { state, actions } = useHome();
  return (
    <>
      <Header />
      {state.isLoading ? (
        <h1> is Loading ..</h1>
      ) : (
        <div className="flex flex-col  sw-[1200px] h-[80vh] items-center justify-center gap-[10%]">
          <div className="flex flex-row w-[100%]">
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
            <div className="flex items-center justify-cente">
              <CardReview />
            </div>
          </div>

          <div className="flex flex-row gap-[3%]">
            <ChipMenu
              image="./historial.png"
              text="Peticiones actualizacion"
              href="dashboard"
            />
            <ChipMenu
              image="./form.png"
              text="Formulario cambio de credential"
              href="/form-card"
            />
            <ChipMenu
              image="./changePassword.png"
              text="Cambiar Password"
              href="/change-password"
            />
            <ChipMenu
              image="./credential.png"
              text="Visualizar Crendencial"
              href={`${
                window.location.origin
              }/digital-card/${localStorage.getItem("userId")}`}
            />
            <ChipMenu
              image="./newUser.png"
              text="Crear nuevo Usuario"
              href="/new-user"
            />
          </div>
        </div>
      )}
    </>
  );
};

export default AdminPage;
