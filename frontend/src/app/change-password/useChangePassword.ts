import UserService from "@/core/UserService";
import { useRouter } from "next/navigation";
import { useState } from "react";
import Swal from "sweetalert2";

type inputsValues = {
  password: string;
  oldPassword: string;
};

const useChangePassword = () => {
  const [inputsValues, setInputsValues] = useState<inputsValues>({
    password: "",
    oldPassword: "",
  });

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
    await UserService.changePasswordRequest(inputsValues.password)
      .then((data) => {
        Swal.fire({
          icon: "success",
          title: "Cambio de Contraseña exitoso",
          text: `User: ${data.username}`,
        });
      })
      .catch((error) => {
        Swal.fire({
          icon: "error",
          title: "Error en la petición:",
        });
      });
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
