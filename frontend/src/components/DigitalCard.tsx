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

const DigitalCard = ({
  name,
  position,
  imageProfile,
  materias,
  linkedin,
  mail,
}: Props) => {
  return (
    <div className="flex flex-1 items-center justify-center  h-[80vh] ">
      <Card
        className="max-sm:w-[340px] max-sm:h-[340px]   w-[550px]"
        id="digitalCard"
      >
        <CardHeader className="max-sm:p-4 p-6 flex-row items-start  ">
          <div className="max-sm:w-[150px] w-[200px] max-w-[200px]">
            <h4 className="max-sm:text-xl font-bold text-3xl">{name}</h4>
            <p className="max-sm:text-base text-lg font-bold  ">{position}</p>
          </div>
          <div className="max-sm:ml-[60px] max-sm:w-[100px]  ml-[188px]">
            <Image
              alt="Card background"
              className="object-cover rounded-xl grid justify-items-end "
              src={imageProfile}
              width={120}
            />
          </div>
        </CardHeader>

        <CardBody className=" overflow-visible py-2 grid grid-cols-2 mb-4">
          <div>
            <small className="max-sm:text-xs text-sm font-semibold mt-3">
              Materias
            </small>
            <ul className="flex flex-col">
              {materias.map((materia: string) => {
                return (
                  <small
                    className=" max-sm:text-xs  text-default-500 mt-1"
                    key={materia}
                  >
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
          </div>
          <div className="grid justify-items-end content-end ">
            <Image
              alt="Card background"
              className="object-cover rounded-xl grid justify-items-end"
              src="/logoUnla.jpeg"
              width={40}
            />
          </div>
        </CardBody>
      </Card>
    </div>
  );
};

export default DigitalCard;
