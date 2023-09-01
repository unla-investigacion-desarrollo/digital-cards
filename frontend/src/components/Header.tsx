import React from "react";
import Image from "next/image";
import Link from "next/link";

const Header = () => {
  return (
    <>
      <header className="">
        <nav
          className="mx-auto flex max-w-7xl items-center justify-between p-6 lg:px-8"
          aria-label="Global"
        >
          <div className="flex lg:flex-1">
            <a
              href="http://www.unla.edu.ar/"
              className="-m-1.5 p-1.5"
              target="_blank"
            >
              <span className="sr-only">Unla</span>
              <Image
                className="h-10 w-auto"
                src="/logoUnla.jpeg"
                alt=""
                width="100"
                height="50"
              />
            </a>
          </div>
          <div className="lg:flex lg:flex-1 lg:justify-end"></div>
        </nav>
      </header>
    </>
  );
};

export default Header;
