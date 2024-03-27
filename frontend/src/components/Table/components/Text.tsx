import React from "react";

const Text = ({ text }: { text: string }) => {
  return (
    <div className="flex flex-col">
      <p className="text-bold text-sm capitalize">{text}</p>
    </div>
  );
};

export default Text;
