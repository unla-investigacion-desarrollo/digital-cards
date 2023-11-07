"use client";
import React from "react";
import { Button, Input } from "@nextui-org/react";
import { MdOutlineVisibility, MdOutlineVisibilityOff } from "react-icons/md";
import useChangePassword from "./useChangePassword";
import Header from "@/components/Header";

const index = () => {
  const { actions, state } = useChangePassword();

  return (
    <>
      <Header />
      <div className="flex  justify-center flex flex-col  px-6 py-12  mt-40 lg:px-8  sm:mt-26">
        <div className="sm:mx-auto sm:w-full sm:max-w-sm">
          <h2 className="mt-10  text-2xl font-bold leading-9 tracking-tight text-white">
            Changed password
          </h2>
        </div>

        <div className="mt-10 sm:mx-auto sm:w-full sm:max-w-sm">
          <form className="space-y-6" action="#" method="POST">
            <div>
              <div className="mt-2">
                <h1>Name del user</h1>
              </div>
            </div>

            <div>
              <div className="flex items-center justify-between"></div>
              <div className="mt-2">
                <Input
                  label="Old Password"
                  value={state.inputsValues.oldPassword}
                  onValueChange={actions.onChangeOldPassword}
                  endContent={
                    <button
                      className="focus:outline-none"
                      type="button"
                      onClick={actions.toggleVisibilityOldPassword}
                    >
                      {state.isVisibleOldPassword ? (
                        <MdOutlineVisibility className="text-2xl text-default-400 pointer-events-none" />
                      ) : (
                        <MdOutlineVisibilityOff className="text-2xl text-default-400 pointer-events-none" />
                      )}
                    </button>
                  }
                  type={state.isVisibleOldPassword ? "text" : "password"}
                />
              </div>
              <div className="mt-2">
                <Input
                  label="New Password"
                  value={state.inputsValues.password}
                  onValueChange={actions.onChangePassword}
                  endContent={
                    <button
                      className="focus:outline-none"
                      type="button"
                      onClick={actions.toggleVisibilityPassword}
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
                Changed
              </Button>
            </div>
          </form>
        </div>
      </div>
    </>
  );
};
export default index;
