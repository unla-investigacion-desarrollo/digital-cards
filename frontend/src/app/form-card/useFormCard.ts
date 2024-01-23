import { useState } from "react";

type inputsState = {
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
};

const useFormCard = () => {
  const [inputs, setInputs] = useState<inputsState>({
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
  });

  const handleInputs = async (
    value: string | string[] | Blob,
    name: string
  ) => {
    setInputs((prevState) => ({
      ...prevState,
      [name]: value,
    }));
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

  return {
    state: {
      inputs,
    },
    actions: {
      handleInputs,
      handleAddSubjectsItem,
      handleRemoveSubjectsItem,
      handleAddUniversityItem,
      handleRemoveUniversityItem,
    },
  };
};

export default useFormCard;
