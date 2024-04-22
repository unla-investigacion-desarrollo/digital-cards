"use client";
import React, { useState } from "react";
import {
  Modal,
  ModalContent,
  ModalHeader,
  ModalBody,
  ModalFooter,
  Button,
  useDisclosure,
  Textarea,
} from "@nextui-org/react";

interface props {
  addFeedback: (feedback: string) => void;
}

const Feedback = ({ addFeedback }: props) => {
  const { isOpen, onOpen, onOpenChange } = useDisclosure();
  const [feedback, setFeedback] = useState<string>("");
  return (
    <>
      <Button onPress={onOpen}>Dar Feedback</Button>
      <Modal isOpen={isOpen} onOpenChange={onOpenChange}>
        <ModalContent>
          {(onClose) => (
            <>
              <ModalHeader className="flex flex-col gap-1">
                Deja tu feedback para el profile
              </ModalHeader>
              <ModalBody>
                <Textarea
                  label="Feedback"
                  placeholder="¡Queremos escuchar tu opinión! Por favor, sé constructivo en tu feedback."
                  className="max-w"
                  value={feedback}
                  onValueChange={(value) => setFeedback(value)}
                />
              </ModalBody>
              <ModalFooter>
                <Button color="danger" variant="light" onPress={onClose}>
                  Close
                </Button>
                <Button
                  color="primary"
                  onPress={() => {
                    onClose();
                    addFeedback(feedback);
                  }}
                >
                  Save and Send
                </Button>
              </ModalFooter>
            </>
          )}
        </ModalContent>
      </Modal>
    </>
  );
};

export default Feedback;
