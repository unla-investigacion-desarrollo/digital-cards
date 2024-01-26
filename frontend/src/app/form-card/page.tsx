"use client";
import React from "react";
import { Input, Textarea, Button, Chip, Avatar } from "@nextui-org/react";
import useFormCard from "./useFormCard";
import SelectCustom from "@/components/SelectCustom";

const index = () => {
  const { state, actions } = useFormCard();

  return (
    <div className="flex flex-col justify-center items-center h-screen">
      <div className="w-[1200px] h-[75vh] p-[1%] space-y-4 flex height-auto text-foreground box-border bg-content1 outline-none rounded-lg">
        <div className="w-1/2 pr-4 mb-5">
          <>
            <h1 className="text-4xl font-semibold mb-2">Perfil</h1>
            <div className="flex flex-col space-y-4">
              <Input
                label="Nombre"
                value={state.inputs.name}
                onChange={(e) => actions.handleInputs(e.target.value, "name")}
                placeholder="Escribe tu nombre"
              />
              <Input
                label="Subtítulo o Cargo"
                value={state.inputs.subtitle}
                onChange={(e) =>
                  actions.handleInputs(e.target.value, "subtitle")
                }
                placeholder="Escribe tu subtítulo o cargo"
              />
              <Input
                label="Linkedlin"
                value={state.inputs.linkedlin}
                onChange={(e) =>
                  actions.handleInputs(e.target.value, "linkedlin")
                }
                placeholder="Escribe tu Linkedlin"
              />
              <SelectCustom
                label="Select Career"
                options={state.careers}
                onChangeValue={actions.handleCareer}
              />
              <Input
                label="Correo Electronico"
                value={state.inputs.correo}
                onChange={(e) => actions.handleInputs(e.target.value, "correo")}
                placeholder="Escribe tu subtítulo o cargo"
              />
              <>
                <div className="mt-2">
                  <Input
                    label="Añadir Materias"
                    value={state.inputs.newSubjects}
                    onChange={(e) =>
                      actions.handleInputs(e.target.value, "newSubjects")
                    }
                    placeholder="Añadir Materias"
                  />
                  <div className="mt-4">
                    {state.inputs.subjects.map((item, index) => (
                      <Chip className="mr-2" key={index}>
                        <div className=" flex flex-row  justify-center items-center">
                          {item}
                          <svg
                            xmlns="http://www.w3.org/2000/svg"
                            height="1em"
                            viewBox="0 0 448 512"
                            onClick={() =>
                              actions.handleRemoveSubjectsItem(index)
                            }
                            className="ml-2 fill-white"
                          >
                            <path d="M135.2 17.7L128 32H32C14.3 32 0 46.3 0 64S14.3 96 32 96H416c17.7 0 32-14.3 32-32s-14.3-32-32-32H320l-7.2-14.3C307.4 6.8 296.3 0 284.2 0H163.8c-12.1 0-23.2 6.8-28.6 17.7zM416 128H32L53.2 467c1.6 25.3 22.6 45 47.9 45H346.9c25.3 0 46.3-19.7 47.9-45L416 128z" />
                          </svg>
                        </div>
                      </Chip>
                    ))}
                  </div>
                  <Button
                    onClick={actions.handleAddSubjectsItem}
                    className="mt-2"
                    disabled={!state.inputs.newSubjects.trim()}
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
              onChange={(event) =>
                actions.handleInputs(event?.target?.files[0], "image")
              }
            />

            {state.inputs.image ? (
              <Avatar
                src={URL.createObjectURL(state.inputs.image)}
                className="w-14 h-14"
              />
            ) : (
              <></>
            )}
          </div>
          <Textarea
            label="Sobre mi"
            value={state.inputs.aboutMe}
            onChange={(e) => actions.handleInputs(e.target.value, "aboutMe")}
            placeholder="Escribe una presentacion para tu perfil"
          />
          <Textarea
            label="Proyectos de Investigación"
            value={state.inputs.projects}
            onChange={(e) => actions.handleInputs(e.target.value, "projects")}
            placeholder="Escribe sobre tus proyectos de investigación"
          />
          <>
            <div className="mt-2">
              <Input
                label="Añadir Universidades"
                value={state.inputs.newUniversity}
                onChange={(e) =>
                  actions.handleInputs(e.target.value, "newUniversity")
                }
                placeholder="Añadir Universidades"
              />
              <div className="mt-4">
                {state.inputs.university.map((item, index) => (
                  <Chip className="mr-2" key={index}>
                    <div className=" flex flex-row  justify-center items-center">
                      {item}
                      <svg
                        xmlns="http://www.w3.org/2000/svg"
                        height="1em"
                        viewBox="0 0 448 512"
                        onClick={() =>
                          actions.handleRemoveUniversityItem(index)
                        }
                        className="ml-2 fill-white"
                      >
                        <path d="M135.2 17.7L128 32H32C14.3 32 0 46.3 0 64S14.3 96 32 96H416c17.7 0 32-14.3 32-32s-14.3-32-32-32H320l-7.2-14.3C307.4 6.8 296.3 0 284.2 0H163.8c-12.1 0-23.2 6.8-28.6 17.7zM416 128H32L53.2 467c1.6 25.3 22.6 45 47.9 45H346.9c25.3 0 46.3-19.7 47.9-45L416 128z" />
                      </svg>
                    </div>
                  </Chip>
                ))}
              </div>
              <Button
                onClick={actions.handleAddUniversityItem}
                className="mt-2"
                disabled={!state.inputs.newUniversity.trim()}
              >
                Añadir
              </Button>
            </div>
          </>
        </div>
      </div>
      <Button
        className="mt-4 ml-4"
        type="submit"
        onClick={() => actions.onClickNewProfile()}
      >
        Enviar
      </Button>
    </div>
  );
};

export default index;
