/* eslint-disable react-hooks/rules-of-hooks */
import DigitalCardPage from ".";

async function getData(idPage: string) {
  const response = await import(
    "../../../mocks/infoDigitalCard/profe_" + idPage + ".json"
  );
  return response.data;
}

const index = async ({ params }: { params: { idPage: string } }) => {
  const data = await getData(params.idPage);

  return (
    <>
      <DigitalCardPage data={data}></DigitalCardPage>
    </>
  );
};

export default index;
