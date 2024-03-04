import UserService from "@/core/UserService";
import { useRouter } from "next/navigation";
import { useState } from "react";
import Swal from "sweetalert2";

type inputsValues = {
  userName: string;
  password: string;
};

const useNewUser = (cookiesLogin: any) => {
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
    await UserService.createNewUserRequest(
      inputsValues.userName,
      inputsValues.password
    )
      .then((data) => {
        Swal.fire({
          icon: "success",
          title: "Usuario Creado",
          text: `User: ${data.username}`,
        });
      })
      .catch((error) => {
        Swal.fire({
          icon: "error",
          title: "Error en la petición:",
          text: error.response.data,
        });
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

export default useNewUser;
