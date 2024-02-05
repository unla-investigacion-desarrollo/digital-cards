"use server";
import { cookies } from "next/headers";

async function cookiesLogin(token: string) {
  cookies().set("token", token);
  return "";
}

async function getCookieUserToken() {
  const token = cookies().get("token")?.value;
  console.log(token);
  return token;
}

export { cookiesLogin, getCookieUserToken };
