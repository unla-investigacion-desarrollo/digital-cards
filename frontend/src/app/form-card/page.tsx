"use client";
import React, { useState } from "react";
import { Input, Textarea, Button, Chip, Avatar, Card } from "@nextui-org/react";

const index = () => {
  const [name, setName] = useState("");
  const [subtitle, setSubtitle] = useState("");
  const [image, setImage] = useState(null);
  const [projects, setProjects] = useState("");
  const [items, setItems] = useState([]);
  const [newItem, setNewItem] = useState("");

  const handleImageUpload = (event) => {
    const file = event.target.files[0];
    setImage(file);
  };

  const handleAddItem = () => {
    if (newItem.trim() !== "") {
      setItems([...items, newItem]);
      setNewItem("");
    }
  };

  const handleRemoveItem = (index) => {
    const updatedItems = items.filter((_, i) => i !== index);
    setItems(updatedItems);
  };

  return (
    <div className="flex flex-col justify-center items-center h-screen">
      <div className="w-[1200px] h-[75vh] p-[1%] space-y-4 flex height-auto text-foreground box-border bg-content1 outline-none rounded-lg">
        <div className="w-1/2 pr-4 mb-5">
          <>
            <h1 className="text-4xl font-semibold mb-2">Perfil</h1>
            <div className="flex flex-col space-y-4">
              <Input
                label="Nombre"
                value={name}
                onChange={(e) => setName(e.target.value)}
                placeholder="Escribe tu nombre"
              />
              <Input
                label="Subtítulo o Cargo"
                value={subtitle}
                onChange={(e) => setSubtitle(e.target.value)}
                placeholder="Escribe tu subtítulo o cargo"
              />
              <Input
                label="Linkedlin"
                value={subtitle}
                onChange={(e) => setSubtitle(e.target.value)}
                placeholder="Escribe tu subtítulo o cargo"
              />
              <Input
                label="Correo Electronico"
                value={subtitle}
                onChange={(e) => setSubtitle(e.target.value)}
                placeholder="Escribe tu subtítulo o cargo"
              />
              <>
                <div className="mt-2">
                  <Input
                    label="Añadir Materias"
                    value={newItem}
                    onChange={(e) => setNewItem(e.target.value)}
                    placeholder="Añadir Materias"
                  />
                  <div className="mt-4">
                    {items.map((item, index) => (
                      <Chip className="mr-2" key={index}>
                        <div className=" flex flex-row  justify-center items-center">
                          {item}
                          <svg
                            xmlns="http://www.w3.org/2000/svg"
                            height="1em"
                            viewBox="0 0 448 512"
                            onClick={() => handleRemoveItem(index)}
                            className="ml-2 fill-white"
                          >
                            <path d="M135.2 17.7L128 32H32C14.3 32 0 46.3 0 64S14.3 96 32 96H416c17.7 0 32-14.3 32-32s-14.3-32-32-32H320l-7.2-14.3C307.4 6.8 296.3 0 284.2 0H163.8c-12.1 0-23.2 6.8-28.6 17.7zM416 128H32L53.2 467c1.6 25.3 22.6 45 47.9 45H346.9c25.3 0 46.3-19.7 47.9-45L416 128z" />
                          </svg>
                        </div>
                      </Chip>
                    ))}
                  </div>
                  <Button
                    onClick={handleAddItem}
                    className="mt-2"
                    disabled={!newItem.trim()}
                  >
                    Añadir
                  </Button>
                </div>
              </>
            </div>
          </>
        </div>

        <div className="w-1/2 flex flex-col  space-y-6">
          <div className=" flex flex-col  space-y-6">
            <label
              htmlFor="imageInput"
              className="text-gray-600 font-medium mt-14"
            >
              Subir Imagen de Perfil
            </label>
            <input
              type="file"
              id="imageInput"
              accept="image/*"
              onChange={handleImageUpload}
            />

            {image ? (
              <Avatar src={URL.createObjectURL(image)} className="w-28 h-28" />
            ) : (
              <></>
            )}
          </div>
          <Textarea
            label="Sobre mi"
            value={projects}
            onChange={(e) => setProjects(e.target.value)}
            placeholder="Escribe una presentacion para tu perfil"
          />
          <Textarea
            label="Proyectos de Investigación"
            value={projects}
            onChange={(e) => setProjects(e.target.value)}
            placeholder="Escribe sobre tus proyectos de investigación"
          />
          <>
            <div className="mt-2">
              <Input
                label="Añadir Universidades"
                value={newItem}
                onChange={(e) => setNewItem(e.target.value)}
                placeholder="Añadir Universidades"
              />
              <div className="mt-4">
                {items.map((item, index) => (
                  <Chip className="mr-2" key={index}>
                    <div className=" flex flex-row  justify-center items-center">
                      {item}
                      <svg
                        xmlns="http://www.w3.org/2000/svg"
                        height="1em"
                        viewBox="0 0 448 512"
                        onClick={() => handleRemoveItem(index)}
                        className="ml-2 fill-white"
                      >
                        <path d="M135.2 17.7L128 32H32C14.3 32 0 46.3 0 64S14.3 96 32 96H416c17.7 0 32-14.3 32-32s-14.3-32-32-32H320l-7.2-14.3C307.4 6.8 296.3 0 284.2 0H163.8c-12.1 0-23.2 6.8-28.6 17.7zM416 128H32L53.2 467c1.6 25.3 22.6 45 47.9 45H346.9c25.3 0 46.3-19.7 47.9-45L416 128z" />
                      </svg>
                    </div>
                  </Chip>
                ))}
              </div>
              <Button
                onClick={handleAddItem}
                className="mt-2"
                disabled={!newItem.trim()}
              >
                Añadir
              </Button>
            </div>
          </>
        </div>
      </div>
      <Button className="mt-4 ml-4" type="submit">
        Enviar
      </Button>
    </div>
  );
};

export default index;
