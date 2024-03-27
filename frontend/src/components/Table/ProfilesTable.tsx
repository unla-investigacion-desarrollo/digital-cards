"use client";
import React from "react";
import {
  Table,
  TableHeader,
  TableColumn,
  TableBody,
  TableRow,
  TableCell,
} from "@nextui-org/react";
import Header from "@/components/Header";
import useProfilesTable from "./hooks/useProfilesTable";

interface Columns {
  name: string;
  uid: string;
}

interface ProfileItemTable {
  profileId: string;
  profileName: string;
  userReview: string;
  review: string;
  status: string;
  isLive: boolean;
}

interface props {
  columns: Columns[];
  profileItems: ProfileItemTable[];
}

const ProfilesTable = ({ columns, profileItems }: props) => {
  const { renderCell } = useProfilesTable();
  return (
    <>
      <Header />
      <div className="pl-[6%] pr-[6%] pt-[2%] pb-[2%]">
        <Table aria-label="Example table with custom cells">
          <TableHeader columns={columns}>
            {(column) => (
              <TableColumn
                key={column.uid}
                align={column.uid === "actions" ? "center" : "start"}
              >
                {column.name}
              </TableColumn>
            )}
          </TableHeader>
          <TableBody items={profileItems}>
            {(item) => (
              <TableRow key={item.profileId}>
                {(columnKey) => (
                  <TableCell>{renderCell(item, columnKey)}</TableCell>
                )}
              </TableRow>
            )}
          </TableBody>
        </Table>
      </div>
    </>
  );
};

export default ProfilesTable;
