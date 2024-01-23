import UserService from "@/core/UserService";
import { useRouter } from "next/navigation";
import { useState } from "react";

type inputsValues = {
  password: string;
  oldPassword: string;
};

const useChangePassword = () => {
  const [inputsValues, setInputsValues] = useState<inputsValues>({
    password: "",
    oldPassword: "",
  });
  const router = useRouter();

  const [isVisiblePassword, setIsVisiblePassword] = useState<Boolean>(false);
  const [isVisibleOldPassword, setIsVisibleOldPassword] =
    useState<Boolean>(false);

  const toggleVisibilityPassword = () =>
    setIsVisiblePassword(!isVisiblePassword);

  const toggleVisibilityOldPassword = () =>
    setIsVisibleOldPassword(!isVisibleOldPassword);

  const onChangePassword = (value: string) => {
    setInputsValues((prevstate) => ({ ...prevstate, password: value }));
  };

  const onChangeOldPassword = (value: string) => {
    setInputsValues((prevstate) => ({ ...prevstate, oldPassword: value }));
  };

  const handleSubmit = async () => {
    //UserService.loginRequest(inputsValues.userName, inputsValues.password);
    //router.push("/home");
  };

  return {
    state: {
      inputsValues,
      isVisiblePassword,
      isVisibleOldPassword,
    },
    actions: {
      onChangeOldPassword,
      onChangePassword,
      toggleVisibilityPassword,
      toggleVisibilityOldPassword,
      handleSubmit,
    },
  };
};

export default useChangePassword;
