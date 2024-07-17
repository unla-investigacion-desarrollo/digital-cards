import React from "react";
import { Select, SelectItem } from "@nextui-org/react";

type OptionSelect = {
  id: string;
  name: string;
};

type props = {
  label: string;
  options: OptionSelect[];
  onChangeValue: any;
};

const SelectCustom = ({ label, options, onChangeValue }: props) => {
  return (
    <div className="flex w-full flex-wrap md:flex-nowrap gap-4">
      <Select
        label={`Select an ${label}`}
        className="max-w-xs"
        onChange={onChangeValue}
      >
        {options.map((option) => (
          <SelectItem key={option.id} value={option.name}>
            {option.name}
          </SelectItem>
        ))}
      </Select>
    </div>
  );
};

export default SelectCustom;
