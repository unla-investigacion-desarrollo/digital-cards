import React from "react";
import { Card, CardBody, CardFooter, Image } from "@nextui-org/react";
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
      title: "Vista completa de credencial",
      img: "/logoUnla.jpeg",
      href: "digital-card/joan-laporte",
    },
    {
      title: "Cambiar Password",
      img: "/logoUnla.jpeg",
      href: "change-password",
    },
  ];

  return (
    <div className="gap-2 grid justify-items-center  grid-cols-2 ">
      {list.map((item, index) => (
        <Card
          shadow="sm"
          key={index}
          isPressable
          onPress={() => router.push(item.href)}
        >
          <CardBody className="overflow-visible p-0">
            <Image
              shadow="sm"
              radius="lg"
              width="100%"
              alt={item.title}
              className="w-full object-cover h-[140px]"
              src={item.img}
            />
          </CardBody>
          <CardFooter className="text-small justify-between">
            <b>{item.title}</b>
            <p className="text-default-500">{item.price}</p>
          </CardFooter>
        </Card>
      ))}
    </div>
  );
}
