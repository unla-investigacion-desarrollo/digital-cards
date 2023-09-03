"use client";
import Header from "@/components/Header";
import React from "react";
import CardQR from "@/components/CardQR";
import useHome from "./useHome";

const index = () => {
  const { state } = useHome();
  return (
    <>
      {state.isLoading ? (
        <h1> is Loading ..</h1>
      ) : (
        <>
          <Header />
          <div className="flex items-center justify-center ">
            <div className="flex flex-col sw-[1200px] h-[75vh] mt-5 items-center justify-center">
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
                <></>
              )}
            </div>
          </div>
        </>
      )}
    </>
  );
};

export default index;
