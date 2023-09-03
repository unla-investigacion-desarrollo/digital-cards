"use client";
import { useState } from "react";

const useDigitalCard = () => {
  const [isCompletedMode, setIsCompletedMode] = useState(true);

  const changeMode = () => {
    setIsCompletedMode(!isCompletedMode);
  };
  return {
    state: {
      isCompletedMode,
    },
    actions: {
      changeMode,
    },
  };
};

export default useDigitalCard;
