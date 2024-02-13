import React from "react";
import { getCookieUserRole } from "../login/cookies";
import AdminPage from "./pages/AdminPage";
import UserPage from "./pages/UserPage";

const page = () => {
  return getCookieUserRole() == "ADMIN" ? <AdminPage /> : <UserPage />;
};

export default page;
