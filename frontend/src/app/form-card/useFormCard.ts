import CareerService from "@/core/CareerService";
import ProfileService from "@/core/ProfileService";
import { useState, useEffect } from "react";

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

  const handleCareer = (change: any) => {
    handleInputs(change.target.value, "careerId");
  };

  const onClickNewProfile = async () => {
    await ProfileService.newProfile(inputs).then((response) => {
      console.log(response);
      alert(response);
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
