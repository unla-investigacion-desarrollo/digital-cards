import React from "react";
import {
  Card,
  CardBody,
  CardFooter,
  CardHeader,
  Image,
} from "@nextui-org/react";
import { useRouter } from "next/navigation";

export default function OptionList() {
  const router = useRouter();
  const list = [
    {
      title: "Historico Peticiones de Actualizacion",
      img: "/logoUnla.jpeg",
      href: "form-card",
    },
    {
      title: "Nueva peticion de cambio de credential",
      img: "/logoUnla.jpeg",
      href: "form-card",
    },
    {
      title: "Cambiar Password",
      img: "/logoUnla.jpeg",
      href: "change-password",
    },
    {
      title: "Vista completa de credencial",
      img: "/logoUnla.jpeg",
      href: "digital-card/joan-laporte",
    },
  ];

  return (
    <div className="gap-2 grid justify-items-center  grid-cols-2 max-w-[1000px]   ">
      {list.map((item, index) => (
        <Card
          className="py-4 w-[100%]"
          key={index}
          isPressable
          onPress={() => router.push(item.href)}
        >
          <CardHeader className="pb-0 pt-2 px-4 flex-col items-center">
            <h4 className="font-bold text-large">{item.title}</h4>
          </CardHeader>
          <CardBody className="flex items-center justify-center overflow-visible py-2 w-[100%]">
            <Image
              alt="Card background"
              className="object-cover rounded-xl "
              src={item.img}
              width={"100%"}
              height={"100%"}
            />
          </CardBody>
        </Card>
      ))}
    </div>
  );
}
