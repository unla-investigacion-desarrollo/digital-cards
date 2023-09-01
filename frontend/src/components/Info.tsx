import React from "react";

const Info = () => {
  return (
    <>
      <div className="relative isolate overflow-hidden mt-12 py-24 sm:py-32">
        <div className="mx-auto max-w-7xl px-6 lg:px-8">
          <div className="mx-auto max-w-2xl lg:mx-0">
            <h2 className="text-4xl font-bold tracking-tight text-white sm:text-6xl">
              Digital Card
            </h2>
            <p className="mt-6 text-lg leading-8 text-gray-300">
              Sistema digital de certificación para los profesores
              universitarios de la Unla. Esta plataforma permitirá a los
              docentes generar y almacenar sus certificados en formato
              electrónico seguro y accesible desde cualquier dispositivo con
              conexión a internet.
            </p>
          </div>
          <div className="mx-auto mt-10 max-w-2xl lg:mx-0 lg:max-w-none">
            <div className="grid grid-cols-1 gap-x-8 gap-y-6 text-base font-semibold leading-7 text-white sm:grid-cols-2 md:flex lg:gap-x-10">
              <a href="http://www.unla.edu.ar/">
                Pagina Oficial Unla <span aria-hidden="true">&rarr;</span>
              </a>
              <a href="https://campus.unla.edu.ar/">
                Campus Unla <span aria-hidden="true">&rarr;</span>
              </a>
            </div>
          </div>
        </div>
      </div>
    </>
  );
};

export default Info;
