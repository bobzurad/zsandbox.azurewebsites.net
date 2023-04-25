import type { AppProps } from "next/app";
import { CategoryProvider } from "../context/category";
import "@/styles/globals.css";

export default function App({ Component, pageProps }: AppProps) {
  return (
    <CategoryProvider>
      <Component {...pageProps} />
    </CategoryProvider>
  );
}
