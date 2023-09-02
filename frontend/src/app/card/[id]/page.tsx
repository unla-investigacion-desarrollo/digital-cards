"use client";
import Header from "@/components/Header";
import React from "react";
import {
  Card,
  CardHeader,
  CardBody,
  Image,
  Switch,
  Link,
  Chip,
} from "@nextui-org/react";

async function getData(id: number) {
  const response = await import("../../../mocks/profe_" + id + ".json");
  return response;
}

const index = async ({ params }: { params: { id: number } }) => {
  const data = await getData(params.id);

  return (
    <>
      <Header />

      <Switch className="flex flex-1 items-center justify-center " size="sm">
        Mode Complete
      </Switch>
      <div className="flex flex-1 items-center justify-center  h-[80vh] ">
        <Card className=" w-[500px] ">
          <CardHeader className="pb-0 pt-4 px-4 flex-row items-start  ">
            <div className="w-[200px] max-w-[200px]">
              <h4 className="font-bold text-3xl">{data.name}</h4>
              <p className="text-lg font-bold  ">{data.position}</p>
            </div>
            <div className="ml-[150px]">
              <Image
                alt="Card background"
                className="object-cover rounded-xl grid justify-items-end"
                src={data.imageProfile}
                width={120}
              />
            </div>
          </CardHeader>

          <CardBody className="overflow-visible py-2 ">
            <div>
              <small className="text-sm font-semibold mt-3">Materias :</small>
              <ul className="flex flex-col">
                {data.materias.map((materia: string) => {
                  return (
                    <small className="text-default-500 mt-1" key={materia}>
                      {materia}
                    </small>
                  );
                })}
              </ul>
              <Link
                isExternal
                showAnchorIcon
                href={data.linkedin}
                color="foreground"
                className="text-sm mt-3"
              >
                Linkedlin
              </Link>
              <Chip radius="sm" className="text-sm ">
                {data.mail}
              </Chip>
              <div className="grid justify-items-end ">
                <Image
                  alt="Card background"
                  className="object-cover rounded-xl grid justify-items-end"
                  src="/logoUnla.jpeg"
                  width={40}
                />
              </div>
            </div>
          </CardBody>
        </Card>
      </div>
    </>
  );
};

export default index;
