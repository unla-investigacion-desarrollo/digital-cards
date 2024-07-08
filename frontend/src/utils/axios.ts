import axios from "axios";

export const axiosInstance = axios.create({
  baseURL: process.env.NEXT_PUBLIC_SERVER_URL,
  headers: {
    Authorization:
      typeof window !== "undefined"
        ? `Bearer ${localStorage.getItem("token")}`
        : "",
    "Access-Control-Allow-Origin": "*",
    "Content-Type": "application/json",
  },
});
