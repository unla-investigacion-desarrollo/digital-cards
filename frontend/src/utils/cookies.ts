"use server";
import { cookies } from "next/headers";

async function cookiesLogin(token: string, role: string) {
  cookies().set("token", token);
  cookies().set("role", role);
  return "";
}

async function getCookieUserToken() {
  const token = cookies().get("token")?.value;
  console.log(token);
  return token;
}

function getCookieUserRole() {
  const role = cookies().get("role")?.value;
  console.log(role);
  return role;
}

function deleteCookies() {
  cookies().delete("role");
  cookies().delete("token");
}

export { cookiesLogin, getCookieUserToken, getCookieUserRole, deleteCookies };
