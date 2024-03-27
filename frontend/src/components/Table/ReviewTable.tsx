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
import useReviewTable from "./hooks/useReviewsTable";

interface Columns {
  name: string;
  uid: string;
}

interface ReviewItemTable {
  reviewId: string;
  ProfileName: string;
  userRequest: string;
  userReviewer: string;
  statusReview: string;
  hasFeeedback: boolean;
}

interface props {
  columns: Columns[];
  reviewItems: ReviewItemTable[];
}

const ProfilesTable = ({ columns, reviewItems }: props) => {
  const { renderCell } = useReviewTable();
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
          <TableBody items={reviewItems}>
            {(item) => (
              <TableRow key={item.reviewId}>
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
