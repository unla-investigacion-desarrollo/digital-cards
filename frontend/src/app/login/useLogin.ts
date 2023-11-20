import UserService from "@/core/UserService";
import { useRouter } from "next/navigation";
import { useState } from "react";

type inputsValues = {
  userName: string;
  password: string;
};

const useLogin = () => {
  const [inputsValues, setInputsValues] = useState<inputsValues>({
    userName: "",
    password: "",
  });
  const router = useRouter();

  const [isVisiblePassword, setIsVisiblePassword] = useState<Boolean>(false);

  const toggleVisibility = () => setIsVisiblePassword(!isVisiblePassword);

  const onChangeUserName = (value: string) => {
    setInputsValues((prevstate) => ({ ...prevstate, userName: value }));
  };

  const onChangePassword = (value: string) => {
    setInputsValues((prevstate) => ({ ...prevstate, password: value }));
  };

  const handleSubmit = async () => {
    await UserService.loginRequest(inputsValues.userName, inputsValues.password)
      .then((data) => {
        console.log("Respuesta:", data);
        if (data.username) router.push("/home");
      })
      .catch((error) => {
        console.error("Error en la petición:", error);
      });
  };

  return {
    state: {
      inputsValues,
      isVisiblePassword,
    },
    actions: {
      onChangeUserName,
      onChangePassword,
      toggleVisibility,
      handleSubmit,
    },
  };
};

export default useLogin;
