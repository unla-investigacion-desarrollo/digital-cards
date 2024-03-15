import React from "react";
import { Tooltip } from "@nextui-org/react";
import { MdEdit, MdDelete } from "react-icons/md";

const Actions = () => {
  return (
    <div className="relative flex items-center gap-2">
      <Tooltip content="Edit profileItemTable">
        <span className="text-lg text-default-400 cursor-pointer active:opacity-50">
          <MdEdit />
        </span>
      </Tooltip>
      <Tooltip color="danger" content="Delete profileItemTable">
        <span className="text-lg text-danger cursor-pointer active:opacity-50">
          <MdDelete />
        </span>
      </Tooltip>
    </div>
  );
};

export default Actions;
