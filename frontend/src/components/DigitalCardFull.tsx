"use client";
import {
  Card,
  CardBody,
  CardHeader,
  Chip,
  Divider,
  Image,
  Link,
  ScrollShadow,
} from "@nextui-org/react";

import React from "react";

type Props = {
  name: string;
  position: string;
  imageProfile: string;
  materias: string[];
  linkedin: string;
  mail: string;
  universidades: string[];
  proyectosAcademicosAndInvestigaciones: any;
};

const DigitalCardFull = ({
  name,
  position,
  imageProfile,
  materias,
  linkedin,
  mail,
  universidades,
  proyectosAcademicosAndInvestigaciones,
}: Props) => {
  return (
    <div className="flex flex-1 items-center justify-center  ">
      <Card className=" w-[1200px] h-[75vh] p-[1%]">
        <CardHeader className="p-8 flex-row items-start  ">
          <div className="w-[600px] max-w-[600px]">
            <h4 className="font-bold text-4xl">{name}</h4>
            <p className="text-xl font-bold  ">{position}</p>
          </div>
          <div className="ml-[350px]">
            <Image
              alt="Card background"
              className="object-cover rounded-xl grid justify-items-end"
              src={imageProfile}
              width={180}
            />
          </div>
        </CardHeader>

        <CardBody className="overflow-visible p-8 grid grid-cols-3 gap-5">
          <div>
            <div className="mb-3" id={"materias"}>
              <small className="text-lg font-semibold ">Materias</small>
              <ul className="flex flex-col">
                {materias.map((materia: string) => {
                  return (
                    <small
                      className="text-default-500 mt-1 text-sm"
                      key={materia}
                    >
                      {materia}
                    </small>
                  );
                })}
              </ul>
            </div>
            <div className="mb-3" id={"proyectos-academicos"}>
              <small className="text-lg font-semibold ">Universidades</small>
              <ul className="flex flex-col">
                {universidades.map((universidad: string) => {
                  return (
                    <small
                      className="text-default-500 mt-1 text-sm"
                      key={universidad}
                    >
                      {universidad}
                    </small>
                  );
                })}
              </ul>
            </div>

            <small className="text-lg font-semibold mt-6">Contacto</small>
            <div className="flex flex-colum mt-1">
              <Link
                isExternal
                showAnchorIcon
                href={linkedin}
                color="foreground"
                className="text-sm mt-3"
              >
                Linkedlin
              </Link>
              <Chip radius="sm" className="text-sm mt-3 ">
                {mail}
              </Chip>
            </div>
          </div>
          <div className="grid-cols-2">
            <div className="grid grid-colum ">
              <small className="text-lg font-semibold ">Sobre mi</small>
              <small className="text-default-500 mt-1  w-[600px] max-h-[120px] text-justify">
                Soy un profesor universitario especializado en sistemas y
                algoritmia. Mi pasión es compartir conocimientos y experiencias
                en el mundo de la informática con mis estudiantes. A lo largo de
                años de enseñanza, he desarrollado un enfoque pedagógico que
                combina la teoría sólida con aplicaciones prácticas. Mi objetivo
                es preparar a los estudiantes para enfrentar los desafíos
                tecnológicos del siglo XXI, inculcando un profundo entendimiento
                de la ciencia de la computación y las habilidades necesarias
                para la resolución de problemas complejos. Además, mi compromiso
                con la investigación me mantiene actualizado en las tendencias
                tecnológicas más recientes. Espero tener la oportunidad de
                trabajar juntos en esta emocionante travesía educativa.
              </small>
            </div>
            <ScrollShadow hideScrollBar className="w-[600px] max-h-[230px]">
              <div id="proyectos-academicos/investigaciones" className="mt-4  ">
                {proyectosAcademicosAndInvestigaciones.map((item) => {
                  return (
                    <div key={item.nombre}>
                      <div className="space-y-0.5">
                        <h4 className="text-small font-medium">
                          {item.nombre}
                        </h4>
                        <p className="text-xs text-default-400 text-justify">
                          {item.description}
                        </p>
                        <div className="text-xs">{item.fecha}</div>
                      </div>
                      <Divider className="my-4" />
                    </div>
                  );
                })}
              </div>
            </ScrollShadow>
          </div>
        </CardBody>
        <div className="text-center text-small text-default-400 font-medium">
          © 2023 Universidad Nacional de Lanús. Todos los derechos reservados
        </div>
      </Card>
    </div>
  );
};

export default DigitalCardFull;
