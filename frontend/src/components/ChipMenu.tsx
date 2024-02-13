"use client";
import { Card, CardBody, Image } from "@nextui-org/react";
import { useRouter } from "next/navigation";
import React from "react";

type props = {
  image: string;
  text: string;
  href: string;
};

const ChipMenu = ({ image, text, href }: props) => {
  const router = useRouter();

  return (
    <Card
      isBlurred
      className="border-none bg-bgHeader/60 dark:bg-default-100/50 max-w-[300px]"
      shadow="sm"
    >
      <CardBody onClick={() => router.push(href)}>
        <div className="flex items-center justify-between">
          <div className="flex-shrink-0">
            <Image
              alt="Album cover"
              className="object-cover"
              height={60}
              shadow="md"
              src={image}
              width={60}
            />
          </div>
          <div className="flex-grow text-right">
            <p className="text-base ">{text}</p>
          </div>
        </div>
      </CardBody>
    </Card>
  );
};

export default ChipMenu;
