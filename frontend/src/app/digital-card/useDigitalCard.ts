"use client";
import html2canvas from "html2canvas";
import jsPDF from "jspdf";
import { useState } from "react";

const useDigitalCard = () => {
  const [isCompletedMode, setIsCompletedMode] = useState(false);

  const exportPdf = () => {
    const idElement = isCompletedMode ? "digitalCardFull" : "digitalCard";
    const elementCard = document.getElementById(idElement) as HTMLElement;
    html2canvas(elementCard, {
      logging: true,
      letterRendering: 1,
      useCORS: true,
    }).then((canvas) => {
      const imgWidth = 210;
      const imgHeight = 130;
      const imgData = canvas.toDataURL("img/png");
      const pdf = new jsPDF("p", "mm", "a4");
      pdf.addImage(imgData, "PNG", 0, 0, imgWidth, imgHeight);
      pdf.save("digitalCard.pdf");
    });
  };

  const changeMode = () => {
    setIsCompletedMode(!isCompletedMode);
  };
  return {
    state: {
      isCompletedMode,
    },
    actions: {
      changeMode,
      exportPdf,
    },
  };
};

export default useDigitalCard;
