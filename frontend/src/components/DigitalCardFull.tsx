"use client";
import {
  Card,
  CardBody,
  CardHeader,
  Chip,
  Image,
  Link,
} from "@nextui-org/react";
import React from "react";

type Props = {
  name: string;
  position: string;
  imageProfile: string;
  materias: string[];
  linkedin: string;
  mail: string;
};

const DigitalCardFull = ({
  name,
  position,
  imageProfile,
  materias,
  linkedin,
  mail,
}: Props) => {
  return (
    <div className="flex flex-1 items-center justify-center  h-[80vh] ">
      <Card className=" w-[1200px] ">
        <CardHeader className="pb-0 pt-4 px-4 flex-row items-start  ">
          <div className="w-[200px] max-w-[200px]">
            <h4 className="font-bold text-3xl">{name}</h4>
            <p className="text-lg font-bold  ">{position}</p>
          </div>
          <div className="ml-[150px]">
            <Image
              alt="Card background"
              className="object-cover rounded-xl grid justify-items-end"
              src={imageProfile}
              width={120}
            />
          </div>
        </CardHeader>

        <CardBody className="overflow-visible py-2 ">
          <div>
            <small className="text-sm font-semibold mt-3">Materias :</small>
            <ul className="flex flex-col">
              {materias.map((materia: string) => {
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
              href={linkedin}
              color="foreground"
              className="text-sm mt-3"
            >
              Linkedlin
            </Link>
            <Chip radius="sm" className="text-sm ">
              {mail}
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
  );
};

export default DigitalCardFull;
