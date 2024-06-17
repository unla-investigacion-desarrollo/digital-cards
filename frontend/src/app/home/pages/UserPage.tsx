"use client";
import Header from "@/components/Header";
import React from "react";
import useHome from "../useHome";
import MobileHome from "../components/MobileHome";
import DesktopHome from "../components/DesktopHome";

const UserPage = () => {
  const { state, actions, chipsItem } = useHome();

  return (
    <>
      <Header />
      {state.isLoading ? (
        <h1> is Loading ..</h1>
      ) : (
        <div className="flex flex-col w-full items-center justify-center gap-10 p-5 lg:h-[80vh]">
          {/* Mobile and small notebook view */}
          <MobileHome
            name={state?.userInfo?.name}
            isQr={state?.isQr}
            position={state?.userInfo?.position}
            qr={state?.userInfo?.qr}
            imageProfile={state?.userInfo?.imageProfile}
            generateQr={actions?.generateQr}
          />
          {/* Larger notebook and desktop view */}
          <DesktopHome
            name={state?.userInfo?.name}
            isQr={state?.isQr}
            position={state?.userInfo?.position}
            qr={state?.userInfo?.qr}
            imageProfile={state?.userInfo?.imageProfile}
            generateQr={actions?.generateQr}
            chipsItem={chipsItem}
          />
        </div>
      )}
    </>
  );
};

export default UserPage;
