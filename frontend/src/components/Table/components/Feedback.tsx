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
  Select,
  SelectItem,
} from "@nextui-org/react";

interface props {
  addFeedback: (feedback: string, status: string) => void;
}

const options = [
  // { value: "PENDING", label: "PENDING" },
  { value: "APPROVED", label: "APPROVED" },
  { value: "REJECTED", label: "REJECTED" },
];

const Feedback = ({ addFeedback }: props) => {
  const { isOpen, onOpen, onOpenChange } = useDisclosure();
  const [feedback, setFeedback] = useState<string>("");
  const [status, setStatus] = useState<string>("");

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
                <Select
                  items={options}
                  label="Feedback"
                  placeholder="Select Option"
                  className="max-w"
                  value={status}
                  onChange={(event) => setStatus(event.target.value)}
                >
                  {(option) => (
                    <SelectItem key={option.value}>{option.label}</SelectItem>
                  )}
                </Select>
              </ModalBody>
              <ModalFooter>
                <Button color="danger" variant="light" onPress={onClose}>
                  Close
                </Button>
                <Button
                  color="primary"
                  onPress={() => {
                    onClose();
                    addFeedback(feedback, status);
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
