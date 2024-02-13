"use client";
import Image from "next/image";
import React from "react";
import { Button, Input } from "@nextui-org/react";
import useLogin from "./useLogin";
import { MdOutlineVisibility, MdOutlineVisibilityOff } from "react-icons/md";
import { cookiesLogin } from "../../utils/cookies";

const index = () => {
  const { actions, state } = useLogin(cookiesLogin);

  return (
    <div className="flex items-center justify-centerflex flex-col  px-6 py-12  mt-48 lg:px-8  sm:mt-26">
      <div className="sm:mx-auto sm:w-full sm:max-w-sm">
        <Image
          className="mx-auto h-12 w-auto"
          src="/logoUnla.jpeg"
          alt="Your Company"
          width="100"
          height="50"
        />
        <h2 className="mt-10 text-center text-2xl font-bold leading-9 tracking-tight text-white">
          Sign in to your account
        </h2>
      </div>

      <div className="mt-10 sm:mx-auto sm:w-full sm:max-w-sm">
        <form className="space-y-6" action="#" method="POST">
          <div>
            <div className="mt-2">
              <Input
                label="UserName"
                value={state.inputsValues.userName}
                onValueChange={actions.onChangeUserName}
              />
            </div>
          </div>

          <div>
            <div className="flex items-center justify-between"></div>
            <div className="mt-2">
              <Input
                label="Password"
                value={state.inputsValues.password}
                onValueChange={actions.onChangePassword}
                endContent={
                  <button
                    className="focus:outline-none"
                    type="button"
                    onClick={actions.toggleVisibility}
                  >
                    {state.isVisiblePassword ? (
                      <MdOutlineVisibility className="text-2xl text-default-400 pointer-events-none" />
                    ) : (
                      <MdOutlineVisibilityOff className="text-2xl text-default-400 pointer-events-none" />
                    )}
                  </button>
                }
                type={state.isVisiblePassword ? "text" : "password"}
              />
            </div>
          </div>

          <div>
            <Button
              className="w-full bg-[#AE3B57]"
              onClick={actions.handleSubmit}
              color="primary"
              variant="solid"
            >
              Sign in
            </Button>
          </div>
        </form>
      </div>
    </div>
  );
};
export default index;
