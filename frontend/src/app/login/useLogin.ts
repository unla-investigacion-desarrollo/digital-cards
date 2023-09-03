import { useState } from "react";

type inputsValues = {
  email: string;
  password: string;
};

const useLogin = () => {
  const [inputsValues, setInputsValues] = useState<inputsValues>({
    email: "",
    password: "",
  });

  const [isVisiblePassword, setIsVisiblePassword] = useState<Boolean>(false);

  const toggleVisibility = () => setIsVisiblePassword(!isVisiblePassword);

  const onChangeEmail = (value: string) => {
    setInputsValues((prevstate) => ({ ...prevstate, email: value }));
  };

  const onChangePassword = (value: string) => {
    setInputsValues((prevstate) => ({ ...prevstate, password: value }));
  };

  return {
    state: {
      inputsValues,
      isVisiblePassword,
    },
    actions: {
      onChangeEmail,
      onChangePassword,
      toggleVisibility,
    },
  };
};

export default useLogin;
