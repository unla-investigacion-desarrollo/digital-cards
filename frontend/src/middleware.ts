import { NextRequest, NextResponse } from "next/server";

export async function middleware(request: NextRequest) {
  const token = request.cookies.get("token")?.value;
  const role = request.cookies.get("role")?.value;
  const protectedRoutes = ["/home"];
  const adminRoutes = ["/change-password"];
  const publicRoutes = ["/", "digital-card"];

  if (protectedRoutes.includes(request.nextUrl.pathname) && !token) {
    const response = NextResponse.redirect(new URL("/login", request.url));
    return response;
  }

  if (adminRoutes.includes(request.nextUrl.pathname) && role !== "ADMIN") {
    return NextResponse.redirect(new URL("/home", request.url));
  }
}
