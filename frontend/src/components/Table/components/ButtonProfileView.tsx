"use client";
import React from "react";
import {
  Modal,
  ModalContent,
  ModalHeader,
  ModalBody,
  ModalFooter,
  Button,
  useDisclosure,
} from "@nextui-org/react";
import DigitalCardFull from "@/components/DigitalCard/DigitalCardFull";

const ButtonProfileView = ({ profileInfo }) => {
  const { isOpen, onOpen, onClose } = useDisclosure();
  console.log(profileInfo);

  return (
    <>
      <div className="flex flex-wrap gap-3">
        <Button key={"full"} onPress={onOpen}>
          Open
        </Button>
      </div>
      <Modal size={"full"} isOpen={isOpen} onClose={onClose}>
        <ModalContent>
          {(onClose) => (
            <>
              <ModalHeader className="flex flex-col gap-1">Profile</ModalHeader>
              <ModalBody>
                <DigitalCardFull
                  name={profileInfo.name}
                  position={profileInfo.title}
                  imageProfile={profileInfo.photo}
                  materias={profileInfo.courses}
                  linkedin={profileInfo.urlLinkedin}
                  mail={profileInfo.correo}
                  universidades={profileInfo.institutions}
                  proyectosAcademicosAndInvestigaciones={profileInfo.projects}
                  moreInfo={profileInfo.moreInfo}
                ></DigitalCardFull>
              </ModalBody>
              <ModalFooter>
                <Button color="danger" variant="light" onPress={onClose}>
                  Close
                </Button>
              </ModalFooter>
            </>
          )}
        </ModalContent>
      </Modal>
    </>
  );
};

export default ButtonProfileView;
