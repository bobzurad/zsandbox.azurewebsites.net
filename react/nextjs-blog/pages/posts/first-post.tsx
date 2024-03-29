import Head from "next/head";
import Script from "next/script";
import Link from "next/link";
import Layout from "../../components/layout";

// THIS FILE IS NO LONGER USED, AND JUST HERE FOR REFERENCE

export default function FirstPost() {
  return (
    <Layout>
      <Head>
        <title>First Post</title>
      </Head>
      {/* example how to load a 3rd party script */}
      <Script
        src="https://connect.facebook.net/en_US/sdk.js"
        strategy="lazyOnload"
        onLoad={() =>
          console.log(`script loaded correctly, window.FB has been populated`)
        }
      />
      <h1>First Post!</h1>
      <h2>
        <Link href="/">Back to Home</Link>
      </h2>
    </Layout>
  );
}
