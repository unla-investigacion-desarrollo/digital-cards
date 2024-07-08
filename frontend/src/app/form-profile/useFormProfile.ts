import CareerService from "@/core/CareerService";
import ProfileService from "@/core/ProfileService";
import { useState, useEffect } from "react";
import Swal from "sweetalert2";
import { useRouter } from "next/navigation";
import ReviewService from "@/core/ReviewService";

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
  profileName: string;
};

type Careers = {
  id: string;
  name: string;
};

const useFormProfile = ({ profileId }: { profileId?: string | null } = {}) => {
  console.log(profileId);
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
    profileName: "",
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

  const onSave = () => {
    if (profileId) {
      onClickEditProfile();
    } else onClickNewProfile();
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

  const onClickEditProfile = async () => {
    await ProfileService.editProfile(inputs, profileId).then((response) => {
      Swal.fire({
        icon: "success",
        title: "Good Edit",
        text: `${response}`,
      });
      router.push("/dashboard");
    });

    //TODO: ESTO DEBERIA ESTAR EN EL BACKEND(CADA VEZ QUE SEA HAGA UN UPDATE, SE NECESITA UN NUEVO REVIEW)
    await ReviewService.newReview(localStorage.getItem("userId"), profileId);
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
      onSave,
    },
  };
};

export default useFormProfile;
