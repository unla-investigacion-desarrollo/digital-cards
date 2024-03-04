import CareerService from "@/core/CareerService";
import ProfileService from "@/core/ProfileService";
import { useState, useEffect } from "react";
import Swal from "sweetalert2";
import { useRouter } from "next/navigation";

type InputsState = {
  name: string;
  subtitle: string;
  linkedlin: string;
  correo: string;
  image: string;
  aboutMe: string;
  projects: string;
  subjects: string[];
  newSubjects: string;
  university: string[];
  newUniversity: string;
  careerId: number | null;
  phone: string;
};

type Careers = {
  id: string;
  name: string;
};

const useFormCard = () => {
  const [inputs, setInputs] = useState<InputsState>({
    name: "",
    subtitle: "",
    linkedlin: "",
    correo: "",
    image: "",
    aboutMe: "",
    projects: "",
    subjects: [],
    newSubjects: "",
    university: [],
    newUniversity: "",
    careerId: null,
    phone: "",
  });

  const [careers, setCareers] = useState<Careers[]>([] as any);
  const router = useRouter();

  const handleInputs = async (
    value: string | string[] | Blob,
    name: string
  ) => {
    if (name == "image") value = await convertToBase64(value);
    setInputs((prevState) => ({
      ...prevState,
      [name]: value,
    }));
  };

  const convertToBase64 = (file: any) => {
    return new Promise((resolve, reject) => {
      const reader = new FileReader();
      reader.onload = () => {
        // Eliminar el prefijo "data:image/png;base64,"
        const base64Content = reader?.result?.split(",")[1];
        resolve(base64Content);
      };
      reader.onerror = reject;
      reader.readAsDataURL(file);
    });
  };

  const handleAddSubjectsItem = () => {
    if (inputs.newSubjects.trim() !== "") {
      handleInputs([...inputs.subjects, inputs.newSubjects], "subjects");
      handleInputs("", "newSubjects");
    }
  };

  const handleRemoveSubjectsItem = (index: number) => {
    const updatedItems = inputs.subjects.filter((_, i) => i !== index);
    handleInputs(updatedItems, "subjects");
  };

  const handleAddUniversityItem = () => {
    if (inputs.newUniversity.trim() !== "") {
      handleInputs([...inputs.university, inputs.newUniversity], "university");
      handleInputs("", "newUniversity");
    }
  };

  const handleRemoveUniversityItem = (index: number) => {
    const updatedItems = inputs.university.filter((_, i) => i !== index);
    handleInputs(updatedItems, "university");
  };

  const handleCareer = (change: any) => {
    handleInputs(change.target.value, "careerId");
  };

  const onClickNewProfile = async () => {
    await ProfileService.newProfile(inputs).then((response) => {
      Swal.fire({
        icon: "success",
        title: "Nueva peticion de credencial exitosa",
        text: `${response}`,
      });
      router.push("/dashboard");
    });
  };

  useEffect(() => {
    request();
  }, []);

  const request = async () => {
    await CareerService.getAll().then((data) => {
      data.forEach((career: any) => {
        setCareers((prevState) => [
          ...prevState,
          { id: career.id, name: career.name },
        ]);
      });
    });
  };

  return {
    state: {
      inputs,
      careers,
    },
    actions: {
      handleInputs,
      handleCareer,
      handleAddSubjectsItem,
      handleRemoveSubjectsItem,
      handleAddUniversityItem,
      handleRemoveUniversityItem,
      onClickNewProfile,
    },
  };
};

export default useFormCard;
