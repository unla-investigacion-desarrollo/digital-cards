/* eslint-disable react-hooks/rules-of-hooks */
import ProfileService from "@/core/ProfileService";
import DigitalCardPage from ".";
import ErrorPage from "./ErrorPage";

const getData = async (id: string) => {
  try {
    const response = await ProfileService.getCurrentProfile(id);

    if (!response) {
      throw new Error(
        "No se encontraron datos para la credencial proporcionada."
      );
    }

    const data = {
      id: response.id,
      name: response.name,
      position: response.title,
      imageProfile: response.photo,
      materias: response.courses,
      universidades: response.institutions,
      proyectosAcademicosAndInvestigaciones: response.projects,
      mail: response.mail,
      linkedin: response.urlLinkedin,
      moreInfo: response.moreInfo,
    };

    return data;
  } catch (error) {
    console.error("Error al obtener los datos:", error);
    throw new Error(
      "No hay credenciales disponibles o se produjo un error al obtener los datos."
    );
  }
};

const index = async ({ params }: { params: { idPage: string } }) => {
  try {
    const data = await getData(params.idPage);
    return <DigitalCardPage data={data}></DigitalCardPage>;
  } catch (error) {
    return <ErrorPage error={error} />;
  }
};

export default index;
