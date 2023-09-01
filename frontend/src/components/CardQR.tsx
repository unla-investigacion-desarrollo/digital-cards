import React from "react";
import Image from "next/image";

type Props = {
  name: string;
  position: string;
  qr: string;
  imageProfile: string;
};

const CardQR = ({ name, position, qr, imageProfile }: Props) => {
  return (
    <>
      <div className="w-[382px] h-[376px] max-w-xs  bg-white rounded-lg shadow-lg dark:bg-gray-800 flex  flex flex-col items-center relative">
        <Image
          className="object-cover rounded-[200%] absolute -top-10 left-[42%] z-[9999] w-[60px] h-[60px]"
          src={imageProfile}
          alt="avatar"
          width={60}
          height={60}
        />
        <div className="py-5 text-center mt-5">
          <a
            href="https://www.linkedin.com/"
            className="block text-xl font-bold text-gray-800 dark:text-white"
            role="link"
          >
            {name}
          </a>
          <span className="text-sm text-gray-700 dark:text-gray-200">
            {position}
          </span>
        </div>
        <Image
          className="object-cover rounded-lg"
          src={qr}
          alt="avatar"
          width={221}
          height={221}
        />
      </div>
    </>
  );
};

export default CardQR;
