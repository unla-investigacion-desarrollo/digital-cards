import {
  Avatar,
  Card,
  CardBody,
  CardFooter,
  CardHeader,
  Image,
} from "@nextui-org/react";
import React from "react";

type props = {
  image: string;
  text: string;
};

const ChipMenu = ({ image, text }: props) => {
  return (
    <Card
      isBlurred
      className="border-none bg-bgHeader/60 dark:bg-default-100/50 max-w-[300px]"
      shadow="sm"
    >
      <CardBody>
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
