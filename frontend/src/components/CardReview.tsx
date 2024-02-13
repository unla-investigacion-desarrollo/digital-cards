import {
  Card,
  CardBody,
  CardFooter,
  CardHeader,
  Chip,
  Divider,
  Image,
} from "@nextui-org/react";
import React from "react";

const CardReview = () => {
  return (
    <div className=" mt-5 items-center justify-center ">
      <Card className="max-w-[600px]">
        <h2 className="font-bold text-2xl mt-3 text-center">
          Ultimo feedback de peticion
        </h2>
        <CardHeader className="flex gap-3">
          <Image
            alt="nextui logo"
            height={50}
            radius="sm"
            src="/profe1.png"
            width={50}
          />
          <div className="flex flex-col">
            <p className="text-md">Alejandra</p>
            <p className="text-small text-default-500">reviewer</p>
          </div>
        </CardHeader>
        <Divider />
        <CardBody>
          <p>
            "Tu presentación es concisa y clara, pero podrías agregar un enlace
            a tu perfil de LinkedIn para mayor credibilidad. Además, ampliar la
            sección 'Acerca de Mí' y proporcionar ejemplos específicos de
            proyectos de investigación sería beneficioso para destacar tu
            experiencia."
          </p>
        </CardBody>
        <Divider />
        <CardFooter>
          <Chip color="danger">Peticion rechazada</Chip>
        </CardFooter>
      </Card>
    </div>
  );
};

export default CardReview;
