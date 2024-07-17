import React from "react";

const ErrorPage = ({ error }) => {
  console.error("Error al obtener los datos:", error);
  return (
    <>
      <div className="flex justify-center items-center h-screen">
        <div className="text-center">
          <h1 className="text-4xl font-bold text-red-600 mb-4">
            Error: No se pudo obtener la credencial
          </h1>
          <p className="text-lg text-gray-700">
            Lo sentimos, no se pudo obtener la credencial necesaria para cargar
            la página. Por favor, inténtalo de nuevo más tarde.
          </p>
        </div>
      </div>
    </>
  );
};

export default ErrorPage;
