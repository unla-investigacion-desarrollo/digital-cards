/* eslint-disable react-hooks/rules-of-hooks */
import ProfileService from "@/core/ProfileService";
import DigitalCardPage from ".";
import type { InferGetServerSidePropsType, GetServerSideProps } from "next";

const getData = async (id: string) => {
  let repo = {};
  await ProfileService.getCurrentProfile(id).then((response) => {
    repo = {
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
  });
  return repo;
};

const index = async ({ params }: { params: { idPage: string } }) => {
  const data = await getData(params.idPage);

  return (
    <>
      <DigitalCardPage data={data}></DigitalCardPage>
    </>
  );
};

export default index;
