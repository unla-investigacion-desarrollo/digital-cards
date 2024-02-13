import React from "react";
import Image from "next/image";
import { deleteCookies } from "@/utils/cookies";
import { useRouter } from "next/navigation";

const Header = () => {
  const router = useRouter();

  return (
    <>
      <header className="bg-bgHeader">
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
          <div className="lg:flex lg:flex-1 lg:justify-end">
            <ul className="flex space-x-4">
              <li>
                <a
                  href="#"
                  className="text-s font-small text-gray-200 hover:text-white transition-colors duration-300 hover:border-b-2 border-white"
                >
                  Peticiones actualizacion
                </a>
              </li>
              <li>
                <a
                  href="/home"
                  className="text-s font-small text-gray-200 hover:text-white transition-colors duration-300 hover:border-b-2 border-white"
                >
                  Home
                </a>
              </li>
              <li>
                <a
                  onClick={() => {
                    deleteCookies();
                    router.push("/login");
                  }}
                  className="text-s font-small text-red-500 hover:text-red-700 transition-colors duration-300 hover:border-b-2 border-red-500"
                >
                  Logout
                </a>
              </li>
            </ul>
          </div>
        </nav>
      </header>
    </>
  );
};

export default Header;
