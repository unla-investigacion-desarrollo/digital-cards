import type { AppProps } from "next/app";
import { NextUIProvider } from "@nextui-org/react";
import "globals.css";

export default function MyApp({ Component, pageProps }: AppProps) {
  return (
    <NextUIProvider>
      <main className="dark text-foreground bg-background">
        <Component {...pageProps} />;
      </main>
    </NextUIProvider>
  );
}
